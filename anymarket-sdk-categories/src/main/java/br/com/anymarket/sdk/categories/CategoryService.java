package br.com.anymarket.sdk.categories;

import br.com.anymarket.sdk.SDKConstants;
import br.com.anymarket.sdk.categories.dto.Category;
import br.com.anymarket.sdk.exception.NotFoundException;
import br.com.anymarket.sdk.http.HttpService;
import br.com.anymarket.sdk.http.Response;
import br.com.anymarket.sdk.http.headers.IntegrationHeader;
import br.com.anymarket.sdk.paging.Page;
import br.com.anymarket.sdk.resource.Link;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

/**
 * Created by marcio.scharam on 17/03/2016.
 */
public class CategoryService extends HttpService {

    private String apiEndPoint;

    public CategoryService(String apiEndPoint) {
        if (isNullOrEmpty(apiEndPoint)) {
            apiEndPoint = SDKConstants.ANYMARKET_HOMOLOG_API_ENDPOINT;
        }
        this.apiEndPoint = apiEndPoint;
    }

    public Category updateCategory(Category category, IntegrationHeader... headers) {
        RequestBodyEntity putRequest = put(apiEndPoint + category.getPathURI() + "/" + category.getId(), category, headers);
        Response response = execute(putRequest);
        return response.to(Category.class);
    }

    public Category insertCategory(Category category, IntegrationHeader... headers) {
        RequestBodyEntity postRequest = post(apiEndPoint + category.getPathURI(), category, headers);
        Response response = execute(postRequest);
        return response.to(Category.class);
    }

    public void deleteCategory(Category category, IntegrationHeader... headers) {
        deleteCategory(category.getId(), headers);
    }

    public void deleteCategory(Long id, IntegrationHeader... headers) {
        HttpRequestWithBody deleteRequest = delete(apiEndPoint + "/categories/" + id, headers);
        Response response = execute(deleteRequest);
        if (response.getStatus() != HttpStatus.SC_NO_CONTENT) {
            throw new NotFoundException(format("Category with id %s not found.", id));
        }
    }


    public Category getCategory(Long id, IntegrationHeader... headers) throws NotFoundException {
        GetRequest getRequest = get(apiEndPoint + "/categories/" + id, headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            return response.to(Category.class);
        }
        throw new NotFoundException(format("Category with id %s not found.", id));
    }

    public List<Category> getAllCategories(IntegrationHeader... headers) throws NotFoundException {
        boolean hasMoreElements;
        ArrayList<Category> allCategories = new ArrayList<Category>();

        String urlToGet = apiEndPoint + "/categories/";

        do {
            GetRequest getRequest = get(urlToGet, headers);
            Response response = execute(getRequest);
            if (response.getStatus() == HttpStatus.SC_OK) {
                Page<Category> rootResponse = response.to(new TypeReference<Page<Category>>(){});
                for (Category root : rootResponse.getContent()) {
                    List<Category> completeRootHierarchy = getCompleteCategory(root, headers);
                    allCategories.addAll(completeRootHierarchy);
                }
                for (Link link : rootResponse.getLinks()) {
                    if (link.getRel().equals("next")) {
                        urlToGet = link.getHref();
                        break;
                    }
                }
                hasMoreElements = !rootResponse.getContent().isEmpty();
            } else {
                throw new NotFoundException("Categories not found.");
            }

        }
        while (hasMoreElements);

        return allCategories;
    }

    private List<Category> getCompleteCategory(Category category, IntegrationHeader... headers) {
        List<Category> completeCategories = new ArrayList<Category>();
        Category completeCategory = getCategory(category.getId(), headers);
        completeCategories.add(completeCategory);
        completeCategories.addAll(loadCompleteChildren(completeCategory, headers));
        return completeCategories;
    }

    private List<Category> loadCompleteChildren(Category parent, IntegrationHeader... headers) {
        List<Category> completeChildren = new ArrayList<Category>();
        for (Category child : parent.getChildren()) {
            completeChildren.addAll(getCompleteCategory(child, headers));
        }
        return completeChildren;
    }

}
