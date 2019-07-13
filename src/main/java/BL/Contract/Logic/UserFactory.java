package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Manager;
import main.java.BL.Contract.User;

public class UserFactory {

    public static User GetUser(int isManager, User user) {
        switch (isManager) {
            case 1:
                Manager manager = new Manager();
                manager.setFirstName(user.getFirstName());
                manager.setLastName(user.getLastName());
                manager.setAge(user.getAge());
                return manager;

            case 0:
                Employee employee = new Employee();
                employee.setFirstName(user.getFirstName());
                employee.setLastName(user.getLastName());
                employee.setAge(user.getAge());
                return employee;

        }
        return user;
    }
}
