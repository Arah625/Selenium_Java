package lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses;

import lh.juicecompany.Pages.Home;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MySavedAddresses extends Home {

    @FindBy(xpath = "//h1[contains(text(),'My saved addresses')]")
    private WebElement savedAddressesPageHeader;
    @FindBy(xpath = "//button[@aria-label='Add a new address']")
    private WebElement addNewAddressButton;
    @FindBy(xpath = "//button//span//*[@data-icon='edit']")
    private WebElement editButton;
    @FindBy(xpath = "//button//span//*[@data-icon='trash-alt']")
    private WebElement deleteButton;
    @FindBy(xpath = "//mat-row//mat-cell[contains(@class,'column-Name')]")
    private List<WebElement> columnNameCell;
    @FindBy(xpath = "//mat-row//mat-cell[contains(@class,'column-Address')]")
    private List<WebElement> columnAddressCell;
    @FindBy(xpath = "//mat-row//mat-cell[contains(@class,'column-Country')]")
    private List<WebElement> columnCountryCell;
    @FindBy(xpath = "//mat-row//mat-cell[contains(@class,'column-Remove')]//button")
    private List<WebElement> columnRemoveCell;
    @FindBy(xpath = "//simple-snack-bar//span[contains(text(),'Your address has been removed.')]")
    private WebElement addressRemovedAlert;
    @FindBy(xpath = "//simple-snack-bar//div//button//span[contains(text(),'X')]")
    private WebElement addressRemovedAlertCloseButton;

    public MySavedAddresses(WebDriver webDriver) {
        super(webDriver);
    }

    private static By addressAddedMessage(String address) {
        return By.xpath("//span[contains(text(),'The address at " + address + " has been successfully added to your addresses.')]");
    }

    public AddNewAddress addNewAddressButtonClick() {
        commonMethods.clickElement(addNewAddressButton);
        return new AddNewAddress(webDriver);
    }

    public boolean isSavedAddressesPageHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(savedAddressesPageHeader);
    }

    public boolean isAddressRemovedAlertVisible() {
        return elementVisibilityHandler.invisibilityOfElement(addressRemovedAlert);
    }

    public MySavedAddresses addressRemovedAlertCloseButtonClick() {
        commonMethods.clickElement(addressRemovedAlertCloseButton);
        return this;
    }

    public boolean isAddressAddedMessageVisible(String address) {
        return elementVisibilityHandler.isElementVisible(addressAddedMessage(address));
    }

    public MySavedAddresses removeEachAddressOnList() {
        if (columnRemoveCell.isEmpty()) {
            return this;
        }
        List<WebElement> listOfAddressesToRemove = elementFinder.findElementsBy(columnRemoveCell);
        while (!listOfAddressesToRemove.isEmpty()) {
            WebElement buttonToClick = listOfAddressesToRemove.get(0);
            commonMethods.clickElement(buttonToClick);
            addressRemovedAlertCloseButtonClick();
            try {
                listOfAddressesToRemove = elementFinder.findElementsBy(columnRemoveCell);
            } catch (TimeoutException e) {
                break;
            }
        }
        return this;
    }

    public boolean isAddressListEmpty() {
        return columnRemoveCell.isEmpty();
    }
}
