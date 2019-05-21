package main.java.common;

import java.util.Iterator;
import java.util.List;

public class CommonUtils {

    public static String ConvertIntListToString(List<Integer> integerList) {
        StringBuilder strbul = new StringBuilder();
        Iterator<Integer> iter = integerList.iterator();
        while (iter.hasNext()) {
            strbul.append(iter.next());
            if (iter.hasNext()) {
                strbul.append(",");
            }
        }
        return strbul.toString();
    }
}
