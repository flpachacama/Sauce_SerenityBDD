package com.saucedemo.questions;

import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ProductoNoDisponible implements Question<Boolean> {

    private final String productName;

    public ProductoNoDisponible(String productName) {
        this.productName = productName;
    }

    public static ProductoNoDisponible named(String productName) {
        return new ProductoNoDisponible(productName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return InventoryPage.addToCartButtonFor(productName).resolveAllFor(actor).isEmpty();
    }
}
