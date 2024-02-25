package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSetup {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public PageSetup(WebDriver webDriver) {
        this.webDriver = WebDriverSetup.getInstance().getWebDriver();
        this.webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        PageFactory.initElements(webDriver, this);
    }
}