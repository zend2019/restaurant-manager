package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Product;
import main.java.database.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ProductController implements IProductManager {

    @Override
    public List<Product> GetProductByProvider(String providerId) {
        return ProductRepository.getProductByProvider(providerId);
    }

    @Override
    public Vector<Product> getListOfAllProducts() {
        return ProductRepository.getListOfAllProducts();
    }

    @Override
    public Product getProductByProductId(String productId) {
        return ProductRepository.getProductByProductId(productId);
    }

    @Override
    public Vector<Product> getListOfProducts(HashMap hashMap) {
        return ProductRepository.getListOfProducts(hashMap);
    }

    @Override
    public String addProduct(Product product) {
        if (product.getRequiredAmount() == 0) {
            return "-1";
        }

        return ProductRepository.addProduct(product);
    }

}
