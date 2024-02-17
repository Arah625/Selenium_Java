package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lh.juicecompany.Project.ProjectInformation;
import lh.juicecompany.System.SystemProperties;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;

public class ExtentReportManager {
    public static ExtentReports extentReports = new ExtentReports();
    static String browserType = null;
    static String browserMode = null;

    public static String getBrowserType(ITestContext context) {
        return browserType = context.getCurrentXmlTest().getParameter("browser");
    }

    public static String getBrowserMode(ITestContext context) {
        return browserMode = context.getCurrentXmlTest().getParameter("browserMode");
    }

    public synchronized static ExtentReports createExtentReports() throws IOException {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(ReportInformation.REPORT_PATH);
        extentSparkReporter.loadJSONConfig(new File("src/main/resources/extentReportsConfig.json"));
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", ProjectInformation.APPLICATION_NAME);
        extentReports.setSystemInfo("URL", ProjectInformation.JUICE_SHOP_URL);
        extentReports.setSystemInfo("Author", ProjectInformation.AUTHOR);
        extentReports.setSystemInfo("Browser type", browserType.toUpperCase());
        extentReports.setSystemInfo("Browser mode", browserMode.toUpperCase());
        extentReports.setSystemInfo("Operating System ", SystemProperties.OS_NAME);
        extentReports.setSystemInfo("Operating System Architecture ", SystemProperties.OS_ARCHITECTURE);
        extentReports.setSystemInfo("Java Version ", SystemProperties.JAVA_VERSION);
        extentReports.setSystemInfo("Java Vendor ", SystemProperties.JAVA_VENDOR);
        return extentReports;
    }
}
