package main.java.database;

import main.java.BL.Contract.Order;
import main.java.common.exceptions.RestaurantManagerException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditOrderTest {

        static EditOrderTest junit;
        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            junit = new EditOrderTest();
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
        }

        @Test
        public void EditOrder(int orderId, Order order) {

            main.java.BL.Contract.Order existingOrder = null;
            if (order == null)
                throw new RestaurantManagerException("User already exist.");
            OrderRepository.editOrder(orderId, order);

        }
    }
}