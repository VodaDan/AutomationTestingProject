package org.example;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
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
}
