package org.example.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;


public class RegisterPage {

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

    public void fillFirstName(String firstName) {
        page.locator("#firstname").fill(firstName);
    }

    public void fillLastName(String lastName) {
        page.locator("#lastname").fill(lastName);
    }

    public void fillEmail(String email) {
        page.locator("#email_address").fill(email);
    }

    public void fillPassword(String password) {
        page.locator("#password").fill(password);
        page.locator("#confirmation").fill(password);
    }

    public void submitRegistration() {
//       this.page.locator(".buttons-set button").click();
        // OR - either way works
       page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register")).click();
    }

    @Test
    public void registerAlreadyRegisteredUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user

        nav.navigateToRegisterPage();
        fillFirstName(testUser.getFirstName());
        fillLastName(testUser.getLastName());
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitRegistration();

        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }

    @Test
    public void RegisterInvalidUserTest() {
        nav.navigateToRegisterPage();
        submitRegistration();
        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }

    @Test
    public void registerValidUserTest() {
        User testUser = new User(); // Create a random user

        nav.navigateToRegisterPage();
        fillFirstName(testUser.getFirstName());
        fillLastName(testUser.getLastName());
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitRegistration();

        assertThat(nav.getPage()).hasTitle("My Account");
        nav.logOutUser();
        assertThat(nav.getPage()).hasTitle("Madison Island");
    }

}
