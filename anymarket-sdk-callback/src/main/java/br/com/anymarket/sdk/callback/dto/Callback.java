package br.com.anymarket.sdk.callback.dto;

import br.com.anymarket.sdk.AnymarketPojo;
import com.google.common.base.MoreObjects;

/**
 * Created by gyowannyqueiroz on 7/19/16.
 */
public class Callback implements AnymarketPojo {

    private String id;

    private String url;

    public Callback() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getPathURI() {
        return "/callback";
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("url", url)
                .toString();
    }
}
