package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[contains(text(),'Profil')]")
    WebElement profilePageHeader;

    @FindBy(xpath = "//*[contains(text(),'Dane personalne')]/../..//div[2]/a/span")
    WebElement editPersonalDataButton;

    @FindBy(xpath = "//*[contains(text(),'Poprawnie zapisano dane personalne.')]")
    WebElement personalDataSavedCorrectlyHeader;


    public boolean isProfilePageHeaderVisible() {
        return isElementVisible(profilePageHeader);
    }

    public boolean isEditPersonalDataButtonVisible() {
        return isElementVisible(editPersonalDataButton);
    }

    public EditPersonalDataPage editPersonalDataButtonClick() throws Exception {
        clickElement(editPersonalDataButton);
        return new EditPersonalDataPage(driver);
    }

    public boolean isPersonalDataSavedCorrectlyHeaderVisible() {
        return isElementVisible(personalDataSavedCorrectlyHeader);
    }


}
