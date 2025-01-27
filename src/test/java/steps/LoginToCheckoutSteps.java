package com.example.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentReportManager;
import com.example.pages.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.example.utils.TestUtils;


public class LoginToCheckoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private ExtentTest test;
    private static ExtentReports extent = ExtentReportManager.getReporter();

    @Given("the user logs in with valid credentials {string} and {string}")
    public void theUserLogsInWithValidCredentials(String username, String password) {
        test = extent.createTest("Successful purchase process");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");

        // Set Chrome options to disable headless mode and enable browser visibility
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        // Navigate to the login page
        driver.get("https://www.saucedemo.com/");
        test.info("Navigated to login page");
        System.out.println("Step executed: Navigated to login page");

        // Initialize page objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);

        // Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        test.info("Successfully logged in as: " + username);
        System.out.println("Step executed: User logged in with username: " + username);
    }

    @And("the user adds an item to the cart")
    public void addFirstProductToCart() {
        productsPage.clickFirstAvailableAddToCartButton();
        test.info("Added the first available product to the cart.");
    }

    @And("the user views the cart")
    public void theUserViewsTheCart() {
        productsPage.clickCartIcon();
        test.info("Navigated to the cart page.");
    }

    @And("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        cartPage.clickCheckout();
        test.info("Proceeded to the checkout page.");
    }

    @And("the user enters the details {string} {string} {string}")
    public void theUserEntersTheDetails(String firstName, String lastName, String zipCode) {
        checkoutPage.fillCheckoutInformation(firstName, lastName, zipCode);
        checkoutPage.clickContinue();
        test.info("Entered checkout details: " + firstName + " " + lastName + ", " + zipCode);
    }

    @And("the user finishes the checkout")
    public void theUserFinishesTheCheckout() {
        checkoutOverviewPage.clickFinish();
        test.info("Finished the checkout process.");
    }

    @Then("the checkout should be complete")
    public void theCheckoutShouldBeComplete() {
        CheckoutDonePage checkoutDonePage = new CheckoutDonePage(driver);

        try {
            if (checkoutDonePage.isCheckoutComplete()) {
                test.pass("Checkout process completed successfully.");
                System.out.println("Checkout process completed successfully.");
            } else {
                String screenshotPath = TestUtils.takeScreenshot(driver, "CheckoutFailed");
                test.fail("Checkout process failed: Required elements are missing.")
                        .addScreenCaptureFromPath(screenshotPath);
                System.out.println("Checkout process failed: Required elements are missing.");
            }
        } catch (Exception e) {
            String screenshotPath = TestUtils.takeScreenshot(driver, "UnexpectedError");
            test.fail("An unexpected error occurred.")
                    .addScreenCaptureFromPath(screenshotPath);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the browser and flush the report
            driver.quit();
            extent.flush();
        }
    }
}
