package Tests.Account;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import Utilities.CredentialManager;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.Register;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUp extends BaseTest {

    private Login login;
    private Register register;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Create random account")
    public void createRandomAccount() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        String password = faker.internet().password();
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        String secretQuestion = "Your eldest siblings middle name?";
        register.selectSecretQuestion(secretQuestion);
        String secretQuestionAnswer = faker.name().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "SECRET QUESTION", secretQuestion, "SECRET QUESTION ANSWER", secretQuestionAnswer);
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Create account")
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

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Unique email address required - cannot create account")
    public void uniqueEmailAddressRequired() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        String password = faker.internet().password();
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        String secretQuestion = "Your eldest siblings middle name?";
        register.selectSecretQuestion(secretQuestion);
        String answer = faker.name().name();
        register.fillAnswer(answer);
        login = register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        Assert.assertTrue(login.isRegistrationSuccessfulInvisible(), "Ghost message 'Registration completed successfully. You can now log in.' is still visible");
        register = login.notYetCustomerButtonClick();
        register.fillEmail(emailAddress);
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        register.selectSecretQuestion(secretQuestion);
        register.fillAnswer(answer);
        login = register.registerButtonClick();
        Assert.assertFalse(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsVisible("Registration completed successfully. You can now log in."));
        Assert.assertTrue(register.isUniqueEmailRequiredAlertVisible(), VisibilityMessage.alertIsVisible("Email must be unique"));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "SECRET QUESTION", secretQuestion, "SECRET QUESTION ANSWER", answer);
    }

    @Test(priority = 4, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check password hints")
    public void checkPasswordHints() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        Assert.assertTrue(register.isNumberOfPasswordHintsVisible(), "Number of hints not correct!");
        String password = faker.internet().password(8, 40, true, true, true);
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(true, true, true, true, true));
        String secretQuestion = "Company you first work for as an adult?";
        register.selectSecretQuestion(secretQuestion);
        String secretQuestionAnswer = faker.company().name();
        register.fillAnswer(secretQuestionAnswer);
        register.registerButtonClick();
        Assert.assertTrue(login.isRegistrationSuccessfulVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password, "SECRET QUESTION", secretQuestion, "SECRET QUESTION ANSWER", secretQuestionAnswer);
    }

    @Test(priority = 5, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Check hint and validation for too short password")
    public void checkPasswordTooShortValidation() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        String password = CredentialManager.credentialReader("tooShortPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(true, true, true, true, false));
        Assert.assertTrue(register.isTooShortPasswordAlertVisible(), VisibilityMessage.ghostMessageIsNotVisible("Registration completed successfully. You can now log in."));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password);
    }

    @Test(priority = 6, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Check hint for missing lower case letter in password")
    public void checkPasswordMissingLowerCaseLetterValidation() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        String password = CredentialManager.credentialReader("noLowerCaseLetterPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(false, true, true, true, true));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password);
    }

    @Test(priority = 7, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Check hint for missing upper case letter in password")
    public void checkPasswordMissingUpperCaseLetterValidation() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        String password = CredentialManager.credentialReader("noUpperCaseLetterPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(true, false, true, true, true));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password);
    }

    @Test(priority = 8, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Check hint for missing special character in password")
    public void checkPasswordMissingSpecialCharacterValidation() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        String password = CredentialManager.credentialReader("noSpecialCharacterPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(true, true, true, false, true));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password);
    }

    @Test(priority = 9, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Check hint for missing digit in password")
    public void checkPasswordMissingDigitValidation() {
        login = home.goToLogin();
        register = login.notYetCustomerButtonClick();
        String emailAddress = faker.internet().emailAddress();
        register.fillEmail(emailAddress);
        register.showPasswordAdvice();
        Assert.assertTrue(register.arePasswordHintsVisible(), "Hints not visible!");
        String password = CredentialManager.credentialReader("noDigitCharacterPassword");
        register.fillPassword(password);
        register.fillRepeatPassword(password);
        Assert.assertTrue(register.arePasswordHintsStatusesCorrect(true, true, false, true, true));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", password);
    }
}