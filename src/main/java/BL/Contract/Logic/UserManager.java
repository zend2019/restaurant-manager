package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;

public class UserManager implements IUSerManager {
    private IRestaurantRepository resturantRepository;

    public UserManager(IRestaurantRepository resturantRepository) {
        this.resturantRepository = resturantRepository;
    }


    @Override
    public void AddUser(User user) {
        User existingUser = resturantRepository.GetUser(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.AddUser(user);
    }

    @Override
    public void UpdateUser(User user, int userId) {
        User existingUser = resturantRepository.GetUser(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.EditUser(user,userId);
    }

    @Override
    public void DeleteUser(int userId) {
        User existingUser = resturantRepository.GetUser(userId);
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.DeleteUser(userId);
    }
}
