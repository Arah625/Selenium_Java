package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

/**
 * Implements the {@link WebDriverFactory} interface to provide methods for creating Google Chrome WebDriver instances.
 * This class enables the creation of WebDriver instances configured for both standard and incognito browsing modes,
 * with specific ChromeOptions set for each mode.
 */
public class ChromeDriverSetup implements WebDriverFactory {

    /**
     * Configures and returns ChromeOptions for standard browsing mode.
     * The configuration includes starting the browser maximized and setting experimental options
     * to enhance automation capabilities while reducing detection.
     *
     * @return {@link ChromeOptions} configured for standard browsing mode.
     */
    private ChromeOptions chromeOptionsStandard() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return chromeOptions;
    }

    /**
     * Configures and returns ChromeOptions for incognito mode.
     * The configuration includes starting the browser in incognito mode maximized, disabling the automation extension,
     * and setting experimental options to enhance automation capabilities while reducing detection.
     *
     * @return {@link ChromeOptions} configured for incognito (private browsing) mode.
     */
    private ChromeOptions chromeOptionsIncognito() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return chromeOptions;
    }

    /**
     * Creates and returns a new ChromeDriver instance in standard mode.
     * This method sets up the WebDriver for Google Chrome using WebDriverManager and configures it
     * with the options for standard browsing mode.
     *
     * @return A {@link WebDriver} instance initialized for Google Chrome in standard mode.
     */
    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptionsStandard());
    }

    /**
     * Creates and returns a new ChromeDriver instance in incognito mode.
     * This method sets up the WebDriver for Google Chrome using WebDriverManager and configures it
     * with the options for incognito browsing mode.
     *
     * @return A {@link WebDriver} instance initialized for Google Chrome in incognito mode.
     */
    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptionsIncognito());
    }
}