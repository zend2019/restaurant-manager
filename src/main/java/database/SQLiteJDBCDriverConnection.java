package main.java.database;

import java.sql.*;


public class SQLiteJDBCDriverConnection {
    public static final String DATABASE_FILE_NAME = "database.db";
    public static final String RELATIVE_PATH = "src\\main\\java\\database\\";

    /**
     * Connect to our local sqlite db
     */
    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + RELATIVE_PATH + DATABASE_FILE_NAME;
            Connection connection = DriverManager.getConnection(url);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM \"Providers\"");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while(rs.next()) {
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    Object object = rs.getObject(columnIndex);
                    System.out.printf("%s, ", object == null ? "NULL" : object.toString());
                }
                System.out.printf("%n");
            }
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}