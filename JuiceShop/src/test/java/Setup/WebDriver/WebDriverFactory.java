package Setup.WebDriver;

import org.openqa.selenium.WebDriver;

/**
 * Defines the interface for WebDriver factories capable of creating WebDriver instances in various modes.
 * Implementations of this interface should provide methods to instantiate WebDrivers in standard and incognito modes.
 */
public interface WebDriverFactory {

    /**
     * Specifies the system property key for the WebDriver Manager (WDM) cache path.
     */
    String WDM_CACHE = "wdm.cachePath";

    /**
     * Specifies the default location where the WebDriver binaries are stored.
     */
    String DRIVER_LOCATION = "D:\\Users\\ChromeDriver";

    /**
     * Creates and returns a new {@link WebDriver} instance in standard mode.
     * Standard mode refers to the default state of the browser without any additional privacy settings or extensions.
     *
     * @return A {@link WebDriver} instance initialized in standard mode.
     */
    WebDriver createDriverStandardMode();

    /**
     * Creates and returns a new {@link WebDriver} instance in incognito (or private browsing) mode.
     * Incognito mode ensures that the browser does not store browsing history, cookies, and site data, or information entered in forms.
     *
     * @return A {@link WebDriver} instance initialized in incognito mode.
     */
    WebDriver createDriverIncognitoMode();
}