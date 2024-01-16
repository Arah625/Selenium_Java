package lh.juicecompany.Pages.Account.PrivacyAndSecurity;

import lh.juicecompany.Pages.BasicPage;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequestDataErasure extends BasicPage {

    @FindBy(xpath = "//h4[contains(text(),'Data Erasure Request (Art. 17 GDPR)')]")
    private WebElement dataErasureRequestPageHeader;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='securityAnswer']")
    private WebElement securityAnswerField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement deleteUserDataButton;

    @FindBy(xpath = "//p[contains(text(),'Sorry to see you leave! Your erasure request will be processed shortly.')]")
    private WebElement erasureRequestSubmittedHeader;

    @FindBy(xpath = "//a[contains(text(),'Go To HomePage')]")
    private WebElement goToHomePageButton;

    public RequestDataErasure(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isDataErasureRequestPageHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(dataErasureRequestPageHeader);
    }

    public RequestDataErasure fillEmailAddress(String emailAddress) {
        commonMethods.sendKeysToElement(emailField, emailAddress);
        return this;
    }

    public RequestDataErasure fillSecurityAnswer(String securityAnswer) {
        commonMethods.sendKeysToElement(securityAnswerField, securityAnswer);
        return this;
    }

    public RequestDataErasure deleteUserDataButtonClick() {
        commonMethods.clickElement(deleteUserDataButton);
        return this;
    }

    public boolean isErasureRequestSubmittedHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(erasureRequestSubmittedHeader);
    }

    public Home goToHomePageButtonClick() {
        commonMethods.clickElement(goToHomePageButton);
        return new Home(webDriver);
    }
}
