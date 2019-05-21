package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Product;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

import java.util.List;

public class ProductManager implements IProductManager {
    private IRestaurantRepository resturantRepository;



    @Override
    public List<Product> GetProductByProvider(Integer providerId) {
        return DatabaseController.getProductByProvider(providerId);
    }
}
