package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutStepTwoPage {

    public static final Target FINISH_BUTTON = Target.the("boton finish")
            .locatedBy("#finish");

    private CheckoutStepTwoPage() {
    }
}
