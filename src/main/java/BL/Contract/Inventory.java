package main.java.BL.Contract;

import java.util.List;

public class Inventory {
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    private List<Product> product;

}
