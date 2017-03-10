package br.com.anymarket.sdk.product;

import br.com.anymarket.sdk.MarketPlace;
import br.com.anymarket.sdk.SDKConstants;
import br.com.anymarket.sdk.exception.NotFoundException;
import br.com.anymarket.sdk.http.HttpService;
import br.com.anymarket.sdk.http.Response;
import br.com.anymarket.sdk.http.headers.IntegrationHeader;
import br.com.anymarket.sdk.paging.Page;
import br.com.anymarket.sdk.product.dto.Image;
import br.com.anymarket.sdk.product.dto.Product;
import br.com.anymarket.sdk.product.dto.ProductComplete;
import br.com.anymarket.sdk.util.SDKUrlEncoder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

public class ProductService extends HttpService {

    private static final String PRODUCTS_URI = "/products";
    private final String apiEndPoint;

    public ProductService(String apiEndPoint) {
        this.apiEndPoint = !isNullOrEmpty(apiEndPoint) ? apiEndPoint :
            SDKConstants.ANYMARKET_HOMOLOG_API_ENDPOINT;
    }

    public Product insertProduct(Product product, IntegrationHeader... headers) {
        RequestBodyEntity post = post(apiEndPoint.concat(PRODUCTS_URI), product, headers);
        Response response = execute(post);
        return response.to(Product.class);
    }

    public Product updateProduct(Product product, IntegrationHeader... headers) {
        RequestBodyEntity put = put(apiEndPoint.concat(PRODUCTS_URI).concat("/")
            .concat(product.getId().toString()), product, headers);
        Response response = execute(put);
        return response.to(Product.class);
    }

    public Product updateProductAndImages(Product product, IntegrationHeader... headers) {
        RequestBodyEntity put = put(apiEndPoint.concat(PRODUCTS_URI).concat("/")
            .concat(product.getId().toString()), product, headers);
        Response response = execute(put);
        if (response.getStatus() == HttpStatus.SC_OK) {
            if (product.getImages() != null) {
                for (Image image : product.getImages()) {
                    if (image.getId() == null) {
                        RequestBodyEntity post = post(apiEndPoint.concat(PRODUCTS_URI).concat("/")
                            .concat(product.getId().toString()).concat("/images/"), image, headers);
                        execute(post);
                    }
                }
            }

            if (product.getImagesForDelete() != null) {
                for (Image image : product.getImagesForDelete()) {
                    if (image.getId() != null) {
                        HttpRequestWithBody delete = delete(apiEndPoint.concat(PRODUCTS_URI).concat("/")
                            .concat(product.getId().toString()).concat("/images/").concat(image.getId().toString()), headers);
                        execute(delete);
                    }
                }
            }
        }
        return getProduct(product.getId(), headers);
    }

    public Product getProduct(Long id, IntegrationHeader... headers) {
        return getProduct(id, Product.class, headers);
    }

    public <T> T getProduct(Long id, Class<T> clazz, IntegrationHeader... headers) {
        GetRequest getRequest = get(apiEndPoint.concat(PRODUCTS_URI).concat("/").concat(id.toString()), headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            return response.to(clazz);
        }
        throw new NotFoundException(format("Product with id %s not found.", id));
    }

    public Product getProductBySku(final String sku, IntegrationHeader... headers) {
        final List<Product> products = getAllProducts(getUrlForProductsWithSku(sku), headers);
        if (!products.isEmpty()) {
            return products.get(0);
        }
        throw new NotFoundException(format("Product with partnerId %s not found.", sku));
    }

    public ProductComplete getProductCompleteBySku(final String sku, IntegrationHeader... headers) {
        final List<ProductComplete> products = getAllCompleteProducts(getUrlForProductsWithSku(sku), headers);
        if (!products.isEmpty()) {
            return products.get(0);
        }
        throw new NotFoundException(format("Product with partnerId %s not found.", sku));
    }

    private String getUrlForProductsWithSku(String sku) {
        return apiEndPoint.concat(PRODUCTS_URI).concat("?sku=")
            .concat(SDKUrlEncoder.encodeParameterToUTF8(sku));
    }

    public List<Product> getAllProducts(final String url, IntegrationHeader... headers) {
        final List<Product> allProducts = new ArrayList<Product>();
        final GetRequest getRequest = get(url, headers);
        final Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            Page<Product> rootResponse = response.to(new TypeReference<Page<Product>>() {
            });
            allProducts.addAll(rootResponse.getContent());
        } else {
            throw new NotFoundException("Products not found.");
        }
        return allProducts;
    }

    public List<ProductComplete> getAllCompleteProducts(final String url, IntegrationHeader... headers) {
        final List<ProductComplete> allProducts = new ArrayList<ProductComplete>();
        final GetRequest getRequest = get(url, headers);
        final Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            Page<ProductComplete> rootResponse = response.to(new TypeReference<Page<ProductComplete>>() {
            });
            allProducts.addAll(rootResponse.getContent());
        } else {
            throw new NotFoundException("Products not found.");
        }
        return allProducts;
    }

    public Map<String, String> getActiveAttributesByMarketPlace(Long idProduct, MarketPlace marketPlace, IntegrationHeader... headers){
        String url = apiEndPoint.concat(PRODUCTS_URI).concat("/").concat(idProduct.toString()).concat("/attributes/").concat(marketPlace.name());
        GetRequest getRequest = get(url, headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            return response.to(HashMap.class);
        }
        throw new NotFoundException(format("Product(id: %is) active attributes not found to this marketplace(%s).", idProduct, marketPlace.name()));
    }

}
