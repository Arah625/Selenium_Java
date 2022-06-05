package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@id='login']")
    WebElement loginInputField;

    @FindBy (xpath = "//*[@id='login']")
    WebElement passwordInputField;

    @FindBy (xpath = "//*[@id='stgMain']//*[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;

    @FindBy (xpath = "//*[@id='stgMain']//button[contains(text(),'Zaloguj się')]")
    WebElement loginButton;

    @FindBy (xpath = "//*[@id='stgMain']")
    WebElement loginForm;


    public void fillLogin(String login) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginInputField));
        loginInputField.clear();
        loginInputField.sendKeys(login);
    }

    public void fillPassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    public boolean isCreateAccountButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(createAccountButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public CreateAccountPage createAccountButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
        return new CreateAccountPage(driver);
    }

    public boolean isLoginButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public CreateAccountPage loginButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new CreateAccountPage(driver);
    }

}
