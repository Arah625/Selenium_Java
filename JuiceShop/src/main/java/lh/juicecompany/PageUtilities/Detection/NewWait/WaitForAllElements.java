package lh.juicecompany.PageUtilities.Detection.NewWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForAllElements extends WaitingUtilities {
    //TODO: OK - WORKS

    public static boolean waitForAllElementsFromListPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        return waitForCondition(fluentWait, driver -> areAllElementsVisible(webElementsList));
    }

    //TODO: OK - WORKS
    public static boolean waitForAllElementsPresence(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForAllElementsFromListPresence(fluentWait, Arrays.asList(webElements));
    }
}
