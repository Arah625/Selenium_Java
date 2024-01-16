package Tests;

import AssertMessages.EnableMessage;
import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import lh.juicecompany.Pages.CustomerFeedback;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Feedback extends BaseTest {

    private CustomerFeedback customerFeedback;


    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void oneToFourStarsFeedback() {
        testCaseName("Leave 1 - 4 star feedback");
        customerFeedback = home.goToCustomerFeedback();
        customerFeedback.fillComment(faker.lorem().sentence());
        customerFeedback.setRandomRating(1, 4);
        customerFeedback.fillCaptchaResult();
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you for your feedback."));
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void fiveStarFeedback() {
        testCaseName("Leave 5 stars Feedback");
        customerFeedback = home.goToCustomerFeedback();
        customerFeedback.fillComment(faker.lorem().sentence());
        customerFeedback.setRating(5);
        customerFeedback.fillCaptchaResult();
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFiveStarFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you so much for your amazing 5-star feedback!"));
    }

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.MEDIUM})
    public void checkSubmitButton() {
        testCaseName("Check submit button with and without setting rating");
        customerFeedback = home.goToCustomerFeedback();
        customerFeedback.fillComment(faker.lorem().sentence());
        customerFeedback.fillCaptchaResult();
        Assert.assertFalse(customerFeedback.isSubmitButtonEnabled(), EnableMessage.buttonIsEnabled("Submit"));
        customerFeedback.setRandomRating(1, 3);
        Assert.assertTrue(customerFeedback.isSubmitButtonEnabled(), EnableMessage.buttonIsNotEnabled("Submit"));
        customerFeedback.submitButtonClick();
        Assert.assertTrue(customerFeedback.isMessageFeedbackSuccessfullySentVisible(), VisibilityMessage.ghostMessageIsNotVisible("Thank you for your feedback."));
    }

}
