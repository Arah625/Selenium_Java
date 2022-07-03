package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountPage extends BasePage{
    public CreateAccountPage (WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//h1[contains(text(),'Rejestracja Onet Poczta')]")
    WebElement registrationOnetMailHeader;

    @FindBy (xpath = "//*[@id = 'alias']")
    WebElement newEmailInput;

    @FindBy (xpath = "//div[contains(text(),'Niestety adres ')]")
    WebElement emailAlreadyTakenMessage;

    @FindBy (xpath = "//strong")
    WebElement emailAddressTakenInMessage;

    @FindBy (xpath = "//div[3]//a[1]//span")
    WebElement firstRecommendedEmailAddressButton;

    @FindBy (xpath = "//button//span[contains(text(),'Dalej')]")
    WebElement submitButton;

    @FindBy (xpath = "//button[contains(text(),'Dalej')]")
    WebElement submitVerificationPhoneNumberButton;

    @FindBy (xpath = "//h2[contains(text(),'Ustaw hasło')]")
    WebElement setPasswordHeader;

    @FindBy (xpath = "//strong")
    WebElement emailAddressInSetPasswordSubheader;

    @FindBy (xpath = "//*[@id = 'newPassword']")
    WebElement newPasswordInput;

    @FindBy (xpath = "//*[@id = 'rePassword']")
    WebElement repeatNewPasswordInput;

    @FindBy (xpath = "//h2[contains(text(),'Dodaj metodę odzyskiwania hasła')]")
    WebElement addRetrievePasswordHeader;

    @FindBy (xpath = "//*[@id = 'recoveryPhone']")
    WebElement recoveryMobilePhoneNumberInput;

    @FindBy (xpath = "//*[@id = 'recoveryEmail']")
    WebElement recoveryEmailAddressInput;

    @FindBy (xpath = "//*[@id = 'phone']")
    WebElement verificationPhoneNumberInput;

    @FindBy (xpath = "//*[@id = 'code']")
    WebElement verificationCodeInput;

    @FindBy (xpath = "//*[@id = 'K']//..//span")
    WebElement femaleGenderRadioButton;

    @FindBy (xpath = "//*[@id = 'M']//..//span")
    WebElement maleGenderRadioButton;

    @FindBy (xpath = "//*[@id = 'name']")
    WebElement firstAndLastNameInput;

    @FindBy (xpath = "//*[@id = 'birthDate.day']")
    WebElement dayOfBirthInput;

    @FindBy (xpath = "//*[@id = 'birthDate.month']")
    WebElement listOfMoths;

    @FindBy (xpath = "//*[@id = 'birthDate.year']")
    WebElement yearOfBirthInput;

    @FindBy (xpath = "//*[@id = 'postalCode']")
    WebElement postalCodeInput;

    @FindBy (xpath = "//h2[contains(text(),'Wybierz pakiet')]")
    WebElement choosePackageHeader;

    @FindBy (xpath = "//div[3]/button")
    WebElement onetMailFreeButton;

    @FindBy (xpath = "//div[4]/button")
    WebElement onetMailPaidButton;

    @FindBy (xpath = "//h2[contains(text(),'Regulaminy i zgody')]")
    WebElement statuteAndAgreementsHeader;

    @FindBy (xpath = "//button//span[contains(text(),'Zaznaczam wszystkie poniższe:')]")
    WebElement acceptAllCheckboxesBelow;

    @FindBy (xpath = "//*[@id = 'agreements.85']")
    WebElement acceptStatueCheckbox;

    @FindBy (xpath = "//*[@id = 'agreements.21']")
    WebElement acceptOkontoServiceCheckbox;

    @FindBy (xpath = "//*[@id = 'agreements.6']")
    WebElement acceptStatueOkontoServiceCheckbox;

    @FindBy (xpath = "//*[@id = 'agreements.1']")
    WebElement acceptMarketingCheckbox;

    @FindBy (xpath = "//h2[contains(text(),'Rejestracja zakończona')]")
    WebElement registrationCompletedHeader;

    @FindBy (xpath = "//span[contains(text(),'Przejdź do poczty')]")
    WebElement goToEmailPageButton;


    public boolean isRegistrationOnetMailHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(registrationOnetMailHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void fillEmailAddress(String emailAddress) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newEmailInput));
        newEmailInput.clear();
        newEmailInput.sendKeys(emailAddress);
    }

    public void fillVerificationPhoneNumber(String phoneNumber) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(verificationPhoneNumberInput));
        verificationPhoneNumberInput.clear();
        verificationPhoneNumberInput.sendKeys(phoneNumber);
    }

    public boolean isEmailAddressAlreadyTakenMessageVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(emailAlreadyTakenMessage)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void firstRecommendedEmailAddressButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(firstRecommendedEmailAddressButton));
        firstRecommendedEmailAddressButton.click();
    }

    public void fillEmailAddress2(String emailAddress) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newEmailInput));
        newEmailInput.clear();
        newEmailInput.sendKeys(emailAddress);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        if (isEmailAddressAlreadyTakenMessageVisible()){
            System.out.println(emailAddressTakenInMessage.getText() + " Email address is already taken");
            System.out.println("Taking first recommended email address: ");
            firstRecommendedEmailAddressButtonClick();
        }
    }

    public void submitButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void submitVerificationPhoneNumberButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitVerificationPhoneNumberButton));
        submitVerificationPhoneNumberButton.click();
    }

    public boolean isSetPasswordHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(setPasswordHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getEmailAddressFromSetPasswordSubheader() {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailAddressInSetPasswordSubheader));
        return emailAddressInSetPasswordSubheader.getText();
    }

    public void fillNewPassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newPasswordInput));
        newPasswordInput.clear();
        newPasswordInput.sendKeys(password);
    }

    public void fillRepeatNewPassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(repeatNewPasswordInput));
        repeatNewPasswordInput.clear();
        repeatNewPasswordInput.sendKeys(password);
    }

    public boolean isAddRetrievePasswordHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(addRetrievePasswordHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void fillRecoveryMobileNumber(String mobileNumber) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recoveryMobilePhoneNumberInput));
        recoveryMobilePhoneNumberInput.clear();
        recoveryMobilePhoneNumberInput.sendKeys(mobileNumber);
    }

    public void fillVerificationCode(String verificationCode) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(verificationCodeInput));
        verificationCodeInput.clear();
        verificationCodeInput.sendKeys(verificationCode);
    }

    public void fillRecoveryEmailAddress(String recoveryEmailAddress) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recoveryEmailAddressInput));
        recoveryEmailAddressInput.clear();
        recoveryEmailAddressInput.sendKeys(recoveryEmailAddress);
    }

    public void femaleGenderButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(femaleGenderRadioButton));
        femaleGenderRadioButton.click();
    }

    public void maleGenderButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(maleGenderRadioButton));
        maleGenderRadioButton.click();
    }

    public void genderButtonClick(String gender) throws InterruptedException {
        if (gender.equalsIgnoreCase("male")){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(maleGenderRadioButton));
            maleGenderRadioButton.click();
        } if (gender.equalsIgnoreCase("female")){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(femaleGenderRadioButton));
            femaleGenderRadioButton.click();
        }
    }

    public void fillFirstNameAndLastName(String firstAndLastName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(firstAndLastNameInput));
        firstAndLastNameInput.clear();
        firstAndLastNameInput.sendKeys(firstAndLastName);
    }

    public void selectDateOfBirth(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = simpleDateFormat.parse(date);
        simpleDateFormat = new SimpleDateFormat("dd");
        String day = simpleDateFormat.format(myDate);
        System.out.println("Day: " + day);
        simpleDateFormat = new SimpleDateFormat("M");
        String month = simpleDateFormat.format(myDate);
        System.out.println("Month: " + month);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(myDate);
        System.out.println("Year: " + year);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(dayOfBirthInput));
        dayOfBirthInput.clear();
        dayOfBirthInput.sendKeys(day);
        Select monthOfBirth = new Select(listOfMoths);
        monthOfBirth.selectByValue(month);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(yearOfBirthInput));
        yearOfBirthInput.clear();
        yearOfBirthInput.sendKeys(year);
    }

    public void fillPostalCode(String postalCode) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(postalCodeInput));
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
    }

    public boolean isChoosePackageHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(choosePackageHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void onetMailFreePlanButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(onetMailFreeButton));
        onetMailFreeButton.click();
    }

    public void onetMailPaidPlanButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(onetMailPaidButton));
        onetMailPaidButton.click();
    }

    public boolean isStatueAndAgreementsHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(statuteAndAgreementsHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void acceptAllCheckboxes() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptAllCheckboxesBelow));
        acceptAllCheckboxesBelow.click();
    }

    public void acceptStatueCheckbox() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptStatueCheckbox));
        acceptStatueCheckbox.click();
    }

    public void acceptOkontoServiceCheckbox() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptOkontoServiceCheckbox));
        acceptOkontoServiceCheckbox.click();
    }

    public void acceptStatueOkontoServiceCheckbox() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptStatueOkontoServiceCheckbox));
        acceptStatueOkontoServiceCheckbox.click();
    }

    public void acceptMarketingCheckbox() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptMarketingCheckbox));
        acceptMarketingCheckbox.click();
    }

    public boolean isRegistrationCompletedHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(registrationCompletedHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public LoginPage goToEmailPageButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(goToEmailPageButton));
        goToEmailPageButton.click();
        return new LoginPage(driver);
    }


}
