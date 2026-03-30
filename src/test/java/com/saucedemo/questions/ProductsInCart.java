package com.saucedemo.questions;

import com.saucedemo.ui.CartPage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ProductsInCart implements Question<List<String>> {

    public static ProductsInCart names() {
        return new ProductsInCart();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        Collection<String> products = Text.ofEach(CartPage.PRODUCT_NAMES).answeredBy(actor);
        return new ArrayList<>(products);
    }
}
