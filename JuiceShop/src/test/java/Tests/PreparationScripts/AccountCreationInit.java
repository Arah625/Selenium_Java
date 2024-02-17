package Tests.PreparationScripts;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.Register;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class containing scripts that are used to set up required conditions for actual tests
 * and be invoked by TestNG's annotations like {@link Test#dependsOnMethods()}, or {@link Test#dependsOnGroups()} before the actual test is run
 */

public class AccountCreationInit extends BaseTest {

    private Login login;
    private Register register;

    @Test(priority = 1, groups = {TestGroup.PREREQUISITE}, description = "Create account")
    public void createAccount() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = CredentialManager.credentialReader("validEmailAddress");
        register.fillEmail(emailAddress);
        String password = CredentialManager.credentialReader("validPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        String secretQuestion = "Company you first work for as an adult?";
        register.selectSecretQuestion(secretQuestion);
        String secretQuestionAnswer = faker.company().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "SECRET QUESTION", secretQuestion, "SECRET QUESTION ANSWER", secretQuestionAnswer);
    }
}
