package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * Implements the {@link WebDriverFactory} interface to provide methods for creating Microsoft Edge WebDriver instances.
 * Supports creating WebDriver instances in standard and incognito modes with specific configurations for each mode.
 */
public class EdgeDriverSetup implements WebDriverFactory {

    /**
     * Configures and returns EdgeOptions for the standard browsing mode.
     * Includes arguments to start the browser maximized.
     *
     * @return {@link EdgeOptions} configured for standard browsing mode.
     */
    private EdgeOptions edgeOptionsStandard() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        return edgeOptions;
    }

    /**
     * Configures and returns EdgeOptions for the incognito mode.
     * Includes arguments to initiate Edge in private browsing mode and start the browser maximized.
     *
     * @return {@link EdgeOptions} configured for incognito (private browsing) mode.
     */
    private EdgeOptions edgeOptionsIncognito() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--inprivate");
        edgeOptions.addArguments("--start-maximized");
        return edgeOptions;
    }

    /**
     * Creates and returns a new EdgeDriver instance in standard mode.
     * Sets up the WebDriver for Microsoft Edge using WebDriverManager and configures it for standard browsing mode.
     *
     * @return A {@link WebDriver} instance initialized for Microsoft Edge in standard mode.
     */
    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptionsStandard());
    }

    /**
     * Creates and returns a new EdgeDriver instance in incognito mode.
     * Sets up the WebDriver for Microsoft Edge using WebDriverManager and configures it for private browsing (incognito) mode.
     *
     * @return A {@link WebDriver} instance initialized for Microsoft Edge in incognito mode.
     */
    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptionsIncognito());
    }
}
