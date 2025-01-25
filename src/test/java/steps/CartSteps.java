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

public class CartSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private ExtentTest test;

    @Given("I am logged into the application")
    public void iAmLoggedIntoTheApplication() {
        test = extent.createTest("Cart Functionality Test");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        test.info("Successfully logged in");
    }

    @When("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        test.info("Added the first product to the cart");
    }

    @Then("the cart should display the product")
    public void theCartShouldDisplayTheProduct() {
        if (productsPage.isProductInCart()) {
            test.pass("Product is successfully displayed in the cart");
        } else {
            String screenshotPath = TestUtils.takeScreenshot(driver, "CartFailure");
            test.fail("Product is not displayed in the cart")
                    .addScreenCaptureFromPath(screenshotPath);
        }

        // Browser schließen
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
