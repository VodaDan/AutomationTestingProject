package tests;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.*;
import pages.LoginPage;
import pages.ProductPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class ProductTest extends BaseTest{

    private ProductPage productPage;

    @Override
    @BeforeEach
    public void startSession () {
        super.startSession();
        productPage = new ProductPage(page);
    }

    @Test
    public void addToWishListTest() {
        LoginPage loginPage = new LoginPage(page);
        User testUser = new User("Jon","Jon","Jon@email.com","user1234");

        nav.navigateToChelseeTee();
        productPage.addToWishlist();
        loginPage.fillEmail(testUser.getEmail());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.submitLogin();
        assertThat(page.locator("div.my-account div.page-title h1")).hasText("My Wishlist");
    }


}
