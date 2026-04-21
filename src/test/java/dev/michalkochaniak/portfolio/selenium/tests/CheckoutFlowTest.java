package dev.michalkochaniak.portfolio.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.michalkochaniak.portfolio.selenium.pages.CatalogPage;
import dev.michalkochaniak.portfolio.selenium.pages.CheckoutPage;
import dev.michalkochaniak.portfolio.selenium.pages.LoginPage;
import dev.michalkochaniak.portfolio.selenium.support.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckoutFlowTest extends BaseUiTest {
    @Test
    @DisplayName("invalid-login-shows-validation")
    void shouldShowValidationForInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);

        loginPage.loginAs("wrong-user", "wrong-password");

        assertTrue(loginPage.isErrorVisible());
    }

    @Test
    @DisplayName("successful-user-can-approve-purchase")
    void shouldAllowHappyPathPurchase() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);
        CatalogPage catalogPage = new CatalogPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.loginAs("portfolio", "quality-first");

        assertTrue(catalogPage.isVisible());
        assertTrue(checkoutPage.isVisible());

        catalogPage.addPackage();
        assertEquals("Items in cart: 1", catalogPage.cartStatus());

        checkoutPage.approvePurchase();
        assertEquals("Purchase approved successfully.", checkoutPage.status());
    }

    @Test
    @DisplayName("empty-cart-blocks-approval")
    void shouldBlockApprovalWhenCartIsEmpty() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);
        CatalogPage catalogPage = new CatalogPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.loginAs("portfolio", "quality-first");
        catalogPage.clearCart();
        checkoutPage.approvePurchase();

        assertEquals("Add a package before approval.", checkoutPage.status());
    }
}
