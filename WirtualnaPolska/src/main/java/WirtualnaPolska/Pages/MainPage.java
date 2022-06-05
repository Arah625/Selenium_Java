package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy (xpath = "//*[@id='site-header']//*[contains(text(),'Poczta')]")
    WebElement emailButton;

    @FindBy (xpath = "//button[contains(text(),'USTAWIENIA ZAAWANSOWANE')]")
    WebElement advancedSettingsButton;

    @FindBy (xpath = "//button[contains(text(),'AKCEPTUJĘ I PRZECHODZĘ DO SERWISU')]")
    WebElement acceptAllTermsButton;

    @FindBy (xpath = "//*[contains(text(),'Cenimy Twoją prywatność')]")
    WebElement termsAcceptationHeader;


    public void acceptTerms(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptAllTermsButton));
        acceptAllTermsButton.click();
    }

    public boolean isTermsAcceptationHeaderVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(termsAcceptationHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void acceptTermsIfVisible() throws InterruptedException {
        if (isTermsAcceptationHeaderVisible()){
            acceptTerms();
        }
    }

    public LoginPage emailButtonClick(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailButton));
        emailButton.click();
        return new LoginPage(driver);
    }


}


