package br.com.anymarket.sdk.order.dto;

import static java.lang.String.format;

public enum NewOrderStatus {

    PENDING("Pendente", OrderStatus.PENDING),
    DELIVERY_ISSUE("Problema no Envio", OrderStatus.DELIVERY_ISSUE),
    PAID("Pago", OrderStatus.PAID_WAITING_SHIP),
    INVOICED("Faturado", OrderStatus.INVOICED),
    SHIPPED("Enviado", OrderStatus.PAID_WAITING_DELIVERY),
    DELIVERED("Entregue", OrderStatus.CONCLUDED),
    CANCELED("Cancelado", OrderStatus.CANCELED);
    private static final String INVALID_STATUS = "%s não é um status de pedido válido.";
    private final String description;
    private OrderStatus oldValue;

    NewOrderStatus(String description, OrderStatus oldValue) {
        this.description = description;
        this.oldValue = oldValue;
    }

    public static NewOrderStatus fromOldValue(OrderStatus oldValue) {
        for (NewOrderStatus status : NewOrderStatus.values()) {
            if (status.getOldValue().equals(oldValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException(format(INVALID_STATUS, oldValue.toString()));
    }

    public String getDescription() {
        return description;
    }

    public OrderStatus getOldValue() {
        return oldValue;
    }
}
