package br.com.anymarket.sdk.callback;

import br.com.anymarket.sdk.SDKConstants;
import br.com.anymarket.sdk.callback.dto.Callback;
import br.com.anymarket.sdk.exception.NotFoundException;
import br.com.anymarket.sdk.http.HttpService;
import br.com.anymarket.sdk.http.Response;
import br.com.anymarket.sdk.http.headers.IntegrationHeader;
import br.com.anymarket.sdk.paging.Page;
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
 * Created by gyowannyqueiroz on 7/19/16.
 */
public class CallbackService extends HttpService {

    public static final String CALLBACKS_URI = "/callbacks/";
    private String apiEndPoint;

    public CallbackService(String apiEndPoint) {
        this.apiEndPoint = !isNullOrEmpty(apiEndPoint) ? apiEndPoint :
                SDKConstants.ANYMARKET_HOMOLOG_API_ENDPOINT;
    }

    public List<Callback> getAllCallbacks(IntegrationHeader... headers) {
        List<Callback> callbackList = new ArrayList<Callback>();
        GetRequest getRequest = get(apiEndPoint.concat(CALLBACKS_URI), headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            Page<Callback> rootResponse = response.to(new TypeReference<Page<Callback>>() {
            });
            callbackList.addAll(rootResponse.getContent());
        } else {
            throw new NotFoundException("Callbacks not found.");
        }
        return callbackList;
    }

    public Callback insertCallback(Callback callback, IntegrationHeader... headers) {
        RequestBodyEntity post = post(apiEndPoint.concat(CALLBACKS_URI), callback, headers);
        Response response = execute(post);
        return response.to(Callback.class);
    }

    public void deleteCallback(Long id, IntegrationHeader... headers) {
        HttpRequestWithBody deleteRequest = delete(apiEndPoint.concat(CALLBACKS_URI).concat(id.toString()), headers);
        Response response = execute(deleteRequest);
        if (response.getStatus() != HttpStatus.SC_NO_CONTENT) {
            throw new NotFoundException(format("Callback with id %s not found.", id));
        }
    }

    public Callback getCallback(Long id, IntegrationHeader... headers) {
        GetRequest getRequest = get(apiEndPoint.concat(CALLBACKS_URI).concat(id.toString()), headers);
        Response response = execute(getRequest);
        if (response.getStatus() == HttpStatus.SC_OK) {
            return response.to(Callback.class);
        }
        throw new NotFoundException(format("Callback with id %s not found.", id));
    }

    public Callback updateCallback(Callback callback, IntegrationHeader... headers) {
        if (callback.getId() == null) {
            throw new NotFoundException("Callback id can't be null");
        }
        RequestBodyEntity putRequest = put(apiEndPoint.concat(CALLBACKS_URI).concat(callback.getId().toString()), callback, headers);
        Response response = execute(putRequest);
        return response.to(Callback.class);
    }
}
