package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WriteMessagePage extends BasePage{
    public WriteMessagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//button//*[contains(text(),'Wyślij')]")
    WebElement sendMessageButton;

    @FindBy (xpath = "//*[@id = 'draftComponent']//*[contains(text(),'Do')]/..//input")
    WebElement messageRecipientField;

    @FindBy (xpath = "//*[@id = 'draftComponent']//*[contains(text(),'Temat')]/..//input")
    WebElement messageTitleField;

    @FindBy (xpath = "//*[@class='DraftEditor-editorContainer']/div")
    WebElement messageField;

    @FindBy (xpath = "//button[contains(text(),'Załącz pliki')]")
    WebElement attachFilesFromDrive;

    public void fillRecipientEmailAddress(String recipientEmailAddress) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(messageRecipientField));
        messageRecipientField.clear();
        messageRecipientField.sendKeys(recipientEmailAddress);
    }

    public void fillMessageTitle(String messageTitle) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(messageTitleField));
        messageTitleField.clear();
        messageTitleField.sendKeys(messageTitle);
    }

    public void fillMessage(String message) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(messageField));
        messageField.clear();
        messageField.sendKeys(message);
    }

    public boolean isSendMessageButtonVisible(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(sendMessageButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public EmailAccountPage sendMessageButtonClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendMessageButton.click();
        return new EmailAccountPage(driver);
    }

}
