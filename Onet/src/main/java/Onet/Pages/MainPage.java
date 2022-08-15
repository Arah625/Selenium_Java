package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@aria-label = 'close']")
    List<WebElement> closePopUpsList;

    @FindBy(xpath = "//*[@aria-label = 'close']")
    WebElement closeSinglePopUp;

    @FindBy(xpath = "//span[contains(text(),'Szanowna Użytkowniczko, Szanowny Użytkowniku')]")
    WebElement popUpHeader;

    @FindBy(xpath = "//button//span[contains(text(),'Przejdź do serwisu')]")
    WebElement goToWebsiteButton;

    @FindBy(xpath = "//button//span[contains(text(),'Ustawienia zaawansowane')]")
    WebElement advancedSettingsButton;

    @FindBy(xpath = "//*[@class = 'headerNavItem mail'] | //*[span = 'E-mail']")
    WebElement emailButton;


    public boolean closeAllPopUpsIfVisible() {
        for (WebElement closeButton : closePopUpsList) {
            if (isPopUpToCloseVisible()) {
                closeButton.click();
                webDriverWaitDefault.until(ExpectedConditions.invisibilityOf(closeButton));
                return true;
            }
        }
        return false;
    }

    public boolean isPopUpToCloseVisible() {
        return isElementVisible(closeSinglePopUp);
    }

    public boolean isPopUpHeaderVisible() {
        return isElementVisible(popUpHeader);
    }

    public void closePopUpIfVisible() throws Exception {
        if (isPopUpHeaderVisible()) {
            goToWebsiteButtonClick();
        }
    }

    public boolean isGoToWebsiteButtonVisible() {
        return isElementVisible(goToWebsiteButton);
    }

    public void goToWebsiteButtonClick() throws Exception {
        clickElement(goToWebsiteButton);
    }

    public boolean isAdvancedSettingsButtonVisible() {
        return isElementVisible(advancedSettingsButton);
    }

    public void advancedSettingsButtonClick() throws Exception {
        clickElement(advancedSettingsButton);
    }

    public LoginPage emailButtonClick() throws Exception {
        clickElement(emailButton);
        return new LoginPage(driver);
    }

}
