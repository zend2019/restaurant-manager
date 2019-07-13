package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

public class UserManager implements IUSerManager {
    private IRestaurantRepository resturantRepository;

    public UserManager(IRestaurantRepository resturantRepository) {
        this.resturantRepository = resturantRepository;
    }


    @Override
    public void AddUser(User user) {
        User existingUser = DatabaseController.getUserById(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        int pass = user.getPassword().hashCode();
        user.setPassword(Integer.toString(pass));
        DatabaseController.addUser(user);
    }

    @Override
    public void UpdateUser(User user, int userId) {
        User existingUser = DatabaseController.getUserById(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        DatabaseController.editUser(user, userId);
    }

    @Override
    public void DeleteUser(int userId) {
        User existingUser = DatabaseController.getUserById(userId);
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        DatabaseController.deleteUser(userId);
    }
}
