import main.java.BL.Contract.User;
import main.java.database.DatabaseAccessManager;
import org.junit.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    /* Function num #5 - Log in access */

    public static User LogIn(String userName, String password) {
       userName = 'noga';
               password = '1234';
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
