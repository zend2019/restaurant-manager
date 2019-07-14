import main.java.BL.Contract.Logic.Login;
import main.java.BL.Contract.User;
import main.java.database.UserRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/* Function num #5 - Log in access */

public class LogInTest {

    static Login junit;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        junit = new Login();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void testValidation() throws Exception {
        //LogIn junit = new LogIn();
        User result = UserRepository.LogIn("admin", "1234");
        assertEquals(true, result);
        /*Assert*/
        //assertFalse("Invalid email ID passed ", result);
    }

}
