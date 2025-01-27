package com.example.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentReportManager;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ExtentTest test;
    private static ExtentReports extent = ExtentReportManager.getReporter();

    @Given("the user is on the products page after login")
    public void theUserIsOnTheProductsPageAfterLogin() {
        test = extent.createTest("Logout Test");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();

        // Navigate to login page and log in
        driver.get("https://www.saucedemo.com/");
        test.info("Navigated to login page");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        test.info("Logged in as standard_user");
        System.out.println("Step executed: User is on the products page after login");
    }

    @When("the user clicks the menu button")
    public void theUserClicksTheMenuButton() {
        productsPage.clickMenuButton();
        test.info("Clicked the menu button");
        System.out.println("Step executed: User clicked the menu button");
    }

    @And("the user clicks the logout button")
    public void theUserClicksTheLogoutButton() {
        productsPage.clickLogoutButton();
        test.info("Clicked the logout button");
        System.out.println("Step executed: User clicked the logout button");
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.equals("https://www.saucedemo.com/")) {
            test.pass("User successfully redirected to the login page");
            System.out.println("Step passed: User successfully redirected to the login page");
        } else {
            test.fail("User was not redirected to the login page. Current URL: " + currentUrl);
            System.out.println("Step failed: User was not redirected to the login page. Current URL: " + currentUrl);
        }

        driver.quit();
    }
}
