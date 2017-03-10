package br.com.anymarket.sdk.product;

import br.com.anymarket.sdk.MarketPlace;
import br.com.anymarket.sdk.SDKConstants;
import br.com.anymarket.sdk.exception.NotFoundException;
import br.com.anymarket.sdk.http.HttpService;
import br.com.anymarket.sdk.http.Response;
import br.com.anymarket.sdk.http.headers.IntegrationHeader;
import br.com.anymarket.sdk.product.dto.SkuMarketPlace;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.apache.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

public class SkuMarketPlaceService extends HttpService {

    private static final String SKUMP_URI = "/skus/%s/marketplaces";
    private final String apiEndPoint;

    public SkuMarketPlaceService(final String apiEndPoint) {
        this.apiEndPoint = !isNullOrEmpty(apiEndPoint) ? apiEndPoint :
            SDKConstants.ANYMARKET_HOMOLOG_API_ENDPOINT;
    }

    public SkuMarketPlace insert(final SkuMarketPlace skuMp, Long idSku, IntegrationHeader... headers) {
        Objects.requireNonNull(skuMp, "Informe o SkuMarketPlace a ser persistido.");
        Objects.requireNonNull(idSku, "Informe o id do SKU");
        RequestBodyEntity post = post(getURLFormated(idSku), skuMp, headers);
        Response response = execute(post);
        return response.to(SkuMarketPlace.class);
    }

    public SkuMarketPlace update(final SkuMarketPlace skuMp, Long idSku, Long idSkuMP, IntegrationHeader... headers) {
        return update(skuMp, idSku, idSkuMP, true, headers);
    }

    public SkuMarketPlace update(final SkuMarketPlace skuMp, Long idSku, Long idSkuMP, boolean syncChanges, IntegrationHeader... headers) {
        Objects.requireNonNull(skuMp, "Informe o SkuMarketPlace a ser atualizado.");
        Objects.requireNonNull(idSkuMP, "Informe o id do SkuMarketplace a ser atualizado.");
        Objects.requireNonNull(idSku, "Informe o id do SKU");

        if (skuMp.getPrice() != null && skuMp.getDiscountPrice() != null) {
            skuMp.getFields().put("HAS_DISCOUNT",
                Boolean.toString(skuMp.getPrice().compareTo(skuMp.getDiscountPrice()) != 0));
        }

        String url = getURLFormated(idSku).concat("/").concat(idSkuMP.toString()).concat("?syncChanges="+syncChanges);
        RequestBodyEntity put = put(url, skuMp, headers);
        Response response = execute(put);
        return response.to(SkuMarketPlace.class);
    }

    public static void main(String []args) {
        System.out.println(new BigDecimal(120).compareTo(null));
    }

    public SkuMarketPlace update(final SkuMarketPlace skuMp, Long idSku, IntegrationHeader... headers) {
        return update(skuMp, idSku, skuMp.getId(), headers);
    }

    private String getURLFormated(final Long idSku) {
        return String.format(apiEndPoint.concat(SKUMP_URI), idSku.toString());
    }

    public SkuMarketPlace save(final SkuMarketPlace skuMp, Long idSku, IntegrationHeader... headers) {
        if (skuMp.getId() != null) {
            return update(skuMp, idSku, headers);
        } else {
            return insert(skuMp, idSku, headers);
        }
    }

    public SkuMarketPlace getSkuMarketPlace(Long idSku, Long idSkuMp, IntegrationHeader... headers) {
        Objects.requireNonNull(idSkuMp, "Informe o id do SkuMarketPlace.");
        Objects.requireNonNull(idSku, "Informe o id do SKU");
        GetRequest getRequest = get(getURLFormated(idSku).concat("/").concat(idSkuMp.toString()), headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            return response.to(SkuMarketPlace.class);
        }
        throw new NotFoundException(format("Sku with id %s not found.", idSkuMp));
    }

    public List<SkuMarketPlace> getAllSkuMps(Long idSku, IntegrationHeader... headers) {
        return getAllSkuMps(idSku, null, headers);
    }
    public List<SkuMarketPlace> getAllSkuMps(Long idSku, MarketPlace marketPlace, IntegrationHeader... headers) {
        final List<SkuMarketPlace> allSkuMps = Lists.newArrayList();
        String urlFormated = getURLFormated(idSku);
        final GetRequest getRequest = get(marketPlace == null ? urlFormated : urlFormated.concat("?marketplace=").concat(marketPlace.name()), headers);
        final Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            List<SkuMarketPlace> rootResponse = response.to(new TypeReference<List<SkuMarketPlace>>() {
            });
            allSkuMps.addAll(rootResponse);
        } else {
            throw new NotFoundException("SkuMps not found.");
        }
        return allSkuMps;
    }
}
