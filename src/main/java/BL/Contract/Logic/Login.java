package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.database.UserRepository;

public class Login implements ILogin {
    public Login() {

    }

    public Login(String admin, String s) {

    }

    @Override
    public User LogInSystem(String userName, String password) {
        return UserRepository.LogIn(userName, password);
    }
}
