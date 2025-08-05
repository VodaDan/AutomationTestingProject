package org.example;
import com.microsoft.playwright.*;
public class Main {
    public static void main(String[] args) {

        // Register test
        Navigation nav = new Navigation(400);
        RegisterPage registerPage = new RegisterPage(nav.getPage());
        User testUser = new User(); // Create a random user

        nav.navigateToRegisterPage();
        registerPage.fillFirstName(testUser.getFirstName());
        registerPage.fillLastName(testUser.getLastName());
        registerPage.fillEmail(testUser.getEmail());
        registerPage.fillPassword(testUser.getPassword());
        registerPage.submitRegistration();

        // Login test
        LoginPage loginPage = new LoginPage(nav.getPage());
        nav.logOutUser();
        nav.navigateToLoginPage();

        loginPage.fillEmail(testUser.getEmail());
        loginPage.fillPassword(testUser.getPassword());





    }
}