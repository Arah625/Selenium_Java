package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver){
        super(driver);
    }


    @FindBy (xpath = "//h3[contains(text(),'Profil')]")
    WebElement profilePageHeader;

    @FindBy (xpath = "//*[contains(text(),'Dane personalne')]/../..//div[2]/a/span")
    WebElement editPersonalDataButton;

    @FindBy (xpath = "//*[contains(text(),'Poprawnie zapisano dane personalne.')]")
    WebElement personalDataSavedCorrectlyHeader;


    public boolean isProfilePageHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(profilePageHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isEditPersonalDataButtonVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(editPersonalDataButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public EditPersonalDataPage editPersonalDataButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editPersonalDataButton));
        editPersonalDataButton.click();
        return new EditPersonalDataPage(driver);
    }

    public boolean isPersonalDataSavedCorrectlyHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(personalDataSavedCorrectlyHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}
