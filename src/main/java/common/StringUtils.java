package main.java.common;

public class StringUtils {
    public static String getStringWithSingleQuotes(String string) {
        return String.format("'%s'", string);
    }
}
