package Tests;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Utilities.SocialMediaData;
import lh.juicecompany.Pages.AboutUsAndSocialMedia.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoreAboutUs extends BaseTest {

    private AboutUs aboutUs;
    private Twitter twitter;
    private Facebook facebook;
    private Slack slack;
    private Reddit reddit;
    private PressKit pressKit;
    private Nft nft;

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our Twitter Social Media Page")
    public void checkRedirectionToTwitter() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        twitter = aboutUs.goToOurTwitterPage();
        String twitterPageTitle = twitter.getPageTitle();
        Assert.assertEquals(twitterPageTitle, SocialMediaData.TWITTER.getPageTitle(), "");
        String twitterPageUrl = twitter.getCurrentUrl();
        Assert.assertEquals(twitterPageUrl, SocialMediaData.TWITTER.getUrl(), "");
        Assert.assertTrue(twitter.isTwitterPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.TWITTER.getSocialMediaPlatform()));
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our Facebook Social Media Page")
    public void checkRedirectionToFacebook() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        facebook = aboutUs.goToOurFacebookPage();
        String facebookPageTitle = facebook.getPageTitle();
        Assert.assertEquals(facebookPageTitle, SocialMediaData.FACEBOOK.getPageTitle(), "");
        String facebookPageUrl = facebook.getCurrentUrl();
        Assert.assertEquals(facebookPageUrl, SocialMediaData.FACEBOOK.getUrl(), "");
        Assert.assertTrue(facebook.isFacebookPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.FACEBOOK.getSocialMediaPlatform()));
    }

    @Test(priority = 3, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our Slack Social Media Page")
    public void checkRedirectionToSlack() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        slack = aboutUs.goToOurSlackPage();
        String slackPageTitle = slack.getPageTitle();
        Assert.assertEquals(slackPageTitle, SocialMediaData.SLACK.getPageTitle(), "");
        String slackPageUrl = slack.getCurrentUrl();
        Assert.assertEquals(slackPageUrl, SocialMediaData.SLACK.getUrl(), "");
        Assert.assertTrue(slack.isSlackPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.SLACK.getSocialMediaPlatform()));
    }

    @Test(priority = 4, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our Reddit Social Media Page")
    public void checkRedirectionToReddit() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        reddit = aboutUs.goToOurRedditPage();
        String redditPageTitle = reddit.getPageTitle();
        Assert.assertEquals(redditPageTitle, SocialMediaData.REDDIT.getPageTitle(), "");
        String redditPageUrl = reddit.getCurrentUrl();
        Assert.assertEquals(redditPageUrl, SocialMediaData.REDDIT.getUrl(), "");
        Assert.assertTrue(reddit.isRedditPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.REDDIT.getSocialMediaPlatform()));
    }

    @Test(priority = 5, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our Press Kit Social Media Page")
    public void checkRedirectionToPressKit() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        pressKit = aboutUs.goToOurPressKitPage();
        String pressKitPageTitle = pressKit.getPageTitle();
        Assert.assertEquals(pressKitPageTitle, SocialMediaData.PRESS_KIT.getPageTitle(), "");
        String pressKitPageUrl = pressKit.getCurrentUrl();
        Assert.assertEquals(pressKitPageUrl, SocialMediaData.PRESS_KIT.getUrl(), "");
        Assert.assertTrue(pressKit.isPressKitPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.PRESS_KIT.getSocialMediaPlatform()));
    }

    @Test(priority = 6, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Check redirection to our NFT Social Media Page")
    public void checkRedirectionToNft() {
        aboutUs = home.goToAboutUs();
        Assert.assertTrue(aboutUs.isAboutUsHeaderVisible(), VisibilityMessage.headerIsNotVisible("About Us"));
        nft = aboutUs.goToOurNftPage();
        String nftPageTitle = nft.getPageTitle();
        Assert.assertEquals(nftPageTitle, SocialMediaData.NFT.getPageTitle(), "");
        String nftPageUrl = nft.getCurrentUrl();
        Assert.assertEquals(nftPageUrl, SocialMediaData.NFT.getUrl(), "");
        Assert.assertTrue(nft.isNftPageVisible(), VisibilityMessage.pageIsNotVisible(SocialMediaData.NFT.getSocialMediaPlatform()));
    }
}
