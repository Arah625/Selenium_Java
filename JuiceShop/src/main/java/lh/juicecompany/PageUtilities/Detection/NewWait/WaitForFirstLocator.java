package lh.juicecompany.PageUtilities.Detection.NewWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForFirstLocator extends WaitingUtilities {

    //TODO: OK - WORKS

    public static boolean waitForAnyElementFromListPresence(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        return waitForCondition(fluentWait, driver -> areElementsFoundByLocatorVisible(locatorsList));
    }
    //TODO: OK - WORKS

    public static boolean waitForAnyElementByPresence(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAnyElementFromListPresence(fluentWait, Arrays.asList(locators));
    }
}
