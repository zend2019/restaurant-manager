package main.java.database;

import main.java.BL.Contract.*;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.DatabaseConstants;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

import static main.java.common.constants.DatabaseConstants.*;


public class DatabaseController {


    public static String getDynamicWhereQueryBuilder(HashMap hashMap) {
        StringBuilder whereQuery;
        Iterator it = hashMap.entrySet().iterator();
        Map.Entry pair = (Map.Entry) it.next();
        whereQuery = new StringBuilder(pair.getKey() + "=" + pair.getValue());
        it.remove();
        while (it.hasNext()) {
            pair = (Map.Entry) it.next();
            String andQuery = " AND " + pair.getKey() + "=" + pair.getValue();
            whereQuery.append(andQuery);
            it.remove();
        }
        return whereQuery.toString();
    }
}
