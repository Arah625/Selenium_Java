package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailAccountPage extends BasePage {

    public EmailAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[contains(text(),'Już niedługo usługa 1login')]")
    WebElement oneLoginFromWpHeader;

    @FindBy(xpath = "//button//*[contains(text(),'Kontynuuj')]")
    WebElement oneLoginFromWpContinueButton;

    @FindBy(xpath = "//button[contains(text(),'Chcę to jeszcze przemyśleć')]")
    WebElement oneLoginFromWpThinkThroughButton;

    @FindBy(xpath = "//*[contains(text(),'Zaznacz wszystkie poniższe')]")
    WebElement markAllCheckBoxes;

    @FindBy(xpath = "//button[contains(text(),'Zaakceptuj')]")
    WebElement acceptAllConsentsForOneLoginFromWpButton;

    @FindBy(xpath = "//*[@id='stgMain']//*[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;

    @FindBy(xpath = "//*[@id = 'Logout-Button']")
    WebElement logoutButton;

    @FindBy(xpath = "//button[2][contains(text(),'napisz')]")
    WebElement writeMessageButton;

    @FindBy(xpath = "//*[@id = 'nh-notify']//*[contains(text(),'Wiadomość została wysłana')]")
    WebElement messageSentNotification;


    public void acceptTermsOf1LoginFromWpIfVisible() throws Exception {
        if (isOneLoginFromWpHeaderVisible()) {
            oneLoginFromWpThinkThroughButtonClick();
        }
    }

    public boolean isOneLoginFromWpHeaderVisible() {
        return isElementVisible(oneLoginFromWpHeader);
    }

    public boolean isOneLoginFromWpContinueButtonVisible() {
        return isElementVisible(oneLoginFromWpContinueButton);
    }

    public void oneLoginFromWpContinueButtonClick() throws Exception {
        clickElement(oneLoginFromWpContinueButton);
    }

    public boolean isOneLoginFromWpThinkThroughButtonVisible() {
        return isElementVisible(oneLoginFromWpThinkThroughButton);
    }

    public void oneLoginFromWpThinkThroughButtonClick() throws Exception {
        clickElement(oneLoginFromWpThinkThroughButton);
    }

    public boolean isAcceptAllConsentsForOneLoginFromWpButtonVisible() {
        return isElementVisible(acceptAllConsentsForOneLoginFromWpButton);
    }

    public void acceptAllConsentsForOneLoginFromWpButtonClick() throws Exception {
        clickElement(acceptAllConsentsForOneLoginFromWpButton);
    }

    public boolean isLogoutButtonVisible() {
        return isElementVisible(logoutButton);
    }

    public void logoutButtonClick() throws Exception {
        clickElement(logoutButton);
    }

    public boolean isWriteMessageButtonVisible() {
        return isElementVisible(writeMessageButton);
    }

    public WriteMessagePage writeMessageButtonClick() throws Exception {
        clickElement(writeMessageButton);
        return new WriteMessagePage(driver);
    }

    public boolean isMessageSentNotificationVisible() {
        return isElementVisible(messageSentNotification);
    }


}
