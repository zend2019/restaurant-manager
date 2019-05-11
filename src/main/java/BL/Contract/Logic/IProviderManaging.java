package main.java.BL.Contract.Logic;
import main.java.BL.Contract.Provider;

public interface IProviderManaging {
    void addProvider(Provider provider);
    void deleteProvider(Provider providerId);
    void editProvider(Provider provider);

}
