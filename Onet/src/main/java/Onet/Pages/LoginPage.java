package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Logowanie do Onet Poczty')]")
    WebElement loginToOnetMailHeader;

    @FindBy(xpath = "//*[@id = 'email']")
    WebElement emailAddressInput;

    @FindBy(xpath = "//button/span[contains(text(), 'Dalej')]")
    WebElement submitEmailAddress;

    @FindBy(xpath = "//*[@id = 'password']")
    WebElement emailPasswordInput;

    @FindBy(xpath = "//*[@id = 'mailFormPerm']/../span")
    WebElement rememberMeCheckbox;

    @FindBy(xpath = "//*[@id = 'loginForm']//*[contains(text(),'Odzyskaj hasło')]")
    WebElement recoverPassword;

    @FindBy(xpath = "//button/span[contains(text(), 'Zaloguj')]")
    WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Zarejestruj się')]/..")
    WebElement createAccountButton;

    public boolean isLoginToOnetMailHeaderVisible() {
        return isElementVisible(loginToOnetMailHeader);
    }

    public void fillEmailAddress(String emailAddress) {
        clearAndSendKeysToElement(emailAddressInput, emailAddress);
    }

    public void fillEmailPassword(String emailPassword) {
        clearAndSendKeysToElement(emailPasswordInput, emailPassword);
    }

    public void rememberMeCheckboxClick() throws Exception {
        clickElement(rememberMeCheckbox);
    }

    public void recoverPasswordButtonClick() throws Exception {
        clickElement(recoverPassword);
    }

    public void submitEmailAddressButtonClick() throws Exception {
        clickElement(submitEmailAddress);
    }

    public EmailAccountPage loginButtonClick() throws Exception {
        clickElement(loginButton);
        return new EmailAccountPage(driver);
    }

    public CreateAccountPage createAccountButtonClick() throws Exception {
        clickElement(createAccountButton);
        return new CreateAccountPage(driver);
    }

}
