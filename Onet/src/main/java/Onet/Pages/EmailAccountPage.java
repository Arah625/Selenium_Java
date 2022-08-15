package Onet.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailAccountPage extends BasePage {

    public EmailAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//header//button/span/span")
    WebElement emailAddress;

//    @FindBy(xpath = "//span[contains(text(),'Napisz wiadomość')]/../..")
    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[3]/aside/div[1]/div/span")
    WebElement writeMessageButton;

    @FindBy(xpath = "//span[contains(text(),'Odebrane')]/../..")
    WebElement receivedMessagesTab;

    @FindBy(xpath = "//button[@id = 'inbox-refresh']/..")
    WebElement refreshInboxButton;

    @FindBy(xpath = "//span[contains(text(),'Społeczności')]/../..")
    WebElement communityTab;

    @FindBy(xpath = "//span[contains(text(),'Oferty')]/../..")
    WebElement offersTab;

    @FindBy(xpath = "//span[contains(text(),'Powiadomienia')]/../..")
    WebElement notificationsTab;

    @FindBy(xpath = "//span[contains(text(),'E-recepty')]/../..")
    WebElement ePrescriptionsTab;

    @FindBy(xpath = "//span[contains(text(),'E-płatności')]/../..")
    WebElement ePaymentsTab;

    @FindBy(xpath = "//*[contains(@id,'attachments')]/..//*[contains(text(),'Załączniki')]/../..")
    WebElement attachmentsTab;

    @FindBy(xpath = "//*[contains(@id,'attachments')]")
    WebElement attachmentsDropdownArrow;

    @FindBy(xpath = "//*[@title = 'Zdjęcia']")
    WebElement photosAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Dokumenty']")
    WebElement documentsAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Wideo']")
    WebElement videoAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Muzyka']")
    WebElement musicAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Prezentacje']")
    WebElement presentationsAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Archiwa']")
    WebElement archivesAttachmentsTab;

    @FindBy(xpath = "//*[@title = 'Inne']")
    WebElement othersAttachmentsTab;

    @FindBy(xpath = "//span[contains(text(),'Kosz')]/../..")
    WebElement binTab;

    @FindBy(xpath = "//span[contains(text(),'Wysłane')]/../..")
    WebElement sentTab;

    @FindBy(xpath = "//span[contains(text(),'SPAM')]/../..")
    WebElement spamTab;

    @FindBy(xpath = "//span[contains(text(),'Szkice')]/../..")
    WebElement draftsTab;

    @FindBy(xpath = "//span[contains(text(),'Foldery')]/../..")
    WebElement foldersTab;

    @FindBy(xpath = "//*[@id = 'field-recipient-to']//input")
    WebElement recipientInputField;

    @FindBy(xpath = "//*[@id = 'field-subject']//input")
    WebElement subjectInputField;

    @FindBy(xpath = "//button[@title = 'Użyj edytora tekstu']")
    WebElement useTextEditorButton;

    @FindBy(xpath = "//*[@id='wrapper']//textarea")
    WebElement messageInputFieldInTextEditor;

    @FindBy(xpath = "//button[@title = 'Powrót']")
    WebElement returnButton;

    @FindBy(xpath = "//button[@title = 'Wyślij']")
    WebElement sendEmailButton;

    @FindBy(xpath = "//button[@title = 'Zapisz szkic']")
    WebElement saveEmailDraftButton;

    @FindBy(xpath = "//button[@title = 'Anuluj']")
    WebElement cancelEmailButton;

    @FindBy(xpath = "//span[contains(text(),'Mail został wysłany')]")
    WebElement emailSentNotification;

    @FindBy(xpath = "//a[@title ='Wyloguj']")
    WebElement logoutButton;

    @FindBy(xpath = "//button//span[contains(text(),'Dalej')]")
    WebElement submitButton;

    @FindBy(xpath = "//span[contains(text(),'Przypomnij później')]")
    WebElement remindLaterButton;

    @FindBy (xpath = "//span[contains(text(),'Chcę korzystać z Onet Poczty bezpłatnie')]")
    WebElement freeAccountUsageCheckbox;

    @FindBy (xpath = "//button//*[contains(text(),'Przejdź do poczty')]")
    WebElement acceptTermsAndContinueToEmailPageButton;


    public String getEmailAddressFromEmailAccountPage() {
        webDriverWaitDefault.until(ExpectedConditions.visibilityOf(emailAddress));
        return emailAddress.getText();
    }

    public boolean isWriteMessageButtonVisible() {
        return isElementVisible(writeMessageButton);
    }

    public void writeMessageButtonClick() throws Exception {
        clickElement(writeMessageButton);
    }

    public void useTextEditorButtonClick() throws Exception {
        clickElement(useTextEditorButton);
    }

    public void receivedMessagesTabClick() throws Exception {
        clickElement(receivedMessagesTab);
    }

    public void refreshInboxButtonClick() throws Exception {
        clickElement(refreshInboxButton);
    }

    public void communityTabClick() throws Exception {
        clickElement(communityTab);
    }

    public void offersTabClick() throws Exception {
        clickElement(offersTab);
    }

    public void notificationsTabClick() throws Exception {
        clickElement(notificationsTab);
    }

    public void ePrescriptionsTabClick() throws Exception {
        clickElement(ePrescriptionsTab);
    }

    public void ePaymentsTabClick() throws Exception {
        clickElement(ePaymentsTab);
    }

    public void attachmentsTabClick() throws Exception {
        clickElement(attachmentsTab);
    }

    public void attachmentsByTypeTabClick(String attachmentType) throws Exception {
        clickElement(attachmentsTab);
        switch (attachmentType) {
            case "Zdjęcia":
                clickElement(photosAttachmentsTab);
                break;
            case "Dokumenty":
                clickElement(documentsAttachmentsTab);
                break;
            case "Wideo":
                clickElement(videoAttachmentsTab);
                break;
            case "Muzyka":
                clickElement(musicAttachmentsTab);
                break;
            case "Prezentacje":
                clickElement(presentationsAttachmentsTab);
                break;
            case "Archiwa":
                clickElement(archivesAttachmentsTab);
                break;
            case "Inne":
                clickElement(othersAttachmentsTab);
                break;
        }
    }

    public void attachmentsDropdownArrowClick() throws Exception {
        clickElement(attachmentsDropdownArrow);
    }

    public void binTabClick() throws Exception {
        clickElement(binTab);
    }

    public void sentTabClick() throws Exception {
        clickElement(sentTab);
    }

    public void spamTabClick() throws Exception {
        clickElement(spamTab);
    }

    public void draftsTabClick() throws Exception {
        clickElement(draftsTab);
    }

    public void foldersTabClick() throws Exception {
        clickElement(foldersTab);
    }

    public void fillRecipient(String recipient) throws Exception {
        clearAndSendKeysToElement(recipientInputField, recipient);
        clickElement(recipientInputField);
    }

    public void fillSubject(String subject) {
        clearAndSendKeysToElement(subjectInputField, subject);
    }

    public void fillMessage(String message) {
        clearAndSendKeysToElement(messageInputFieldInTextEditor, message);
    }

    public void returnButtonClick() throws Exception {
        clickElement(returnButton);
    }

    public void sendEmailButtonClick() throws Exception {
        clickElement(sendEmailButton);
    }

    public void saveEmailDraftButtonClick() throws Exception {
        clickElement(saveEmailDraftButton);
    }

    public void cancelEmailButtonClick() throws Exception {
        clickElement(cancelEmailButton);
    }

    public boolean isEmailSentNotificationVisible() {
        return isElementVisible(emailSentNotification);
    }

    public boolean isLogoutButtonVisible() {
        return isElementVisible(logoutButton);
    }

    public ProfilePage avatarIconClick(String emailAddress) throws Exception {
        isElementVisible(logoutButton);
        WebElement avatarIcon = driver.findElement(By.xpath("//a[@title ='" + emailAddress + "']//img"));
        clickElement(avatarIcon);
        return new ProfilePage(driver);
    }

    public boolean isSubmitButtonVisible() {
        return isElementVisible(submitButton);
    }

    public void submitButtonClick() throws Exception {
        clickElement(submitButton);
    }

    public void remindLaterButtonClick() throws Exception {
        clickElement(remindLaterButton);
    }

    public boolean isFreeAccountUsageCheckboxVisible() {
        return isElementVisible(freeAccountUsageCheckbox);
    }

    public void freeAccountUsageCheckboxClick() throws Exception {
        clickElement(freeAccountUsageCheckbox);
    }

    public void acceptTermsAndContinueToEmailPageButtonClick() throws Exception {
        clickElement(acceptTermsAndContinueToEmailPageButton);
    }

    public void clickThroughDataConfirmation() throws Exception {
        if (isSubmitButtonVisible()) {
            try {
                submitButtonClick();
                remindLaterButtonClick();
            } catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        } if (isFreeAccountUsageCheckboxVisible()) {
            freeAccountUsageCheckboxClick();
            acceptTermsAndContinueToEmailPageButtonClick();
        }
    }

}
