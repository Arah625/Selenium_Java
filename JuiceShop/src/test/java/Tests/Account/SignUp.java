package Tests.Account;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.Register;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUp extends BaseTest {

    private Login login;
    private Register register;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL})
    public void createRandomAccount() {
        testCaseName("Create random account");
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        String password = faker.internet().password();
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        register.selectSecretQuestion("Your eldest siblings middle name?");
        String secretQuestionAnswer = faker.name().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL})
    public void createAccount() {
        testCaseName("Create account");
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        register.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        register.selectSecretQuestion("Company you first work for as an adult?");
        String secretQuestionAnswer = faker.company().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
    }

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL})
    public void uniqueEmailAddressRequired() {
        testCaseName("Unique email address required - cannot create account");
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        String password = faker.internet().password();
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        register.selectSecretQuestion("Your eldest siblings middle name?");
        String answer = faker.name().name();
        register.fillAnswer(answer);
        login = register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        Assert.assertTrue(login.isRegistrationSuccessfulInvisible(), "Ghost message 'Registration completed successfully. You can now log in.' is still visible");
        register = login.notYetCustomerButtonClick();
        register.fillEmail(emailAddress);
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        register.selectSecretQuestion("Your eldest siblings middle name?");
        register.fillAnswer(answer);
        login = register.registerButtonClick();
        Assert.assertFalse(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsVisible("Registration completed successfully. You can now log in."));
        Assert.assertTrue(register.isUniqueEmailRequiredAlertVisible(), VisibilityMessage.alertIsVisible("Email must be unique"));
    }
}
