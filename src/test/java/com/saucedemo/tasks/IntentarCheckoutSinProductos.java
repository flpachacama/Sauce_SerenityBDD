package com.saucedemo.tasks;

import com.saucedemo.ui.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class IntentarCheckoutSinProductos implements Task {

    public static IntentarCheckoutSinProductos now() {
        return Tasks.instrumented(IntentarCheckoutSinProductos.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (!CartPage.PRODUCT_NAMES.resolveAllFor(actor).isEmpty()) {
            actor.attemptsTo(Click.on(CartPage.CHECKOUT_BUTTON));
        }
    }
}
