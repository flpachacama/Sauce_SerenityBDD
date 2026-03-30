package com.saucedemo.tasks;

import com.saucedemo.models.CheckoutData;
import com.saucedemo.ui.CheckoutStepOnePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class FillCheckoutInformation implements Task {

    private final CheckoutData checkoutData;

    public FillCheckoutInformation(CheckoutData checkoutData) {
        this.checkoutData = checkoutData;
    }

    public static FillCheckoutInformation withData(CheckoutData checkoutData) {
        return Tasks.instrumented(FillCheckoutInformation.class, checkoutData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(checkoutData.getFirstName()).into(CheckoutStepOnePage.FIRST_NAME_FIELD),
                Enter.theValue(checkoutData.getLastName()).into(CheckoutStepOnePage.LAST_NAME_FIELD),
                Enter.theValue(checkoutData.getZipCode()).into(CheckoutStepOnePage.ZIP_CODE_FIELD),
                Click.on(CheckoutStepOnePage.CONTINUE_BUTTON)
        );
    }
}
