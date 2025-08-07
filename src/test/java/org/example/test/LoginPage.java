package org.example.test;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.regex.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private static Page page;
    private static Navigation nav;

    private static String emailSelector;
    private static String passwordSelector;
    private static String loginButtonSelector;

    @BeforeAll
    public static void startSessions() {
        Navigation navi = Navigation.getInstance(0,true); // slowMo , headless true
        nav = navi;
        page = nav.getPage();

        emailSelector = "#email";
        passwordSelector = "#pass";
        loginButtonSelector = "#send2";

    }

//    @AfterAll
//    public static void closeSession() {
//        nav.navigateClose();
//    }

    public void fillEmail(String email) {
        page.locator(emailSelector).fill(email);
    }

    public void fillPassword(String password) {
        page.locator(passwordSelector).fill(password);
    }

    public void submitLogin(){
        page.locator(loginButtonSelector).click();
    }

    @Test
    public void loginValidUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user
        nav.navigateToLoginPage();
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitLogin();
        assertThat(nav.getPage()).hasTitle("My Account");
        nav.logOutUser();
        assertThat(nav.getPage()).hasTitle("Madison Island");
    }

    @Test
    public void loginInvalidUserTest() {
        nav.navigateToLoginPage();
        submitLogin();
        assertThat(nav.getPage()).hasTitle("Customer Login");
    }

    @Test
    public void loginRegisteredUserWithInvalidEmailFormat(){
        User testUser = new User("Jon","Jon","Jon","user1234"); // email: Jon
        nav.navigateToLoginPage();
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitLogin();
        assertThat(nav.getPage()).hasTitle(Pattern.compile(".*login.*", Pattern.CASE_INSENSITIVE));
    }
}
