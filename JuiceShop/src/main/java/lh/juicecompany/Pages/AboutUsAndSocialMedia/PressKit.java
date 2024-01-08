package lh.juicecompany.Pages.AboutUsAndSocialMedia;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PressKit extends BasicPage {

    @FindBy(xpath = "//div[@id='repository-container-header']//strong//a[contains(text(),'owasp-swag')]")
    private WebElement githubRepository;

    private String pressKitTabTitle = "owasp-swag/projects/juice-shop at master · OWASP/owasp-swag · GitHub";

    public PressKit(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(pressKitTabTitle));
    }

    public boolean isPressKitPageVisible() {
        return isElementVisible(githubRepository);
    }
}
