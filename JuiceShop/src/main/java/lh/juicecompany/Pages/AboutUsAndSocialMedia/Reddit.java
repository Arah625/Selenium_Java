package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Reddit extends BasicPage {

    //TODO: there is a shadow-root on reddit owasp page - check it

    @FindBy(xpath = "//a[@id='reddit-logo']")
    private WebElement redditLogo;


    private String redditTabTitle = "owasp_juiceshop";

    public Reddit(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(redditTabTitle));
    }

    public boolean isRedditPageVisible() {
        return elementVisibilityHandler.isElementVisible(redditLogo);
    }
}
