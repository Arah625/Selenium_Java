package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }


    @FindBy (xpath = "//*[@id = 'loginForm']")
    WebElement loginForm;

    @FindBy (xpath = "//*[@id = 'mailFormLogin']")
    WebElement emailAddressInput;

    @FindBy (xpath = "//*[@id = 'mailFormPassword']")
    WebElement emailPasswordInput;

    @FindBy (xpath = "//*[@id = 'mailFormPerm']/../span")
    WebElement rememberMeCheckbox;

    @FindBy (xpath = "//*[@id = 'loginForm']//*[contains(text(),'Odzyskaj hasło')]")
    WebElement recoverPassword;

    @FindBy (xpath = "//*[@id = 'loginForm']//*[@class = 'loginButton']")
    WebElement loginButton;

    @FindBy (xpath = "//a[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;




    public boolean isLoginFormVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(loginForm)).isDisplayed();
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

    public EmailAccountPage LoginButtonClick() throws InterruptedException {
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
