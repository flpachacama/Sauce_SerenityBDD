package com.saucedemo.utils;

public final class ProductNameFormatter {

    private ProductNameFormatter() {
    }

    public static String toSauceDemoSlug(String productName) {
        return productName
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", "");
    }
}
