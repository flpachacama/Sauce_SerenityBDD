package com.saucedemo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucedemo.models.CheckoutData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class TestDataRepository {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static JsonNode loginData;
    private static JsonNode checkoutData;
    private static Map<String, Boolean> productAvailability;

    private TestDataRepository() {
    }

    public static boolean isExpectedSuccessfulLogin(String username, String password) {
        for (JsonNode node : readLoginData()) {
            boolean sameUser = username.equals(node.path("username").asText());
            boolean samePassword = password.equals(node.path("password").asText());
            if (sameUser && samePassword) {
                return "success".equalsIgnoreCase(node.path("resultado_login").asText());
            }
        }
        return false;
    }

    public static CheckoutData checkoutById(String id) {
        for (JsonNode node : readCheckoutData()) {
            if (id.equals(node.path("id").asText())) {
                return new CheckoutData(
                        node.path("firstName").asText(),
                        node.path("lastName").asText(),
                        node.path("zipCode").asText()
                );
            }
        }
        throw new IllegalArgumentException("No checkout data found for id: " + id);
    }

    public static boolean isProductAvailable(String productName) {
        return readProductAvailability().getOrDefault(productName, false);
    }

    private static JsonNode readLoginData() {
        if (loginData == null) {
            loginData = readJsonArray("data/login-data.json");
        }
        return loginData;
    }

    private static JsonNode readCheckoutData() {
        if (checkoutData == null) {
            checkoutData = readJsonArray("data/checkout-data.json");
        }
        return checkoutData;
    }

    private static Map<String, Boolean> readProductAvailability() {
        if (productAvailability == null) {
            productAvailability = readProductsCsv("data/products-data.csv");
        }
        return productAvailability;
    }

    private static JsonNode readJsonArray(String resourcePath) {
        try (InputStream input = openResource(resourcePath)) {
            return OBJECT_MAPPER.readTree(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot read resource: " + resourcePath, exception);
        }
    }

    private static Map<String, Boolean> readProductsCsv(String resourcePath) {
        Map<String, Boolean> values = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(openResource(resourcePath), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }
                String productName = parts[0].trim();
                boolean available = Boolean.parseBoolean(parts[1].trim());
                values.put(productName, available);
            }
            return values;
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot read resource: " + resourcePath, exception);
        }
    }

    private static InputStream openResource(String resourcePath) {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
        if (input == null) {
            throw new IllegalStateException("Resource not found: " + resourcePath);
        }
        return input;
    }
}
