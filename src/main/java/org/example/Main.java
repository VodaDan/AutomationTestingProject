package org.example;
import com.microsoft.playwright.*;
public class Main {
    public static void main(String[] args) {

        // Register test
        Navigation nav = new Navigation(400);
        RegisterPage registerPage = new RegisterPage(nav.getPage());
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a random user

        nav.navigateToRegisterPage();
        registerPage.fillFirstName(testUser.getFirstName());
        registerPage.fillLastName(testUser.getLastName());
        registerPage.fillEmail(testUser.getEmail());
        registerPage.fillPassword(testUser.getPassword());
        registerPage.submitRegistration();
//
//        nav.logOutUser();
//
//        // Login test
//        LoginPage loginPage = new LoginPage(nav.getPage());
//        nav.navigateToLoginPage();
//        loginPage.fillEmail(testUser.getEmail());
//        loginPage.fillPassword(testUser.getPassword());
//        loginPage.submitLogin();
//
//        // Add to wishlist

                /** Need to be logged in prior */

//        nav.navigateToChelseeTee();
//        nav.getPage().locator("a.link-wishlist").click();
//
//        // Smoke Test
//        Checkout checkoutTest = new Checkout(nav.getPage());
//        DeliveryAddress randomAddress = new DeliveryAddress();
//        nav.navigateHomeQa2();
//        checkoutTest.addProductToCart();
//        nav.navigateToCheckout();
//        checkoutTest.fillFirstName(randomAddress.getFirstName());
//        checkoutTest.fillEmail(randomAddress.getEmail());
//        checkoutTest.fillCity(randomAddress.getCity());
//        checkoutTest.fillZip(randomAddress.getZip());
//        checkoutTest.fillAddress(randomAddress.getAddress());
//        checkoutTest.fillPhone(randomAddress.getPhone());
//        checkoutTest.fillLastName(randomAddress.getFirstName());
//        nav.getPage().locator("#billing\\:region_id").selectOption("Alaska");
//        nav.getPage().locator("#billing-buttons-container > button").click();






    }
}