package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class ChromeDriverSetup implements WebDriverFactory {

    private ChromeOptions chromeOptionsStandard() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return chromeOptions;
    }

    private ChromeOptions chromeOptionsIncognito() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return chromeOptions;
    }

    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptionsStandard());
    }

    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptionsIncognito());
    }
}
