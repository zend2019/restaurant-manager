package main.java.database;

import main.java.BL.Contract.User;

import java.sql.*;

import static main.java.common.constants.DatabaseConstants.*;

public class UserRepository {

    /* Function num #1 - Adding a new user */

    public static void addUser(User user) {
        int id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String age = Integer.toString(user.getAge());
        String dateOfBirth = user.getDateOfBirth();
        String username = user.getUserName();
        String phoneNumber = user.getPhoneNmuber();
        String password = user.getPassword();

        String sql = String.format("INSERT INTO user(id,first_name,last_name,age,date_of_birth,username,phone_number,password) VALUES(%s,%s,%s,%s,%s,%s,%s)",
                USER_TABLE_ID_COLUMN,
                USER_TABLE_FIRST_NAME_COLUMN,
                USER_TABLE_LAST_NAME_COLUMN,
                USER_TABLE_AGE_COLUMN,
                USER_TABLE_DATE_OF_BIRTH_COLUMN,
                USER_TABLE_USERNAME_COLUMN,
                USER_TABLE_PHONE_NUMBER_COLUMN);

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
            pstmt.setString(8, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
    }

    /* Function num #2 - Editing an existing user */

    public static void editUser(User user, int userId) {
    }

    /* Function num #3 - Deleting an existing user */

    public static void deleteUser(int userId) {
        String sql = String.format("DELETE from user where %s =" + userId, USER_TABLE_ID_COLUMN);


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

    /* Function num #4 - Getting the username by its id */

    public static User getUserById(int id) {
        User user = new User();
        String sql = String.format("SELECT * FROM user WHERE %s = " + id, USER_TABLE_ID_COLUMN);
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

    /* Function num #5 - Log in access */

    public static User LogIn(String userName, String password) {
        String sql = String.format("SELECT * FROM user WHERE username ='%s' And password = %s ",
                userName, password);
        User user = new User();
        boolean result = false;
        Connection conn = DatabaseAccessManager.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));
            user.setDateOfBirth(rs.getString("date_of_birth"));
            user.setUserName(rs.getString("username"));
            user.setPhoneNmuber(rs.getString("phone_number"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseAccessManager.closeConnection(conn);
        }
        return user;
    }


}

