package lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses;

import lh.juicecompany.Pages.Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewAddress extends Home {

    @FindBy(xpath = "//input[@placeholder='Please provide a country.']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@placeholder='Please provide a name.']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Please provide a mobile number.']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//input[@placeholder='Please provide a ZIP code.']")
    private WebElement zipCodeField;

    @FindBy(xpath = "//textarea[@placeholder='Please provide an address.']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@placeholder='Please provide a city.']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@placeholder='Please provide a state.']")
    private WebElement stateField;

    @FindBy(xpath = "//button//span[contains(text(),'Back')]/..")
    private WebElement backButton;

    @FindBy(xpath = "//button[@id='submitButton']")
    private WebElement submitButton;

    public AddNewAddress(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewAddress fillCountry(String country) {
        commonMethods.sendKeysToElement(countryField, country);
        return this;
    }

    public AddNewAddress fillName(String name) {
        commonMethods.sendKeysToElement(nameField, name);
        return this;
    }

    public AddNewAddress fillMobileNumber(String mobileNumber) {
        commonMethods.sendKeysToElement(mobileNumberField, mobileNumber);
        return this;
    }

    public AddNewAddress fillZipCode(String zipCode) {
        commonMethods.sendKeysToElement(zipCodeField, zipCode);
        return this;
    }

    public AddNewAddress fillAddress(String address) {
        commonMethods.sendKeysToElement(addressField, address);
        return this;
    }

    public AddNewAddress fillCity(String city) {
        commonMethods.sendKeysToElement(cityField, city);
        return this;
    }

    public AddNewAddress fillState(String state) {
        commonMethods.sendKeysToElement(stateField, state);
        return this;
    }

    public MySavedAddresses submitButtonClick() {
        commonMethods.clickElement(submitButton);
        return new MySavedAddresses(webDriver);
    }
}
