package main.java.dataAccess;

import main.java.BL.Contract.User;

public interface IResturantRepository {
    void AddUser(User user);
    void EditUser(User user, int uderId);
    void DeleteUser (int user);
    User GetUder (int uderId);

}
