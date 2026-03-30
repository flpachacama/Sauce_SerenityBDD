package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final Target PRODUCT_NAMES = Target.the("productos en el carrito")
            .locatedBy(".cart_item .inventory_item_name");

    public static final Target CHECKOUT_BUTTON = Target.the("boton checkout")
            .locatedBy("#checkout");

    private CartPage() {
    }
}
