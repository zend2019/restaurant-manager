package main.java.database;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.BL.Contract.Product;
import main.java.common.DateUtils;
import main.java.common.constants.DatabaseConstants;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

import static main.java.common.constants.DatabaseConstants.*;
import static main.java.database.DatabaseController.getDynamicWhereQueryBuilder;

public class OrderRepository {


    /* Function num #1 - Adding a new order */

    public static void addOrderdItem (HashMap<String, Integer> products, int orderId){

        String sql = String.format("INSERT INTO ordered_items(order_id,item_id,ordered_units) VALUES(%s,%s,%s)",
                ORDERED_ITEMS_TABLE_ORDER_ID_COLUMN,
                ORDERED_ITEMS_TABLE_ITEM_ID_COLUMN,
                ORDERED_ITEMS_TABLE_ORDERED_UNITS_COLUMN);

        for (Map.Entry p : products.entrySet()) {
            Connection conn = DatabaseAccessManager.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, orderId);
                pstmt.setString(2, p.getKey().toString());
                pstmt.setInt(3, Integer.parseInt(p.getValue().toString()));

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                DatabaseAccessManager.closeConnection(conn);
            }
        }
    }

    /* Function num #2 - Editing an existing order */

    public static void editOrder(int orderId, Order order) {
    }

    public static int addOrder(Order order) {
        int id = order.getOrderId();
        Date deliveryDate = order.getDeliveryDate();
        Double totalAmount = order.getTotalAmount();
        String sql = String.format("INSERT INTO orders(id,delivery_date,total_amount) VALUES(%s,%s,%s)",
                ORDERS_TABLE_ORDER_ID_COLUMN,
                ORDERS_TABLE_DELIVERY_DATE_COLUMN,
                ORDERS_TABLE_TOTAL_AMOUNT_COLUMN);


        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, String.valueOf(deliveryDate));
            pstmt.setDouble(3, totalAmount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return id;
    }

    /* Function num #3 - Getting an existing order by its id */

    public static Order getOrderById(int id) {
        Order order = new Order();
        String sql = String.format("SELECT * FROM orders WHERE id = " + id, ORDERS_TABLE_ORDER_ID_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
            order.setOrderId(rs.getInt("id"));
            List<String> d = new ArrayList<String>(Arrays.asList(rs.getString("product_id").split(",")));
            order.setDeliveryDate(rs.getDate("delivery_date"));
            order.setTotalAmount(rs.getDouble("total_amount"));

//        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return order;
    }

    /* Function num #3 - Getting all the orders */

    public static Vector<Order> getAllOrders() {
        Vector<Order> orders = new Vector<Order>();
        String sql = String.format("SELECT %s FROM orders", ORDERS_TABLE_ORDER_ID_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("id"));
                List<String> d = new ArrayList<String>(Arrays.asList(rs.getString("product_id").split(",")));
                order.setDeliveryDate(rs.getDate("delivery_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return orders;
    }

    /* Function num #3 - Getting an old order's details by its id */

    public static HashMap<String, Integer> getOrderdItemByOrderId(Integer orderId) {
        HashMap<String, Integer> result = new HashMap<>();
        String sql = String.format("SELECT * FROM ordered_items WHERE order_id = " + orderId,
                ORDERED_ITEMS_TABLE_ID_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.put(rs.getString("item_id"),rs.getInt("amount"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return result;
    }
    public static Vector<Product> getListOfOrderedProductsByOrder(String orderId) {
        String sql = "SELECT item_name,category,provider,ordered_units,price\n" +
                "FROM ordered_items JOIN product ON product.id=ordered_items.item_id\n WHERE order_id = " + orderId;
        Connection conn = DatabaseAccessManager.getConnection();
        Vector<Product> productsList = new Vector<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setProductName(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN));
                product.setCategory(Category.valueOf(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN)));
                product.setProviderId(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN));
                product.setPrice(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_PRICE_COLUMN));
                product.setCurrentProductAmount(rs.getInt(DatabaseConstants.ORDERED_ITEMS_TABLE_ORDERED_UNITS_COLUMN));
                productsList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return productsList;
    }

    public static Vector<Order> getListOfOrders(HashMap hashMap) {
        String sql = "SELECT order_id,product.provider,total_amount,order_status,order_date,delivery_date\n" +
                "    FROM ordered_items JOIN product ON product.id=ordered_items.item_id\n" +
                "    JOIN orders ON ordered_items.order_id=orders.id WHERE " + DatabaseController.getDynamicWhereQueryBuilder(hashMap);

        Connection conn = DatabaseAccessManager.getConnection();
        Vector<Order> ordersList = new Vector<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Order order = new Order();
                order.setOrderId(rs.getInt(DatabaseConstants.ORDERED_ITEMS_TABLE_ORDER_ID_COLUMN));
                //order.setProviderId(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN));
                order.setTotalAmount(rs.getDouble(DatabaseConstants.ORDERS_TABLE_TOTAL_AMOUNT_COLUMN));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString(DatabaseConstants.ORDERS_TABLE_ORDER_STATUS_COLUMN)));
                order.setOrderDate(DateUtils.getDateByString(rs.getString(DatabaseConstants.ORDERS_TABLE_ORDER_DATE_COLUMN)));
                order.setDeliveryDate(DateUtils.getDateByString(rs.getString(DatabaseConstants.ORDERS_TABLE_DELIVERY_DATE_COLUMN)));
                ordersList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return ordersList;
    }
}
