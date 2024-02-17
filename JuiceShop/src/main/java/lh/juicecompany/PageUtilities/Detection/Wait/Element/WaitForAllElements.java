package lh.juicecompany.PageUtilities.Detection.Wait.Element;

import lh.juicecompany.PageUtilities.Detection.NewWait.WaitingUtilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;

public class WaitForAllElements extends WaitingUtilities {

    //TODO: Change methods names and add Logging instead of souts

    public static boolean waitForAllElementsFromListPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        // Create a copy of the webElementsList to track elements not visible or found
        List<WebElement> elementsToCheck = new ArrayList<>(webElementsList);
        // Use a Set to track descriptions of elements that were not visible or found for final logging
        Set<String> notVisibleOrFoundElementsDescriptions = new HashSet<>();

        try {
            fluentWait.ignoring(NoSuchElementException.class) // Ignore NoSuchElementException
                      .until(driver -> {
                          elementsToCheck.forEach(element -> {
                              try {
                                  if (element.isDisplayed()) {
                                      // Element is visible, remove its description from the logging set if it was added previously
                                      notVisibleOrFoundElementsDescriptions.remove(element.toString());
                                  } else {
                                      // Element not visible, add its description for potential logging
                                      notVisibleOrFoundElementsDescriptions.add(element.toString());
                                  }
                              } catch (NoSuchElementException e) {
                                  // Element not found, add its unique identifier for logging
                                  notVisibleOrFoundElementsDescriptions.add(element.toString());
                              }
                          });
                          // Condition is met if there are no descriptions to log, meaning all elements were visible at some point
                          return notVisibleOrFoundElementsDescriptions.isEmpty();
                      });
            // If fluentWait.until completes without TimeoutException, all elements were visible before the timeout
            return true;
        } catch (TimeoutException e) {
            // Timeout reached, log elements that were not visible or found
            System.out.println("Timeout reached. Not all elements were visible before the timeout.");
            notVisibleOrFoundElementsDescriptions.forEach(description -> System.out.println("Element not visible or not found: " + description));
            return false;
        }
    }

    public static boolean waitForAllElementsPresence(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForAllElementsFromListPresence(fluentWait, Arrays.asList(webElements));
    }

    public static boolean waitForEachElementFromListVisibility(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        List<WebElement> remainingElements = new ArrayList<>(webElementsList); // Initially, all elements are to be checked.

        try {
            fluentWait.until(driver -> {
                Iterator<WebElement> iterator = remainingElements.iterator();
                while (iterator.hasNext()) {
                    WebElement webElement = iterator.next();
                    try {
                        System.out.println("Waiting for element: " + webElement); // Log attempt to check element.
                        if (webElement.isDisplayed()) {
                            System.out.println("Element found and visible: " + webElement); // Log visible element.
                            iterator.remove(); // Remove the visible element immediately.
                        }
                    } catch (NoSuchElementException noSuchElementException) {
                        // Element not found in the DOM, consider it not visible for this iteration.
                    }
                }
                return remainingElements.isEmpty(); // Continue until all are found or timeout is reached.
            });

            return true; // All elements were visible before the timeout.
        } catch (TimeoutException e) {
            System.out.println("Timeout reached. The following elements were not visible:");
            remainingElements.forEach(webElement -> System.out.println(webElement.toString()));
            return false; // Not all elements were visible before the timeout.
        }
    }

    public static boolean waitForEachElementFromListVisibility(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForEachElementFromListVisibility(fluentWait, Arrays.asList(webElements));
    }
}
