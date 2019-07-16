package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.User;

public interface IUSerManager {

    String AddUser(Employee user, boolean isManager);

    void DeleteUser (int userId);
}
