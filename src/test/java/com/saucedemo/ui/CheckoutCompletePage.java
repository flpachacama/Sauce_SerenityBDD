package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutCompletePage {

    public static final Target COMPLETE_HEADER = Target.the("mensaje de compra finalizada")
            .locatedBy(".complete-header");

    private CheckoutCompletePage() {
    }
}
