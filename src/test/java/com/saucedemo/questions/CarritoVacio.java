package com.saucedemo.questions;

import com.saucedemo.ui.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CarritoVacio implements Question<Boolean> {

    public static CarritoVacio value() {
        return new CarritoVacio();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return CartPage.PRODUCT_NAMES.resolveAllFor(actor).isEmpty();
    }
}
