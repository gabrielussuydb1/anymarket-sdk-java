package br.com.anymarket.sdk.exception;

import java.util.Map;

/**
 * Created by marcio.scharam on 18/03/2016.
 */
public abstract class AnymarketSDKException extends RuntimeException {
    private String details;
    private Map<String, Object> resources;

    public AnymarketSDKException(String message) {
        super(message);
    }

    public AnymarketSDKException(String message, String details) {
        super(message);
        this.details = details;
    }

    public AnymarketSDKException(String message, String details, Map<String, Object> resources) {
        super(message);
        this.details = details;
        this.resources = resources;
    }

    public String getDetails() {
        return details;
    }

    public Map<String, Object> getResources() {
        return resources;
    }
}
