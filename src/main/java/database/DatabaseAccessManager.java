package main.java.database;

import main.java.common.EnvironmentUtils;
import main.java.common.exceptions.RestaurantManagerException;

import java.math.BigDecimal;
import java.sql.*;


public class DatabaseAccessManager {

    private static Connection getConnection() {
        Connection conn;
        final String jdbcDriverName = EnvironmentUtils.getDbDriver();
        try {
            Class.forName(jdbcDriverName);
            conn = DriverManager.getConnection(EnvironmentUtils.getDbUrl());
        } catch (Exception e) {
            throw new RestaurantManagerException("Failed to connect to db " + e.getMessage());
        }
        return conn;
    }

    /**
     * This close provided connection
     *
     * @param conn - connection to close
     */
    private static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.printf("Failed to close database connection with error " + e.getMessage());
        }
    }


    /**
     * This method execute single result query - in case of multiple result SeleniumException will be thorown
     *
     * @param query      - query for execution
     * @param returnType - Wrapper class valid values: Boolean, String, Long, Integer, Date
     * @return returnType Object (String/bool/int/BigDecimal etc..)
     */
    public static Object executeSingleSimpleResultQuery(String query, Class returnType) {
        Connection conn = null;
        PreparedStatement stat;
        Object result = null;
        try {
            conn = getConnection();
            stat = conn.prepareStatement(query);
            final ResultSet resultSet = stat.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                if (i >= 1) {
                    throw new RestaurantManagerException("Multiple result returned for query " + query);
                }
                i++;
                result = fetchResult(returnType, resultSet);
            }
        } catch (SQLException e) {
            throw new RestaurantManagerException("Failed to execute query with error: " + e.getMessage() + "\n" + query, e);
        } finally {
            closeConnection(conn);
        }
        System.out.print("Return value " + result.toString());
        return result;
    }

    private static Object fetchResult(Class returnType, ResultSet resultSet) throws SQLException {
        Object result;
        if (returnType.getName().equalsIgnoreCase(Boolean.class.getSimpleName())) {
            result = resultSet.getBoolean(1);
        } else if (returnType.getName().equalsIgnoreCase(Long.class.getSimpleName())) {
            result = resultSet.getLong(1);
        } else if (returnType.getName().equalsIgnoreCase(Integer.class.getSimpleName())) {
            result = resultSet.getInt(1);
        } else if (returnType.getName().equalsIgnoreCase(String.class.getSimpleName())) {
            result = resultSet.getString(1);
        } else if (returnType.getName().equalsIgnoreCase(BigDecimal.class.getSimpleName())) {
            result = resultSet.getBigDecimal(1);
        } else if (returnType.getName().equalsIgnoreCase(Date.class.getSimpleName())) {
            result = resultSet.getTimestamp(1);
        } else {
            result = resultSet.getObject(1);
        }
        return result;
    }

    public static void executeQueryAndCommit(String query) {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            System.out.println("About to execute query " + query);
            stmt.execute(query);
            System.out.println("About to commit query " + query);
            conn.commit();
        } catch (SQLException e) {
            throw new RestaurantManagerException("Failed to execute and commit query with error: " + e.getMessage() + "\n" + query, e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * This is a simple example for a more "unique" query example that should be used for UI (swing) cases.
     */
    public static void executeStatementExample(Connection con) {
        try (Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM \"Providers\"";

            boolean results = stmt.execute(SQL);
            int rsCount = 0;

            // Loop through the available result sets.
            do {
                if (results) {
                    ResultSet rs = stmt.getResultSet();
                    rsCount++;

                    // Show data from the result set.
                    System.out.println("RESULT SET #" + rsCount);
                    while (rs.next()) {
                        System.out.println(rs.getString("ProviderId") + ", " + rs.getString("ProviderName"));
                    }
                }
                System.out.println();
                results = stmt.getMoreResults();
            } while (results);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //few examples for debug if you'll need
//        executeQueryAndCommit("INSERT INTO \"Providers\" VALUES('yoyo','hahaha',3)");
//        executeStatementExample(getConnection());
//        executeSingleSimpleResultQuery("SELECT ProviderID FROM \"Providers\" WHERE ProviderID='BLABLAL'", String.class);
    }
}