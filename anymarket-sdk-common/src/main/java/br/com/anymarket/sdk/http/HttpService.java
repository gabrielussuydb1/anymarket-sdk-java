package br.com.anymarket.sdk.http;

import br.com.anymarket.sdk.exception.HttpClientException;
import br.com.anymarket.sdk.exception.HttpServerException;
import br.com.anymarket.sdk.exception.UnauthorizedException;
import br.com.anymarket.sdk.http.headers.IntegrationHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

import java.io.IOException;

public class HttpService {

    protected RequestBodyEntity put(String url, Object body, IntegrationHeader... headers) {
        HttpRequestWithBody put = Unirest.put(url);
        addHeaders(put, headers);

        return put.body(writeValue(body));
    }

    protected GetRequest get(String url, IntegrationHeader... headers) {
        GetRequest unirest = Unirest.get(url);
        addHeaders(unirest, headers);
        return unirest;
    }

    protected RequestBodyEntity post(String url, Object body, IntegrationHeader... headers) {
        HttpRequestWithBody post = Unirest.post(url);
        addHeaders(post, headers);

        return post.body(writeValue(body));
    }

    protected HttpRequestWithBody delete(String url, IntegrationHeader... headers) {
        HttpRequestWithBody delete = Unirest.delete(url);
        addHeaders(delete, headers);

        return delete;
    }

    public <T> T readValue(String value, TypeReference<T> valueType) {
        try {
            return Mapper.get().readValue(value, valueType);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String writeValue(Object value) {
        try {
            return Mapper.get().writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addHeaders(HttpRequest request, IntegrationHeader... headers) {
        for (IntegrationHeader header : headers) {
            request.header(header.getKey(), header.getValue());
        }
    }

    protected Response execute(BaseRequest request) {
        try {
            HttpResponse<String> response = request.asString();
            checkGenericErrorToThrowGenericException(response);
            return new Response(response.getStatus(), response.getBody());
        } catch (UnirestException e) {
            return null;
        }
    }

    private void checkGenericErrorToThrowGenericException(HttpResponse<String> response) {
        int statusCode = response.getStatus();
        if(statusCode >= 500){
            throw new HttpServerException(response.getBody());
        }
        else if(statusCode == 401){
            throw new UnauthorizedException(response.getBody());
        }
        else if(statusCode >= 400 && statusCode != 404){
            throw new HttpClientException(response.getBody());
        }

    }

}
