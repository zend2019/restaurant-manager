package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Provider;
import main.java.dataAccess.IRestaurantRepository;

import java.util.List;

public class ProductManager implements IProductManager {
    private IRestaurantRepository resturantRepository;



    @Override
    public List<Product> GetProductByProvider(Integer providerId) {
        return resturantRepository.GetProductByProvider(providerId);
    }
}
