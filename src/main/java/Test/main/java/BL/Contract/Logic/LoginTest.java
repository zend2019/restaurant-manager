package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.User;
import main.java.database.UserRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

// Unit testing for validation

public class LoginTest {

    static Login login;
    private static String userName = "admin";
    private static String password = "1234";
    private static String userNoExist = "alon";
    private static Employee employee;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login = new Login();
        employee= new Employee();
        employee.setUserName(userName);
        employee.setPassword(password);
        employee.setDateOfBirth(new Date());
        employee.setHireDate(new Date());
        UserRepository.addUser(employee,true);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        UserRepository.deleteUserByUderName(userName);
    }


    @Test
    public void login_existUserName_success() throws Exception {

        User result = login.LogInSystem(userName,password);
        assertEquals(userName, result.getUserName());
    }

    @Test
    public void login_noExistUserName_failedLogin() throws Exception {

        User result = login.LogInSystem(userNoExist,password);
        assertEquals(null, result.getUserName());
    }
}
