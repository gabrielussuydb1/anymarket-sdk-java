package br.com.anymarket.sdk.exception;

import java.util.Map;

/**
 * Created by marcio.scharam on 18/03/2016.
 */
public class HttpClientException extends AnymarketSDKException {

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, String details) {
        super(message, details);
    }

    public HttpClientException(String message, String details, Map<String, Object> resources) {
        super(message, details, resources);
    }
}
