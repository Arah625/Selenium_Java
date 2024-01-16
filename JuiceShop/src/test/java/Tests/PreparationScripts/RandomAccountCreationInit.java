package Tests.PreparationScripts;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.Register;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class containing scripts that are used to set up required conditions for actual tests
 * and be invoked by TestNG's annotations like {@link Test#dependsOnMethods()}, or {@link Test#dependsOnGroups()} before the actual test is run
 */

public class RandomAccountCreationInit extends BaseTest {

    protected String emailAddress;
    protected String validPassword = CredentialManager.credentialReader("validPassword");
    protected String secretQuestionAnswer;
    private Login login;
    private Register register;


    @Test(priority = 2, groups = {TestGroup.PREREQUISITE})
    public void createRandomAccount() {
        testCaseName("Create random account");
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.fillPassword(validPassword);
        register.fillRepeatPassword(validPassword);
        register.selectSecretQuestion("Company you first work for as an adult?");
        secretQuestionAnswer = faker.company().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
    }

}
