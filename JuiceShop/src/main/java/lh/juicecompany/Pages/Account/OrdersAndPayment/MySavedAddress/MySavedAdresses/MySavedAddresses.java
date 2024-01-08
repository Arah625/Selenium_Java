package lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses;

import lh.juicecompany.Pages.Home;
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


    public MySavedAddresses(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewAddress addNewAddressButtonClick() {
        clickElement(addNewAddressButton);
        return new AddNewAddress(webDriver);
    }

    public boolean isSavedAddressesPageHeaderVisible() {
        return isElementVisible(savedAddressesPageHeader);
    }


}
