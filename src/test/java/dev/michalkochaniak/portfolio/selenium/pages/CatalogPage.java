package dev.michalkochaniak.portfolio.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage {
    private final WebDriver driver;
    private final By panel = By.id("catalog-panel");
    private final By addPackageButton = By.id("add-package-button");
    private final By clearCartButton = By.id("clear-cart-button");
    private final By cartStatus = By.id("cart-status");

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isVisible() {
        return driver.findElement(panel).isDisplayed();
    }

    public CatalogPage addPackage() {
        driver.findElement(addPackageButton).click();
        return this;
    }

    public CatalogPage clearCart() {
        driver.findElement(clearCartButton).click();
        return this;
    }

    public String cartStatus() {
        return driver.findElement(cartStatus).getText();
    }
}
