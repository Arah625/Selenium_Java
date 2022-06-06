package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountPage extends BasePage{

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[contains(text(),'Imię')]/../input")
    WebElement firstNameInputField;

    @FindBy (xpath = "//*[contains(text(),'Nazwisko')]/../input")
    WebElement lastNameInputField;

    @FindBy (xpath = "//*[@id='female']/..")
    WebElement femaleRadioButton;

    @FindBy (xpath = "//*[@id='male']/..")
    WebElement maleRadioButton;

    @FindBy (xpath = "//*[contains(text(),'Dzień')]/../input")
    WebElement dayOfBirthInputField;

    @FindBy (xpath = "//*[@name='month']")
    WebElement monthOfBirthSelect;

    @FindBy (xpath = "//*[@name='year']")
    WebElement yearOfBirthSelect;

    @FindBy (xpath = "//*[contains(text(),'Wybierz login')]/..//input")
    WebElement loginInputField;

    @FindBy (xpath = "//*[@id='password']")
    WebElement passwordInputField;

    @FindBy (xpath = "//*[contains(text(),'Powtórz hasło')]/..//input")
    WebElement repeatPasswordInputField;

    @FindBy (xpath = "//*[contains(text(),'Numer telefonu komórkowego')]/..//input")
    WebElement mobilePhoneInputField;

    @FindBy (xpath = "//button[contains(text(),'Email pomocniczy')]")
    WebElement recoveryEmailButton;

    @FindBy (xpath = "//*[@name='recoveryEmail']")
    WebElement recoveryEmailInputField;

    @FindBy (xpath = "//*[@id='free']/..")
    WebElement freeAccountRadioButton;

    @FindBy (xpath = "//*[@id='pro']/..")
    WebElement paidAccountRadioButton;

    @FindBy (xpath = "//*[@id='selectAll']/..")
    WebElement acceptAllTermsCheckbox;

    @FindBy (xpath = "//*[@id='confirm']")
    WebElement acceptStatuteCheckbox;

    @FindBy (xpath = "//*[@id='14th']")
    WebElement fourteenDaysCheckbox;

    @FindBy (xpath = "//*[@id='processing_holding']")
    WebElement marketingPurposesDataProcessingCheckbox;

    @FindBy (xpath = "//*[@id='marketing']")
    WebElement receivingMarketingInfoCheckbox;

    @FindBy (xpath = "//*[@id='alcogambling']")
    WebElement receivingAlcoholAndGamblingMarketingInfoCheckbox;

    @FindBy (xpath = "//button[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;

    @FindBy (xpath = "//*[contains(text(),'Twoje konto')]")
    WebElement accountCreatedHeader;

    @FindBy (xpath = "//*[contains(text(),'Masz już konto?')]/..//*[contains(text(),'Zaloguj się')]")
    WebElement loginToAccountButton;

    public void fillFirstName(String firstName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(firstNameInputField));
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(lastNameInputField));
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastName);
    }

    public void genderFemaleRadioButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(femaleRadioButton));
        femaleRadioButton.click();
    }

    public void genderMaleRadioButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(maleRadioButton));
        maleRadioButton.click();
    }

    public void genderButtonClick(String gender) throws InterruptedException {
        if (gender.equalsIgnoreCase("male")){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(maleRadioButton));
            maleRadioButton.click();
        } if (gender.equalsIgnoreCase("female")){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(femaleRadioButton));
            femaleRadioButton.click();
        }
    }

    public void selectDateOfBirth(String day, String month, String year) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(dayOfBirthInputField));
        dayOfBirthInputField.clear();
        dayOfBirthInputField.sendKeys(day);
        Select monthOfBirth = new Select(monthOfBirthSelect);
//        monthOfBirth.selectByValue(month);
        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(yearOfBirthSelect);
        yearOfBirth.selectByValue(year);
    }

    public void selectDateOfBirth(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = simpleDateFormat.parse(date);
        simpleDateFormat = new SimpleDateFormat("dd");
        String day = simpleDateFormat.format(myDate);
        System.out.println("Day: " + day);
        simpleDateFormat = new SimpleDateFormat("MM");
        String month = simpleDateFormat.format(myDate);
        System.out.println("Month: " + month);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(myDate);
        System.out.println("Year: " + year);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(dayOfBirthInputField));
        dayOfBirthInputField.clear();
        dayOfBirthInputField.sendKeys(day);
        Select monthOfBirth = new Select(monthOfBirthSelect);
        monthOfBirth.selectByValue(month);
//        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(yearOfBirthSelect);
        yearOfBirth.selectByValue(year);
    }

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

    public void fillRepeatPassword(String repeatPassword) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(repeatPasswordInputField));
        repeatPasswordInputField.clear();
        repeatPasswordInputField.sendKeys(repeatPassword);
    }

    public void fillMobilePhoneNumber(String mobilePhoneNumber) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mobilePhoneInputField));
        mobilePhoneInputField.clear();
        mobilePhoneInputField.sendKeys(mobilePhoneNumber);
    }

    public void addRecoveryEmailAddressButtonClick(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recoveryEmailButton));
        recoveryEmailButton.click();
    }

    public void fillRecoveryEmailAddress(String recoveryEmail) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recoveryEmailInputField));
        recoveryEmailInputField.clear();
        recoveryEmailInputField.sendKeys(recoveryEmail);
    }

    public void freeAccountRadioButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(freeAccountRadioButton));
        freeAccountRadioButton.click();
    }

    public void paidAccountRadioButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(paidAccountRadioButton));
        paidAccountRadioButton.click();
    }

    public void checkAllCheckboxes() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptAllTermsCheckbox));
        acceptAllTermsCheckbox.click();
    }

    public void createAccountButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
    }

    public LoginPage loginToAccountButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginToAccountButton));
        loginToAccountButton.click();
        return new LoginPage(driver);
    }

    public boolean isAccountCreatedHeaderVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(accountCreatedHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
