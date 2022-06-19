package Onet.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailAccountPage extends BasePage{

    public EmailAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//header//button/span/span")
    WebElement emailAddress;

    @FindBy (xpath = "//span[contains(text(),'Napisz wiadomość')]/../..")
    WebElement writeMessageButton;

    @FindBy (xpath = "//span[contains(text(),'Odebrane')]/../..")
    WebElement receivedMessagesTab;

    @FindBy (xpath = "//button[@id = 'inbox-refresh']/..")
    WebElement refreshInboxButton;

    @FindBy (xpath = "//span[contains(text(),'Społeczności')]/../..")
    WebElement communityTab;

    @FindBy (xpath = "//span[contains(text(),'Oferty')]/../..")
    WebElement offersTab;

    @FindBy (xpath = "//span[contains(text(),'Powiadomienia')]/../..")
    WebElement notificationsTab;

    @FindBy (xpath = "//span[contains(text(),'E-recepty')]/../..")
    WebElement ePrescriptionsTab;

    @FindBy (xpath = "//span[contains(text(),'E-płatności')]/../..")
    WebElement ePaymentsTab;

    @FindBy (xpath = "//*[contains(@id,'attachments')]/..//*[contains(text(),'Załączniki')]/../..")
    WebElement attachmentsTab;

    @FindBy (xpath = "//*[contains(@id,'attachments')]")
    WebElement attachmentsDropdownArrow;

    @FindBy (xpath = "//*[@title = 'Zdjęcia']")
    WebElement photosAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Dokumenty']")
    WebElement documentsAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Wideo']")
    WebElement videoAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Muzyka']")
    WebElement musicAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Prezentacje']")
    WebElement presentationsAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Archiwa']")
    WebElement archivesAttachmentsTab;

    @FindBy (xpath = "//*[@title = 'Inne']")
    WebElement othersAttachmentsTab;

    @FindBy (xpath = "//span[contains(text(),'Kosz')]/../..")
    WebElement binTab;

    @FindBy (xpath = "//span[contains(text(),'Wysłane')]/../..")
    WebElement sentTab;

    @FindBy (xpath = "//span[contains(text(),'SPAM')]/../..")
    WebElement spamTab;

    @FindBy (xpath = "//span[contains(text(),'Szkice')]/../..")
    WebElement draftsTab;

    @FindBy (xpath = "//span[contains(text(),'Foldery')]/../..")
    WebElement foldersTab;

    @FindBy (xpath = "//*[@id = 'field-recipient-to']//input")
    WebElement recipientInputField;

    @FindBy (xpath = "//*[@id = 'field-subject']//input")
    WebElement subjectInputField;

    @FindBy (xpath = "//button[@title = 'Użyj edytora tekstu']")
    WebElement useTextEditorButton;

    @FindBy (xpath = "//*[@id='wrapper']//textarea")
    WebElement messageInputFieldInTextEditor;

    @FindBy (xpath = "//button[@title = 'Powrót']")
    WebElement returnButton;

    @FindBy (xpath = "//button[@title = 'Wyślij']")
    WebElement sendEmailButton;

    @FindBy (xpath = "//button[@title = 'Zapisz szkic']")
    WebElement saveEmailDraftButton;

    @FindBy (xpath = "//button[@title = 'Anuluj']")
    WebElement cancelEmailButton;

    @FindBy (xpath = "//span[contains(text(),'Mail został wysłany')]")
    WebElement emailSentNotification;

    @FindBy (xpath = "//a[@title ='Wyloguj']")
    WebElement logoutButton;


    public String getEmailAddressFromEmailAccountPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailAddress));
        return emailAddress.getText();
    }

    public boolean isWriteMessageButtonVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(writeMessageButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void writeMessageButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        writeMessageButton.click();
    }

    public void useTextEditorButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(useTextEditorButton));
        useTextEditorButton.click();
    }

    public void receivedMessagesTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(receivedMessagesTab));
        receivedMessagesTab.click();
    }

    public void refreshInboxButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(refreshInboxButton));
        refreshInboxButton.click();
    }

    public void communityTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(communityTab));
        communityTab.click();
    }

    public void offersTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(offersTab));
        offersTab.click();
    }

    public void notificationsTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(notificationsTab));
        notificationsTab.click();
    }

    public void ePrescriptionsTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ePrescriptionsTab));
        ePrescriptionsTab.click();
    }

    public void ePaymentsTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ePaymentsTab));
        ePaymentsTab.click();
    }

    public void attachmentsTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(attachmentsTab));
        attachmentsTab.click();
        attachmentsTab.click();
    }

    public void attachmentsByTypeTabClick(String attachmentType) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(attachmentsTab));
        attachmentsTab.click();
        switch (attachmentType){
            case "Zdjęcia":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(photosAttachmentsTab));
                photosAttachmentsTab.click();
                break;
            case "Dokumenty":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(documentsAttachmentsTab));
                documentsAttachmentsTab.click();
                break;
            case "Wideo":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(videoAttachmentsTab));
                videoAttachmentsTab.click();
                break;
            case "Muzyka":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(musicAttachmentsTab));
                musicAttachmentsTab.click();
                break;
            case "Prezentacje":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(presentationsAttachmentsTab));
                presentationsAttachmentsTab.click();
                break;
            case "Archiwa":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(archivesAttachmentsTab));
                archivesAttachmentsTab.click();
                break;
            case "Inne":
                webDriverWait.until(ExpectedConditions.elementToBeClickable(othersAttachmentsTab));
                othersAttachmentsTab.click();
                break;
        }
    }

    public void attachmentsDropdownArrowClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(attachmentsDropdownArrow));
        attachmentsDropdownArrow.click();
    }

    public void binTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(binTab));
        binTab.click();
    }

    public void sentTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(sentTab));
        sentTab.click();
    }

    public void spamTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(spamTab));
        spamTab.click();
    }

    public void draftsTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftsTab));
        draftsTab.click();
    }

    public void foldersTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(foldersTab));
        foldersTab.click();
    }

    public void fillRecipient(String recipient) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(recipientInputField));
        recipientInputField.clear();
        recipientInputField.sendKeys(recipient);
        recipientInputField.click();
    }

    public void fillSubject(String subject) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectInputField));
        subjectInputField.clear();
        subjectInputField.sendKeys(subject);
    }

    public void fillMessage(String message) {
        webDriverWait.until(ExpectedConditions.visibilityOf(messageInputFieldInTextEditor));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(messageInputFieldInTextEditor));
        messageInputFieldInTextEditor.clear();
        messageInputFieldInTextEditor.sendKeys(message);
    }

    public void returnButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(returnButton));
        returnButton.click();
    }

    public void sendEmailButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(sendEmailButton));
        sendEmailButton.click();
    }

    public void saveEmailDraftButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveEmailDraftButton));
        saveEmailDraftButton.click();
    }

    public void cancelEmailButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(cancelEmailButton));
        cancelEmailButton.click();
    }

    public boolean isEmailSentNotificationVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(emailSentNotification)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLogoutButtonVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public ProfilePage avatarIconClick(String emailAddress) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        WebElement avatarIcon = driver.findElement(By.xpath("//a[@title ='" + emailAddress + "']//img"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(avatarIcon));
        avatarIcon.click();
        return new ProfilePage(driver);
    }

}
