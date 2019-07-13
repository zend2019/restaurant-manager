package main.java.BL.Contract.Logic;

import main.java.database.UserRepository;

public class Login implements ILogin {
    @Override
    public boolean LogInSystem(String userName, String password) {
        password = Integer.toString(password.hashCode());
        return UserRepository.LogIn(userName, password);
    }
}
