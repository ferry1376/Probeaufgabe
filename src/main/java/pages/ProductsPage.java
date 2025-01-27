package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class ProductsPage {
    private WebDriver driver;

    @FindBy(css = ".inventory_item .btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    private List<WebElement> removeButtons;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement menuButton; // Menu button

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;




    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addAllProductsToCart() {
        for (WebElement button : addToCartButtons) {
            button.click();
        }
    }

    public boolean validateRemoveButtons() {
        boolean allButtonsWork = true;

        for (WebElement button : removeButtons) {
            try {
                button.click();
                if (button.isDisplayed()) {
                    allButtonsWork = false; // Button bleibt sichtbar
                }
            } catch (Exception e) {
                allButtonsWork = false;
            }
        }
        return allButtonsWork;
    }

    public void addFirstProductToCart() {
        if (!addToCartButtons.isEmpty()) {
            addToCartButtons.get(0).click();
        } else {
            throw new IllegalStateException("Keine Add-to-Cart-Buttons gefunden!");
        }
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }

    public boolean isProductInCart() {
        return cartBadge.isDisplayed();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public void clickFirstAvailableAddToCartButton() {
        for (WebElement button : addToCartButtons) {
            if (button.isDisplayed() && button.isEnabled()) {
                button.click();
                System.out.println("Clicked on an available 'Add to Cart' button.");
                return; // Exit the loop after clicking
            }
        }
        throw new IllegalStateException("No available 'Add to Cart' button found!");
    }

    public void clickMenuButton() {
        menuButton.click();
        System.out.println("Clicked on the Menu button.");
    }

    public void clickLogoutButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Warte, bis der Logout-Button sichtbar und klickbar ist
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

            // Klicke auf den Logout-Button mit JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", logoutButton);
            System.out.println("Clicked on the Logout button using JavaScript.");
        } catch (Exception e) {
            System.err.println("Failed to click the Logout button: " + e.getMessage());
            throw e;
        }
    }


}
