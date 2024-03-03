package lh.juicecompany.PageUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

/**
 * A utility class for collecting and manipulating WebDriver elements and locators.
 * Provides methods to create lists of locators and web elements, and to convert locators to web elements.
 */
public class ElementCollector {

    /**
     * Creates a list of {@link By} locators from a variable number of arguments.
     *
     * @param locators A variable number of {@link By} locators.
     * @return A list containing all provided {@link By} locators.
     */
    public static List<By> createListOfLocators(By... locators) {
        return Arrays.asList(locators);
    }

    /**
     * Creates a list of {@link WebElement}s from a variable number of arguments.
     *
     * @param webElements A variable number of {@link WebElement}s.
     * @return A list containing all provided {@link WebElement}s.
     */
    public static List<WebElement> createListOfWebElements(WebElement... webElements) {
        return Arrays.asList(webElements);
    }

    /**
     * Converts a list of {@link By} locators into a list of {@link WebElement}s by finding all matching elements
     * in the current page context using a given WebDriver. This method streamlines the process of collecting
     * web elements from multiple locators, enhancing test script readability and maintainability.
     *
     * @param driver       The {@link WebDriver} instance used to find elements.
     * @param locatorsList A list of {@link By} locators to be converted into {@link WebElement}s.
     * @return A list of {@link WebElement}s corresponding to the provided locators. Each locator is used to find
     * and add all matching elements to the list. If a locator does not match any elements on the page,
     * no elements are added to the list for that locator, thus effectively filtering out non-matching locators.
     */
    public List<WebElement> convertListOfLocatorsToListOfWebElements(WebDriver driver, List<By> locatorsList) {
        return locatorsList.stream()
                           .flatMap(locator -> driver.findElements(locator).stream())
                           .toList();
    }
}