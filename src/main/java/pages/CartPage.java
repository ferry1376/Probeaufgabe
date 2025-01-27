package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for the Checkout button
    private By checkoutButton = By.id("checkout"); // Replace with the actual ID or locator of the checkout button

    // Method to click on the Checkout button
    public void clickCheckout() {
        WebElement button = driver.findElement(checkoutButton);
        button.click();
    }
}
