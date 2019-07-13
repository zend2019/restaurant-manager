package main.java.database;
import java.sql.*;
import java.util.Vector;

import static main.java.common.constants.DatabaseConstants.*;

public class CategoryRepository {


    /* Function num #1 - Getting all categories' name */

    public static Vector<String> getAllCategoryNames() {
        Vector<String> categoryNames = new Vector<>();
        String sql = String.format("SELECT distinct %s FROM categories",
                CATEGORIES_TABLE_CATEGORY_NAME_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                categoryNames.add(rs.getString(CATEGORIES_TABLE_CATEGORY_NAME_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return categoryNames;
    }

    /* Function num #2 - Getting category id by its name */

    public static int getCategoryIdByName(String categoryName) {
        int categoryId = -1;
        String sql = String.format("SELECT %s FROM categories WHERE %s = " + categoryName,
                CATEGORIES_TABLE_CATEGORY_ID_COLUMN,
                CATEGORIES_TABLE_CATEGORY_NAME_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            categoryId = rs.getInt(CATEGORIES_TABLE_CATEGORY_ID_COLUMN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return categoryId;
    }

}
