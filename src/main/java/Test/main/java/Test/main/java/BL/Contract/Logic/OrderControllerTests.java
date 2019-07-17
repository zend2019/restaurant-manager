package main.java.Test.main.java.BL.Contract.Logic;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Logic.Login;
import main.java.BL.Contract.Logic.OrderController;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.database.OrderRepository;
import main.java.database.RestaurantRepository;
import main.java.database.UserRepository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class OrderControllerTests {

    private static OrderController orderController;
    private static Order order;
    private static double missingBudget;
    private static int orderId;
    private static final int RESTURANT_ID = 1;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        orderController = new OrderController();
    }

    @Before
    public void testInitalize() {
        orderId = 0;
    }

    @After
    public void testCleanUp() {
        if (orderId != -1) {
            OrderRepository.deleteOrder(orderId);
            RestaurantRepository.updateBudget(RESTURANT_ID, missingBudget, false);
        }
    }


    @Test
    public void addOrder_currectAmount_sucess() {
        //Arrange
        double budget = RestaurantRepository.getRestaurant().getBudget();
        double amount = budget - 10;
        Order order = CreateOrder(amount);

        //action
        int addedOrderId = orderController.AddOrder(order);
        orderId = addedOrderId;
        missingBudget = budget;

        //assert
        double actualBudget = RestaurantRepository.getRestaurant().getBudget();
        assertNotSame(-1, addedOrderId);
        assertEquals(budget - amount, actualBudget);


    }

    @Test
    public void addOrder_inCurrectAmount_OrderNotAdded() {
        //Arrange
        double budget = RestaurantRepository.getRestaurant().getBudget();
        double amount = budget +10;
        Order order = CreateOrder(amount);

        //action
        int addedOrderId = orderController.AddOrder(order);
        orderId = addedOrderId;
        missingBudget = budget;

        //assert
        double actualBudget = RestaurantRepository.getRestaurant().getBudget();
        assertEquals(-1, addedOrderId);
        assertEquals(budget, actualBudget);
    }

    private Order CreateOrder(double amount) {
        order = new Order();
        order.setTotalAmount(amount);
        order.setOrderStatus(OrderStatus.inProcess);
        order.setDeliveryDate(new Date());
        order.setOrderDate(new Date());
        return order;
    }


}
