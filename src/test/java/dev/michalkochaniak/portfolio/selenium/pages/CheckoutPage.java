package dev.michalkochaniak.portfolio.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private final By panel = By.id("checkout-panel");
    private final By payButton = By.id("pay-button");
    private final By status = By.id("checkout-status");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isVisible() {
        return driver.findElement(panel).isDisplayed();
    }

    public CheckoutPage approvePurchase() {
        driver.findElement(payButton).click();
        return this;
    }

    public String status() {
        return driver.findElement(status).getText();
    }
}
