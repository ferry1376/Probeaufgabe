package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    @FindBy(css = ".inventory_item .btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    private List<WebElement> removeButtons;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

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
}
