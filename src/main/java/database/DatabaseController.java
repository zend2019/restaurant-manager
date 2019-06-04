package main.java.database;

import main.java.BL.Contract.*;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.DatabaseConstants;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

import static main.java.common.constants.DatabaseConstants.*;


public class DatabaseController {


    public static void main(String[] args) throws ParseException {
        //Adding simple User test
//        User user = new User();
//        user.setFirstName("test");
//        user.setLastName("dla");
//        user.setAge("11");
//        user.setDateOfBirth("1/1/1993");
//        user.setPhoneNmuber("0547504868");
//        addUser(user);

        //Adding simple Restaurant
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(1);
//        restaurant.setName("zvikush");
//        addRestaurant(restaurant);


        //Adding simple provider
//        Provider provider = new Provider();
//        provider.setId(1);
//        provider.setCompanyName("great company");
//        provider.setContactName("elinush");
////        addProvider(provider);

        //Adding product sample
//        Product product = new Product();
//        product.setProductId("5");
//        product.setProductName("yolo");
//        product.setPrice("255");
//        product.setExpirationDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));
//        product.setCurrentProductAmount(66);
//        product.setRequiredAmount(55);
//        product.setProviderId("5");
//        addProduct(product);

//        adding order sample
//        Order order = new Order();
//        order.setOrderId(1);
//        order.setProductType("Hairline");
//        order.setProvider(provider);
//        order.setDeliveryDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1993"));
//        order.setTotalAmount(22.56);
//        addOrder(order);
//        getUserById(1);
//        getAllProviderCompanyName();
        HashMap hashMap = new HashMap();
//        hashMap.put("id", "1");
        hashMap.put("name", "'yolo'");
//        hashMap.put("price", "25");
//        hashMap.put("expiration_date", "'Fri Jan 01 00:00:00 IST 1993'");
//        hashMap.put("current_amount", "21");
        getListOfProducts(hashMap);
    }

