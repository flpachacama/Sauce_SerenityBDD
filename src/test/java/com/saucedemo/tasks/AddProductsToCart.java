package com.saucedemo.tasks;

import com.saucedemo.ui.InventoryPage;
import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class AddProductsToCart implements Task {

    private final List<String> productNames;

    public AddProductsToCart(List<String> productNames) {
        this.productNames = productNames;
    }

    public static AddProductsToCart named(List<String> productNames) {
        return Tasks.instrumented(AddProductsToCart.class, productNames);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String productName : productNames) {
            actor.attemptsTo(Click.on(InventoryPage.addToCartButtonFor(productName)));
        }
    }
}
