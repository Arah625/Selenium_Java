package Setup;

import Setup.BrowserMode.ModeAnnotation;
import Setup.BrowserMode.ModeType;
import com.github.javafaker.Faker;
import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base extends WebDriverManager {
    private final String JUICE_SHOP_URL = "http://localhost:3000/#/";
    protected Faker faker = new Faker();
    protected Home home;

    protected String testCaseName(String testCaseName) {
        InfoMessage.startingTestCase(testCaseName);
        return testCaseName;
    }

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(ITestContext context) {
        String currentTestClass = context.getCurrentXmlTest().getClasses().get(0).getName();
        try {
            Class<?> clazz = Class.forName(currentTestClass);
            if (clazz.isAnnotationPresent(ModeAnnotation.class)) {
                ModeType mode = clazz.getAnnotation(ModeAnnotation.class).value();
                switch (mode) {
                    case INCOGNITO:
                        initializeChromeDriverIncognitoMode();
                        break;
                    case STANDARD:
                    default:
                        initializeChromeDriverStandardMode();
                        break;
                }
            } else {
                initializeChromeDriverStandardMode();
            }
            commonSetup();
        } catch (ClassNotFoundException ignored) {

        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (webDriver != null) {
                webDriver.close();
            }
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtException(webDriverException, ErrorMessage.FAILED_TO_CLOSE_BROWSER);
        }
    }

    @AfterClass(alwaysRun = true)
    public void finalTearDown() {
        try {
            if (webDriver != null) {
                webDriver.quit();
            }
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtException(webDriverException, ErrorMessage.FAILED_TO_QUIT_WEBDRIVER);
        }
    }

    private void commonSetup() {
        webDriver.get(JUICE_SHOP_URL);
        home = new Home(webDriver);
        home.dismissPopUps();
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
        }
    }


}
