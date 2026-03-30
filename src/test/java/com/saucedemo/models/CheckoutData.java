package com.saucedemo.models;

import java.util.Map;

public class CheckoutData {

    private final String firstName;
    private final String lastName;
    private final String zipCode;

    public CheckoutData(String firstName, String lastName, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    public static CheckoutData fromMap(Map<String, String> values) {
        return new CheckoutData(values.get("firstName"), values.get("lastName"), values.get("zipCode"));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipCode() {
        return zipCode;
    }
}
