package org.example;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegisterPage {

    private Page page;

    public RegisterPage(Page page) {
        this.page = page;
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

}
