package main.java.BL.Contract.Logic;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;
import main.java.database.DatabaseController;

public class OrderManager implements IOrderManager {

    public OrderManager(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    private IRestaurantRepository restaurantRepository;


    @Override
    public int AddOrder(Order order) {
        double currentBudget = restaurantRepository.GetBudget();
        if (currentBudget < order.getTotalAmount()) {
            throw new RestaurantManagerException("Your order is above the current budget");
        }
        int orderId = DatabaseController.addOrder(order);

        return orderId;
    }

    @Override
    public Order GetOrder(int orderId) {
        return DatabaseController.getOrder(orderId);
    }

    @Override
    public void EditOrder(int orderId, Order order) {

        Order existingOrder = DatabaseController.getOrder(orderId);
        if (order == null)
            throw new RestaurantManagerException("User already exist.");
        DatabaseController.editOrder(orderId, order);

    }

    @Override
    public void CompletedOrder(int orderId) {
        Order order = restaurantRepository.GetOrder(orderId);
        order.setOrderStatus(OrderStatus.completed);
        restaurantRepository.EditOrder(orderId, order);
    }

    @Override
    public void AddRatingPerOrder(int rating, int orderId) {
        Order order = DatabaseController.getOrder(orderId);
        order.setRating(rating);
        DatabaseController.editOrder(orderId, order);

    }
}
