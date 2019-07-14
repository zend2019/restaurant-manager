package main.java.BL.Contract.Logic;
import main.java.database.UserRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LoginTest {

    static Login junit;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        junit = new Login();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Test
    public void logInSystem() throws Exception {
            //LogIn junit = new LogIn();
            boolean result = UserRepository.LogIn("admin","1234");
            assertEquals(true, result);
            /*Assert*/
            assertFalse("Invalid email ID passed ", result);
        }
    }
