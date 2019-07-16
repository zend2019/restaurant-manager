package main.java.BL.Contract.Logic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Unit testing for validation

public class LoginTest {

    static LoginTest junit;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        junit = new LoginTest();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Test
    public void testValidation() throws Exception {
        LoginTest junit = new LoginTest();
        //boolean result = junit.login("admin","1234");
         //assertEquals(true, result);
    }

    }
