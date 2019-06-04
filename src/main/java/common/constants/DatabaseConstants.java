package main.java.common.constants;

public class DatabaseConstants {
    //Product table//
    public static final String PRODUCT_TABLE_ITEM_ID_COLUMN = "id";
    public static final String PRODUCT_TABLE_ITEM_NAME_COLUMN = "item_name";
    public static final String PRODUCT_TABLE_ITEM_PRICE_COLUMN = "price";
    public static final String PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN = "expiration_date";
    public static final String PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN = "current_amount";
    public static final String PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN = "required_amount";
    public static final String PRODUCT_TABLE_ITEM_PROVIDER_COLUMN = "provider";
    public static final String PRODUCT_TABLE_ITEM_CATEGORY_COLUMN = "category";

    //Category table//
    public static final String CATEGORIES_TABLE_CATEGORY_NAME_COLUMN = "category_name";
    public static final String CATEGORIES_TABLE_CATEGORY_ID_COLUMN = "id";

    //Provider table//
    public static final String PROVIDER_TABLE_PROVIDER_ID_COLUMN = "id";
    public static final String PROVIDER_TABLE_COMPANY_NAME_COLUMN = "company_name";
    public static final String PROVIDER_TABLE_CONTACT_NAME_COLUMN = "contact_name";
    public static final String PROVIDER_TABLE_PRODUCT_TYPE_COLUMN = "product_type";

    //Orders table//
    public static final String ORDERS_TABLE_ORDER_ID_COLUMN = "id";
    public static final String ORDERS_TABLE_PRODUCT_TYPE_COLUMN = "product_type";
    public static final String ORDERS_TABLE_PROVIDER_COLUMN = "provider";
    public static final String ORDERS_TABLE_DELIVERY_DATE_COLUMN = "delivery_date";
    public static final String ORDERS_TABLE_TOTAL_AMOUNT_COLUMN = "total_amount";
}
