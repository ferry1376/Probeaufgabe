package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // WebElemente der Login-Seite
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    // Konstruktor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialisiert die WebElemente
    }

    public void open() {
        driver.get("https://www.saucedemo.com/"); // Ersetze die URL durch die tatsächliche URL deiner Login-Seite
    }

    // Methode, um Benutzernamen einzugeben
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    // Methode, um Passwort einzugeben
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Methode, um den Login-Button zu klicken
    public void clickLoginButton() {
        loginButton.click();
    }
}
