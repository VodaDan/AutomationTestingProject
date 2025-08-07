package org.example.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;


public class RegisterPage {

    private static Page page;
    private static Navigation nav;
    private static String firstNameSelector;
    private static String lastNameSelector;
    private static String emailSelector;
    private static String passwordSelector;
    private static String registerButtonSelector;
    private static String confirmationPasswordSelector;
    private static String middleNameSelector;


    @BeforeAll
    public static void startSessions() {
        Navigation navi = Navigation.getInstance(150,true); // slowMo , headless true
        nav = navi;
        page = nav.getPage();

        firstNameSelector = "#firstname";
        lastNameSelector = "#lastname";
        emailSelector = "#email_address";
        passwordSelector = "#password";
        confirmationPasswordSelector = "#confirmation";
        registerButtonSelector = "#";
        middleNameSelector ="#middlename";

    }
//
//    @AfterAll
//    public static void closeSession() {
//        nav.navigateClose();
//    }

    public void fillFirstName(String firstName) {
        page.locator(firstNameSelector).fill(firstName);
    }

    public void fillLastName(String lastName) {
        page.locator(lastNameSelector).fill(lastName);
    }

    public void fillEmail(String email) {
        page.locator(emailSelector).fill(email);
    }

    public void fillPassword(String password) {
        page.locator(passwordSelector).fill(password);
        page.locator(confirmationPasswordSelector).fill(password);
    }

    public void submitRegistration() {
//       this.page.locator(".buttons-set button").click();
        // OR - either way works
       page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register")).click();
    }

    public void fillRegistrationForm(User testUser){
        fillFirstName(testUser.getFirstName());
        fillLastName(testUser.getLastName());
        fillEmail(testUser.getEmail());
        fillPassword(testUser.getPassword());
        submitRegistration();
    }
    @Test
    public void registerVerifyInputFieldAndButtonTest(){
        nav.navigateToRegisterPage();
        assertThat(nav.getPage().locator(firstNameSelector)).isEditable();
        assertThat(nav.getPage().locator(lastNameSelector)).isEditable();
        assertThat(nav.getPage().locator(emailSelector)).isEditable();
        assertThat(nav.getPage().locator(passwordSelector)).isEditable();
        assertThat(nav.getPage().locator(confirmationPasswordSelector)).isEditable();
        assertThat(nav.getPage().locator(middleNameSelector)).isEditable();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isEnabled();

    }

    @Test
    public void registerAlreadyRegisteredUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user

        nav.navigateToRegisterPage();
        fillRegistrationForm(testUser);

        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }

    @Test
    public void RegisterInvalidUserTest() {
        nav.navigateToRegisterPage();
        submitRegistration();
        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }

    @Ignore
    public void registerValidUserTest() {
        User testUser = new User(); // Create a random user
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        fillRegistrationForm(testUser);

        assertThat(nav.getPage()).hasTitle("My Account");
        nav.logOutUser();
        assertThat(nav.getPage()).hasTitle("Madison Island");
    }

    @Test // Test fails, the message is not shown, retest after fix (if ever)
    public void registerInvalidEmailTest(){
        User testUser = new User("Jon","Jon","Jon","user1234");
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        fillRegistrationForm(testUser);
        Locator emailValidation = nav.getPage().locator("input[type='email']+div.validation-advice");
        assertThat(emailValidation).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(1500));
    }

}
