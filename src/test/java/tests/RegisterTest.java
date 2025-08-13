package tests;
import pages.Navigation;
import models.User;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @Override
    @BeforeEach
    public void startSession () {
        super.startSession();
        registerPage = new RegisterPage(page);
    }

    @Test
    public void registerVerifyInputFieldAndButtonTest(){
        nav.navigateToRegisterPage();
        assertThat(page.locator(registerPage.getFirstNameSelector())).isEditable();
        assertThat(page.locator(registerPage.getLastNameSelector())).isEditable();
        assertThat(page.locator(registerPage.getEmailSelector())).isEditable();
        assertThat(page.locator(registerPage.getPasswordSelector())).isEditable();
        assertThat(page.locator(registerPage.getConfirmationPasswordSelector())).isEditable();
        assertThat(page.locator(registerPage.getMiddleNameSelector())).isEditable();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"))).isEnabled();

    }

    @Test
    public void registerAlreadyRegisteredUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user

        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);

        assertThat(page).hasTitle("Create New Customer Account");
    }

    @Test
    public void RegisterInvalidUserTest() {
        nav.navigateToRegisterPage();
        registerPage.submitRegistration();
        assertThat(page).hasTitle("Create New Customer Account");
    }


    @Test
    public void registerValidUserTest() {
        User testUser = new User(); // Create a random user
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);

        // Check page title to confirm registration
        assertThat(page).hasTitle("My Account");
        nav.logOutUser();

        // Check logout went successfully
        assertThat(page).hasTitle("Madison Island");

    }
    @Disabled("Fails due to bug #1234 - registration form doesn't show asterisk and the mandatory field is not appearing")
    @Test
    public void registerInvalidEmailTest(){
        User testUser = new User("Jon","Jon","Jon","user1234");
        nav.navigateHomeQa2();
        nav.navigateToRegisterPage();
        registerPage.fillRegistrationForm(testUser);
        Locator emailValidation = page.locator("input[type='email']+div.validation-advice");
        assertThat(emailValidation).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(1500));
    }
}
