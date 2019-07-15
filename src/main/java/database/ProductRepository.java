package main.java.database;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.DatabaseConstants;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static main.java.common.constants.DatabaseConstants.*;
import static main.java.database.DatabaseController.getCategoryIdByName;

public class ProductRepository {

    /* Function num #1 - Adding a new product */

    public static void addProduct(Product product) {
        String id = product.getProductId();
        String name = product.getProductName();
        int category = getCategoryIdByName(StringUtils.getStringWithSingleQuotes(product.getCategory().toString()));
        String price = product.getPrice();
        String expirationDate = DateUtils.formatDateToString(product.getExpirationDate()); //todo string casting might cause issues...need to check
        int currentAmount = product.getCurrentProductAmount();
        int requiredAmount = product.getRequiredAmount();
        String provider = product.getProviderId();

        String sql = String.format("INSERT INTO product(id,item_name,category,provider,price,expiration_date,current_amount,required_amount) VALUES(%s,%s,%s,%s,%s,%s,%s,%s)",
                PRODUCT_TABLE_ITEM_ID_COLUMN,
                PRODUCT_TABLE_ITEM_NAME_COLUMN,
                PRODUCT_TABLE_ITEM_CATEGORY_COLUMN,
                PRODUCT_TABLE_ITEM_PROVIDER_COLUMN,
                PRODUCT_TABLE_ITEM_PRICE_COLUMN,
                PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN,
                PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN,
                PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, category);
            pstmt.setString(4, provider);
            pstmt.setString(5, price);
            pstmt.setString(6, expirationDate);
            pstmt.setInt(7, currentAmount);
            pstmt.setInt(8, requiredAmount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    /* Function num #2 - Editing an existing product */

    public static void editProduct(Product product) {
        String id = product.getProductId();
        String name = product.getProductName();
        int category = getCategoryIdByName(StringUtils.getStringWithSingleQuotes(product.getCategory().toString()));
        String price = product.getPrice();
        String expirationDate = DateUtils.formatDateToString(product.getExpirationDate()); //todo string casting might cause issues...need to check
        int currentAmount = product.getCurrentProductAmount();
        int requiredAmount = product.getRequiredAmount();
        String provider = product.getProviderId();
        String sql = String.format("Update product set (id,item_name,category,provider,price,expiration_date,current_amount,required_amount) VALUES(%s,%s,%s,%s,%s,%s,%s,%s) where id=" + product.getProductId(),
                PRODUCT_TABLE_ITEM_ID_COLUMN,
                PRODUCT_TABLE_ITEM_NAME_COLUMN,
                PRODUCT_TABLE_ITEM_CATEGORY_COLUMN,
                PRODUCT_TABLE_ITEM_PROVIDER_COLUMN,
                PRODUCT_TABLE_ITEM_PRICE_COLUMN,
                PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN,
                PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN,
                PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, category);
            pstmt.setString(4, provider);
            pstmt.setString(5, price);
            pstmt.setString(6, expirationDate);
            pstmt.setInt(7, currentAmount);
            pstmt.setInt(8, requiredAmount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    /* Function num #3 - Deleting an existing product */

    public static void deleteProduct(String productId) {
        String sql = String.format("DELETE from product where id =" + productId, PRODUCT_TABLE_ITEM_ID_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    /* Function num #4 - Getting a list of all products */

    public static List<Product> getProductList(int providerId) {
        String sql = String.format("SELECT* FROM product WHERE %s = ", PRODUCT_TABLE_ITEM_PROVIDER_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, providerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setProductId(rs.getString("id"));
                product.setProductName(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return products;
    }

    /* Function num #5 - Getting a list of all products by its provider id */

    public static List<Product> getProductByProviderId(int providerId) {
        String sql = String.format("SELECT* FROM product WHERE %s = ", PRODUCT_TABLE_ITEM_PROVIDER_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, providerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setProductId(rs.getString("id"));
                product.setProductName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return products;
    }

    /* Function num #6 - Getting a list of all products */

    public static Vector<Product> getListOfAllProducts() {
        String sql = "SELECT * FROM product ";
        Connection conn = DatabaseAccessManager.getConnection();
        Vector<Product> productsList = new Vector<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setProductId(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_ID_COLUMN));
                product.setProductName(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN));
                product.setPrice(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_PRICE_COLUMN));
                product.setCategory(Category.valueOf(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN)));
                product.setExpirationDate(DateUtils.getDateByString(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN)));
                product.setCurrentProductAmount(rs.getInt(DatabaseConstants.PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN));
                product.setRequiredAmount(rs.getInt(DatabaseConstants.PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN));
                product.setProviderId(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN));
                productsList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return productsList;

    }
    /* Function num #7 - Getting a list of all products' name */

    public static Vector<String> getAllProductsNames() {
        Vector<String> productNames = new Vector<>();
        String sql = String.format("SELECT distinct %s FROM product", PRODUCT_TABLE_ITEM_NAME_COLUMN);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                productNames.add(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return productNames;
    }

    /* Function num #8 - Getting product by its provider */

    public static Vector<Product> getProductByProvider(String providerId) {
        String sql = String.format("SELECT* FROM product WHERE %s = ", PRODUCT_TABLE_ITEM_PROVIDER_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        Vector<Product> products = new Vector<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setProductId(rs.getString("id"));
                product.setProductName(rs.getString(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN));
                product.setPrice(rs.getString("price"));
                product.setExpirationDate(DateUtils.getDateByString(rs.getString("expiration_date")));
                product.setCurrentProductAmount(rs.getInt("current_amount"));
                product.setRequiredAmount(rs.getInt("required_amount"));
                product.setProviderId(rs.getString("provider"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return products;
    }

}
