package main.java.database;

import main.java.BL.Contract.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.java.common.constants.DatabaseConstants.*;

public class RestaurantRepository {

    /* Function num #1 - Adding a new restaurant */

    public static void addRestaurant(Restaurant restaurant) {
        String name = restaurant.getName();
        int id = restaurant.getId();
        String sql = String.format("INSERT INTO restaurant(id,name) VALUES(%s,%s)", RESTAURANT_TABLE_ID_COLUMN, RESTAURANT_TABLE_NAME_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    public static Restaurant getRestaurant() {
        Restaurant restaurant= new Restaurant();
        String sql = String.format("Select %s  from restaurant ", RESTAURANT_TABLE_BUDGET_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            restaurant.setBudget(rs.getDouble(RESTAURANT_TABLE_BUDGET_COLUMN));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return restaurant;
    }


}