package main.java.BL.Contract;

import java.util.List;

public class Provider extends User {

    private String companyName;
    private List<Integer> productType;
    private String contactName;
    private String providerId;
    private Integer rating;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Integer> getProductType() {
        return productType;
    }

    public void setProductType(List<Integer> productType) {
        this.productType = productType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


}
