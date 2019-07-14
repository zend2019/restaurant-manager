import main.java.BL.Contract.Order;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.database.OrderRepository;
import org.junit.*;

public class EditOrderTest {

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
    public void EditOrder(int orderId, Order order) {

        Order existingOrder = null;
        if (order == null)
            throw new RestaurantManagerException("User already exist.");
        OrderRepository.editOrder(orderId, order);

    }
}
