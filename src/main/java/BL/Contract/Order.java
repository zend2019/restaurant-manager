package main.java.BL.Contract;

import java.util.*;

public class Order {
    private int orderId;


    private List<HashMap> orderedProducts = new ArrayList<>();
    private Double totalAmount;
    private int rating;
    private Date OrderDate;
    private Date deliveryDate;
    private OrderStatus orderStatus;
    private HashMap<String,Integer> productIds;


    public List<HashMap> getOrderedProducts(){
        return  orderedProducts;
    }

    public void setOrderedProducts(Vector<Vector> data, int iProductId, int iOrderedUnits){
        for (int i = 0; i < data.size(); i++) {
            HashMap map = new HashMap();
            map.put(data.get(i).get(iProductId),data.get(i).get(iOrderedUnits));
            orderedProducts.add(map);
        }
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getOrderId() {
        return orderId;
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


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }
    public HashMap<String, Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(HashMap<String, Integer> productIds) {
        this.productIds = productIds;
    }

}
