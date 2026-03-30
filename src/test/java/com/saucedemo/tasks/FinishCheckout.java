package com.saucedemo.tasks;

import com.saucedemo.ui.CheckoutStepTwoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class FinishCheckout implements Task {

    public static FinishCheckout now() {
        return Tasks.instrumented(FinishCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CheckoutStepTwoPage.FINISH_BUTTON));
    }
}
