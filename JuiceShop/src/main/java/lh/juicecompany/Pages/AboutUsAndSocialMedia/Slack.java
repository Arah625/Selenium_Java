package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Slack extends BasicPage {

    @FindBy(xpath = "//div[@id='banner']/p[contains(text(),'Please support the OWASP mission to improve software security through Open Source initiatives and community education. ')]")
    private WebElement owaspSupportBanner;

    @FindBy(xpath = "//h4[contains(text(),'Enter your email to join our Slack community')]")
    private WebElement joinOurSlackCommunityHeader;


    private String slackTabTitle = "Slack Invite | OWASP Foundation";

    public Slack(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(slackTabTitle));
    }

    public boolean isSlackPageVisible() {
        return elementVisibilityHandler.isElementVisible(owaspSupportBanner) && elementVisibilityHandler.isElementVisible(joinOurSlackCommunityHeader);
    }

}
