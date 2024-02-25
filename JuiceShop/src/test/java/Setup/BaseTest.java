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

public class BaseTest {
    //    protected Faker faker = new Faker();
    protected Faker faker = new Faker(new Locale.Builder().setLanguage("AU").build());

    protected Home home;
    protected WebDriver webDriver;


    public static void repeatAction(Runnable action, int repetitions) {
        for (int i = 0; i < repetitions; i++) {
            action.run();
        }
    }

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

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
//        try {
//            if (ExtentTestManager.getTest() != null) {
//                ExtentReportManager.extentReports.flush(); // Flush before closing WebDriver
//            }
//        } finally {
        InfoMessage.afterMethodStart();
        try {
//            WebDriverSetup.getInstance().closeDriver();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtException(webDriverException, ErrorMessage.FAILED_TO_CLOSE_BROWSER);
        }
        InfoMessage.afterMethodEnd();
    }

    @AfterClass(alwaysRun = true)
    public void finalTearDown() {
        InfoMessage.afterClassStart();
        try {
            //           WebDriverSetup.getInstance().quitDriver();
            ExtentReportUtilities.openReportInBrowser();
        } catch (WebDriverException webDriverException) {
            ErrorMessage.caughtElementException(webDriverException, ErrorMessage.FAILED_TO_QUIT_WEB_DRIVER);
        }
        InfoMessage.afterClassEnd();
    }

    private void commonSetup() {
        webDriver.get(ProjectInformation.JUICE_SHOP_URL);
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
