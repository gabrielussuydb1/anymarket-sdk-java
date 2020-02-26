package br.com.anymarket.sdk.variation;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

public class VariationType {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("partnerId")
    private String partnerId;
    @JsonPropertyOrder("visualVariation")
    private Boolean visualVariation = false;
    @JsonProperty("values")
    private List<Variation> values = new ArrayList<Variation>();

    public Long getId() {
        return id;
    }

    public VariationType() {}

    public VariationType(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Boolean getVisualVariation() {
        return visualVariation;
    }

    public void setVisualVariation(Boolean visualVariation) {
        this.visualVariation = visualVariation;
    }

    public List<Variation> getValues() {
        return values;
    }

    public void setValues(List<Variation> values) {
        this.values = values;
    }

    public void addValueToList(Variation variation) {
        if (variation != null && !this.values.contains(variation)) {
            this.values.add(variation);
        }
    }
}
