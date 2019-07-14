package main.java.database;
import main.java.common.exceptions.RestaurantManagerException;
import org.junit.*;


public class AddOrderTest {

    static AddOrderTest junit;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        junit = new AddOrderTest();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    //addOrderedItem(Order order)
    public void addOrderedItem() {
        double currentBudget = 120;
        //TotalAmount = 300
        if (currentBudget < 300) {
            throw new RestaurantManagerException("Your order is above the current budget");
        }

    }

}
