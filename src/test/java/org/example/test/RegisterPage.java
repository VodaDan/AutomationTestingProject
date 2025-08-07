package org.example.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


public class RegisterPage {

    private Page page;
    private Navigation nav;

    public RegisterPage() {
        Navigation nav = new Navigation(150);
        this.nav = nav;
        this.page = nav.getPage();
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
    public void registerUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a random user

        nav.navigateToRegisterPage();
        fillFirstName(testUser.getFirstName());
        fillLastName(testUser.getLastName());
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitRegistration();
        nav.navigateClose();
    }

}
