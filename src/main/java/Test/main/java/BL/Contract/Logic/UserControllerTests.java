package main.java.Test.main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Logic.UserController;
import main.java.BL.Contract.User;
import main.java.common.constants.GUIConstants;
import main.java.database.RestaurantRepository;
import main.java.database.UserRepository;
import org.junit.*;

import java.util.Date;

public class UserControllerTests {

    private static UserController userController;

    private static String userName = "testtesttest";
    private static String password = "1234";
    private static String userNoExist = "alon";
    private static String firstName = "dan";
    private static String lastName = "dandan";
    private static boolean needDeleteUser;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userController = new UserController();
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
    public void addUser_user_userAdded() {
        //arrange
        Employee employee = createEmployee();

        //action
        String result = userController.AddUser(employee, true);
        needDeleteUser = true;

        //assert
        User actualUser = UserRepository.getUserByUserName(employee.getUserName());
        Assert.assertEquals(actualUser.getFirstName(), firstName);
        Assert.assertEquals(actualUser.getLastName(), lastName);
    }

    @Test
    public void addUser_existingUserName_userNotAdded() {
        //arrange
        Employee employee = createEmployee();
        userController.AddUser(employee, true);
        //action
        String result = userController.AddUser(employee, true);
        needDeleteUser = true;
        //assert
        Assert.assertEquals(result, GUIConstants.USER_EXIST);
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
