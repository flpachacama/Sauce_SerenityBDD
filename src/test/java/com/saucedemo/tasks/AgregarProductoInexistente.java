package com.saucedemo.tasks;

import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class AgregarProductoInexistente implements Task {

    private final String productName;

    public AgregarProductoInexistente(String productName) {
        this.productName = productName;
    }

    public static AgregarProductoInexistente named(String productName) {
        return Tasks.instrumented(AgregarProductoInexistente.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // The task intentionally avoids click actions when the target product is unavailable.
        InventoryPage.addToCartButtonFor(productName).resolveAllFor(actor);
    }
}
