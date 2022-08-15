package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='login']")
    WebElement loginInputField;

    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//*[@id='stgMain']//*[contains(text(),'Załóż konto')]/..")
    WebElement createAccountButton;

    @FindBy(xpath = "//*[@id='stgMain']//button[contains(text(),'Zaloguj się')]")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id='stgMain']")
    WebElement loginForm;


    public void fillLogin(String login) {
        clearAndSendKeysToElement(loginInputField, login);
    }

    public void fillPassword(String password) {
        clearAndSendKeysToElement(passwordInputField, password);
    }

    public boolean isCreateAccountButtonVisible() {
        return isElementVisible(createAccountButton);
    }

    public CreateAccountPage createAccountButtonClick() throws Exception {
        clickElement(createAccountButton);
        return new CreateAccountPage(driver);
    }

    public boolean isLoginButtonVisible() {
        return isElementVisible(loginButton);
    }

    public EmailAccountPage loginButtonClick() throws Exception {
        clickElement(loginButton);
        return new EmailAccountPage(driver);
    }

}
