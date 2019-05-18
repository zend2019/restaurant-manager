package main.java.BL.Contract;

import java.util.List;

public class Provider extends User {

    private String companyName;
    private List<Integer> productType;
    private String contactName;
    private Integer providerId;

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

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}
