package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    LoginPage emailPage;

    @FindBy (xpath = "//span[contains(text(),'Szanowna Użytkowniczko, Szanowny Użytkowniku,')]")
    WebElement popUpHeader;

    @FindBy (xpath = "//button//span[contains(text(),'Przejdź do serwisu')]")
    WebElement goToWebsiteButton;

    @FindBy (xpath = "//button//span[contains(text(),'Ustawienia zaawansowane')]")
    WebElement advancedSettingsButton;

    @FindBy (xpath = "//*[@class = 'headerNavItem mail']")
    WebElement emailButton;


    public boolean isPopUpHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(popUpHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isGoToWebsiteButtonVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(goToWebsiteButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public void goToWebsiteButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(goToWebsiteButton));
        goToWebsiteButton.click();
    }

    public boolean isAdvancedSettingsButtonVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(advancedSettingsButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void advancedSettingsButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(advancedSettingsButton));
        advancedSettingsButton.click();
    }

    public LoginPage emailButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailButton));
        emailButton.click();
        return new LoginPage(driver);
    }

}
