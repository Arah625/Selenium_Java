package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for all page objects within the application. It initializes the WebDriver and WebDriverWait instances
 * necessary for interacting with web pages. Additionally, it uses Selenium's PageFactory to initialize web elements.
 */
public class PageSetup {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    /**
     * Constructor for PageSetup. Initializes the WebDriver and WebDriverWait instances using the singleton WebDriverSetup.
     * Also initializes page elements with Selenium's PageFactory.
     *
     * @param webDriver The WebDriver instance to be used for page interactions. This is typically obtained
     *                  from WebDriverSetup's singleton instance, ensuring consistent WebDriver usage across page objects.
     */
    public PageSetup(WebDriver webDriver) {
        this.webDriver = WebDriverSetup.getInstance().getWebDriver();
        this.webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        PageFactory.initElements(webDriver, this);
    }
}