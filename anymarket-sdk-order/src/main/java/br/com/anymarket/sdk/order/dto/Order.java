package br.com.anymarket.sdk.order.dto;

import br.com.anymarket.sdk.MarketPlace;
import br.com.anymarket.sdk.serializer.SDKDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("marketPlaceId")
    private String marketPlaceId;

    @JsonProperty("marketPlaceNumber")
    private String marketPlaceNumber;

    @JsonProperty("marketPlace")
    private MarketPlace marketPlace;

    @JsonProperty("partnerId")
    private String partnerId;

    @JsonProperty("createdAt")
    @JsonSerialize(using = SDKDateSerializer.class)
    private Date createdAt;

    @JsonProperty("paymentDate")
    @JsonSerialize(using = SDKDateSerializer.class)
    private Date paymentDate;

    @JsonProperty("transmissionStatus")
    private OrderTransmissionStatus transmissionStatus;

    @JsonProperty("status")
    private OrderStatus status;

    @JsonProperty("marketPlaceUrl")
    private String marketPlaceUrl;

    @JsonProperty("marketPlaceShipmentStatus")
    private String marketPlaceShipmentStatus;

    @JsonProperty("invoice")
    private InvoiceResource invoice;

    @JsonProperty("marketPlaceStatus")
    private String marketPlaceStatus;

    @JsonProperty("marketPlaceStatusComplement")
    private String marketPlaceStatusComplement;

    @JsonProperty("discount")
    private BigDecimal discount;

    @JsonProperty("freight")
    private BigDecimal freight;

    @JsonProperty("gross")
    private BigDecimal gross;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("interestValue")
    private BigDecimal interestValue;

    @JsonProperty("shipping")
    private ShippingResource shipping;

    @JsonProperty("buyer")
    private BuyerResource buyer;

    @JsonProperty("tracking")
    private TrackingResource tracking;

    @JsonProperty("payments")
    private List<PaymentResource> payments = new ArrayList<PaymentResource>();

    @JsonProperty("items")
    private List<OrderItemResource> items = new ArrayList<OrderItemResource>();

    @JsonProperty("deliveryStatus")
    private DeliveryStatus deliveryStatus;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("orderType")
    private OrderType orderType;

    @JsonProperty("originNumber")
    private String originNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketPlaceId() {
        return marketPlaceId;
    }

    public void setMarketPlaceId(String marketPlaceId) {
        this.marketPlaceId = marketPlaceId;
    }

    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderTransmissionStatus getTransmissionStatus() {
        return transmissionStatus;
    }

    public void setTransmissionStatus(OrderTransmissionStatus transmissionStatus) {
        this.transmissionStatus = transmissionStatus;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getMarketPlaceUrl() {
        return marketPlaceUrl;
    }

    public void setMarketPlaceUrl(String marketPlaceUrl) {
        this.marketPlaceUrl = marketPlaceUrl;
    }

    public String getMarketPlaceShipmentStatus() {
        return marketPlaceShipmentStatus;
    }

    public void setMarketPlaceShipmentStatus(String marketPlaceShipmentStatus) {
        this.marketPlaceShipmentStatus = marketPlaceShipmentStatus;
    }

    public InvoiceResource getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceResource invoice) {
        this.invoice = invoice;
    }

    public String getMarketPlaceStatus() {
        return marketPlaceStatus;
    }

    public void setMarketPlaceStatus(String marketPlaceStatus) {
        this.marketPlaceStatus = marketPlaceStatus;
    }

    public String getMarketPlaceStatusComplement() {
        return marketPlaceStatusComplement;
    }

    public void setMarketPlaceStatusComplement(String marketPlaceStatusComplement) {
        this.marketPlaceStatusComplement = marketPlaceStatusComplement;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ShippingResource getShipping() {
        return shipping;
    }

    public void setShipping(ShippingResource shipping) {
        this.shipping = shipping;
    }

    public BuyerResource getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerResource buyer) {
        this.buyer = buyer;
    }

    public TrackingResource getTracking() {
        return tracking;
    }

    public void setTracking(TrackingResource tracking) {
        this.tracking = tracking;
    }

    public List<PaymentResource> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentResource> payments) {
        this.payments = payments;
    }

    public List<OrderItemResource> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResource> items) {
        this.items = items;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getMarketPlaceNumber() {
        return marketPlaceNumber;
    }

    public void setMarketPlaceNumber(String marketPlaceNumber) {
        this.marketPlaceNumber = marketPlaceNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public BigDecimal getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(BigDecimal interestValue) {
        this.interestValue = interestValue;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(String originNumber) {
        this.originNumber = originNumber;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("marketPlaceId", marketPlaceId)
            .add("marketPlaceNumber", marketPlaceNumber)
            .add("marketPlace", marketPlace)
            .add("createdAt", createdAt)
            .add("paymentDate", paymentDate)
            .add("transmissionStatus", transmissionStatus)
            .add("status", status)
            .add("type", orderType)
            .add("originNumber", originNumber)
            .add("marketPlaceUrl", marketPlaceUrl)
            .add("marketPlaceShipmentStatus", marketPlaceShipmentStatus)
            .add("invoice", invoice)
            .add("marketPlaceStatus", marketPlaceStatus)
            .add("marketPlaceStatusComplement", marketPlaceStatusComplement)
            .add("discount", discount)
            .add("freight", freight)
            .add("gross", gross)
            .add("total", total)
            .add("interestValue", interestValue)
            .add("observation", observation)
            .add("tracking", tracking)
            .add("items", items)
            .toString();
    }
}
