package Tests;

import Setup.BaseTest;
import Setup.TestGroup;
import Utilities.CredentialManager;
import com.github.javafaker.CreditCardType;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MyPaymentOptions;
import lh.juicecompany.Pages.Google.SelectAccount;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Cards extends BaseTest {
    private Login login;
    private MyPaymentOptions myPaymentOptions;
    private SelectAccount selectAccount;
    
    @Test(priority = 5, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL})
    public void loginToAccount() {
        testCaseName("Login to account");
        login = home.goToLogin();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        login.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        login.fillPassword(password);
        home = login.loginButtonClick();
        myPaymentOptions = home.goTomyPaymentOptions();
        myPaymentOptions.expandAddNewCardDropdown();
        String cardOwner = faker.name().firstName() + " " + faker.name().lastName();
        myPaymentOptions.fillCardOwnerName(cardOwner);
        String cardNumber = faker.finance().creditCard(CreditCardType.MASTERCARD).replace("-", "");
        myPaymentOptions.fillCardNumber(cardNumber);
        myPaymentOptions.selectExpiryMonth();
        myPaymentOptions.selectExpiryYear();
        myPaymentOptions.submitButtonClick();
        Assert.assertTrue(myPaymentOptions.isCardSavedGhostMessageVisible(cardNumber));
    }
}
