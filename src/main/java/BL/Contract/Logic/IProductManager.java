package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Provider;

import java.util.List;
import java.util.Vector;

public interface IProductManager {

    List<Product> GetProductByProvider(String providerId);
    Vector<Product> getListOfAllProducts();
}
