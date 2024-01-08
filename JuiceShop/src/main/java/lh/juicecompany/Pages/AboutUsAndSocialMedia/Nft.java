package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Nft extends BasicPage {

    @FindBy(xpath = "//h1[@data-id='TextHeading' and contains(text(),'Juice Shop')]")
    private WebElement juiceShopHeader;
    private String nftTabTitle = "Juice Shop - Collection | OpenSea";

    public Nft(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(nftTabTitle));
    }

    public boolean isNftPageVisible() {
        return isElementVisible(juiceShopHeader);
    }
}
