package lh.juicecompany.PageUtilities.Detection.Wait.Element;

import lh.juicecompany.PageUtilities.Detection.NewWait.WaitingUtilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class WaitForAllElements extends WaitingUtilities {

    //TODO: Change methods names and add Logging instead of souts

    public static boolean waitForAllElementsFromListPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        // Initialize a set to track descriptions of elements not visible or found
        Set<String> notVisibleOrFoundElementsDescriptions = ConcurrentHashMap.newKeySet();

        boolean allElementsVisible = waitForCondition(fluentWait, driver -> {
            // AtomicBoolean to track if all elements are visible in this check
            AtomicBoolean allVisible = new AtomicBoolean(true);

            webElementsList.forEach(element -> {
                try {
                    if (element.isDisplayed()) {
                        // If element is visible, it's not included in the not visible or found set
                        notVisibleOrFoundElementsDescriptions.remove(element.toString());
                    } else {
                        // If element is not visible, add its description for logging
                        notVisibleOrFoundElementsDescriptions.add(element.toString());
                        allVisible.set(false);
                    }
                } catch (NoSuchElementException e) {
                    // Element not found, add its unique identifier for logging
                    notVisibleOrFoundElementsDescriptions.add(element.toString());
                    allVisible.set(false);
                }
            });

            // Return true if all elements are visible; false otherwise
            return allVisible.get();
        });

        // After waitForCondition
        if (!allElementsVisible) {
            System.out.println("Timeout reached. Not all elements were visible before the timeout.");
            notVisibleOrFoundElementsDescriptions.forEach(description ->
                    System.out.println("Element not visible or not found: " + description));
            return false;
        }

        return true;
    }

    public static boolean waitForAllElementsPresence(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForAllElementsFromListPresence(fluentWait, Arrays.asList(webElements));
    }

    public static boolean waitForEachElementFromListVisibility(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        List<WebElement> remainingElements = new ArrayList<>(webElementsList); // Copy of the webElements list to track visibility.

        // Use the waitForCondition method to encapsulate the waiting logic.
        boolean allElementsVisible = waitForCondition(fluentWait, driver -> {
            Iterator<WebElement> iterator = remainingElements.iterator();
            while (iterator.hasNext()) {
                WebElement webElement = iterator.next();
                try {
                    if (webElement.isDisplayed()) {
                        // If the webElement is visible, log it and remove from the list.
                        System.out.println("Element found and visible: " + webElement);
                        iterator.remove(); // Remove the visible element immediately.
                    }
                } catch (NoSuchElementException noSuchElementException) {
                    // If an element is not found in the DOM, remove it from the list as well.
                    iterator.remove();
                    // Optionally, log this situation if needed for debugging.
                }
            }
            // The condition is met (return true) when the remainingElements list is empty.
            return remainingElements.isEmpty();
        });

        // After the waitForCondition call
        if (!allElementsVisible) {
            System.out.println("Timeout reached. The following elements were not visible:");
            remainingElements.forEach(webElement -> System.out.println(webElement.toString()));
            return false; // Indicates not all elements were visible before the timeout.
        }

        return true; // Indicates all elements were visible before the timeout.
    }

    public static boolean waitForEachElementFromListVisibility(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForEachElementFromListVisibility(fluentWait, Arrays.asList(webElements));
    }
}
