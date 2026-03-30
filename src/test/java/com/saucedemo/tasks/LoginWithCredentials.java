package com.saucedemo.tasks;

import com.saucedemo.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class LoginWithCredentials implements Task {

    private final String username;
    private final String password;

    public LoginWithCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static LoginWithCredentials using(String username, String password) {
        return Tasks.instrumented(LoginWithCredentials.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
