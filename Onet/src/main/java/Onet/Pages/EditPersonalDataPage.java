package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditPersonalDataPage extends BasePage{
    public EditPersonalDataPage(WebDriver driver){
        super(driver);
    }


    @FindBy (xpath = "//h3[contains(text(),'Edycja danych personalnych')]")
    WebElement editPersonalDataPageHeader;

    @FindBy (xpath = "//*[contains(text(),'Dane personalne')]/../..//div[2]/a/span")
    WebElement editPersonalDataButton;

    @FindBy (xpath = "//*[@id='name']")
    WebElement nameInputField;

    @FindBy (xpath = "//*[@id='surname']")
    WebElement surnameInputField;

    @FindBy (xpath = "//*[@id='K']/..")
    WebElement femaleGenderRadioButton;

    @FindBy (xpath = "//*[@id='M']/..")
    WebElement maleGenderRadioButton;

    @FindBy (xpath = "//*[@id='birthDate.day']")
    WebElement dayOfBirthSelect;

    @FindBy (xpath = "//*[@id='birthDate.month']")
    WebElement monthOfBirthSelect;

    @FindBy (xpath = "//*[@id='birthDate.year']")
    WebElement yearOfBirthSelect;

    @FindBy (xpath = "//*[@id='country']")
    WebElement countryInputField;

    @FindBy (xpath = "//*[@id='city']")
    WebElement cityInputField;

    @FindBy (xpath = "//*[@id='postalCode']")
    WebElement postalCodeInputField;

    @FindBy (xpath = "//button//*[contains(text(),'Zapisz')]")
    WebElement saveChangesButton;


    public boolean isEditPersonalDataPageHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(editPersonalDataPageHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void fillName(String name) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(nameInputField));
        nameInputField.clear();
        nameInputField.sendKeys(name);
    }

    public void fillSurname(String surname) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(surnameInputField));
        surnameInputField.clear();
        surnameInputField.sendKeys(surname);
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

    public void selectDateOfBirth(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-MM-yyyy");
        Date myDate = simpleDateFormat.parse(date);
        simpleDateFormat = new SimpleDateFormat("d");
        String day = simpleDateFormat.format(myDate);
        System.out.println("Day: " + day);
        simpleDateFormat = new SimpleDateFormat("MM");
        String month = simpleDateFormat.format(myDate);
        switch (month){
            case "01":
                month = "styczeń";
                break;
            case "02":
                month = "luty";
                break;
            case "03":
                month = "marzec";
                break;
            case "04":
                month = "kwiecień";
                break;
            case "05":
                month = "maj";
                break;
            case "06":
                month = "czerwiec";
                break;
            case "07":
                month = "lipiec";
                break;
            case "08":
                month = "sierpień";
                break;
            case "09":
                month = "wrzesień";
                break;
            case "10":
                month = "październik";
                break;
            case "11":
                month = "listopad";
                break;
            case "12":
                month = "grudzień";
                break;
        }
        System.out.println("Month: " + month);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(myDate);
        System.out.println("Year: " + year);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(dayOfBirthSelect));
        Select dayOfBirth = new Select(dayOfBirthSelect);
        dayOfBirth.selectByValue(day);
        Select monthOfBirth = new Select(monthOfBirthSelect);
        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(yearOfBirthSelect);
        yearOfBirth.selectByValue(year);
    }

    public void saveChangesButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
        saveChangesButton.click();
    }


}