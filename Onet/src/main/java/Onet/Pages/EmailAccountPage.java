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

    @FindBy (xpath = "//span[contains(text(),'E-płatności')]/../..")
    WebElement ePaymentsTab;

    @FindBy (xpath = "//span[contains(text(),'Załączniki')]/../..")
    WebElement attachmentsTab;




    public void writeMessageButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        writeMessageButton.click();
    }

}
