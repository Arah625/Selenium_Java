package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Twitter extends BasicPage {

    @FindBy(xpath = "//span[contains(text(),'Zaloguj się do serwisu X')]")
    private WebElement loginToServiceHeader;

    private String twitterTabTitle = "Zaloguj się do serwisu X / X";


    public Twitter(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(twitterTabTitle));
    }

    public boolean isTwitterPageVisible() {
        return elementVisibilityHandler.isElementVisible(loginToServiceHeader);
    }
}
