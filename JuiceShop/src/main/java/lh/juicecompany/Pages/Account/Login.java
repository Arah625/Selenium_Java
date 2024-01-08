package lh.juicecompany.Pages.Account;

import lh.juicecompany.Pages.Google.SelectAccount;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends Home {


    //    @FindBy(xpath = "//span[contains(text(),'Registration completed successfully. You can now log in.')]")

    private static final By REGISTRATION_COMPLETED_ALERT_BY = By.xpath("//span[contains(text(),'Registration completed successfully. You can now log in.')]");
    @FindBy(xpath = "//h1[contains(text(),'Login')]")
    private WebElement loginModalHeader;
    @FindBy(xpath = "//div[contains(text(),'Invalid email or password.')]")
    private WebElement invalidEmailOrPasswordAlert;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='mat-error-0' and contains(text(),'Please provide an email address.')]")
    private WebElement emptyEmailFieldValidationAlert;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id='mat-error-1' and contains(text(),'Please provide a password.')]")
    private WebElement emptyPasswordFieldValidationAlert;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement loginButton;
    @FindBy(xpath = "//button[@id='loginButtonGoogle']")
    private WebElement loginWithGoogleAccountButton;
    @FindBy(xpath = "//input[@id='rememberMe-input']/..")
    private WebElement rememberMeCheckbox;
    @FindBy(xpath = "//input[@id='rememberMe-input']")
    private WebElement rememberMeCheckboxState;
    @FindBy(xpath = "//div[@id='newCustomerLink']")
    private WebElement registerButton;
//    private WebElement registrationCompletedAlert;

    public Login(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isInvalidEmailOrPasswordAlertVisible() {
        return isElementVisible(invalidEmailOrPasswordAlert);
    }

    public Login fillEmail(String emailAddress) {
        sendKeysToElement(emailField, emailAddress);
        return this;
    }

    public boolean isEmptyEmailFieldValidationAlertVisible() {
        return isElementVisible(emptyEmailFieldValidationAlert);
    }

    public Login fillPassword(String password) {
        sendKeysToElement(passwordField, password);
        return this;
    }

    public boolean isEmptyPasswordFieldValidationAlertVisible() {
        return isElementVisible(emptyPasswordFieldValidationAlert);
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }

    public Home loginButtonClick() {
        clickElement(loginButton);
        return new Home(webDriver);
    }

    public SelectAccount loginWithGoogleAccountButtonClick() {
        clickElement(loginWithGoogleAccountButton);
        return new SelectAccount(webDriver);
    }

    public boolean isRememberMeCheckboxSelected() {
        return isElementSelected(rememberMeCheckboxState);
    }

    public Login rememberMeCheckboxCheck() {
        clickElement(rememberMeCheckbox);
        return this;
    }

    public Register notYetCustomerButtonClick() {
        clickElement(registerButton);
        return new Register(webDriver);
    }

    public boolean isRegistrationSuccessfulVisible() {
        return isElementVisible(REGISTRATION_COMPLETED_ALERT_BY);
    }

    public boolean isRegistrationSuccessfulInvisible() {
        return invisibilityOfElement(REGISTRATION_COMPLETED_ALERT_BY);
    }

}
