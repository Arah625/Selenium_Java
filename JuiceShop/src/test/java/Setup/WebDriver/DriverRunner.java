package Setup.WebDriver;

import Setup.WebDriver.BrowserDriver.ChromeDriverSetup;
import Setup.WebDriver.BrowserDriver.EdgeDriverSetup;
import Setup.WebDriver.BrowserDriver.FirefoxDriverSetup;

/**
 * Facilitates the selection of a specific WebDriverFactory implementation based on the browser type.
 * This class is responsible for providing the correct WebDriverFactory instance to initialize WebDriver
 * instances for Chrome, Firefox, or Edge browsers.
 */
public class DriverRunner {

    /**
     * Returns an instance of {@link WebDriverFactory} based on the specified browser type.
     * This method allows for dynamic selection of the browser-specific WebDriverFactory implementation,
     * supporting Chrome, Firefox, and Edge.
     *
     * @param browserType The type of the browser for which the WebDriverFactory is requested.
     *                    Supported values include "CHROME", "FIREFOX", and "EDGE".
     * @return An instance of {@link WebDriverFactory} that corresponds to the specified browser type.
     * @throws IllegalArgumentException If the specified browser type is not supported.
     */
    public static WebDriverFactory getBrowser(String browserType) {
        return switch (browserType.toUpperCase()) {
            case "CHROME" -> new ChromeDriverSetup();
            case "FIREFOX" -> new FirefoxDriverSetup();
            case "EDGE" -> new EdgeDriverSetup();
            default -> throw new IllegalArgumentException("Browser type not supported: " + browserType);
        };
    }
}