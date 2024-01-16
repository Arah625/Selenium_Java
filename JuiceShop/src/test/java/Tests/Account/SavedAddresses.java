package Tests.Account;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.AddNewAddress;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.MySavedAddresses;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SavedAddresses extends BaseTest {

    private Login login;
    private MySavedAddresses mySavedAddresses;
    private AddNewAddress addNewAddress;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void goToMySavedAddressesPage() {
        testCaseName("Change language - check site elements");
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
        String mobileNumber = faker.phoneNumber().subscriberNumber(9).replaceAll("-", "").replaceAll("\\.", "");
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
    }
}
