package lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress;

import lh.juicecompany.PageUtilities.Action.Element.DropdownButton;
import lh.juicecompany.PageUtilities.SelectOption;
import lh.juicecompany.PageUtilities.StringUtilities;
import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPaymentOptions extends BasicPage {

    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]")
    private WebElement myPaymentOptionsHeader;
    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]/..//mat-row")
    private WebElement tableRow;
    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]/..//mat-cell[contains(@class,'Number')]")
    private WebElement tableNumberCell;
    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]/..//mat-cell[contains(@class,'Name')]")
    private WebElement tableNameCell;
    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]/..//mat-cell[contains(@class,'Expiry')]")
    private WebElement tableExpiryCell;
    @FindBy(xpath = "//h1[contains(text(),'My Payment Options')]/..//mat-cell[contains(@class,'Remove')]//button")
    private WebElement tableRemoveCell;
    @FindBy(xpath = "//mat-expansion-panel-header[@id='mat-expansion-panel-header-0']")
    private WebElement addNewCardDropdownButton;

    @FindBy(xpath = "//mat-label[contains(text(),'Name')]/../../..//input")
    private WebElement nameField;

    @FindBy(xpath = "//mat-label[contains(text(),'Card Number')]/../../..//input")
    private WebElement cardNumberField;

    @FindBy(xpath = "//mat-label[contains(text(),'Expiry Month')]/../../..//select")
    private WebElement expiryMonthList;

    @FindBy(xpath = "//mat-label[contains(text(),'Expiry Year')]/../../..//select")
    private WebElement expiryYearList;

    @FindBy(xpath = "//button[@id='submitButton']")
    private WebElement submitButton;
    private DropdownButton dropdownButton;

    public MyPaymentOptions(WebDriver webDriver) {
        super(webDriver);
        this.dropdownButton = new DropdownButton();
    }

    private static By cardSavedGhostMessageXpath(String cardNumber) {
        return By.xpath("//span[contains(text(),'Your card ending with " + cardNumber + " has been saved for your convenience.')]");
    }

    public MyPaymentOptions expandAddNewCardDropdown() {
        dropdownButton.expand(addNewCardDropdownButton);
        return this;
    }

    public MyPaymentOptions closeAddNewCardDropdown() {
        dropdownButton.collapse(addNewCardDropdownButton);
        return this;
    }

    public MyPaymentOptions fillCardOwnerName(String name) {
        commonMethods.sendKeysToElement(nameField, name);
        return this;
    }

    public MyPaymentOptions fillCardNumber(String cardNumber) {
        commonMethods.sendKeysToElement(cardNumberField, cardNumber);
        return this;
    }

    public MyPaymentOptions selectExpiryMonth() {
        SelectOption.byRandomIndexFromList(expiryMonthList);
        return this;
    }

    public MyPaymentOptions selectExpiryMonth(int month) {
        SelectOption.byIndexFromList(expiryMonthList, month);
        return this;
    }

    public MyPaymentOptions selectExpiryYear() {
        SelectOption.byRandomIndexFromList(expiryYearList);
        return this;
    }

    public MyPaymentOptions submitButtonClick() {
        commonMethods.clickElement(submitButton);
        return this;
    }

    public boolean isCardSavedGhostMessageVisible(String cardNumber) {
        return elementVisibilityHandler.isElementVisible(cardSavedGhostMessageXpath(StringUtilities.getLastNumberOfCharacters(cardNumber, 4)));
    }


}
