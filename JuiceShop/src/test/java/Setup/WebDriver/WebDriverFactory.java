package Setup.WebDriver;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    String WDM_CACHE = "wdm.cachePath";
    String DRIVER_LOCATION = "D:\\Users\\ChromeDriver";

    WebDriver createDriverStandardMode();

    WebDriver createDriverIncognitoMode();
}
