package Setup;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import reports.ExtentTestManager;

public class TestLogger {

    private static Markup valueLabel(String key) {
        return MarkupHelper.createLabel(key + " :", ExtentColor.PURPLE);
    }

    public static void logIterationVariables(int iteration, Object... vars) {
        try {
            ExtentTest currentTest = ExtentTestManager.getTest();
            if (currentTest == null) {
                System.err.println("ExtentTest instance required for logging is not available.");
                return;
            }
            if (vars.length % 2 != 0) {
                System.err.println("Variables should be in key - value pairs.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            Markup iterationLabel = MarkupHelper.createLabel("Iteration : " + iteration, ExtentColor.ORANGE);
            sb.append(String.format("<table><tr><td><strong>%s</strong></td><td>", iterationLabel.getMarkup()));

            for (int i = 0; i < vars.length; i += 2) {
                String key = String.valueOf(vars[i]); // Convert key to string
                String value = String.valueOf(vars[i + 1]); // Convert value to string, handling any type
                sb.append(String.format("%s %s<br>", valueLabel(key).getMarkup(), " <strong>" + value + " </strong>"));
            }

            sb.append("</td></tr></table>");
            currentTest.info(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logTestVariables(Object... vars) {
        try {
            ExtentTest currentTest = ExtentTestManager.getTest();
            if (currentTest == null) {
                System.err.println("ExtentTest instance required for logging is not available.");
                return;
            }
            if (vars.length % 2 != 0) {
                System.err.println("Variables should be in key - value pairs.");
                return;
            }
            for (int i = 0; i < vars.length; i += 2) {
                String key = String.valueOf(vars[i]); // Convert key to string
                String value = String.valueOf(vars[i + 1]); // Convert value to string, handling any type
                currentTest.info(valueLabel(key).getMarkup() + " <strong>" + value + " </strong>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
