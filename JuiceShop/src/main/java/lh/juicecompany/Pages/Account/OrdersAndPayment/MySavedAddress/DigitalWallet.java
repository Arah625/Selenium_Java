package lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DigitalWallet extends BasicPage {

    @FindBy(xpath = "//h1/div[contains(text(),'Digital Wallet')]")
    private WebElement digitalWalletHeader;

    @FindBy(xpath = "//input[@aria-label='Enter an amount']")
    private WebElement amountField;

    @FindBy(xpath = "//button[@id='submitButton']")
    private WebElement continueButton;

    public DigitalWallet(WebDriver webDriver) {
        super(webDriver);
    }
}
