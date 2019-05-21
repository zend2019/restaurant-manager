package main.java.BL.Contract;

import java.util.Date;
import java.util.List;

public class Order {
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private OrderStatus orderStatus;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getProvider() {
        return provider;
    }

    public void setProvider(List<String> provider) {
        this.provider = provider;
    }


    private Date deliveryDate;
    private int orderId;
    private List<String> productIds;
    private List<String> provider;
    private Double totalAmount;
    private int rating;


}
