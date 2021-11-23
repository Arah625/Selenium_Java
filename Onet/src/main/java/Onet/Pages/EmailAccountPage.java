package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailAccountPage extends BasePage{

    public EmailAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//span[contains(text(),'Napisz wiadomość')]/../..")
    WebElement writeMessageButton;

    @FindBy (xpath = "//span[contains(text(),'Odebrane')]/../..")
    WebElement receivedMessagesTab;

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




    public void writeMessageButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        writeMessageButton.click();
    }

    public void receivedMessagesTabClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(receivedMessagesTab));
        receivedMessagesTab.click();
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

}
