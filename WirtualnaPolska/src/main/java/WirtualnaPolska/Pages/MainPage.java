package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='site-header']//*[contains(text(),'Poczta')]/..")
    WebElement emailButton;

    @FindBy(xpath = "//button[contains(text(),'USTAWIENIA ZAAWANSOWANE')]")
    WebElement advancedSettingsButton;

    @FindBy(xpath = "//button[contains(text(),'AKCEPTUJĘ I PRZECHODZĘ DO SERWISU')]")
    WebElement acceptAllTermsButton;

    @FindBy(xpath = "//*[contains(text(),'Cenimy Twoją prywatność')]")
    WebElement termsAcceptationHeader;

    @FindBy(xpath = "//*[contains(text(),'Przejdź teraz')]")
    WebElement skipAdvertisementButton;

    @FindBy(xpath = "//*[@id = 'logos']")
    WebElement logo;

    public boolean isLogoVisible() {
        return isElementVisible(logo);
    }

    public void acceptTerms() throws Exception {
        clickElement(acceptAllTermsButton);
    }

    public boolean isTermsAcceptationHeaderVisible() {
        return isElementVisible(termsAcceptationHeader);
    }

    public void acceptTermsIfVisible() throws InterruptedException {
        try {
            acceptTerms();
            skipAdvertisementButtonClick();
        } catch (Exception e) {
            System.out.println("No popups visible to close");
        }
    }

    public boolean isSkipAdvertisementButtonVisible() {
        return isElementVisible(skipAdvertisementButton);
    }

    public void skipAdvertisementButtonClick() throws Exception {
        clickElement(skipAdvertisementButton);
    }

    public void skipAdvertisementButtonIfVisible() throws Exception {
        if (isSkipAdvertisementButtonVisible()) {
            skipAdvertisementButtonClick();
        }
    }

    public LoginPage emailButtonClick() throws Exception {
        clickElement(emailButton);
        return new LoginPage(driver);
    }


}


