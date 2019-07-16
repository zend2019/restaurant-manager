package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Order;
import main.java.BL.Contract.Product;

import java.util.HashMap;
import java.util.Vector;

public interface IOrderManager {

    int AddOrder(Order order);

    Order GetOrder (int orderId);

    Vector<Product> getListOfOrderedProductsByOrder(String orderId);

    Vector<Order> getListOfOrders(HashMap hashMap);
}
