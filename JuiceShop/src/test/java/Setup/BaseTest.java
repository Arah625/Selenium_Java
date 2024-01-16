package Setup;

import Setup.WebDriver.DriverRunner;
import Setup.WebDriver.WebDriverFactory;
import com.github.javafaker.Faker;
import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    private final String JUICE_SHOP_URL = "http://localhost:3000/#/";
    protected Faker faker = new Faker();
    protected Home home;
    protected WebDriver webDriver;

    protected String testCaseName(String testCaseName) {
        InfoMessage.startingTestCase(testCaseName);
        return testCaseName;
    }

    @Parameters({"browser", "browserMode"})
    @BeforeMethod(alwaysRun = true)
    public void driverSetup(String browser, String browserMode) {
        InfoMessage.beforeMethodStart();
        InfoMessage.browserAndMode(browser.toUpperCase(), browserMode);
        WebDriverFactory factory = DriverRunner.getBrowser(browser);
        try {
            switch (browserMode.toUpperCase()) {
                case "INCOGNITO":
                    webDriver = factory.createDriverIncognitoMode();
                    break;
                case "STANDARD":
                    webDriver = factory.createDriverStandardMode();
                    break;
            }
            WebDriverSetup.getInstance().setWebDriver(webDriver);
            commonSetup();
        } catch (IllegalArgumentException exception) {
            ErrorMessage.caughtException(exception, ErrorMessage.UNKNOWN_BROWSER_MODE);
        }
        InfoMessage.beforeMethodEnd();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        InfoMessage.afterMethodStart();
        try {
            WebDriverSetup.getInstance().closeDriver();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtException(webDriverException, ErrorMessage.FAILED_TO_CLOSE_BROWSER);
        }
        InfoMessage.afterMethodEnd();
    }

    @AfterClass(alwaysRun = true)
    public void finalTearDown() {
        InfoMessage.afterClassStart();
        try {
            WebDriverSetup.getInstance().quitDriver();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtElementException(webDriverException, ErrorMessage.FAILED_TO_QUIT_WEB_DRIVER);
        }
        InfoMessage.afterClassEnd();
    }

    private void commonSetup() {
        webDriver.get(JUICE_SHOP_URL);
        home = new Home(webDriver);
        home.dismissPopUps();
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }
}
