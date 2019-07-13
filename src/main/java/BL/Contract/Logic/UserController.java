package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.UserRepository;

public class UserController implements IUSerManager {
    private IRestaurantRepository restaurantRepository;

    public UserController(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public void AddUser(User user) {
        User existingUser = UserRepository.getUserById(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        int pass = user.getPassword().hashCode();
        user.setPassword(Integer.toString(pass));
        UserRepository.addUser(user);
    }

    @Override
    public void UpdateUser(User user, int userId) {
        User existingUser = UserRepository.getUserById(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        UserRepository.editUser(user, userId);
    }

    @Override
    public void DeleteUser(int userId) {
        User existingUser = UserRepository.getUserById(userId);
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        UserRepository.deleteUser(userId);
    }
}
