package Tests.StandardMode.Account;

import AssertMessages.VisibilityMessage;
import Setup.TestGroup;
import Tests.StandardMode.PreparationScripts.AccountCreationInit;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Google.SelectAccount;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginToAccount extends AccountCreationInit {
    private Login login;
    private SelectAccount selectAccount;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.LOW})
    public void checkFieldAlertsForNullValues() {
        testCaseName("Fields validation alert for null values");
        login = home.goToLogin();
        login.fillEmail("");
        login.fillPassword("");
        login.rememberMeCheckboxCheck();
        Assert.assertTrue(login.isEmptyEmailFieldValidationAlertVisible(), VisibilityMessage.fieldValidationAlertIsNotVisible("Please provide an email address."));
        Assert.assertTrue(login.isEmptyPasswordFieldValidationAlertVisible(), VisibilityMessage.fieldValidationAlertIsNotVisible("Please provide a password."));
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void checkWrongEmailOrPasswordValidation() {
        testCaseName("Validation for wrong email or password");
        login = home.goToLogin();
        login.fillEmail(faker.internet().emailAddress());
        login.fillPassword(faker.internet().password());
        login.loginButtonClick();
        Assert.assertTrue(login.isInvalidEmailOrPasswordAlertVisible(), VisibilityMessage.alertIsNotVisible("Invalid email or password."));
    }

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void signInWithGoogleAccount() {
        testCaseName("Go to Sign in with Google account page");
        login = home.goToLogin();
        selectAccount = login.loginWithGoogleAccountButtonClick();
        Assert.assertTrue(selectAccount.isSignInWithGoogleAccountModalHeaderVisible(), VisibilityMessage.modalHeaderIsNotVisible("Sign in with Google"));
        Assert.assertTrue(selectAccount.isSignInWithGoogleAccountHeaderVisible(), VisibilityMessage.headerIsNotVisible("Choose an account"));
    }

    @Test(priority = 4, groups = {TestGroup.REGRESSION, TestGroup.LOW})
    public void signInWithGoogleAccountGoBackToPreviousPage() {
        testCaseName("Go to Sign in with Google account page and go back to Juice Shop home page");
        login = home.goToLogin();
        selectAccount = login.loginWithGoogleAccountButtonClick();
        Assert.assertTrue(selectAccount.isSignInWithGoogleAccountModalHeaderVisible(), VisibilityMessage.modalHeaderIsNotVisible("Sign in with Google"));
        Assert.assertTrue(selectAccount.isSignInWithGoogleAccountHeaderVisible(), VisibilityMessage.headerIsNotVisible("Choose an account"));
        selectAccount.goToPreviousPage();
        Assert.assertTrue(home.isAllProductsHeaderVisible(), VisibilityMessage.headerIsNotVisible("All Products"));
    }

    /**
     * Test uses {@link Test#dependsOnMethods()} to call preparation script to create account first.
     * Annotation {@link Test#alwaysRun()} makes sure that this test is always executed, even if the account cannot be created
     */
    @Test(priority = 5, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, dependsOnMethods = {"createAccount"}, alwaysRun = true)
    public void loginToAccount() {
        testCaseName("Login to account");
        login = home.goToLogin();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        login.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        login.fillPassword(password);
        home = login.loginButtonClick();
        Assert.assertTrue(home.isYourBasketButtonVisible(), VisibilityMessage.buttonIsNotVisible("Your Basket"));
        Assert.assertFalse(home.isLoginButtonVisible(), VisibilityMessage.buttonIsVisible("Login"));
        Assert.assertTrue(home.isLogoutButtonVisible(), VisibilityMessage.buttonIsVisible("Logout"));
    }
}
