package main.java.BL.Contract.Logic;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.common.exceptions.RestaurantManagerException;
import main.java.dataAccess.IRestaurantRepository;

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
        int orderId = restaurantRepository.AddOrder(order);

        return orderId;
    }

    @Override
    public Order GetOrder(int orderId) {
        return restaurantRepository.GetOrder(orderId);

    }

    @Override
    public void EditOrder(int orderId, Order order) {

        Order existingOrder = restaurantRepository.GetOrder(orderId);
        if (order == null)
            throw new RestaurantManagerException("User already exist.");
        restaurantRepository.EditOrder(orderId, order);

    }

    @Override
    public void CompletedOrder(int orderId) {
        Order order = restaurantRepository.GetOrder(orderId);
        order.setOrderStatus(OrderStatus.completed);
        restaurantRepository.EditOrder(orderId, order);
    }

    @Override
    public void AddRatingPerOrder(int rating, int orderId) {
        Order order = restaurantRepository.GetOrder(orderId);
        order.setRating(rating);
        restaurantRepository.EditOrder(orderId, order);

    }
}
