package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Provider;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

import java.util.List;

public class ProviderManaging implements IProviderManaging {

    private IRestaurantRepository resturantRepository;


    @Override
    public void addProvider(Provider provider) {
        DatabaseController.addProvider(provider);
    }

    @Override
    public void deleteProvider(int providerId) {
        DatabaseController.deleteProvider(providerId);
    }

    @Override
    public void editProvider(Provider provider, int providerId) {
        DatabaseController.deditProvider(provider,providerId);
    }

    @Override
    public void getRatingProvider(int providerId) {

    }

    @Override
    public List<Provider> GetProviderByCategory(Category category) {
        return DatabaseController.getProviderByCategory(category);
    }


}
