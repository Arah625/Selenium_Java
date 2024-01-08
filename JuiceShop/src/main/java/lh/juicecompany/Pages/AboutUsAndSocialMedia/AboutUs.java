package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.PageUtilities.TabHandler;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUs extends Home {

    @FindBy(xpath = "//h1[contains(text(),'About Us')]")
    private WebElement aboutUsPageHeader;

    @FindBy(xpath = "//button//span[contains(text(),'Twitter')]/..")
    private WebElement twitterButton;

    @FindBy(xpath = "//button//span[contains(text(),'Facebook')]/..")
    private WebElement facebookButton;

    @FindBy(xpath = "//button//span[contains(text(),'Slack')]/..")
    private WebElement slackButton;

    @FindBy(xpath = "//button//span[contains(text(),'Reddit')]/..")
    private WebElement redditButton;

    @FindBy(xpath = "//button//span[contains(text(),'Press Kit')]/..")
    private WebElement pressKitButton;

    @FindBy(xpath = "//button//span[contains(text(),'NFT')]/..")
    private WebElement nftButton;

    private TabHandler tabHandler;


    public AboutUs(WebDriver webDriver) {
        super(webDriver);
        this.tabHandler = new TabHandler(webDriver);
    }

    public boolean isAboutUsHeaderVisible() {
        return isElementVisible(aboutUsPageHeader);
    }

    public Twitter goToOurTwitterPage() {
        clickElement(twitterButton);
        tabHandler.switchToChildTab();
        return new Twitter(webDriver);
    }

    public Facebook goToOurFacebookPage() {
        clickElement(facebookButton);
        tabHandler.switchToChildTab();
        return new Facebook(webDriver);
    }

    public Slack goToOurSlackPage() {
        clickElement(slackButton);
        tabHandler.switchToChildTab();
        return new Slack(webDriver);
    }

    public Reddit goToOurRedditPage() {
        clickElement(redditButton);
        tabHandler.switchToChildTab();
        return new Reddit(webDriver);
    }

    public PressKit goToOurPressKitPage() {
        clickElement(pressKitButton);
        tabHandler.switchToChildTab();
        return new PressKit(webDriver);
    }

    public Nft goToOurNftPage() {
        clickElement(nftButton);
        tabHandler.switchToChildTab();
        return new Nft(webDriver);
    }


}
