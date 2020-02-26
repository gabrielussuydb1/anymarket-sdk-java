package br.com.anymarket.sdk.variation;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Variation {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String value;

    @JsonProperty("type")
    private VariationType type;

    @JsonProperty("partnerId")
    private String partnerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VariationType getType() {
        return type;
    }

    public void setType(VariationType type) {
        this.type = type;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variation variation = (Variation) o;
        return Objects.equals(value, variation.value)
                && Objects.equals(type, variation.type)
                && Objects.equals(partnerId, variation.partnerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type, partnerId);
    }


}
