package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent;

    static {
        try {
            extent = ExtentReportManager.createExtentReports();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().threadId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest("<span style='color:yellow;'>" + testName + "</span>",
                "<span style='color:green'>Description: \"<strong>" + desc + "</strong>\"</span>");
        extentTestMap.put((int) Thread.currentThread().threadId(), test);
        return test;
    }
}
