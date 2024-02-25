package lh.juicecompany.PageUtilities.Detection.NewWait;

import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class WaitingUtilities {

    private static final ElementFinder elementFinder;

    static {
        elementFinder = new ElementFinder();
    }

//    private static boolean isElementDisplayedAndNotNull(WebElement element) {
//        try {
//            boolean isVisible = element != null && element.isDisplayed();
//            if (isVisible) {
//                InfoMessage.elementIsVisible(element);
//            }
//            return isVisible;
//        } catch (NoSuchElementException | NullPointerException exception) {
//            return false;
//        }
//    }

    private static boolean isElementDisplayedAndNotNull(WebElement element, LoggingBehavior loggingBehavior) {
        try {
            boolean isVisible = element != null && element.isDisplayed();
            if (isVisible && (loggingBehavior == LoggingBehavior.LOG_VISIBLE)) {
                InfoMessage.elementIsVisible(element); // Assuming this method logs the visibility
            }
            return isVisible;
        } catch (NoSuchElementException | NullPointerException exception) {
            return false;
        }
    }

    //TODO: Check all final methods, in test, positive and negative scenarios, if they work properly. If so, then delete redundant code/clean up this class. Also rethink all methods names

//    private static boolean isElementDisplayedAndNotNull(WebElement element) {
//        try {
//            return element != null && element.isDisplayed();
//        } catch (NoSuchElementException | NullPointerException exception) {
//            return false;
//        }
//    }

    protected static boolean isAnyElementInListVisible(List<WebElement> elements) {
        return elements.stream()
                       .anyMatch(element -> isElementDisplayedAndNotNull(element, LoggingBehavior.LOG_VISIBLE));
    }

    protected static boolean areElementsFoundByLocatorVisible(List<By> locators) {
        return locators.stream().anyMatch(locator -> {
            List<WebElement> elements = elementFinder.findElementsBy(locator);
            return elements.stream()
                           .anyMatch(element -> isElementDisplayedAndNotNull(element, LoggingBehavior.LOG_VISIBLE));
        });
    }

//    protected static boolean areElementsFoundByLocatorVisible(List<By> locators) {
//        return locators.stream().anyMatch(locator ->
//                elementFinder.findElementsBy(locator)
//                             .stream()
//                             .anyMatch(WaitingUtilities::isElementDisplayedAndNotNull));
//    }

    protected static boolean areAllElementsVisible(List<WebElement> elements) {
        //                InfoMessage.elementIsVisible(element);
        //                System.out.println("Widoczny element: " + element);
        return elements.stream()
                       .allMatch(element -> isElementDisplayedAndNotNull(element, LoggingBehavior.LOG_NOT_VISIBLE));
    }

//    protected static boolean areAllElementsVisible(List<WebElement> elements) {
//        return elements.stream().allMatch(WaitingUtilities::isElementDisplayedAndNotNull);
//    }

    protected static boolean areAllLocatorsVisible(List<By> locators) {
        return locators.stream().allMatch(locator -> {
            List<WebElement> elements = elementFinder.findElementsBy(locator);
            return !elements.isEmpty() && elements.stream()
                                                  .allMatch(element -> isElementDisplayedAndNotNull(element, LoggingBehavior.LOG_NOT_VISIBLE));
        });
    }

//    protected static boolean areAllLocatorsVisible(List<By> locators) {
//        return locators.stream().allMatch(locator -> {
//            List<WebElement> elements = elementFinder.findElementsBy(locator);
//            return !elements.isEmpty() && areAllElementsVisible(elements);
//        });
//    }

    protected static boolean waitForCondition(FluentWait<WebDriver> fluentWait, Function<WebDriver, Boolean> condition) {
        try {
            return fluentWait.until(condition);
        } catch (TimeoutException timeoutException) {
            System.out.println("Exception while waiting: " + Arrays.toString(timeoutException.getStackTrace()));
            return false;
        }
    }

    protected static boolean logNotVisibleElementsAndReturnFailure(List<WebElement> notVisibleElements) {
        System.out.println("Timeout reached. The following elements were not visible:");
        notVisibleElements.forEach(webElement -> System.out.println(webElement.toString()));
        return false; // Indicates not all elements were visible before the timeout.
    }


    public enum LoggingBehavior {
        LOG_VISIBLE,
        LOG_NOT_VISIBLE
    }
}
