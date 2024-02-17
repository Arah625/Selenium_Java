package Tests;

import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import Utilities.CredentialManager;
import com.github.javafaker.CreditCardType;
import lh.juicecompany.PageUtilities.IntegerUtilities;
import lh.juicecompany.PageUtilities.StringUtilities;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MyPaymentOptions;
import lh.juicecompany.Pages.Google.SelectAccount;
import org.testng.Assert;
import org.testng.annotations.Test;
import reports.RetryAnalyzer;

public class Cards extends BaseTest {
    private Login login;
    private MyPaymentOptions myPaymentOptions;
    private SelectAccount selectAccount;

    @Test(priority = 5, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, retryAnalyzer = RetryAnalyzer.class, description = "Add credit to account")
    public void addCreditCardToAccount() {
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
        String cardNumber = StringUtilities.leaveOnlyDigits(faker.finance().creditCard(CreditCardType.MASTERCARD));
        myPaymentOptions.fillCardNumber(cardNumber);
        int month = IntegerUtilities.getRandomInclusive(1, 12);
        myPaymentOptions.selectExpiryMonth(month);
        int year = IntegerUtilities.getRandomInclusive(2080, 2099);
        myPaymentOptions.selectExpiryYear(year);
        myPaymentOptions.submitButtonClick();
        Assert.assertTrue(myPaymentOptions.isCardSavedGhostMessageVisible(cardNumber));
        Assert.assertTrue(myPaymentOptions.areCardsLastFourDigitsVisible(cardNumber));
        Assert.assertTrue(myPaymentOptions.isCardOwnersNameVisible(cardOwner));
        Assert.assertTrue(myPaymentOptions.isCardExpiryDateVisible(month, year));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "CARD OWNER", cardOwner, "CARD NUMBER", cardNumber, "EXPIRY MONTH", month, "EXPIRY YEAR", year);
    }
}
