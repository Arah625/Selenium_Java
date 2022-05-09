package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }


    @FindBy (xpath = "//h1[contains(text(), 'Logowanie do Onet Poczty')]")
    WebElement loginToOnetMailHeader;

    @FindBy (xpath = "//*[@id = 'email']")
    WebElement emailAddressInput;

    @FindBy (xpath = "//button/span[contains(text(), 'Dalej')]")
    WebElement submitEmailAddress;

    @FindBy (xpath = "//*[@id = 'password']")
    WebElement emailPasswordInput;

    @FindBy (xpath = "//*[@id = 'mailFormPerm']/../span")
    WebElement rememberMeCheckbox;

    @FindBy (xpath = "//*[@id = 'loginForm']//*[contains(text(),'Odzyskaj hasło')]")
    WebElement recoverPassword;

    @FindBy (xpath = "//button/span[contains(text(), 'Zaloguj')]")
    WebElement loginButton;

    @FindBy (xpath = "//span[contains(text(),'Zarejestruj się')]")
    WebElement createAccountButton;




    public boolean isLoginToOnetMailHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(loginToOnetMailHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void fillEmailAddress(String emailAddress) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailAddressInput));
        emailAddressInput.clear();
        emailAddressInput.sendKeys(emailAddress);
    }

    public void fillEmailPassword(String emailPassword) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailPasswordInput));
        emailPasswordInput.clear();
        emailPasswordInput.sendKeys(emailPassword);
    }

    public void rememberMeCheckboxClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        rememberMeCheckbox.click();
    }

    public void recoverPasswordButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recoverPassword));
        recoverPassword.click();
    }

    public void submitEmailAddressButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitEmailAddress));
        submitEmailAddress.click();
    }

    public EmailAccountPage loginButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new EmailAccountPage(driver);
    }

    public CreateAccountPage createAccountButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
        return new CreateAccountPage(driver);
    }

}
