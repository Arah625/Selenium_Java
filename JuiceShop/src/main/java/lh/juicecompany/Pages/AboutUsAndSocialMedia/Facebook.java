package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Facebook extends BasicPage {

    @FindBy(xpath = "//html[@id='facebook']")
    private WebElement facebookPage;

    private String facebookTabTitle = "OWASP Juice Shop | Facebook";

    public Facebook(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(facebookTabTitle));
    }

    public boolean isFacebookPageVisible() {
        return isElementVisible(facebookPage);
    }
}
