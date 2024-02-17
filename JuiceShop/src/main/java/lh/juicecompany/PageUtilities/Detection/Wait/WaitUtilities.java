package lh.juicecompany.PageUtilities.Detection.Wait;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.function.Function;

public abstract class WaitUtilities {

    private static final ElementFinder elementFinder;

    static {
        elementFinder = new ElementFinder();
    }

    protected static boolean waitForCondition(FluentWait<WebDriver> fluentWait, Function<WebDriver, Boolean> condition) {
        try {
            return fluentWait.until(condition);
        } catch (TimeoutException timeoutException) {
            System.out.println("Exception while waiting: " + Arrays.toString(timeoutException.getStackTrace()));
            return false;
        }
    }
}
