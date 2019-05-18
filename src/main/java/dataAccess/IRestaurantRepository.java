package main.java.dataAccess;

import main.java.BL.Contract.*;

import java.util.List;

public interface IRestaurantRepository {
    void AddUser(User user);

    void EditUser(User user, int userId);

    void DeleteUser(int user);

    User GetUser(int userId);

    int AddOrder(Order order);

    Order GetOrder(int orderId);

    void EditOrder(int orderId, Order order);

    double GetBudget();

    List<Provider> GetProviderByCategory(Category category);
    List<Product> GetProductByProvider(Integer providerId);
}
