package tests;

import com.microsoft.playwright.*;
import models.DeliveryAddress;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Navigation;
import pages.OrderPage;
import pages.RegisterPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrderTest {

    private static Playwright playwright;
    private static Browser browser;
    private Page page;
    private OrderPage orderPage;
    private Navigation nav;

    @BeforeEach
    public void startSession () {
        Navigation nav = new Navigation(0);
        this.nav = nav;
        this.playwright = nav.getPlaywright();
        this.browser = nav.getBrowser();
        this.page = nav.getPage();
        orderPage = new OrderPage(page);
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    public void addProductToCartTest(){
        nav.navigateHomeQa2();
        orderPage.addProductToCart();  // the method adds 2 in qty field
        assertThat(page.locator("table#shopping-cart-table tbody tr")).hasCount(1);
        assertThat(page.locator("td.product-cart-actions > input[class='input-text qty']")).hasValue("2");
    }

    @Test
    public void removeProductFromCart() {
        nav.navigateHomeQa2();
        orderPage.addProductToCart();
        nav.navigateHomeQa2();
        nav.navigateToShoppingCart();
        page.locator("tr.first.odd td:last-child a").click();
        assertThat(page.locator("div.page-title h1")).hasText(Pattern.compile(".*shopping cart is empty.*",Pattern.CASE_INSENSITIVE));
    }

    @Test
    public void checkoutAddProductAndFinishOrder() {
        //        Smoke Test
        DeliveryAddress randomAddress = new DeliveryAddress();
        nav.navigateHomeQa2();
        orderPage.addProductToCart();
        nav.navigateToCheckout();
        orderPage.fillFirstName(randomAddress.getFirstName());
        orderPage.fillEmail(randomAddress.getEmail());
        orderPage.fillCity(randomAddress.getCity());
        orderPage.fillZip(randomAddress.getZip());
        orderPage.fillAddress(randomAddress.getAddress());
        orderPage.fillPhone(randomAddress.getPhone());
        orderPage.fillLastName(randomAddress.getFirstName());
        nav.getPage().locator(orderPage.getRegionSelector()).selectOption("Alaska");
        page.locator("label[for='billing:use_for_shipping_no']").click();
        nav.getPage().locator("#billing-buttons-container > button").click();

    }
}
