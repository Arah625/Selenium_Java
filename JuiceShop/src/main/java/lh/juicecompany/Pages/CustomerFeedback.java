package lh.juicecompany.Pages;

import org.mvel2.MVEL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerFeedback extends Home {
    @FindBy(xpath = "//textarea[@id='comment']")
    private WebElement commentField;

    @FindBy(xpath = "//mat-slider[@id='rating']")
    private WebElement ratingSlider;

    @FindBy(xpath = "//div[@class='mat-slider-thumb']")
    private WebElement sliderThumb;

    @FindBy(xpath = "//code[@id='captcha']")
    private WebElement captchaCode;

    @FindBy(xpath = "//input[@id='captchaControl']")
    private WebElement captchaResultField;

    @FindBy(xpath = "//button[@id='submitButton']")
    private WebElement submitButton;

    @FindBy(xpath = "//span[contains(text(),'Thank you for your feedback.')]")
    private WebElement feedbackSentMessage;

    @FindBy(xpath = "//span[contains(text(),'Thank you so much for your amazing 5-star feedback!')]")
    private WebElement fiveStarFeedbackSentMessage;

    @FindBy(xpath = "//span[contains(text(),'Wrong answer to CAPTCHA. Please try again.')]")
    private WebElement wrongCaptchaMessage;


    public CustomerFeedback(WebDriver webDriver) {
        super(webDriver);
    }

    public CustomerFeedback fillComment(String comment) {
        commonMethods.sendKeysToElement(commentField, comment);
        return this;
    }

    private String getCaptchaCode() {
        return commonMethods.getTextFromElement(captchaCode);
    }

    private String solveEquation() {
        Object result = MVEL.eval(getCaptchaCode());
        return result.toString();
    }

    public CustomerFeedback fillCaptchaResult() {
        commonMethods.sendKeysToElement(captchaResultField, solveEquation());
        return this;
    }

    public boolean isSubmitButtonEnabled() {
        return button.isEnabled(submitButton);
    }

    public CustomerFeedback submitButtonClick() {
        commonMethods.clickElement(submitButton);
        return this;
    }

    public CustomerFeedback setRandomRating() {
        toggleSwitch.moveSliderRandomly(sliderThumb, ratingSlider);
        return this;
    }

    public CustomerFeedback setRandomRating(int minValue, int maxValue) {
        toggleSwitch.moveSliderRandomly(sliderThumb, ratingSlider, minValue, maxValue);
        return this;
    }

    public CustomerFeedback setRating(int rating) {
        toggleSwitch.moveSlider(sliderThumb, ratingSlider, rating);
        return this;
    }

    public boolean isMessageFeedbackSuccessfullySentVisible() {
        return elementVisibilityHandler.isElementVisible(feedbackSentMessage);
    }

    public boolean isMessageFiveStarFeedbackSuccessfullySentVisible() {
        return elementVisibilityHandler.isElementVisible(fiveStarFeedbackSentMessage);
    }

    public boolean isMessageWrongCaptchaVisible() {
        return elementVisibilityHandler.isElementVisible(wrongCaptchaMessage);
    }

}
