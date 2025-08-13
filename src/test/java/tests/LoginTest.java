package tests;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Override
    @BeforeEach
    public void startSession() {
        super.startSession();
        loginPage = new LoginPage(page);
    }

    @Test
    public void loginValidUserTest() {
        User testUser = new User("Jon","Jon","Jon@email.com","user1234"); // Create a registered user
        nav.navigateToLoginPage();
        loginPage.fillEmail(testUser.getEmail());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.submitLogin();
        this.page.locator("a[data-target-element='#header-account']").click();
        assertThat(page.locator("a[title='Log Out']")).isVisible();
        nav.logOutUser();
//        assertThat(nav.getPage()).hasTitle("Madison Island");
    }

    @Test
    public void loginInvalidUserTest() {
        nav.navigateToLoginPage();
        loginPage.submitLogin();
        assertThat(page).hasTitle("Customer Login");
    }

    @Test
    public void loginRegisteredUserWithInvalidEmailFormat(){
        User testUser = new User("Jon","Jon","Jon","user1234"); // email: Jon
        nav.navigateToLoginPage();
        loginPage.fillEmail(testUser.getEmail());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.submitLogin();
        assertThat(page).hasTitle(Pattern.compile(".*login.*", Pattern.CASE_INSENSITIVE));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/loginData.csv", numLinesToSkip = 1)
    @DisplayName("Login Submission test")
    public void loginParameterizedTest(String username, String password) {
        nav.navigateToLoginPage();
        loginPage.fillEmail(username.toString());
        loginPage.fillPassword(password.toString());
        loginPage.submitLogin();
        assertThat(page).hasTitle("Customer Login");
    }

}
