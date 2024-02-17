package Tests.Account;

import AssertMessages.VisibilityMessage;
import Setup.TestGroup;
import Setup.TestLogger;
import Tests.PreparationScripts.RandomAccountCreationInit;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.PrivacyAndSecurity.RequestDataErasure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAccount extends RandomAccountCreationInit {
    private Login login;
    private RequestDataErasure dataErasureRequest;

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Delete account", dependsOnMethods = {"createRandomAccount"}, alwaysRun = true)
    public void deleteAccount() {
        login = home.goToLogin();
        login.fillEmail(emailAddress);
        login.fillPassword(validPassword);
        home = login.loginButtonClick();
        dataErasureRequest = home.goToRequestDataErasure();
        Assert.assertTrue(dataErasureRequest.isDataErasureRequestPageHeaderVisible(), VisibilityMessage.headerIsNotVisible("Data Erasure Request (Art. 17 GDPR)"));
        dataErasureRequest.fillEmailAddress(emailAddress);
        dataErasureRequest.fillSecurityAnswer(secretQuestionAnswer);
        dataErasureRequest.deleteUserDataButtonClick();
        Assert.assertTrue(dataErasureRequest.isErasureRequestSubmittedHeaderVisible(), VisibilityMessage.headerIsNotVisible("Sorry to see you leave! Your erasure request will be processed shortly."));
        home = dataErasureRequest.goToHomePageButtonClick();
        Assert.assertTrue(home.isAllProductsHeaderVisible(), VisibilityMessage.headerIsNotVisible("All Products"));
        TestLogger.logTestVariables("EMAIL", emailAddress, "PASSWORD", validPassword, "SECRET QUESTION", secretQuestion, "SECRET QUESTION ANSWER", secretQuestionAnswer);
    }
}