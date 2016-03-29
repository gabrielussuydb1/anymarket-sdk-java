package br.com.anymarket.sdk.exception;

/**
 * Created by marcio.scharam on 18/03/2016.
 */
public abstract class AnymarketSDKException extends RuntimeException {

    public AnymarketSDKException(String message) {
        super(message);
    }
}
