package com.example.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentReportManager;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class FilterSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private ExtentTest test;

    @Given("I am on the inventory page")
    public void iAmOnTheInventoryPage() {
        test = extent.createTest("Filter Functionality Test - Inventory Page");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");

        // WebDriver initialisieren
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        // LoginPage und ProductsPage initialisieren
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        // Login ausführen
        try {
            loginPage.open();
            test.info("Login page opened");
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            test.info("Login performed with standard username and password");

            // Sicherstellen, dass die Inventarseite geladen ist
            Assertions.assertTrue(driver.getCurrentUrl().contains("inventory"),
                    "Inventory page did not load correctly.");
            test.pass(" Inventory page loaded successfully");
        } catch (Exception e) {
            test.fail("Error with opening the Inventory Page: " + e.getMessage());
            throw e;
        }
    }

    @When("I apply the {string} filter")
    public void iApplyTheFilter(String filterOption) {
        try {
            if (filterOption.equalsIgnoreCase("Price: Low to High")) {
                productsPage.applyPriceLowToHighFilter();
                test.info("Filter 'Price: Low to High' applied.");
            } else {
                String message = "Filter option not supported: " + filterOption;
                test.fail(message);
                throw new IllegalArgumentException(message);
            }
        } catch (Exception e) {
            test.fail("Error by applying the Filters: " + e.getMessage());
            throw e;
        }
    }

    @Then("the products should be sorted in ascending order of price")
    public void theProductsShouldBeSortedInAscendingOrderOfPrice() {
        try {
            boolean isSortedCorrectly = productsPage.isSortedByPriceLowToHigh();
            Assertions.assertTrue(isSortedCorrectly, "Products are not sorted by price from low to high.");
            test.pass(" Products are correctly sorted in ascending order of price.");
        } catch (AssertionError e) {
            test.fail("Products are not correctly sorted : " + e.getMessage());
            throw e;
        } finally {
            // Browser schließen
            if (driver != null) {
                driver.quit();
                test.info("Browser closed.");
            }
            extent.flush(); // Bericht abschließen
        }
    }
}
