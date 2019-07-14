package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.User;

public interface IUSerManager {

    void AddUser(Employee user, boolean isManager);

    void UpdateUser (User user, int userId);

    void DeleteUser (int userId);
}
