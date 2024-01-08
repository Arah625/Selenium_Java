package Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class WebDriverManager extends WebDriverConfiguration {

    protected WebDriver webDriver;

    private String setSystemPropertyForChromeDriver() {
        return System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH);
    }

    public void initializeChromeDriverStandardMode() {
        setSystemPropertyForChromeDriver();
        webDriver = new ChromeDriver(chromeOptionsStandard());
    }

    public void initializeChromeDriverIncognitoMode() {
        setSystemPropertyForChromeDriver();
        webDriver = new ChromeDriver(chromeOptionsIncognito());
    }

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

}
