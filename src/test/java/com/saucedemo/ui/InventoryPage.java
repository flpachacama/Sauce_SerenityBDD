package com.saucedemo.ui;

import com.saucedemo.utils.ProductNameFormatter;
import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    private static final String ADD_TO_CART_BUTTON_BY_SLUG = "css:button[data-test='add-to-cart-%s']";

    public static final Target CART_LINK = Target.the("icono del carrito")
            .locatedBy(".shopping_cart_link");

    public static final Target CART_BADGE = Target.the("contador del carrito")
            .locatedBy(".shopping_cart_badge");

    public static final Target INVENTORY_TITLE = Target.the("titulo de inventario")
            .locatedBy(".title");

    private InventoryPage() {
    }

    public static Target addToCartButtonFor(String productName) {
        String slug = ProductNameFormatter.toSauceDemoSlug(productName);
        return Target.the("boton agregar al carrito para " + productName)
                .locatedBy(String.format(ADD_TO_CART_BUTTON_BY_SLUG, slug));
    }
}
