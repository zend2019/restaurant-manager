package main.java.database;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Provider;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import static main.java.common.constants.DatabaseConstants.*;

public class ProviderRepository {

        /* Function num #1 - Editing an existing provider */

        public static void editProvider(Provider provider, int providerId) {
        }

        public static void addProvider(Provider provider) {
            int id = provider.getId();
            String companyName = provider.getCompanyName();
            String contactName = provider.getContactName();
//        List<Integer> productType = provider.getProductType(); //TODO - @Dana , why list of integers for product type?

            String sql = String.format("INSERT INTO provider(id,company_name,contact_name) VALUES(%s,%s,%s)", PROVIDER_TABLE_PROVIDER_ID_COLUMN, PROVIDER_TABLE_COMPANY_NAME_COLUMN, PROVIDER_TABLE_CONTACT_NAME_COLUMN);

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

        /* Function num #2 - Getting all company name by the provider id */

        public static Vector<String> getAllProviderCompanyName() {
            Vector<String> providersNames = new Vector<>();

            String sql = String.format("SELECT distinct %s FROM provider", PROVIDER_TABLE_COMPANY_NAME_COLUMN);

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

        /* Function num #3 - Getting a list  of all providers which supply specific category */

        public static List<Provider> getProviderByCategory(Category category) {
            return null;
        }

        public static void deleteProvider(String providerId) {
            String sql = String.format("DELETE from provider where id =" + providerId, PROVIDER_TABLE_PROVIDER_ID_COLUMN);

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

        /* Function num #4 - Getting provider id by its name */

        public static String getProviderIdByName(String providerName) {
            String providerId = "";
            String sql = String.format("SELECT %s FROM provider WHERE %s =" + providerName,
                    PROVIDER_TABLE_PROVIDER_ID_COLUMN,
                    PROVIDER_TABLE_COMPANY_NAME_COLUMN);

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


        /* Function num #5 - Getting provider name by its id */
        public static String getProviderNameById(String providerId) {
            String providerName = "";
            String sql = "SELECT " + PROVIDER_TABLE_COMPANY_NAME_COLUMN + " FROM provider WHERE " + PROVIDER_TABLE_PROVIDER_ID_COLUMN + "=" + providerId;
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


    }

