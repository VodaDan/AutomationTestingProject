package pages;


import models.User;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;



public class RegisterPage {

    private Page page;
    private String firstNameSelector;
    private String lastNameSelector;
    private String emailSelector;
    private String passwordSelector;
    private String registerButtonSelector;
    private String confirmationPasswordSelector;
    private String middleNameSelector;

    public RegisterPage(Page pageSent) {
        page = pageSent;
        firstNameSelector = "#firstname";
        lastNameSelector = "#lastname";
        emailSelector = "#email_address";
        passwordSelector = "#password";
        confirmationPasswordSelector = "#confirmation";
        registerButtonSelector = "#";
        middleNameSelector ="#middlename";
    }

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

    public String getFirstNameSelector() {
        return firstNameSelector;
    }

    public void setFirstNameSelector(String firstNameSelector) {
        this.firstNameSelector = firstNameSelector;
    }

    public String getLastNameSelector() {
        return lastNameSelector;
    }

    public void setLastNameSelector(String lastNameSelector) {
        this.lastNameSelector = lastNameSelector;
    }

    public String getEmailSelector() {
        return emailSelector;
    }

    public void setEmailSelector(String emailSelector) {
        this.emailSelector = emailSelector;
    }

    public String getPasswordSelector() {
        return passwordSelector;
    }

    public void setPasswordSelector(String passwordSelector) {
        this.passwordSelector = passwordSelector;
    }

    public String getRegisterButtonSelector() {
        return registerButtonSelector;
    }

    public void setRegisterButtonSelector(String registerButtonSelector) {
        this.registerButtonSelector = registerButtonSelector;
    }

    public String getConfirmationPasswordSelector() {
        return confirmationPasswordSelector;
    }

    public void setConfirmationPasswordSelector(String confirmationPasswordSelector) {
        this.confirmationPasswordSelector = confirmationPasswordSelector;
    }

    public String getMiddleNameSelector() {
        return middleNameSelector;
    }

    public void setMiddleNameSelector(String middleNameSelector) {
        this.middleNameSelector = middleNameSelector;
    }
}
