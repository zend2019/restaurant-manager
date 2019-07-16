package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Manager;
import main.java.BL.Contract.User;
import main.java.database.UserRepository;
import org.junit.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Unit testing for validation

public class LoginTest {

    static Login login;
    private static String userName = "admin";
    private static String password = "1234";
    private static String userNoExist = "alon";
    private static String firstName = "dan";
    private static String lastName = "dandan";
    private static boolean needDeleteUser;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login= new Login();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        UserRepository.deleteUserByUderName(userName);
    }

    @Before
    public void testInitalize() {
        needDeleteUser = false;
    }

    @After
    public void cleanUp() {
        if (needDeleteUser) {
            UserRepository.deleteUserByUderName(userName);
        }
    }

    @Test
    public void login_existManagerUserName_success() throws Exception {
        //Arrange
        Employee employee = createEmployee();
        UserRepository.addUser(employee, true);

        //Action
        User result = login.LogInSystem(userName, password);
        needDeleteUser = true;

        //Assert
        assertEquals(userName, result.getUserName());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertTrue(result instanceof Manager);
    }

    @Test
    public void login_existEmployeeUserName_success() throws Exception {
        //Arrange
        Employee employee = createEmployee();
        UserRepository.addUser(employee, false);

        //Action
        User result = login.LogInSystem(userName, password);
        needDeleteUser = true;

        //Assert
        assertEquals(userName, result.getUserName());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertTrue(result instanceof Employee);
    }

    @Test
    public void login_noExistUserName_failedLogin() throws Exception {

        //Action
        User result = login.LogInSystem(userNoExist, password);
        needDeleteUser = false;

        //Assert
        assertEquals(null, result.getUserName());
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setUserName(userName);
        employee.setPassword(password);
        employee.setDateOfBirth(new Date());
        employee.setHireDate(new Date());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }
}
