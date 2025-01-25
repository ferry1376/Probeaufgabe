package com.example.steps;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.ExtentReportManager;
import com.example.pages.LoginPage;
import com.example.utils.TestUtils;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private ExtentTest test;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        test = extent.createTest("Valid Login Test");
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        test.info("Navigated to login page");
    }

    @When("I try to log in with the following credentials:")
    public void iTryToLogInWithTheFollowingCredentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> credential : credentials) {
            String username = credential.get("username");
            String password = credential.get("password");

            // Login ausführen
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            // Validierung
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("inventory.html")) {
                test.pass("Login successful for user: " + username);
            } else {
                String screenshotPath = TestUtils.takeScreenshot(driver, "FailedLogin_" + username);
                test.fail("Login failed for user: " + username)
                        .addScreenCaptureFromPath(screenshotPath);
            }

            // Zurück zur Login-Seite navigieren (falls erforderlich)
            driver.navigate().to("https://www.saucedemo.com/");
        }

    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        // Abschließende Nachricht im Report hinzufügen
        test.info("All login tests completed.");

        // Browser schließen
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Bericht abschließen
    }

}
