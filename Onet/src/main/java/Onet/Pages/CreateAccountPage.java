package Onet.Pages;

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

    @FindBy(xpath = "//h1[contains(text(),'Rejestracja Onet Poczta')]")
    WebElement registrationOnetMailHeader;

    @FindBy(xpath = "//*[@id = 'alias']")
    WebElement newEmailInput;

    @FindBy(xpath = "//div[contains(text(),'Niestety adres ')]")
    WebElement emailAlreadyTakenMessage;

    @FindBy(xpath = "//strong")
    WebElement emailAddressTakenInMessage;

    @FindBy(xpath = "//div[3]//a[1]//span")
    WebElement firstRecommendedEmailAddressButton;

    @FindBy(xpath = "//button//span[contains(text(),'Dalej')]")
    WebElement submitButton;

    @FindBy(xpath = "//button[contains(text(),'Dalej')]")
    WebElement submitVerificationPhoneNumberButton;

    @FindBy(xpath = "//h2[contains(text(),'Ustaw hasło')]")
    WebElement setPasswordHeader;

    @FindBy(xpath = "//strong")
    WebElement emailAddressInSetPasswordSubheader;

    @FindBy(xpath = "//*[@id = 'newPassword']")
    WebElement newPasswordInput;

    @FindBy(xpath = "//*[@id = 'rePassword']")
    WebElement repeatNewPasswordInput;

    @FindBy(xpath = "//h2[contains(text(),'Dodaj metodę odzyskiwania hasła')]")
    WebElement addRetrievePasswordHeader;

    @FindBy(xpath = "//*[@id = 'recoveryPhone']")
    WebElement recoveryMobilePhoneNumberInput;

    @FindBy(xpath = "//*[@id = 'recoveryEmail']")
    WebElement recoveryEmailAddressInput;

    @FindBy(xpath = "//*[@id = 'phone']")
    WebElement verificationPhoneNumberInput;

    @FindBy(xpath = "//*[@id = 'code']")
    WebElement verificationCodeInput;

    @FindBy(xpath = "//*[@id = 'K']//..//span")
    WebElement femaleGenderRadioButton;

    @FindBy(xpath = "//*[@id = 'M']//..//span")
    WebElement maleGenderRadioButton;

    @FindBy(xpath = "//*[@id = 'name']")
    WebElement firstAndLastNameInput;

    @FindBy(xpath = "//*[@id = 'birthDate.day']")
    WebElement dayOfBirthInput;

    @FindBy(xpath = "//*[@id = 'birthDate.month']")
    WebElement listOfMoths;

    @FindBy(xpath = "//*[@id = 'birthDate.year']")
    WebElement yearOfBirthInput;

    @FindBy(xpath = "//*[@id = 'postalCode']")
    WebElement postalCodeInput;

    @FindBy(xpath = "//h2[contains(text(),'Wybierz pakiet')]")
    WebElement choosePackageHeader;

    @FindBy(xpath = "//div[3]/button")
    WebElement onetMailFreeButton;

    @FindBy(xpath = "//div[4]/button")
    WebElement onetMailPaidButton;

    @FindBy(xpath = "//h2[contains(text(),'Regulaminy i zgody')]")
    WebElement statuteAndAgreementsHeader;

    @FindBy(xpath = "//button//span[contains(text(),'Zaznaczam wszystkie poniższe:')]")
    WebElement acceptAllCheckboxesBelow;

    @FindBy(xpath = "//*[@id = 'agreements.85']")
    WebElement acceptStatueCheckbox;

    @FindBy(xpath = "//*[@id = 'agreements.21']")
    WebElement acceptOkontoServiceCheckbox;

    @FindBy(xpath = "//*[@id = 'agreements.6']")
    WebElement acceptStatueOkontoServiceCheckbox;

    @FindBy(xpath = "//*[@id = 'agreements.1']")
    WebElement acceptMarketingCheckbox;

    @FindBy(xpath = "//h2[contains(text(),'Rejestracja zakończona')]")
    WebElement registrationCompletedHeader;

    @FindBy(xpath = "//span[contains(text(),'Przejdź do poczty')]")
    WebElement goToEmailPageButton;


    public boolean isRegistrationOnetMailHeaderVisible() {
        return isElementVisible(registrationOnetMailHeader);
    }

    public void fillEmailAddress(String emailAddress) {
        clearAndSendKeysToElement(newEmailInput, emailAddress);
    }

    public void fillVerificationPhoneNumber(String phoneNumber) {
        clearAndSendKeysToElement(verificationPhoneNumberInput, phoneNumber);
    }

    public boolean isEmailAddressAlreadyTakenMessageVisible() {
        return isElementVisible(emailAlreadyTakenMessage);
    }

    public void firstRecommendedEmailAddressButtonClick() throws Exception {
        clickElement(firstRecommendedEmailAddressButton);
    }

    public void fillEmailAddress2(String emailAddress) throws Exception {
        clearAndSendKeysToElement(newEmailInput, emailAddress);
        clickElement(submitButton);
        if (isEmailAddressAlreadyTakenMessageVisible()) {
            System.out.println(emailAddressTakenInMessage.getText() + " Email address is already taken");
            System.out.println("Taking first recommended email address: ");
            firstRecommendedEmailAddressButtonClick();
        }
    }

    public void submitButtonClick() throws Exception {
        clickElement(submitButton);
    }

    public void submitVerificationPhoneNumberButtonClick() throws Exception {
        clickElement(submitVerificationPhoneNumberButton);
    }

    public boolean isSetPasswordHeaderVisible() {
        return isElementVisible(setPasswordHeader);
    }

    public String getEmailAddressFromSetPasswordSubheader() {
        webDriverWaitDefault.until(ExpectedConditions.visibilityOf(emailAddressInSetPasswordSubheader));
        return emailAddressInSetPasswordSubheader.getText();
    }

    public void fillNewPassword(String password) {
        clearAndSendKeysToElement(newPasswordInput, password);
    }

    public void fillRepeatNewPassword(String password) {
        clearAndSendKeysToElement(repeatNewPasswordInput, password);
    }

    public boolean isAddRetrievePasswordHeaderVisible() {
        return isElementVisible(addRetrievePasswordHeader);
    }

    public void fillRecoveryMobileNumber(String mobileNumber) {
        clearAndSendKeysToElement(recoveryMobilePhoneNumberInput, mobileNumber);
    }

    public void fillVerificationCode(String verificationCode) {
        clearAndSendKeysToElement(verificationCodeInput, verificationCode);
    }

    public void fillRecoveryEmailAddress(String recoveryEmailAddress) {
        clearAndSendKeysToElement(recoveryEmailAddressInput, recoveryEmailAddress);
    }

    public void femaleGenderButtonClick() throws Exception {
        clickElement(femaleGenderRadioButton);
    }

    public void maleGenderButtonClick() throws Exception {
        clickElement(maleGenderRadioButton);
    }

    public void genderButtonClick(String gender) throws Exception {
        if (gender.equalsIgnoreCase("male")) {
            maleGenderButtonClick();
        }
        if (gender.equalsIgnoreCase("female")) {
            femaleGenderButtonClick();
        }
    }

    public void fillFirstNameAndLastName(String firstAndLastName) {
        clearAndSendKeysToElement(firstAndLastNameInput, firstAndLastName);
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
        webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(dayOfBirthInput));
        dayOfBirthInput.clear();
        dayOfBirthInput.sendKeys(day);
        Select monthOfBirth = new Select(listOfMoths);
        monthOfBirth.selectByValue(month);
        webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(yearOfBirthInput));
        yearOfBirthInput.clear();
        yearOfBirthInput.sendKeys(year);
    }

    public void fillPostalCode(String postalCode) {
        clearAndSendKeysToElement(postalCodeInput, postalCode);
    }

    public boolean isChoosePackageHeaderVisible() {
        return isElementVisible(choosePackageHeader);
    }

    public void onetMailFreePlanButtonClick() throws Exception {
        clickElement(onetMailFreeButton);
    }

    public void onetMailPaidPlanButtonClick() throws Exception {
        clickElement(onetMailPaidButton);
    }

    public boolean isStatueAndAgreementsHeaderVisible() {
        return isElementVisible(statuteAndAgreementsHeader);
    }

    public void acceptAllCheckboxes() throws Exception {
        clickElement(acceptAllCheckboxesBelow);
    }

    public void acceptStatueCheckbox() throws Exception {
        clickElement(acceptStatueCheckbox);
    }

    public void acceptOkontoServiceCheckbox() throws Exception {
        clickElement(acceptOkontoServiceCheckbox);
    }

    public void acceptStatueOkontoServiceCheckbox() throws Exception {
        clickElement(acceptStatueOkontoServiceCheckbox);
    }

    public void acceptMarketingCheckbox() throws Exception {
        clickElement(acceptMarketingCheckbox);
    }

    public boolean isRegistrationCompletedHeaderVisible() {
        return isElementVisible(registrationCompletedHeader);
    }

    public LoginPage goToEmailPageButtonClick() throws Exception {
        clickElement(goToEmailPageButton);
        return new LoginPage(driver);
    }


}
