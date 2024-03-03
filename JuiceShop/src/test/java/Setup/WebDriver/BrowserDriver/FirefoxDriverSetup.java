package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Implements the {@link WebDriverFactory} interface to provide methods for creating Firefox WebDriver instances.
 * This class supports creating WebDriver instances in both standard and incognito modes for Firefox browser.
 */
public class FirefoxDriverSetup implements WebDriverFactory {

    /**
     * Configures and returns the default FirefoxOptions for standard mode.
     *
     * @return {@link FirefoxOptions} configured for standard browsing mode.
     */
    private FirefoxOptions firefoxOptionsStandard() {
        return new FirefoxOptions();
    }

    /**
     * Configures and returns FirefoxOptions for incognito mode.
     * Adds the necessary argument to initiate Firefox in private browsing mode.
     *
     * @return {@link FirefoxOptions} configured for incognito (private browsing) mode.
     */
    private FirefoxOptions firefoxOptionsIncognito() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.addArguments("--private-window");
        return firefoxOptions;
    }

    /**
     * Creates and returns a new FirefoxDriver instance in standard mode.
     * This method sets up the WebDriver for Firefox using WebDriverManager and configures it for standard mode.
     *
     * @return A {@link WebDriver} instance initialized for Firefox in standard mode.
     */
    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptionsStandard());
    }

    /**
     * Creates and returns a new FirefoxDriver instance in incognito mode.
     * This method sets up the WebDriver for Firefox using WebDriverManager and configures it for incognito mode.
     *
     * @return A {@link WebDriver} instance initialized for Firefox in incognito mode.
     */
    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptionsIncognito());
    }
}
