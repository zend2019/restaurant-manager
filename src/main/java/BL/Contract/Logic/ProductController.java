package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Product;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

import java.util.List;

public class ProductController implements IProductManager {
    private IRestaurantRepository resturantRepository;



    @Override
    public List<Product> GetProductByProvider(String providerId) {
        return DatabaseController.getProductByProvider(providerId);
    }
}
