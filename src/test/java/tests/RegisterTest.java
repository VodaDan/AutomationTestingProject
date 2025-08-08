package tests;
import pages.Navigation;
import models.User;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class RegisterTest {

    private static Playwright playwright;
    private static Browser browser;
    private Page page;
    private RegisterPage registerPage;
    private Navigation nav;

    @BeforeEach
    public void startSession () {
        Navigation nav = new Navigation(0);
        this.playwright = nav.getPlaywright();
        this.browser = nav.getBrowser();
        this.page = nav.getPage();
        registerPage = new RegisterPage(page);
        this.nav = nav;
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    public void registerVerifyInputFieldAndButtonTest(){
        nav.navigateToRegisterPage();
        assertThat(nav.getPage().locator(registerPage.getFirstNameSelector())).isEditable();
        assertThat(nav.getPage().locator(registerPage.getLastNameSelector())).isEditable();
        assertThat(nav.getPage().locator(registerPage.getEmailSelector())).isEditable();
        assertThat(nav.getPage().locator(registerPage.getPasswordSelector())).isEditable();
        assertThat(nav.getPage().locator(registerPage.getConfirmationPasswordSelector())).isEditable();
        assertThat(nav.getPage().locator(registerPage.getMiddleNameSelector())).isEditable();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isEnabled();

    }

    @Test
    public void registerAlreadyRegisteredUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user

        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);

        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }

    @Test
    public void RegisterInvalidUserTest() {
        nav.navigateToRegisterPage();
        registerPage.submitRegistration();
        assertThat(nav.getPage()).hasTitle("Create New Customer Account");
    }


    @Test
    public void registerValidUserTest() {
        User testUser = new User(); // Create a random user
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);

        // Check page title to confirm registration
        assertThat(nav.getPage()).hasTitle("My Account");
        nav.logOutUser();

        // Check logout went successfully
        assertThat(nav.getPage()).hasTitle("Madison Island");

    }
    @Disabled("Fails due to bug #1234 - registration form doesn't show asterisk and the mandatory field is not appearing")
    @Test
    public void registerInvalidEmailTest(){
        User testUser = new User("Jon","Jon","Jon","user1234");
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);
        Locator emailValidation = nav.getPage().locator("input[type='email']+div.validation-advice");
        assertThat(emailValidation).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(1500));
    }
}
