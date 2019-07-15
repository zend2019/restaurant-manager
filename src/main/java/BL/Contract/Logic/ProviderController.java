package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Provider;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.ProviderRepository;

import java.util.List;

    public class ProviderController implements IProviderManaging {

    private IRestaurantRepository resturantRepository;


    @Override
    public void addProvider(Provider provider) {
        ProviderRepository.addProvider(provider);
    }

    @Override
    public void deleteProvider(int providerId) {
        ProviderRepository.deleteProvider(String.valueOf(providerId));
    }

    @Override
    public void editProvider(Provider provider, int providerId) {
        ProviderRepository.editProvider(provider,providerId);
    }

    @Override
    public void getRatingProvider(int providerId) {

    }

    @Override
    public List<Provider> GetProviderByCategory(Category category) {
        return ProviderRepository.getProviderByCategory(category);
    }

    @Override
    public String getProviderNameById(String providerId) {
        return ProviderRepository.getProviderNameById(providerId);
    }

        @Override
        public String getProviderIdByName(String providerName) {
            return ProviderRepository.getProviderIdByName(providerName);
        }


    }
