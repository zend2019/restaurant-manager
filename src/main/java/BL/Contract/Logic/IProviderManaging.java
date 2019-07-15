package main.java.BL.Contract.Logic;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Provider;

import java.util.List;

public interface IProviderManaging {
    void addProvider(Provider provider);
    void deleteProvider(int providerId);
    void editProvider(Provider provider,int providerId);
    void getRatingProvider(int providerId);
    List<Provider> GetProviderByCategory(Category category);
    String getProviderNameById(String providerId);
}
