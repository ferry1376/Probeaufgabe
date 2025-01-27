package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for the Finish button
    private By finishButton = By.id("finish"); // Replace with the actual ID or locator of the finish button

    // Method to click on the Finish button
    public void clickFinish() {
        WebElement button = driver.findElement(finishButton);
        button.click();
    }
}