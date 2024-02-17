package Tests;

import AssertMessages.EnableMessage;
import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import lh.juicecompany.PageUtilities.IntegerUtilities;
import lh.juicecompany.Pages.CustomerFeedback;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Feedback extends BaseTest {

    private CustomerFeedback customerFeedback;

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Leave 1 - 4 star feedback")
    public void oneToFourStarsFeedback() {
        customerFeedback = home.goToCustomerFeedback();
        String comment = faker.lorem().sentence();
        customerFeedback.fillComment(comment);
        int randomRating = IntegerUtilities.getRandomInclusive(1, 4);
        customerFeedback.setRating(randomRating);
        customerFeedback.fillCaptchaResult();
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you for your feedback."));
        TestLogger.logTestVariables("COMMENT", comment, "RATING", randomRating);
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Leave 5 stars Feedback")
    public void fiveStarFeedback() {
        customerFeedback = home.goToCustomerFeedback();
        String comment = faker.lorem().sentence();
        customerFeedback.fillComment(comment);
        int rating = 5;
        customerFeedback.setRating(rating);
        customerFeedback.fillCaptchaResult();
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFiveStarFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you so much for your amazing 5-star feedback!"));
        TestLogger.logTestVariables("COMMENT", comment, "RATING", rating);
    }

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.MEDIUM}, description = "Check submit button with and without setting rating")
    public void checkSubmitButton() {
        customerFeedback = home.goToCustomerFeedback();
        String comment = faker.lorem().sentence();
        customerFeedback.fillComment(comment);
        customerFeedback.fillCaptchaResult();
        Assert.assertFalse(customerFeedback.isSubmitButtonEnabled(), EnableMessage.buttonIsEnabled("Submit"));
        int randomRating = IntegerUtilities.getRandomInclusive(1, 3);
        customerFeedback.setRating(randomRating);
        Assert.assertTrue(customerFeedback.isSubmitButtonEnabled(), EnableMessage.buttonIsNotEnabled("Submit"));
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you for your feedback."));
        TestLogger.logTestVariables("COMMENT", comment, "RATING", randomRating);
    }
}
