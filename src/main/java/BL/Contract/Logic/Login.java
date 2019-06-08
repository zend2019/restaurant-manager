package main.java.BL.Contract.Logic;

import main.java.database.DatabaseController;

public class Login implements ILogin {
    @Override
    public boolean LogInSystem(String userName, String password) {
        password = Integer.toString(password.hashCode());
        return DatabaseController.LogIn(userName, password);
    }
}
