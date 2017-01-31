package br.com.anymarket.sdk.order.dto;

import static com.google.common.base.Strings.isNullOrEmpty;

public enum OrderType {
    EXCHANGE("Exchange"),
    SALE("Sale");

    private String description;

    OrderType(String description) {
        this.description = description;
    }

    public static OrderType get(String description){
        if (isNullOrEmpty(description)) {
            return SALE;
        }

        for (OrderType orderType : values()) {
            if (orderType.getDescription().equals(description)) {
                return orderType;
            }
        }

        throw new IllegalArgumentException(description + " não é um tipo de pedido válido.");
    }

    public String getDescription() {
        return description;
    }
}

