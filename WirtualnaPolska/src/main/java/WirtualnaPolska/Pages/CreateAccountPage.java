package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountPage extends BasePage {

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Imię')]/../input")
    WebElement firstNameInputField;

    @FindBy(xpath = "//*[contains(text(),'Nazwisko')]/../input")
    WebElement lastNameInputField;

    @FindBy(xpath = "//*[@id='female']/..")
    WebElement femaleRadioButton;

    @FindBy(xpath = "//*[@id='male']/..")
    WebElement maleRadioButton;

    @FindBy(xpath = "//*[contains(text(),'Dzień')]/../input")
    WebElement dayOfBirthInputField;

    @FindBy(xpath = "//*[@name='month']")
    WebElement monthOfBirthSelect;

    @FindBy(xpath = "//*[@name='year']")
    WebElement yearOfBirthSelect;

    @FindBy(xpath = "//*[contains(text(),'Wybierz login')]/..//input")
    WebElement loginInputField;

    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//*[contains(text(),'Powtórz hasło')]/..//input")
    WebElement repeatPasswordInputField;

    @FindBy(xpath = "//*[contains(text(),'Numer telefonu komórkowego')]/..//input")
    WebElement mobilePhoneInputField;

    @FindBy(xpath = "//button[contains(text(),'Email pomocniczy')]")
    WebElement recoveryEmailButton;

    @FindBy(xpath = "//*[@name='recoveryEmail']")
    WebElement recoveryEmailInputField;

    @FindBy(xpath = "//*[@id='free']/..")
    WebElement freeAccountRadioButton;

    @FindBy(xpath = "//*[@id='pro']/..")
    WebElement paidAccountRadioButton;

    @FindBy(xpath = "//*[@id='selectAll']/..")
    WebElement acceptAllTermsCheckbox;

    @FindBy(xpath = "//*[@id='confirm']")
    WebElement acceptStatuteCheckbox;

    @FindBy(xpath = "//*[@id='14th']")
    WebElement fourteenDaysCheckbox;

    @FindBy(xpath = "//*[@id='processing_holding']")
    WebElement marketingPurposesDataProcessingCheckbox;

    @FindBy(xpath = "//*[@id='marketing']")
    WebElement receivingMarketingInfoCheckbox;

    @FindBy(xpath = "//*[@id='alcogambling']")
    WebElement receivingAlcoholAndGamblingMarketingInfoCheckbox;

    @FindBy(xpath = "//button[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;

    @FindBy(xpath = "//*[contains(text(),'Twoje konto')]")
    WebElement accountCreatedHeader;

    @FindBy(xpath = "//*[contains(text(),'Masz już konto?')]/..//*[contains(text(),'Zaloguj się')]")
    WebElement loginToAccountButton;

    public void fillFirstName(String firstName) {
        clearAndSendKeysToElement(firstNameInputField, firstName);
    }

    public void fillLastName(String lastName) {
        clearAndSendKeysToElement(lastNameInputField, lastName);
    }

    public void genderFemaleRadioButtonClick() throws Exception {
        clickElement(femaleRadioButton);
    }

    public void genderMaleRadioButtonClick() throws Exception {
        clickElement(maleRadioButton);
    }

    public void genderButtonClick(String gender) throws Exception {
        if (gender.equalsIgnoreCase("male")) {
            genderMaleRadioButtonClick();
        }
        if (gender.equalsIgnoreCase("female")) {
            genderFemaleRadioButtonClick();
        }
    }

    public void selectDateOfBirth(String day, String month, String year) {
        webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(dayOfBirthInputField));
        dayOfBirthInputField.clear();
        dayOfBirthInputField.sendKeys(day);
        Select monthOfBirth = new Select(monthOfBirthSelect);
//        monthOfBirth.selectByValue(month);
        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(yearOfBirthSelect);
        yearOfBirth.selectByValue(year);
    }

    public void selectDateOfBirth(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(dayOfBirthInputField));
        dayOfBirthInputField.clear();
        dayOfBirthInputField.sendKeys(day);
        Select monthOfBirth = new Select(monthOfBirthSelect);
        monthOfBirth.selectByValue(month);
//        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(yearOfBirthSelect);
        yearOfBirth.selectByValue(year);
    }

    public void fillLogin(String login) {
        clearAndSendKeysToElement(loginInputField, login);
    }

    public void fillPassword(String password) {
        clearAndSendKeysToElement(passwordInputField, password);
    }

    public void fillRepeatPassword(String repeatPassword) {
        clearAndSendKeysToElement(repeatPasswordInputField, repeatPassword);
    }

    public void fillMobilePhoneNumber(String mobilePhoneNumber) {
        clearAndSendKeysToElement(mobilePhoneInputField, mobilePhoneNumber);

    }

    public void addRecoveryEmailAddressButtonClick() throws Exception {
        clickElement(recoveryEmailButton);
    }

    public void fillRecoveryEmailAddress(String recoveryEmail) {
        clearAndSendKeysToElement(recoveryEmailInputField, recoveryEmail);
    }

    public void freeAccountRadioButtonClick() throws Exception {
        clickElement(freeAccountRadioButton);
    }

    public void paidAccountRadioButtonClick() throws Exception {
        clickElement(paidAccountRadioButton);
    }

    public void checkAllCheckboxes() throws Exception {
        clickElement(acceptAllTermsCheckbox);
    }

    public void createAccountButtonClick() throws Exception {
        clickElement(createAccountButton);
    }

    public LoginPage loginToAccountButtonClick() throws Exception {
        clickElement(loginToAccountButton);
        return new LoginPage(driver);
    }

    public boolean isAccountCreatedHeaderVisible() {
        return isElementVisible(accountCreatedHeader);
    }

}
