package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Product;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

import java.util.List;

public class ProductManager implements IProductManager {
    private IRestaurantRepository resturantRepository;


    @Override
    public List<Product> GetProductByProvider(String providerId) {
        return DatabaseController.getProductByProvider(providerId);
    }

    public void AddProduct(Product product) {
        DatabaseController.addProduct(product);
    }

    public void SetProduct(Product product) {
        DatabaseController.editProduct(product);
    }

    public void DeleteProduct(String productId) {
        DatabaseController.deleteProduct(productId);
    }
}