    public static void addUser(User user) {
        int id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String age = Integer.toString(user.getAge());
        String dateOfBirth = user.getDateOfBirth();
        String username = user.getUserName();
        String phoneNumber = user.getPhoneNmuber();

        String sql = "INSERT INTO user(id,first_name,last_name,age,date_of_birth,username,phone_number) VALUES(?,?,?,?,?,?,?)";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, age);
            pstmt.setString(5, dateOfBirth);
            pstmt.setString(6, username);
            pstmt.setString(7, phoneNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    public static void addRestaurant(Restaurant restaurant) {
        String name = restaurant.getName();
        int id = restaurant.getId();
        String sql = "INSERT INTO restaurant(id,name) VALUES(?,?)";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    public static void addProvider(Provider provider) {
        int id = provider.getId();
        String companyName = provider.getCompanyName();
        String contactName = provider.getContactName();
//        List<Integer> productType = provider.getProductType(); //TODO - @Dana , why list of integers for product type?
        String sql = "INSERT INTO provider(id,company_name,contact_name) VALUES(?,?,?)";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, companyName);
            pstmt.setString(3, contactName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    public static void addProduct(Product product) {
        String id = product.getProductId();
        String name = product.getProductName();
        int category = getCategoryIdByName(StringUtils.getStringWithSingleQuotes(product.getCategory().toString()));
        String price = product.getPrice();
        String expirationDate = DateUtils.formatDateToString(product.getExpirationDate()); //todo string casting might cause issues...need to check
        int currentAmount = product.getCurrentProductAmount();
        int requiredAmount = product.getRequiredAmount();
        String provider = product.getProviderId();
        String sql = "INSERT INTO product(id,item_name,category,provider,price,expiration_date,current_amount,required_amount) VALUES(?,?,?,?,?,?,?,?)";
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


    public static List<Product> getProductList(int providerId) {
        String sql = "SELECT* FROM product WHERE providerId = ? ";
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

    public static List<Product> getProductByProviderId(int providerId) {
        String sql = "SELECT* FROM product WHERE providerId = ? ";
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


    public static int addOrder(Order order) {
        int id = order.getOrderId();
        String productType = String.join(",", order.getProductIds()); //String.join(",",order.getProductIds());
        String provider = String.join(",", order.getProvider());
        Date deliveryDate = order.getDeliveryDate();
        Double totalAmount = order.getTotalAmount();
        String sql = "INSERT INTO orders(id,product_id,provider,delivery_date,total_amount) VALUES(?,?,?,?,?)";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, productType);
            pstmt.setObject(3, provider);
            pstmt.setString(4, String.valueOf(deliveryDate));
            pstmt.setDouble(5, totalAmount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return id;
    }

    public static Order getOrderById(int id) {
        Order order = new Order();
        String sql = "SELECT * FROM orders WHERE id = " + id;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
            order.setOrderId(rs.getInt("id"));
            List<String> d = new ArrayList<String>(Arrays.asList(rs.getString("product_id").split(",")));
            order.setProductIds(new ArrayList<String>(Arrays.asList(rs.getString("product_id").split(","))));
            order.setProvider(new ArrayList<String>(Arrays.asList(rs.getString("provider").split(","))));
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

    public static User getUserById(int id) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE id = " + id;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));
            user.setDateOfBirth(rs.getString("date_of_birth"));
            user.setUserName(rs.getString("username"));
            user.setPhoneNmuber(rs.getString("phone_number"));
//        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return user;
    }

    /*
     SQLite Reset Primary Key Field (mostly will be used for auto autoincrement for ids) run the following queries:
    delete from your_table;
    delete from sqlite_sequence where name='your_table';
     */
    public static Vector<Product> getListOfProducts(HashMap hashMap) {
        String sql = "SELECT* FROM product WHERE " + getDynamicWhereQueryBuilder(hashMap);
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

    private static String getDynamicWhereQueryBuilder(HashMap hashMap) {
        StringBuilder whereQuery;
        Iterator it = hashMap.entrySet().iterator();
        Map.Entry pair = (Map.Entry) it.next();
        whereQuery = new StringBuilder(pair.getKey() + "=" + pair.getValue());
        it.remove();
        while (it.hasNext()) {
            pair = (Map.Entry) it.next();
            String andQuery = " AND " + pair.getKey() + "=" + pair.getValue();
            whereQuery.append(andQuery);
            it.remove();
        }
        return whereQuery.toString();
    }

    public static Vector<String> getAllProviderCompanyName() {
        Vector<String> providersNames = new Vector<>();
        String sql = "SELECT distinct company_name FROM provider";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                providersNames.add(rs.getString("company_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return providersNames;
    }

    public static Vector<String> getAllCategoryNames() {
        Vector<String> categoryNames = new Vector<>();
        String sql = "SELECT distinct "+ CATEGORIES_TABLE_CATEGORY_NAME_COLUMN +" FROM categories";
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

    public static Vector<String> getAllProductsNames() {
        Vector<String> productNames = new Vector<>();
        String sql = "SELECT distinct "+ DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN +" FROM product";
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

    public static void editOrder(int orderId, Order order) {
    }

    public static List<Product> getProductByProvider(String providerId) {
        String sql = "SELECT* FROM product WHERE provider=" + providerId;
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

    public static void editUser(User user, int userId) {
    }

    public static void deleteUser(int userId) {
        String sql = "DELETE from user where id ="+userId;
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

    public static List<Provider> getProviderByCategory(Category category) {
        return null;
    }

    public static void deleteProvider(String providerId) {
        String sql = "DELETE from provider where id ="+providerId;
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

    public static String getProviderIdByName(String providerName){
        String providerId ="";
        String sql = "SELECT "+ PROVIDER_TABLE_PROVIDER_ID_COLUMN +" FROM provider WHERE "+ PROVIDER_TABLE_COMPANY_NAME_COLUMN +"=" + providerName;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            providerId = rs.getString(PROVIDER_TABLE_PROVIDER_ID_COLUMN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return providerId;
    }

    public static String getProviderNameById(String providerId){
        String providerName ="";
        String sql = "SELECT "+ PROVIDER_TABLE_COMPANY_NAME_COLUMN +" FROM provider WHERE "+ PROVIDER_TABLE_PROVIDER_ID_COLUMN +"=" + providerId;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            providerName = rs.getString(PROVIDER_TABLE_COMPANY_NAME_COLUMN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return providerName;
    }

    public static int getCategoryIdByName(String categoryName){
        int categoryId = -1;
        String sql = "SELECT "+ CATEGORIES_TABLE_CATEGORY_ID_COLUMN +" FROM categories WHERE "+ CATEGORIES_TABLE_CATEGORY_NAME_COLUMN +"=" + categoryName;
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

    public static void editProvider(Provider provider, int providerId) {
    }
}
