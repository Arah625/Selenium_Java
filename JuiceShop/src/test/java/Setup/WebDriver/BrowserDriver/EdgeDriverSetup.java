package Setup.WebDriver.BrowserDriver;

import Setup.WebDriver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverSetup implements WebDriverFactory {

    private EdgeOptions edgeOptionsStandard() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        return edgeOptions;
    }

    private EdgeOptions edgeOptionsIncognito() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--inprivate");
        edgeOptions.addArguments("--start-maximized");
        return edgeOptions;
    }

    @Override
    public WebDriver createDriverStandardMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptionsStandard());
    }

    @Override
    public WebDriver createDriverIncognitoMode() {
        System.setProperty(WDM_CACHE, DRIVER_LOCATION);
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptionsIncognito());
    }
}
