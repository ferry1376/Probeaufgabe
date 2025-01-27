package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutDonePage {
    private WebDriver driver;

    // Locators for the elements using provided XPaths
    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/span")
    private WebElement checkoutCompleteHeader;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/h2")
    private WebElement thankYouMessage;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div")
    private WebElement orderDispatchedMessage;

    @FindBy(xpath = "//*[@id='back-to-products']")
    private WebElement backHomeButton;

    // Constructor
    public CheckoutDonePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to validate the elements on the page
    public boolean isCheckoutComplete() {
        try {
            return checkoutCompleteHeader.isDisplayed() &&
                    thankYouMessage.isDisplayed() &&
                    orderDispatchedMessage.isDisplayed() &&
                    backHomeButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("Error while verifying checkout completion: " + e.getMessage());
            return false; // Return false if any element is not found
        }
    }
}
