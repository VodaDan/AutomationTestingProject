package org.example.test;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginPage {

    private static Page page;
    private static Navigation nav;

    @BeforeAll
    public static void startSessions(){
        Navigation navi = new Navigation(150, true); // slowMo , headless true
        nav = navi;
        page = nav.getPage();
    }

    @AfterAll
    public static void closeSession(){
        nav.navigateClose();
    }

    public void fillEmail(String email) {
        page.locator("#email").fill(email);
    }

    public void fillPassword(String password) {
        page.locator("#pass").fill(password);
    }

    public void submitLogin(){
        page.locator("#send2").click();
    }

    @Test
    public void loginValidUserTest(){
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user

        nav.navigateToLoginPage();
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitLogin();
    }
}
