package lh.juicecompany.Pages.Account;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class Register extends BasicPage {

    private static final By PASSWORD_ADVICE_ELEMENT_BY = By.xpath("//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div");
    private final List<String> PASSWORD_ADVICES_LIST = Arrays.asList("contains at least one lower character", "contains at least one upper character", "contains at least one digit", "contains at least one special character", "contains at least 8 characters");
    @FindBy(xpath = "//input[@id='emailControl']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='passwordControl']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='repeatPasswordControl']")
    private WebElement repeatPasswordField;
    @FindBy(xpath = "//*[@id='mat-slide-toggle-1-input']/..//span")
    private WebElement showPasswordAdviceToggleSwitch;
    @FindBy(xpath = "//mat-select[@name='securityQuestion']")
    private WebElement securityQuestionDropdownButton;
    @FindBy(xpath = "//input[@id='securityAnswerControl']")
    private WebElement answerField;
    @FindBy(xpath = "//button[@id='registerButton']")
    private WebElement registerButton;
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div")
    private List<WebElement> passwordAdviceList;
    @FindBy(xpath = "//div[@class='error' and contains(text(),'Email must be unique')]")
    private WebElement uniqueEmailRequiredAlert;

    public Register(WebDriver webDriver) {
        super(webDriver);
    }

    public Register fillEmail(String email) {
        commonMethods.sendKeysToElement(emailField, email);
        return this;
    }

    public Register fillPassword(String password) {
        commonMethods.sendKeysToElement(passwordField, password);
        return this;
    }

    public Register fillRepeatPassword(String password) {
        commonMethods.sendKeysToElement(repeatPasswordField, password);
        return this;
    }

    public Register showPasswordAdvice() {
        commonMethods.clickElement(showPasswordAdviceToggleSwitch);
        return this;
    }

    public boolean arePasswordAdvicesVisible() {
        return elementVisibilityHandler.isNumberOfElementsByWebElementsVisible(passwordAdviceList, 5);
    }

    private Register securityQuestionDropdownButtonClick() {
        commonMethods.clickElement(securityQuestionDropdownButton);
        return this;
    }

    public void selectSecretQuestion(String secretQuestion) {
        securityQuestionDropdownButtonClick();
        commonMethods.clickElement(elementFinder.findElementBy(By.xpath("//div[@aria-label='Selection list for the security question']//span[contains(text(),'" + secretQuestion + "')]")));
    }

    public Register fillAnswer(String answer) {
        commonMethods.sendKeysToElement(answerField, answer);
        return this;
    }

    public Login registerButtonClick() {
        commonMethods.clickElement(registerButton);
        return new Login(webDriver);
    }


    public boolean isUniqueEmailRequiredAlertVisible() {
        return elementVisibilityHandler.isElementVisible(uniqueEmailRequiredAlert);
    }


}
