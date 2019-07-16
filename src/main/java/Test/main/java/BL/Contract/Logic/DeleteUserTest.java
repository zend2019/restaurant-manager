package main.java.BL.Contract.Logic;

import main.java.BL.Contract.User;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.database.AddOrderTest;
import main.java.database.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteUserTest {

    static DeleteUserTest junit;

    @Before
    public void setUp() throws Exception {
        junit = new DeleteUserTest();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deleteUser() {
        //deleteUser(userId); = '2';
          //  User existingUser = UserRepository.getUserById(userId);
          //  if (existingUser != null) {
                throw new RestaurantManagerException("User already exist.");
            }
         //   UserRepository.deleteUser(userId);
        //}
    }