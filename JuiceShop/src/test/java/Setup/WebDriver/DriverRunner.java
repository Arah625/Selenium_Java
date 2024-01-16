package Setup.WebDriver;

import Setup.WebDriver.BrowserDriver.ChromeDriverSetup;
import Setup.WebDriver.BrowserDriver.EdgeDriverSetup;
import Setup.WebDriver.BrowserDriver.FirefoxDriverSetup;

public class DriverRunner {

    public static WebDriverFactory getBrowser(String browserType) {
        switch (browserType.toUpperCase()) {
            case "CHROME":
                return new ChromeDriverSetup();
            case "FIREFOX":
                return new FirefoxDriverSetup();
            case "EDGE":
                return new EdgeDriverSetup();
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
    }
}
