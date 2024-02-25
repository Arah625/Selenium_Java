package lh.juicecompany.PageUtilities.Detection.Wait.Locator;

import lh.juicecompany.PageUtilities.Detection.Wait.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WaitForAllLocators extends WaitUtilities {

    //TODO: OK - WORKS
//    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
//        return waitForCondition(fluentWait, driver -> areAllLocatorsVisible(locatorsList));
//    }

    //TODO: Method that looks for each element passed as argument. If it finds, then it does not check for it again, and focus on missing ones

    public static boolean waitForEachLocatorVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        List<By> remainingLocators = new ArrayList<>(locatorsList); // Initially, all elements are to be checked.

        boolean allVisible = waitForCondition(fluentWait, driver -> {
            Iterator<By> iterator = remainingLocators.iterator();
            while (iterator.hasNext()) {
                By locator = iterator.next();
                try {
                    WebElement webElement = driver.findElement(locator);
                    if (webElement.isDisplayed()) {
                        iterator.remove(); // Element is visible, remove it.
                    }
                } catch (NoSuchElementException e) {
                    // Element not found, consider it not visible for this iteration.
                }
            }
            return remainingLocators.isEmpty(); // If empty, all elements were found and visible.
        });

        if (!allVisible) {
            System.out.println("Timeout reached. The following locators' elements were not visible:");
            remainingLocators.forEach(locator -> System.out.println(locator.toString()));
        }

        return allVisible; // Reflects the actual outcome based on visibility.
    }

    //TODO: Method that constantly checks all locators on list, even if finds one.
    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        List<By> notVisibleLocators = new ArrayList<>(locatorsList); // Copy of locators list to track not found elements.

        // Directly using waitForCondition with a lambda expression for the custom condition.
        boolean allElementsVisible = waitForCondition(fluentWait, driver -> {
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
        });

        if (!allElementsVisible) {
            // Logging not visible elements after checking visibility.
            System.out.println("Some elements were not visible: ");
            notVisibleLocators.forEach(locator -> System.out.println("Element not found or not visible for locator: " + locator));
        }

        return allElementsVisible;
    }
    //TODO: OK - WORKS

    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAllLocatorsVisibility(fluentWait, Arrays.asList(locators));
    }

    public static boolean waitForEachLocatorVisibility(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForEachLocatorVisibility(fluentWait, Arrays.asList(locators));
    }
}