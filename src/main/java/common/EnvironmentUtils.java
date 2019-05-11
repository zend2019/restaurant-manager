package main.java.common;

import main.java.common.constants.Constants;

public class EnvironmentUtils {

    private static String dbDriver;
    private static String dbUrl;

    public static String getDbDriver() {
        if (dbDriver == null) {
            setDbDriver("org.sqlite.JDBC");
        }
        return dbDriver;
    }

    public static void setDbDriver(String dbDriver) {
        EnvironmentUtils.dbDriver = dbDriver;
    }


    public static String getDbUrl() {
        if (dbUrl == null) {
            setDbUrl("jdbc:sqlite:" + Constants.RELATIVE_PATH + Constants.DATABASE_FILE_NAME);
        }
        return dbUrl;
    }

    public static void setDbUrl(String getDbUrl) {
        EnvironmentUtils.dbUrl = getDbUrl;
    }
}
