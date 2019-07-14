package main.java.BL.Contract.Logic;

import main.java.database.UserRepository;

public class Login implements ILogin {
    public Login() {

    }

    public Login(String admin, String s) {

    }

    @Override
    public boolean LogInSystem(String userName, String password) {
        //password = Integer.toString(password.hashCode());
        UserRepository.getUserById(1);
        return UserRepository.LogIn(userName, password);
    }
}
