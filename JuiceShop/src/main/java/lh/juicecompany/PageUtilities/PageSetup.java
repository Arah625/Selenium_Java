package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSetup {

    private static final int WAIT_TIME = 10;

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public PageSetup(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, WAIT_TIME);
        PageFactory.initElements(webDriver, this);
    }


}
