package lh.juicecompany.PageUtilities.Detection.Wait.Element;

import lh.juicecompany.PageUtilities.Detection.Wait.WaitUtilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class WaitForAllElements extends WaitUtilities {

    //TODO: Change methods names and add Logging instead of souts

    public static boolean waitForAllElementsFromListPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        Set<String> notVisibleOrFoundElementsDescriptions = ConcurrentHashMap.newKeySet();

        boolean allElementsVisible = waitForCondition(fluentWait, driver -> {
            AtomicBoolean allVisible = new AtomicBoolean(true);

            webElementsList.forEach(element -> {
                try {
                    if (element.isDisplayed()) {
                        notVisibleOrFoundElementsDescriptions.remove(element.toString());
                    } else {
                        notVisibleOrFoundElementsDescriptions.add(element.toString());
                        allVisible.set(false);
                    }
                } catch (NoSuchElementException e) {
                    notVisibleOrFoundElementsDescriptions.add(element.toString());
                    allVisible.set(false);
                }
            });

            return allVisible.get();
        });

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

        boolean allElementsVisible = waitForCondition(fluentWait, driver -> {
            Iterator<WebElement> iterator = remainingElements.iterator();
            while (iterator.hasNext()) {
                WebElement webElement = iterator.next();
                try {
                    if (webElement.isDisplayed()) {
                        System.out.println("Element found and visible: " + webElement);
                        iterator.remove();
                    }
                } catch (NoSuchElementException noSuchElementException) {
                    iterator.remove();
                }
            }
            return remainingElements.isEmpty();
        });

        if (!allElementsVisible) {
            System.out.println("Timeout reached. The following elements were not visible:");
            remainingElements.forEach(webElement -> System.out.println(webElement.toString()));
            return false;
        }

        return true;
    }

    public static boolean waitForEachElementFromListVisibility(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForEachElementFromListVisibility(fluentWait, Arrays.asList(webElements));
    }
}
