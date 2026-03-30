package com.saucedemo.stepdefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import com.saucedemo.models.CheckoutData;
import com.saucedemo.questions.CartBadgeCount;
import com.saucedemo.questions.OrderConfirmationMessage;
import com.saucedemo.questions.ProductsInCart;
import com.saucedemo.tasks.AddProductsToCart;
import com.saucedemo.tasks.FillCheckoutInformation;
import com.saucedemo.tasks.FinishCheckout;
import com.saucedemo.tasks.LoginWithCredentials;
import com.saucedemo.tasks.OpenCart;
import com.saucedemo.tasks.OpenSauceDemo;
import com.saucedemo.tasks.StartCheckout;
import com.saucedemo.utils.ExpectedMessages;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Duration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceDemoStepDefinitions {

    private Actor comprador;
    private List<String> selectedProducts;
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        OnStage.setTheStage(new OnlineCast());
        comprador = OnStage.theActorCalled("Comprador");
        comprador.can(BrowseTheWeb.with(driver));
        selectedProducts = new ArrayList<>();
    }

    @Given("el comprador abre el portal de SauceDemo")
    public void openSauceDemo() {
        comprador.attemptsTo(OpenSauceDemo.homePage());
    }

    @When("inicia sesion con usuario {string} y clave {string}")
    public void loginWithCredentials(String username, String password) {
        comprador.attemptsTo(LoginWithCredentials.using(username, password));
    }

    @When("agrega los siguientes productos al carrito:")
    public void addProducts(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        selectedProducts = rows.stream()
                .map(row -> row.get("producto"))
                .collect(Collectors.toList());

        comprador.attemptsTo(AddProductsToCart.named(selectedProducts));
        comprador.should(seeThat(CartBadgeCount.value(), equalTo(selectedProducts.size())));
    }

    @When("visualiza el carrito de compras")
    public void openCart() {
        comprador.attemptsTo(OpenCart.fromHeader());
        comprador.should(seeThat(ProductsInCart.names(), 
                containsInAnyOrder(selectedProducts.toArray(new String[0]))));
    }

    @When("completa el checkout con los datos:")
    public void fillCheckout(DataTable dataTable) {
        Map<String, String> checkoutMap = dataTable.asMaps(String.class, String.class).get(0);
        CheckoutData checkoutData = CheckoutData.fromMap(checkoutMap);

        comprador.attemptsTo(
                StartCheckout.now(),
                FillCheckoutInformation.withData(checkoutData)
        );
    }

    @When("finaliza la compra")
    public void finishCheckout() {
        comprador.attemptsTo(FinishCheckout.now());
    }

    @Then("debe ver el mensaje de confirmacion {string}")
    public void shouldSeeConfirmation(String expectedMessage) {
        comprador.should(seeThat(OrderConfirmationMessage.text(), equalTo(expectedMessage)));
        comprador.should(seeThat(OrderConfirmationMessage.text(), equalTo(ExpectedMessages.ORDER_SUCCESS)));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
