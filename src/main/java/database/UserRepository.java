package main.java.database;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Logic.UserFactory;
import main.java.BL.Contract.User;
import main.java.common.DateUtils;

import java.sql.*;
import java.util.Vector;

import static main.java.common.constants.DatabaseConstants.*;

public class UserRepository {

    /* Function num #1 - Adding a new user */

    public static void addUser(Employee user, boolean isManager) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String dateOfBirth = DateUtils.formatDateToString(user.getDateOfBirth());
        String username = user.getUserName();
        String phoneNumber = user.getPhoneNumber();
        String password = user.getPassword();
        String hireDate = DateUtils.formatDateToString(user.getHireDate());

        String sql = String.format("INSERT INTO user(%s,%s,%s,%s,%s,%s,%s,%s) VALUES('%s','%s','%s','%s','%s','%s',%s,'%s')",
                USER_TABLE_FIRST_NAME_COLUMN,
                USER_TABLE_LAST_NAME_COLUMN,
                USER_TABLE_DATE_OF_BIRTH_COLUMN,
                USER_TABLE_USERNAME_COLUMN,
                USER_TABLE_PHONE_NUMBER_COLUMN,
                USER_TABLE_PASSWORD_COLUMN,
                USER_TABLE_IS_MANAGER_COLUMN,
                USER_TABLE_HIRE_DATE_COLUMN,
                firstName, lastName, dateOfBirth, username, phoneNumber, password, isManager ? 1 : 0, hireDate);

        Connection conn = DatabaseAccessManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }


    /* Function num #2 - Deleting an existing user */

    public static void deleteUser(int userId) {
        String sql = String.format("DELETE from user where %s =" + userId, USER_TABLE_USERNAME_COLUMN);


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

    public static void deleteUserByUderName(String userName) {
        String sql = String.format("DELETE from user where %s ='%s'", USER_TABLE_USERNAME_COLUMN, userName);


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

    /* Function num #3 - Getting the username by its id */

    public static User getUserByUserName(String userName) {
        User user = new User();
        String sql = String.format("SELECT * FROM user WHERE %s = '%s'", USER_TABLE_USERNAME_COLUMN, userName);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth"));
            user.setUserName(rs.getString("username"));
            user.setPhoneNumber(rs.getString("phone_number"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return user;
    }


    public static User getUserById(int userId) {
        User user = new User();
        String sql = String.format("SELECT * FROM user WHERE %s = " + userId, USER_TABLE_ID_COLUMN);
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth"));
            user.setUserName(rs.getString("username"));
            user.setPhoneNumber(rs.getString("phone_number"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return user;
    }


    /* Function num #4 - Log in access */

    public static User LogIn(String userName, String password) {
        String sql = String.format("SELECT * FROM user WHERE username ='%s' And password = %s ",
                userName, password);
        User user = new User();
        boolean result = false;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int isManager = (rs.getInt("is_manager"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUserName(rs.getString("username"));
            user.setPhoneNumber(rs.getString("phone_number"));

            //using factory to create the currect type of user.
            user = UserFactory.GetUser(isManager, user);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return user;
    }

    public static Vector<String> getAllDepartment() {
        Vector<String> providersNames = new Vector<>();
        String sql = "SELECT distinct department_name FROM department";
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                providersNames.add(rs.getString("department_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return providersNames;
    }


}

