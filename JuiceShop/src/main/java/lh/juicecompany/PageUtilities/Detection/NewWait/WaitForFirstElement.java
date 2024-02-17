package lh.juicecompany.PageUtilities.Detection.NewWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForFirstElement extends WaitingUtilities {
    //TODO: OK - WORKS

    public static boolean waitForAnyElementPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        return waitForCondition(fluentWait, driver -> isAnyElementInListVisible(webElementsList));
    }

    //TODO: OK - WORKS
    public static boolean waitForAnyElementPresence(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForAnyElementPresence(fluentWait, Arrays.asList(webElements));
    }
}