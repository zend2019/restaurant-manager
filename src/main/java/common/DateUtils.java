package main.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getDateByString(String string) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(string);
    }

    public static String formatDateToString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
