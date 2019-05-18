package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;

public interface IUSerManager {

    void AddUser(User user);

    void UpdateUser (User user, int userId);

    void DeleteUser (int userId);
}
