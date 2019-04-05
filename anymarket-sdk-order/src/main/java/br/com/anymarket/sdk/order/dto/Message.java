package br.com.anymarket.sdk.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class Message {

    @JsonProperty("orderInMarketPlace")
    private String orderInMarketPlace;
    @JsonProperty("message")
    private String message;
    @JsonProperty("idAccount")
    private Long idAccount;
    @JsonProperty("idOrder")
    private Long idOrder;

    public Message(String orderInMarketPlace, String message, Long idAccount, Long idOrder) {
        this.orderInMarketPlace = orderInMarketPlace;
        this.message = message;
        this.idAccount = idAccount;
        this.idOrder = idOrder;
    }

    public Message() {
    }

    public String getOrderInMarketPlace() {
        return this.orderInMarketPlace;
    }

    public void setOrderInMarketPlace(String orderInMarketPlace) {
        this.orderInMarketPlace = orderInMarketPlace;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }
}
