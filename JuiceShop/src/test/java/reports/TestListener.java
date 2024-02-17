package reports;

import Setup.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lh.juicecompany.Logger.InfoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.webDriver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("I am in onFinish method " + iTestContext.getName());
        ExtentReportManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        InfoMessage.startingTestCaseWithDescription(ExtentReportUtilities.getTestDescription(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(ExtentReportUtilities.getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.getTest()
                         .log(Status.PASS, MarkupHelper.createLabel("<strong>Test Passed</strong>", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(ExtentReportUtilities.getTestMethodName(iTestResult) + " test is failed.");
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.INFO, MarkupHelper.createLabel("<strong>Test Failed</strong>", ExtentColor.RED));
            Throwable throwable = iTestResult.getThrowable();
            ExtentReportUtilities.logBasicThrowableDetailsOnFail(iTestResult);
            ExtentReportUtilities.logExceptionSummary(test, throwable, Status.FAIL);
            ExtentReportUtilities.logDetailedThrowableInfoOnFail(test, throwable);
            ExtentReportUtilities.addScreenshotToReport(test);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(ExtentReportUtilities.getTestMethodName(iTestResult) + " test is skipped.");
        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            test.log(Status.INFO, MarkupHelper.createLabel("<strong>Test Skipped</strong>", ExtentColor.GREY));
            Throwable throwable = iTestResult.getThrowable();
            ExtentReportUtilities.logBasicThrowableDetailsOnFail(iTestResult);
            ExtentReportUtilities.logExceptionSummary(test, throwable, Status.SKIP);
            ExtentReportUtilities.logDetailedThrowableInfoOnFail(test, throwable);
            ExtentReportUtilities.addScreenshotToReport(test);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info("Test failed but it is in defined success ratio " + ExtentReportUtilities.getTestMethodName(iTestResult));
    }
}