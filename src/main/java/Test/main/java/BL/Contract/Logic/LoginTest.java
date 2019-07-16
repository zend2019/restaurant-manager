package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Unit testing for validation

public class LoginTest {

    static Login login;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        login = new Login();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Test
    public void testValidation() throws Exception {

        User result = login.LogInSystem("admin", "1234");
        assertEquals(true, result);
    }

}
