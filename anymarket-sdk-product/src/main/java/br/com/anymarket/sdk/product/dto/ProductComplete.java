package br.com.anymarket.sdk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductComplete extends ProductBase {

    @JsonProperty("skus")
    private List<SkuComplete> skus;

    public List<SkuComplete> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuComplete> skus) {
        this.skus = skus;
    }

}
