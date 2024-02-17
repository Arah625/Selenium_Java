package reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class ExtentReportUtilities {


    public static String getTestDescription(ITestResult result) {
        String testDesc = result.getMethod().getDescription();
        InfoMessage.startingTestCaseWithDescription(testDesc);
        return testDesc;
    }

    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getMethodName();
    }

    public static String captureScreenshot() {
        WebDriver driver = WebDriverSetup.getInstance().getWebDriver();
        return ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
    }

    public static void addScreenshotToReport(ExtentTest test) {
        String base64Screenshot = captureScreenshot();
        test.addScreenCaptureFromBase64String(base64Screenshot);
    }

    public static void logDetailedThrowableInfoOnFail(ExtentTest test, Throwable throwable) {
        if (throwable != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String stackTrace = sw.toString().replace(System.lineSeparator(), "<br>")
                                  .replace("\tat", "&nbsp;&nbsp;&nbsp;&nbsp;");
            test.log(Status.FAIL, "<details><summary>Click to see full stacktrace</summary>" + stackTrace + "</details>");
        }
    }

    public static void logExceptionSummary(ExtentTest test, Throwable throwable, Status status) {
        if (throwable != null) {
            test.log(status, throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
        }
    }

    public static void logBasicThrowableDetailsOnFail(ITestResult iTestResult) {
        Throwable throwable = iTestResult.getThrowable();
        if (throwable != null) {
            StringBuilder detailedMessage = new StringBuilder();
            String className = iTestResult.getTestClass().getName();
            detailedMessage.append("Failed test in Class: <strong>").append(className).append("</strong><br>");
            detailedMessage.append("Failed testMethod: <strong>")
                           .append(iTestResult.getMethod().getMethodName())
                           .append("</strong><br>");

            int lineNumber = findLineNumber(throwable, className);
            if (lineNumber != -1) {
                detailedMessage.append("Failed at line: <strong>").append(lineNumber).append("</strong>");
            }
            ExtentTestManager.getTest().fail(detailedMessage.toString());
        }
    }

    public static int findLineNumber(Throwable throwable, String className) {
        for (StackTraceElement element : throwable.getStackTrace()) {
            if (element.getClassName().equals(className)) {
                return element.getLineNumber();
            }
        }
        return -1;
    }

    public static void openReportInBrowser() {
        if (Desktop.isDesktopSupported()) {
            try {
                File reportFile = new File(ReportInformation.REPORT_PATH);
                Desktop.getDesktop().browse(reportFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}