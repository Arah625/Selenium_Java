package reports;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static int maxTry = 0;
    private int count = 0;

    public RetryAnalyzer() {
    }

    public static void setMaxTryFromContext(ITestContext context) {
        String retryCountString = context.getCurrentXmlTest().getParameter("maxRetryCount");
        if (retryCountString != null) {
            maxTry = Integer.parseInt(retryCountString);
        }
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            }
        }
        return false;
    }
}