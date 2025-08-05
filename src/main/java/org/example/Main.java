package org.example;
import com.microsoft.playwright.*;
public class Main {
    public static void main(String[] args) {

        // Register test
        Navigation nav = new Navigation(400);
        RegisterPage registerPage = new RegisterPage(nav.getPage());
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a random user

//        nav.navigateToRegisterPage();
//        registerPage.fillFirstName(testUser.getFirstName());
//        registerPage.fillLastName(testUser.getLastName());
//        registerPage.fillEmail(testUser.getEmail());
//        registerPage.fillPassword(testUser.getPassword());
//        registerPage.submitRegistration();
//
//        nav.logOutUser();

        // Login test
        LoginPage loginPage = new LoginPage(nav.getPage());
        nav.navigateToLoginPage();
        loginPage.fillEmail(testUser.getEmail());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.submitLogin();

        // Add to wishlist
        nav.navigateToChelseeTee();
        nav.getPage().locator("#product_addtocart_form > div.product-shop > div.product-options-bottom > ul.add-to-links > li:nth-child(1) > a").click();

        // Smoke Test
        Checkout checkoutTest = new Checkout(nav.getPage());
        nav.navigateHomeQa2();




    }
}