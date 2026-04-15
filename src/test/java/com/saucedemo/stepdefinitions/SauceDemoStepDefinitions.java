package com.saucedemo.stepdefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import com.saucedemo.models.CheckoutData;
import com.saucedemo.questions.CarritoVacio;
import com.saucedemo.questions.CartBadgeCount;
import com.saucedemo.questions.MensajeErrorLogin;
import com.saucedemo.questions.OrderConfirmationMessage;
import com.saucedemo.questions.ProductoNoDisponible;
import com.saucedemo.questions.ProductsInCart;
import com.saucedemo.tasks.AgregarProductoInexistente;
import com.saucedemo.tasks.AddProductsToCart;
import com.saucedemo.tasks.FillCheckoutInformation;
import com.saucedemo.tasks.FinishCheckout;
import com.saucedemo.tasks.IntentarCheckoutSinProductos;
import com.saucedemo.tasks.LoginFallido;
import com.saucedemo.tasks.LoginWithCredentials;
import com.saucedemo.tasks.OpenCart;
import com.saucedemo.tasks.OpenSauceDemo;
import com.saucedemo.tasks.StartCheckout;
import com.saucedemo.utils.ExpectedMessages;
import com.saucedemo.utils.TestDataRepository;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.saucedemo.ui.CartPage;
import com.saucedemo.ui.InventoryPage;
import com.saucedemo.ui.LoginPage;

public class SauceDemoStepDefinitions {

    private Actor comprador;
    private List<String> selectedProducts;
    private WebDriver driver;
    private String lastProduct;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=PasswordCheck,PasswordLeakDetection");

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        OnStage.setTheStage(new OnlineCast());
        comprador = OnStage.theActorCalled("Comprador");
        comprador.can(BrowseTheWeb.with(driver));
        selectedProducts = new ArrayList<>();
        lastProduct = "";
    }

    @Given("el usuario abre la pagina de SauceDemo")
    public void openSauceDemo() {
        comprador.attemptsTo(OpenSauceDemo.homePage());
    }

    @When("inicia sesion con {string} y {string}")
    public void loginWithCredentials(String username, String password) {
        boolean successfulLogin = TestDataRepository.isExpectedSuccessfulLogin(username, password);
        if (successfulLogin) {
            comprador.attemptsTo(LoginWithCredentials.using(username, password));
        } else {
            comprador.attemptsTo(LoginFallido.using(username, password));
        }
    }

    @When("agrega {string} al carrito")
    public void addProduct(String productName) {
        lastProduct = productName;
        if ("no_aplica".equalsIgnoreCase(productName) || !isInInventory()) {
            return;
        }

        boolean configuredAsAvailable = TestDataRepository.isProductAvailable(productName);
        boolean notPresentInUi = comprador.asksFor(ProductoNoDisponible.named(productName));

        if (!configuredAsAvailable || notPresentInUi) {
            comprador.attemptsTo(AgregarProductoInexistente.named(productName));
            selectedProducts.clear();
            return;
        }

        selectedProducts = List.of(productName);
        comprador.attemptsTo(AddProductsToCart.named(selectedProducts));
    }

    @When("visualiza el carrito")
    public void openCart() {
        if (isInInventory()) {
            comprador.attemptsTo(OpenCart.fromHeader());
        }
    }

    @When("completa checkout usando {string}")
    public void completeCheckout(String checkoutId) {
        if ("no_aplica".equalsIgnoreCase(checkoutId)) {
            return;
        }

        if (selectedProducts.isEmpty()) {
            if (isInInventory()) {
                comprador.attemptsTo(OpenCart.fromHeader());
            }
            comprador.attemptsTo(IntentarCheckoutSinProductos.now());
            return;
        }

        CheckoutData checkoutData = TestDataRepository.checkoutById(checkoutId);
        if (isInInventory()) {
            comprador.attemptsTo(OpenCart.fromHeader());
        }
        comprador.attemptsTo(
                StartCheckout.now(),
                FillCheckoutInformation.withData(checkoutData),
                FinishCheckout.now()
        );
    }

    @Then("valida {string}")
    public void validateResult(String expectedResult) {
        switch (expectedResult.toLowerCase()) {
            case "success":
                comprador.should(seeThat(
                        "usuario en inventario",
                        actor -> !InventoryPage.INVENTORY_TITLE.resolveAllFor(actor).isEmpty(),
                        equalTo(true)
                ));
                break;
            case "error_login":
                String loginError = comprador.asksFor(MensajeErrorLogin.text());
                if (loginError == null || loginError.trim().isEmpty()) {
                    comprador.should(seeThat(
                            "permanece en pagina de login",
                            actor -> !LoginPage.USERNAME_FIELD.resolveAllFor(actor).isEmpty(),
                            equalTo(true)
                    ));
                } else {
                    comprador.should(seeThat(
                            MensajeErrorLogin.text(),
                            containsStringIgnoringCase(ExpectedMessages.LOGIN_ERROR_PREFIX)
                    ));
                }
                break;
            case "agregado":
                comprador.should(seeThat(CartBadgeCount.value(), equalTo(selectedProducts.size())));
                break;
            case "producto_no_existe":
                comprador.should(seeThat(ProductoNoDisponible.named(lastProduct), equalTo(true)));
                break;
            case "carrito_con_items":
                comprador.should(seeThat(CarritoVacio.value(), equalTo(false)));
                comprador.should(seeThat(ProductsInCart.names(), hasItem(lastProduct)));
                break;
            case "carrito_vacio":
                comprador.should(seeThat(CarritoVacio.value(), equalTo(true)));
                break;
            case "compra_exitosa":
                comprador.should(seeThat(
                        OrderConfirmationMessage.text(),
                        containsStringIgnoringCase(ExpectedMessages.ORDER_SUCCESS)
                ));
                break;
            case "checkout_bloqueado":
                comprador.should(seeThat(CarritoVacio.value(), equalTo(true)));
                comprador.should(seeThat(
                        "checkout disponible pero condicionado por datos",
                        actor -> !CartPage.CHECKOUT_BUTTON.resolveAllFor(actor).isEmpty(),
                        equalTo(true)
                ));
                break;
            case "no_aplica":
                break;
            default:
                throw new IllegalArgumentException("Resultado no soportado: " + expectedResult);
        }
    }

    private boolean isInInventory() {
        return !InventoryPage.INVENTORY_TITLE.resolveAllFor(comprador).isEmpty();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
