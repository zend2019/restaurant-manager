package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Order;

import java.util.Vector;

public interface IOrderManager {

    int AddOrder(Order order);

    Order GetOrder(int orderId);

    void EditOrder(int orderId, Order order);

    void CompletedOrder(int orderId);

    void AddRatingPerOrder(int rating, int orderId);

    Vector<Order> GetAllOpenOrder();
}
