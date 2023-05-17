package com.dm.customer.util;

/**
 * APIRoute for url
 */
public class APIRoute {

    public static final String CUSTOMER_DATA_POST = "/customer/data/post";
    public static final String CUSTOMER_DATA_GET = "/customer/data/get";
    public static final String CUSTOMER_DATA_SEARCH = "/customer/data/search";
    public static final String CUSTOMER_DATA_UPDATE = "/customer/data/update";
    public static final String CUSTOMER_DATA_DELETE = "/customer/data/delete";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_REGISTER = "/user/register";
    private APIRoute() {
        throw new IllegalStateException("Utility class");
    }
}
