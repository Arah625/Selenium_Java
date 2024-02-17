package lh.juicecompany.PageUtilities.Detection.Wait.Locator;

import lh.juicecompany.PageUtilities.Detection.NewWait.WaitingUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForFirstLocator extends WaitingUtilities {

    //TODO: Change methods names and add Logging instead of souts
    public static boolean waitForAnyElementFromListPresence(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        try {
            return fluentWait.until(driver -> {
                for (By locator : locatorsList) {
                    List<WebElement> elements = driver.findElements(locator);
                    if (!elements.isEmpty()) {
                        for (WebElement element : elements) {
                            if (element.isDisplayed()) {
                                System.out.println("Element found and is visible: " + locator);
                                return true;
                            }
                        }
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("Timeout reached without any element from the list becoming visible.");
            locatorsList.forEach(locator -> System.out.println("Element not found or not visible for locator: " + locator));
            return false;
        }
    }

    public static boolean waitForAnyElementByPresence(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAnyElementFromListPresence(fluentWait, Arrays.asList(locators));
    }
}
