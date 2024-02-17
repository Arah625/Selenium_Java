package lh.juicecompany.PageUtilities.Detection.Wait.Locator;

import lh.juicecompany.PageUtilities.Detection.Wait.WaitUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class WaitForAllLocators extends WaitUtilities {

    //TODO: OK - WORKS
//    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
//        return waitForCondition(fluentWait, driver -> areAllLocatorsVisible(locatorsList));
//    }

    //TODO: Method that looks for each element passed as argument. If it finds, then it does not check for it again, and focus on missing ones

    public static boolean waitForEachLocatorVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        List<By> remainingLocators = new ArrayList<>(locatorsList); // Initially, all elements are to be checked.

        try {
            fluentWait.until(driver -> {
                Iterator<By> iterator = remainingLocators.iterator();
                while (iterator.hasNext()) {
                    By locator = iterator.next();
                    try {
                        System.out.println("Waiting for locator: " + locator); // Log attempt to check element.
                        WebElement webElement = driver.findElement(locator);
                        if (webElement.isDisplayed()) {
                            System.out.println("Element found and visible: " + locator); // Log visible element.
                            iterator.remove(); // Remove the visible element immediately.
                        }
                    } catch (NoSuchElementException e) {
                        // Element not found in the DOM, consider it not visible for this iteration.
                    }
                }
                return remainingLocators.isEmpty(); // Continue until all are found or timeout is reached.
            });

            return true; // All elements were visible before the timeout.
        } catch (TimeoutException e) {
            System.out.println("Timeout reached. The following locators elements were not visible:");
            remainingLocators.forEach(webElement -> System.out.println(webElement.toString()));
            return false; // Not all elements were visible before the timeout.
        }
    }

    //TODO: Method that constantly checks all locators on list, even if finds one.
    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        List<By> notVisibleLocators = new ArrayList<>(locatorsList); // Copy of locators list to track not found elements.

        // Custom Function to check visibility of all elements.
        Function<WebDriver, Boolean> visibilityOfAllElements = driver -> {
            boolean allVisible = true;
            List<By> currentlyNotVisible = new ArrayList<>();

            for (By locator : locatorsList) {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                    allVisible = false;
                    currentlyNotVisible.add(locator);
                }
            }

            notVisibleLocators.retainAll(currentlyNotVisible); // Update the list with currently not visible elements.
            return allVisible;
        };

        try {
            // Setting the polling interval and custom condition for visibility.
            fluentWait.until(visibilityOfAllElements);
            return true; // All elements were visible before timeout.
        } catch (TimeoutException e) {
            // Logging not visible elements after timeout.
            if (!notVisibleLocators.isEmpty()) {
                System.out.println("Timeout reached. The following elements were not visible: ");
                notVisibleLocators.forEach(locator -> System.out.println("Element not found or not visible for locator: " + locator));

//                for (By locator : notVisibleLocators) {
//                    System.out.println(locator.toString());
//                }
            }
            return false; // Not all elements were visible before timeout.
        }
    }
    //TODO: OK - WORKS

    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAllLocatorsVisibility(fluentWait, Arrays.asList(locators));
    }

    public static boolean waitForEachLocatorVisibility(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForEachLocatorVisibility(fluentWait, Arrays.asList(locators));
    }
}