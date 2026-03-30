package com.saucedemo.questions;

import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CartBadgeCount implements Question<Integer> {

    public static CartBadgeCount value() {
        return new CartBadgeCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        String badge = Text.of(InventoryPage.CART_BADGE).answeredBy(actor);
        if (badge == null || badge.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badge.trim());
    }
}
