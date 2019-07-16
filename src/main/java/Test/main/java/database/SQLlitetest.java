/* package main.java.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class SQLlitetest {
}

// Unit testing for SelectWithQueryID
public class SQLliteTest2 {
    static SQLlite L;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        L=SQLlite.CreateInstanceSQL();
        String Query = "INSERT INTO User (OrderTitle,Destination,BJustification,Comments,OrderID) VALUES(?,?,?,?,?)";
        L.InsertOrder(Query, "Noga", "Aviram", "test", "TestComments", 1000000);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        L.deleteID(1000000);
    }

    @Test
    public void testSelectWithQueryID() {
        String sql = "SELECT max(OrderID) FROM NewOrderPage1";
        int result=0;

        try (Connection conn = L.ConnectToDB();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                result = rs.getInt(1);
            }
            assertEquals(1000000, result);
        }
        catch (SQLException e)
        {
            // System.out.println(e.getMessage());
        }
    }

}
*/