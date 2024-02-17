package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverSetup implements WebDriverFactory {

    private FirefoxOptions firefoxOptionsStandard() {
        return new FirefoxOptions();
    }

    private FirefoxOptions firefoxOptionsIncognito() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.addArguments("--private-window");
        return firefoxOptions;
    }

    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptionsStandard());
    }

    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptionsIncognito());
    }
}
