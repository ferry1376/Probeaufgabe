package com.example.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for input fields and continue button
    private By firstNameField = By.id("first-name"); // Replace with the actual ID of the first name field
    private By lastNameField = By.id("last-name");   // Replace with the actual ID of the last name field
    private By zipCodeField = By.id("postal-code");  // Replace with the actual ID of the zip/postal code field
    private By continueButton = By.id("continue");   // Replace with the actual ID of the continue button

    // Method to fill in the checkout information
    public void fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    // Method to click on the Continue button
    public void clickContinue() {
        WebElement button = driver.findElement(continueButton);
        button.click();
    }
}
