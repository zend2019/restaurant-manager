package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IResturantRepository;

public class UserManager implements IUSerManager {
    private IResturantRepository resturantRepository;

    public UserManager(IResturantRepository resturantRepository) {
        this.resturantRepository = resturantRepository;
    }


    @Override
    public void AddUser(User user) {
        User existingUser = resturantRepository.GetUder(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.AddUser(user);
    }

    @Override
    public void UpdateUser(User user, int userId) {
        User existingUser = resturantRepository.GetUder(user.getId());
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.EditUser(user,userId);
    }

    @Override
    public void DeleteUser(int userId) {
        User existingUser = resturantRepository.GetUder(userId);
        if (existingUser != null) {
            throw new RestaurantManagerException("User already exist.");
        }
        resturantRepository.DeleteUser(userId);
    }
}
