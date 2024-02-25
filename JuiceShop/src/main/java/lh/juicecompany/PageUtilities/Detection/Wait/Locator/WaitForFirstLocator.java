package lh.juicecompany.PageUtilities.Detection.Wait.Locator;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.Detection.Wait.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForFirstLocator extends WaitUtilities {

    private static final ElementFinder elementFinder = new ElementFinder();

    //TODO: Change methods names and add Logging instead of souts
    public static boolean waitForAnyElementFromListPresence(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        return waitForCondition(fluentWait, driver -> {
            for (By locator : locatorsList) {
                List<WebElement> elements = elementFinder.findElementsBy(locator);
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
    }

    public static boolean waitForAnyElementByPresence(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAnyElementFromListPresence(fluentWait, Arrays.asList(locators));
    }
}