package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Order;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Restaurant;
import main.java.database.OrderRepository;
import main.java.database.RestaurantRepository;

import java.util.HashMap;
import java.util.Vector;


public class OrderController implements IOrderManager {

    @Override
    public int AddOrder(Order order) {
        Restaurant restaurant = RestaurantRepository.getRestaurant();
        double currentBudget = restaurant.getBudget();
        if (currentBudget < order.getTotalAmount()) {
            // Not enogth mbudget to order;
            return -1;
        }
        int orderId = OrderRepository.addOrder(order);
        //update current budget
        RestaurantRepository.updateBudget(restaurant.getId(), currentBudget - order.getTotalAmount(), false);


        return orderId;
    }

    @Override
    public Order GetOrder(int orderId) {
        return OrderRepository.getOrderById(orderId);
    }


    @Override
    public Vector<Product> getListOfOrderedProductsByOrder(String orderId) {
        return OrderRepository.getListOfOrderedProductsByOrder(orderId);
    }

    @Override
    public Vector<Order> getListOfOrders(HashMap hashMap) {
        return OrderRepository.getListOfOrders(hashMap);
    }
}
;