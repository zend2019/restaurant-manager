package main.java.BL.Contract;

import java.util.Date;

public class Product {

    private String productName;
    private String price;
    private Date expirationDate;
    private Integer productId;
    private Integer currentProductAmount;
    private Integer requiredAmount;
    private String provider;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCurrentProductAmount() {
        return currentProductAmount;
    }

    public void setCurrentProductAmount(Integer currentProductAmount) {
        this.currentProductAmount = currentProductAmount;
    }

    public Integer getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


}
