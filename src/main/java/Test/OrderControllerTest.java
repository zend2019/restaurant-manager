import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.OrderRepository;
import org.junit.*;

public class OrderControllerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public int AddOrder(Order order) {
        double currentBudget = 120;
        //TotalAmount = 300
        if (currentBudget < 300) {
            throw new RestaurantManagerException("Your order is above the current budget");
        }
        int orderId = OrderRepository.addOrder(order);

        return orderId;
    }

    @Test
    public void EditOrder(int orderId, Order order) {

        Order existingOrder = null;
        if (order == null)
            throw new RestaurantManagerException("User already exist.");
        OrderRepository.editOrder(orderId, order);

    }
}
