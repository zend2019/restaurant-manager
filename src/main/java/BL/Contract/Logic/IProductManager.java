package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Provider;

import java.util.List;

public interface IProductManager {

    List<Product> GetProductByProvider(Integer providerId);
}
