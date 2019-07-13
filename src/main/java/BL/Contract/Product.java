package main.java.BL.Contract;

import java.util.Date;

public class Product {

    private String productName;
    private String price;
    private Date expirationDate;
    private String productId;
    private Integer currentProductAmount;
    private Integer requiredAmount;
    private String providerId;
    private Category category;


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

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getAmountOfLake() {
        return this.requiredAmount - this.currentProductAmount;
    }


}
