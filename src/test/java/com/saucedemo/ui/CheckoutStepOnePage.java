package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutStepOnePage {

    public static final Target FIRST_NAME_FIELD = Target.the("campo first name")
            .locatedBy("#first-name");

    public static final Target LAST_NAME_FIELD = Target.the("campo last name")
            .locatedBy("#last-name");

    public static final Target ZIP_CODE_FIELD = Target.the("campo zip code")
            .locatedBy("#postal-code");

    public static final Target CONTINUE_BUTTON = Target.the("boton continue")
            .locatedBy("#continue");

    private CheckoutStepOnePage() {
    }
}
