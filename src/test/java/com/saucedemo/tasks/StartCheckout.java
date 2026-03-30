package com.saucedemo.tasks;

import com.saucedemo.ui.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class StartCheckout implements Task {

    public static StartCheckout now() {
        return Tasks.instrumented(StartCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CartPage.CHECKOUT_BUTTON));
    }
}
