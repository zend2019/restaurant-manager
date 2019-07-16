package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.database.UserRepository;

public class UserController implements IUSerManager {


    @Override
    public String AddUser(Employee user, boolean isManager) {
        User existingUser = UserRepository.getUserByUserName(user.getUserName());
        String firstName=existingUser.getFirstName();
        if (firstName != null) {
            return "User already exist.";
        }
        UserRepository.addUser(user,isManager);
        return "";
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
