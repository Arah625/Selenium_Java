package Tests.Account;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import Utilities.CredentialManager;
import lh.juicecompany.PageUtilities.StringUtilities;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.AddNewAddress;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.MySavedAddresses;
import org.testng.Assert;
import org.testng.annotations.Test;
import reports.RetryAnalyzer;

import java.util.concurrent.atomic.AtomicInteger;

public class SavedAddresses extends BaseTest {

    private Login login;
    private MySavedAddresses mySavedAddresses;
    private AddNewAddress addNewAddress;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, retryAnalyzer = RetryAnalyzer.class, description = "Add new address")
    public void addNewAddress() {
        login = home.goToLogin();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        login.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        login.fillPassword(password);
        home = login.loginButtonClick();
        mySavedAddresses = home.goToMySavedAddresses();
        Assert.assertTrue(mySavedAddresses.isSavedAddressesPageHeaderVisible(), VisibilityMessage.headerIsNotVisible("My saved addresses"));
        addNewAddress = mySavedAddresses.addNewAddressButtonClick();
        String country = faker.address().country();
        addNewAddress.fillCountry(country);
        String name = faker.name().fullName();
        addNewAddress.fillName(name);
        String mobileNumber = StringUtilities.leaveOnlyDigits(faker.phoneNumber().subscriberNumber(9));
        addNewAddress.fillMobileNumber(mobileNumber);
        String zipCode = faker.address().zipCodeByState(faker.address().stateAbbr());
        addNewAddress.fillZipCode(zipCode);
        String address = faker.address().fullAddress();
        addNewAddress.fillAddress(address);
        String city = faker.address().city();
        addNewAddress.fillCity(city);
        String state = faker.address().state();
        addNewAddress.fillState(state);
        mySavedAddresses = addNewAddress.submitButtonClick();
        Assert.assertTrue(mySavedAddresses.isAddressAddedMessageVisible(city), VisibilityMessage.ghostMessageIsVisible("The address at " + address + " has been successfully added to your addresses."));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "COUNTRY", country, "NAME", name, "MOBILE NUMBER", mobileNumber, "ZIP CODE", zipCode, "ADDRESS", address, "CITY", city, "STATE", state);
    }

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, retryAnalyzer = RetryAnalyzer.class, description = "Delete all addresses")
    public void deleteAllAddresses() {
        login = home.goToLogin();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        login.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        login.fillPassword(password);
        home = login.loginButtonClick();
        mySavedAddresses = home.goToMySavedAddresses();
        Assert.assertTrue(mySavedAddresses.isSavedAddressesPageHeaderVisible(), VisibilityMessage.headerIsNotVisible("My saved addresses"));
        AtomicInteger iterationCounter = new AtomicInteger(1); // For thread-safe increment if running tests in parallel
        repeatAction(() -> {
            int currentIteration = iterationCounter.getAndIncrement();

            addNewAddress = mySavedAddresses.addNewAddressButtonClick();
            String country = faker.address().country();
            String name = faker.name().fullName();
            String mobileNumber = StringUtilities.leaveOnlyDigits(faker.phoneNumber().subscriberNumber(9));
            String zipCode = faker.address().zipCodeByState(faker.address().stateAbbr());
            String address = faker.address().fullAddress();
            String city = faker.address().city();
            String state = faker.address().state();
            addNewAddress.fillAddNewAddressForm(country, name, mobileNumber, zipCode, address, city, state);
            mySavedAddresses = addNewAddress.submitButtonClick();
            Assert.assertTrue(mySavedAddresses.isAddressAddedMessageVisible(city), VisibilityMessage.ghostMessageIsVisible("The address at " + address + " has been successfully added to your addresses."));
            System.out.println("Logging iteration: " + currentIteration); // Debugging statement
            TestLogger.logIterationVariables(currentIteration, // You need to manage this variable to track the current iteration
                    "Country", country,
                    "Name", name,
                    "Mobile Number", mobileNumber,
                    "Zip Code", zipCode,
                    "Address", address,
                    "City", city,
                    "State", state);
        }, 5);
        Assert.assertFalse(mySavedAddresses.isAddressListEmpty(), VisibilityMessage.listOfElementsIsEmpty("Address"));
        mySavedAddresses.removeEachAddressOnList();
        Assert.assertTrue(mySavedAddresses.isAddressListEmpty(), VisibilityMessage.listOfElementsIsNotEmpty("Address"));
    }
}
