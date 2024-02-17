package lh.juicecompany.Pages.Account;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Register extends BasicPage {

    private static final List<String> PASSWORD_ADVICES_LIST = Arrays.asList("contains at least one lower character", "contains at least one upper character", "contains at least one digit", "contains at least one special character", "contains at least 8 characters");
    private static final List<String> WRONG_PASSWORD_ADVICES_LIST = Arrays.asList("contains at least one lower characterr", "contains at least one upper characterr", "contains at least one digitt", "contains at least one special characterr", "contains at least 8 characters");
    private static final List<String> BAD_PASSWORD_ADVICES_LIST = Arrays.asList("contains at least one lower characterr", "contains at least one upper characterr", "contains at least one digitt", "contains at least one special characterr", "contains at least 8 characterss");
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
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'contains at least one lower characterr')]")
    private WebElement firstHint;
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'contains at least one upper character')]")
    private WebElement secondHint;
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'contains at least one digitt')]")
    private WebElement thirdHint;
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'contains at least one special character')]")
    private WebElement fourthHint;
    @FindBy(xpath = "//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'contains at least 8 characters')]")
    private WebElement fifthHint;

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

    public boolean isNumberOfPasswordHintsVisible() {
        return elementVisibilityHandler.isNumberOfElementsByWebElementsVisible(passwordAdviceList, passwordHintsList().size());
    }

    private By passwordHintXpath(String passwordHint) {
        return By.xpath("//*[@id='registration-form']/mat-password-strength-info/mat-card/mat-card-content/div/span[contains(text(),'" + passwordHint + "')]");
    }

//    private List<By> passwordHintsList() {
//        return WRONG_PASSWORD_ADVICES_LIST.stream().map(this::passwordHintXpath).toList();
//    }

    private List<WebElement> passwordHintsList() {
        ArrayList<WebElement> webElementArrayList = new ArrayList<>();
        webElementArrayList.add(firstHint);
        webElementArrayList.add(secondHint);
        webElementArrayList.add(thirdHint);
        webElementArrayList.add(fourthHint);
        webElementArrayList.add(fifthHint);
        return webElementArrayList;
    }

    public boolean arePasswordHintsVisible() {
        return elementVisibilityHandler.areAllElementsVisible(passwordHintsList());
//        return elementVisibilityHandler.areAllElementsVisible(firstHint, secondHint, thirdHint, fourthHint, fifthHint);
    }

    private Register securityQuestionDropdownButtonClick() {
        commonMethods.clickElement(securityQuestionDropdownButton);
        return this;
    }

    public void selectSecretQuestion(String secretQuestion) {
        securityQuestionDropdownButtonClick();
        commonMethods.clickElement(elementFinder.locateElementBy(By.xpath("//div[@aria-label='Selection list for the security question']//span[contains(text(),'" + secretQuestion + "')]")));
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