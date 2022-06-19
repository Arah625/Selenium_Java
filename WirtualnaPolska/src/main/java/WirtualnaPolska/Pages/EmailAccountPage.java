package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailAccountPage extends BasePage{

    public EmailAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//h3[contains(text(),'Już niedługo usługa 1login')]")
    WebElement oneLoginFromWpHeader;

    @FindBy (xpath = "//button//*[contains(text(),'Kontynuuj')]")
    WebElement oneLoginFromWpContinueButton;

    @FindBy (xpath = "//button[contains(text(),'Chcę to jeszcze przemyśleć')]")
    WebElement oneLoginFromWpThinkThroughButton;

    @FindBy (xpath = "//*[contains(text(),'Zaznacz wszystkie poniższe')]")
    WebElement markAllCheckBoxes;

    @FindBy (xpath = "//button[contains(text(),'Zaakceptuj')]")
    WebElement acceptAllConsentsForOneLoginFromWpButton;

    @FindBy (xpath = "//*[@id='stgMain']//*[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;

    @FindBy (xpath = "//*[@id = 'Logout-Button']")
    WebElement logoutButton;

    @FindBy (xpath = "//button[2][contains(text(),'napisz')]")
    WebElement writeMessageButton;

    @FindBy (xpath = "//*[@id = 'nh-notify']//*[contains(text(),'Wiadomość została wysłana')]")
    WebElement messageSentNotification;


    public void acceptTermsOf1LoginFromWpIfVisible() throws InterruptedException {
        if (isOneLoginFromWpHeaderVisible()){
            oneLoginFromWpThinkThroughButtonClick();
        }
    }

    public boolean isOneLoginFromWpHeaderVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(oneLoginFromWpHeader)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isOneLoginFromWpContinueButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(oneLoginFromWpContinueButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void oneLoginFromWpContinueButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(oneLoginFromWpContinueButton));
        oneLoginFromWpContinueButton.click();
    }

    public boolean isOneLoginFromWpThinkThroughButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(oneLoginFromWpThinkThroughButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void oneLoginFromWpThinkThroughButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(oneLoginFromWpThinkThroughButton));
        oneLoginFromWpThinkThroughButton.click();
    }

    public boolean isAcceptAllConsentsForOneLoginFromWpButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(acceptAllConsentsForOneLoginFromWpButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void acceptAllConsentsForOneLoginFromWpButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptAllConsentsForOneLoginFromWpButton));
        acceptAllConsentsForOneLoginFromWpButton.click();
    }

    public boolean isLogoutButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void logoutButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public boolean isWriteMessageButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(writeMessageButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public WriteMessagePage writeMessageButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        writeMessageButton.click();
        return new WriteMessagePage(driver);
    }

    public boolean isMessageSentNotificationVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(messageSentNotification)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }



}
