package com.saucedemo.questions;

import com.saucedemo.ui.CheckoutCompletePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class OrderConfirmationMessage implements Question<String> {

    public static OrderConfirmationMessage text() {
        return new OrderConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CheckoutCompletePage.COMPLETE_HEADER).answeredBy(actor);
    }
}
