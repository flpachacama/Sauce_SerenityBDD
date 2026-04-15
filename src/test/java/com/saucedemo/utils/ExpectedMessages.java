package com.saucedemo.utils;

public final class ExpectedMessages {

    public static final String ORDER_SUCCESS = "THANK YOU FOR YOUR ORDER";
    public static final String LOGIN_ERROR_PREFIX = "Epic sadface:";
    public static final String LOGIN_INVALID_CREDENTIALS =
            "Epic sadface: Username and password do not match any user in this service";
    public static final String LOGIN_LOCKED_USER =
            "Epic sadface: Sorry, this user has been locked out.";

    private ExpectedMessages() {
    }
}
