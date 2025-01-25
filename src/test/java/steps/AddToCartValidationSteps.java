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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCartValidationSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private ExtentTest test;

    @Given("I am logged in as {string} to validate all products")
    public void iAmLoggedInAsToValidateAllProducts(String username) {
        test = extent.createTest("Validate All Add to Cart Buttons");
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

    @When("I check all Add to Cart buttons on the products page")
    public void iCheckAllAddToCartButtonsOnTheProductsPage() {
        productsPage = new ProductsPage(driver);

        for (WebElement button : productsPage.getAddToCartButtons()) {
            try {
                button.click();
                test.pass("Add to Cart button clicked successfully.");
            } catch (Exception e) {
                String screenshotPath = TestUtils.takeScreenshot(driver, "FailedAddToCart");
                test.fail("Add to Cart button failed.")
                        .addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    @Then("all Add to Cart buttons should work correctly")
    public void allAddToCartButtonsShouldWorkCorrectly() {
        test.info("Validation of all Add to Cart buttons completed.");

        // Browser schließen
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
