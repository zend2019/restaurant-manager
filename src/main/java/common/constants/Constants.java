package main.java.common.constants;

import javax.swing.*;

public class Constants {
    //Database//
    public static final String DATABASE_FILE_NAME = "database.db";
    public static final String RELATIVE_PATH = "src\\main\\java\\database\\";

    //GUI labels//
    public static final JLabel FIELD_REQUIRED = new JLabel("Field is required");
    public static final JLabel ALL_FIELDS_REQUIRED = new JLabel("All fields are required");
    public static final JLabel ATLEAST_ONE_FIELD_REQUIRED = new JLabel("At least One field is required");
    public static final JLabel ITEM_ADDED = new JLabel("Item was added");
    public static final JLabel SEARCHING = new JLabel("Searching...");
    public static final JLabel EXCEED_LIMIT = new JLabel("Selection exceeded the limited amount");

    //Field names//
    public static final String EMPTY_FIELD = "";
    public static final String ITEM_ID = "ItemId";
    public static final String CATEGORY = "Category";
    public static final String PRICE = "Price";
    public static final String AVAILABLE_UNITS = "Available_Units";
    public static final String REQUIRED_UNITS = "Required_Units";
    public static final String EXPIRATION_DATE = "Expiration_Date";
    public static final String DELIVERY_DATE = "Delivery_Date";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_STATUS = "Order_Status";
    public static final int INVENTORY_MATRIX_COLUMNS = 7;
}
