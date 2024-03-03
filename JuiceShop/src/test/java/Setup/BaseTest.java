package Setup;

import Setup.WebDriver.DriverRunner;
import Setup.WebDriver.WebDriverFactory;
import com.github.javafaker.Faker;
import lh.juicecompany.Exceptions.UnknownBrowserModeException;
import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import lh.juicecompany.Pages.Home;
import lh.juicecompany.Project.ProjectInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import reports.ExtentReportManager;
import reports.ExtentReportUtilities;
import reports.ExtentTestManager;
import reports.RetryAnalyzer;

import java.util.Locale;

/**
 * Base class for all test classes, providing setup and teardown methods for WebDriver instances,
 * and initializing common resources and utilities for tests.
 */
public class BaseTest {
    protected Faker faker = new Faker(new Locale.Builder().setLanguage("AU").build());

    protected Home home;
    protected WebDriver webDriver;

    /**
     * Executes a set of actions repeatedly for a specified number of times.
     * This method can be used for various repetitive tasks, such as creating multiple accounts,
     * filling out forms, or any other set of actions that need to be executed multiple times.
     *
     * @param action      The set of actions to be executed, encapsulated as a {@link Runnable}.
     * @param repetitions The number of times the set of actions should be executed.
     */
    public static void repeatAction(Runnable action, int repetitions) {
        for (int i = 0; i < repetitions; i++) {
            action.run();
        }
    }

    /**
     * Sets up the WebDriver before each test method based on the specified browser and mode.
     * Initializes the WebDriver and manages test context for reporting. This method configures
     * the WebDriver according to the browser type and mode provided, handling different browser
     * modes specifically and throwing an exception for unsupported modes.
     *
     * @param browser     The type of browser to use, specified in test configuration.
     * @param browserMode The mode the browser should run in (e.g., "INCOGNITO", "STANDARD").
     * @param context     The test context provided by TestNG, used for managing test execution details.
     * @param iTestResult The test result object, used for detailed test reporting.
     * @throws UnknownBrowserModeException If the specified browser mode is not recognized or supported,
     *                                     indicating a configuration error or a request for an unimplemented feature.
     */
    @Parameters({"browser", "browserMode"})
    @BeforeMethod(alwaysRun = true)
    public void driverSetup(String browser, String browserMode, ITestContext context, ITestResult iTestResult) {
        RetryAnalyzer.setMaxTryFromContext(context);
        ExtentReportManager.getBrowserMode(context);
        ExtentReportManager.getBrowserType(context);
        InfoMessage.beforeMethodStart();
        InfoMessage.browserAndMode(browser.toUpperCase(), browserMode);
        WebDriverFactory factory = DriverRunner.getBrowser(browser);
        switch (browserMode.toUpperCase()) {
            case "INCOGNITO":
                webDriver = factory.createDriverIncognitoMode();
                break;
            case "STANDARD":
                webDriver = factory.createDriverStandardMode();
                break;
            default:
                throw new UnknownBrowserModeException(ErrorMessage.UNKNOWN_BROWSER_MODE);
        }
        webDriver.manage().window().maximize();
        WebDriverSetup.getInstance().setWebDriver(webDriver);
        commonSetup();
        InfoMessage.beforeMethodEnd();
        ExtentTestManager.startTest(ExtentReportUtilities.getTestMethodName(iTestResult), ExtentReportUtilities.getTestDescription(iTestResult));
    }

    /**
     * Tears down the WebDriver instance after each test method.
     * Handles closing the browser and capturing any WebDriver exceptions.
     *
     * @param result The result of the test execution.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        InfoMessage.afterMethodStart();
        try {
            WebDriverSetup.getInstance().closeDriver();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtException(webDriverException, ErrorMessage.FAILED_TO_CLOSE_BROWSER);
        }
        InfoMessage.afterMethodEnd();
    }

    /**
     * Final cleanup actions to be performed after all test methods in the class have been executed.
     * Quits the WebDriver and performs final reporting actions.
     */
    @AfterClass(alwaysRun = true)
    public void finalTearDown() {
        InfoMessage.afterClassStart();
        try {
            WebDriverSetup.getInstance().quitDriver();
            ExtentReportUtilities.openReportInBrowser();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtElementException(webDriverException, ErrorMessage.FAILED_TO_QUIT_WEB_DRIVER);
        }
        InfoMessage.afterClassEnd();
    }

    /**
     * Common setup actions to be performed at the start of each test method.
     * Navigates to the initial URL and performs any required initial actions on the page.
     */
    private void commonSetup() {
        webDriver.get(ProjectInformation.JUICE_SHOP_URL);
        home = new Home(webDriver);
        home.dismissPopUps();
    }
}