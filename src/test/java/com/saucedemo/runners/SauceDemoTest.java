package com.saucedemo.runners;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/saucedemo/compra_e2e.feature",
        glue = "com.saucedemo.stepdefinitions",
        snippets = CAMELCASE
)
public class SauceDemoTest {
}
