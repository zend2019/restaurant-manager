package main.java.common.constants;

public class DatabaseConstants {

    //User table
    public static final String USER_TABLE_ID_COLUMN = "id";
    public static final String USER_TABLE_FIRST_NAME_COLUMN = "first_name";
    public static final String USER_TABLE_LAST_NAME_COLUMN = "last_name";
    public static final String USER_TABLE_AGE_COLUMN = "age";
    public static final String USER_TABLE_DATE_OF_BIRTH_COLUMN = "date_of_birth";
    public static final String USER_TABLE_USERNAME_COLUMN = "username";
    public static final String USER_TABLE_PHONE_NUMBER_COLUMN = "phone_number";
    public static final String USER_TABLE_IS_MANAGER_COLUMN = "is_manager";

    //Product table
    public static final String PRODUCT_TABLE_ITEM_ID_COLUMN = "id";
    public static final String PRODUCT_TABLE_ITEM_NAME_COLUMN = "item_name";
    public static final String PRODUCT_TABLE_ITEM_PRICE_COLUMN = "price";
    public static final String PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN = "expiration_date";
    public static final String PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN = "current_amount";
    public static final String PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN = "required_amount";
    public static final String PRODUCT_TABLE_ITEM_PROVIDER_COLUMN = "provider";
    public static final String PRODUCT_TABLE_ITEM_CATEGORY_COLUMN = "category";

    //Category table
    public static final String CATEGORIES_TABLE_CATEGORY_NAME_COLUMN = "category_name";
    public static final String CATEGORIES_TABLE_CATEGORY_ID_COLUMN = "id";

    //Provider table
    public static final String PROVIDER_TABLE_PROVIDER_ID_COLUMN = "id";
    public static final String PROVIDER_TABLE_COMPANY_NAME_COLUMN = "company_name";
    public static final String PROVIDER_TABLE_CONTACT_NAME_COLUMN = "contact_name";
    public static final String PROVIDER_TABLE_PRODUCT_TYPE_COLUMN = "product_type";

    //Orders table
    public static final String ORDERS_TABLE_ORDER_ID_COLUMN = "id";
    public static final String ORDERS_TABLE_PRODUCT_TYPE_COLUMN = "product_type";
    public static final String ORDERS_TABLE_PROVIDER_COLUMN = "provider";
    public static final String ORDERS_TABLE_DELIVERY_DATE_COLUMN = "delivery_date";
    public static final String ORDERS_TABLE_ORDER_DATE_COLUMN = "order_date";
    public static final String ORDERS_TABLE_TOTAL_AMOUNT_COLUMN = "total_amount";
    public static final String ORDERS_TABLE_ORDER_STATUS_COLUMN = "order_status";

    //Ordered items table
    public static final String ORDERED_ITEMS_TABLE_ID_COLUMN = "id";
    public static final String ORDERED_ITEMS_TABLE_ITEM_ID_COLUMN = "item_id";
    public static final String ORDERED_ITEMS_TABLE_ORDER_ID_COLUMN = "order_id";
    public static final String ORDERED_ITEMS_TABLE_ORDERED_UNITS_COLUMN = "ordered_units";

    //Restaurant table
    public static final String RESTAURANT_TABLE_ID_COLUMN = "id";
    public static final String RESTAURANT_TABLE_NAME_COLUMN = "name";

}
