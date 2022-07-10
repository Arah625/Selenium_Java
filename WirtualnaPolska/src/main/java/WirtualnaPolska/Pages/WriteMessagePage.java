package WirtualnaPolska.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WriteMessagePage extends BasePage {
    public WriteMessagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button//*[contains(text(),'Wyślij')]")
    WebElement sendMessageButton;

    @FindBy(xpath = "//*[@id = 'draftComponent']//*[contains(text(),'Do')]/..//input")
    WebElement messageRecipientField;

    @FindBy(xpath = "//*[@id = 'draftComponent']//*[contains(text(),'Temat')]/..//input")
    WebElement messageTitleField;

    @FindBy(xpath = "//*[@class='DraftEditor-editorContainer']/div")
    WebElement messageField;

    @FindBy(xpath = "//button[contains(text(),'Załącz pliki')]")
    WebElement attachFilesFromDrive;

    public void fillRecipientEmailAddress(String recipientEmailAddress) {
        clearAndSendKeysToElement(messageRecipientField, recipientEmailAddress);
    }

    public void fillMessageTitle(String messageTitle) {
        clearAndSendKeysToElement(messageTitleField, messageTitle);
    }

    public void fillMessage(String message) {
        clearAndSendKeysToElement(messageField, message);
    }

    public boolean isSendMessageButtonVisible() {
        return isElementVisible(sendMessageButton);
    }

    public EmailAccountPage sendMessageButtonClick() throws Exception {
        clickElement(sendMessageButton);
        return new EmailAccountPage(driver);
    }

}
