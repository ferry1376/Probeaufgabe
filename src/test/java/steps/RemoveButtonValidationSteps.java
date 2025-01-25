package com.example.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentReportManager;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import com.example.utils.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RemoveButtonValidationSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private ExtentTest test;

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        test = extent.createTest("Validate Remove Buttons for Products");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        test.info("Successfully logged in as: " + username);
    }

    @When("I add all products to the cart")
    public void iAddAllProductsToTheCart() {
        productsPage = new ProductsPage(driver);
        productsPage.addAllProductsToCart();
        test.info("All products added to the cart");
    }

    @When("I attempt to remove each product from the cart")
    public void iAttemptToRemoveEachProductFromTheCart() {
        productsPage = new ProductsPage(driver);

        if (productsPage.validateRemoveButtons()) {
            test.pass("All Remove buttons worked as expected");
        } else {
            String screenshotPath = TestUtils.takeScreenshot(driver, "FailedRemoveButtons");
            test.fail("Some Remove buttons did not function correctly")
                    .addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Then("the Remove buttons should function correctly")
    public void theRemoveButtonsShouldFunctionCorrectly() {
        test.info("Validation of Remove buttons completed.");

        // Browser schließen
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
