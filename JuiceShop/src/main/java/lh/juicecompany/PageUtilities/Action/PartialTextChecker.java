package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides utility methods for checking the presence of text within web elements,
 * including partial text matches in single elements or collections of elements,
 * and verifying if any or all of a list of expected text values are present.
 */
public class PartialTextChecker {

    private static final ElementFinder elementFinder;
    private static final CommonMethods commonMethods;

    static {
        commonMethods = new CommonMethods();
        elementFinder = new ElementFinder();
    }

    private PartialTextChecker() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if the expected partial text is present in any of the elements located by a specific {@link By} locator.
     *
     * @param locator       The {@link By} locator to find elements.
     * @param expectedValue The partial text expected to be present in any of the elements.
     * @return {@code true} if the expected partial text is found in any of the elements, {@code false} otherwise.
     */
    public static boolean isPartialTextPresentInAnyElementLocatedBy(By locator, String expectedValue) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        return elements.stream().anyMatch(element -> commonMethods.getTextFromElement(element).contains(expectedValue));
    }

    /**
     * Checks if the expected partial text is present in any element within a list of {@link WebElement}s.
     *
     * @param elements      The list of {@link WebElement}s to check.
     * @param expectedValue The partial text expected to be present in any of the elements.
     * @return {@code true} if the expected partial text is found in any of the elements, {@code false} otherwise.
     */
    public static boolean isPartialTextPresentInAnyElement(List<WebElement> elements, String expectedValue) {
        return elements.stream().anyMatch(element -> commonMethods.getTextFromElement(element).contains(expectedValue));
    }

    /**
     * Checks if any partial text from a list of expected values is present in elements located by a specific {@link By} locator.
     *
     * @param locator        The {@link By} locator to find elements.
     * @param expectedValues The list of partial text values expected to be present in any of the elements.
     * @return {@code true} if any partial text from the list is found in the elements, {@code false} otherwise.
     */
    public static boolean isAnyPartialTextFromListPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        return elements.stream()
                       .anyMatch(element -> valuesToFindSet.contains(commonMethods.getTextFromElement(element)));
    }

    /**
     * Checks if any partial text from a list of expected values is present in a list of {@link WebElement}s.
     *
     * @param elements       The list of {@link WebElement}s to check.
     * @param expectedValues The list of partial text values expected to be present in any of the elements.
     * @return {@code true} if any partial text from the list is found in the elements, {@code false} otherwise.
     */
    public static boolean isAnyPartialTextFromListPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        return elements.stream()
                       .anyMatch(element -> valuesToFindSet.contains(commonMethods.getTextFromElement(element)));
    }

    /**
     * Verifies that all provided text values are contained within the text of elements located by a specific {@link By} locator.
     *
     * @param locator        The {@link By} locator to find elements.
     * @param expectedValues The list of text values expected to be contained within any of the elements' text.
     * @return {@code true} if all provided text values are found within the elements' text, {@code false} otherwise.
     */
    public static boolean areAllContainedTextsPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> remainingTextsToFind = new HashSet<>(expectedValues);

        for (WebElement element : elements) {
            String elementText = commonMethods.getTextFromElement(element);
            remainingTextsToFind.removeIf(elementText::contains);
            if (remainingTextsToFind.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies that all provided text values are contained within the text of a list of {@link WebElement}s.
     *
     * @param elements       The list of {@link WebElement}s to check.
     * @param expectedValues The list of text values expected to be contained within any of the elements' text.
     * @return {@code true} if all provided text values are found within the elements' text, {@code false} otherwise.
     */
    public static boolean areAllContainedTextsPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> remainingTextsToFind = new HashSet<>(expectedValues);

        for (WebElement element : elements) {
            String elementText = commonMethods.getTextFromElement(element);
            remainingTextsToFind.removeIf(elementText::contains);
            if (remainingTextsToFind.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the expected partial text is present in the text of an element located by a specific {@link By} locator.
     *
     * @param locator       The {@link By} locator to find the element.
     * @param expectedValue The partial text expected to be present in the element.
     * @return {@code true} if the expected partial text is found in the element, {@code false} otherwise.
     */
    public static boolean isPartialTextPresentInElementLocatedBy(By locator, String expectedValue) {
        return commonMethods.getTextFromElement(elementFinder.locateElementBy(locator)).contains(expectedValue);
    }

    /**
     * Checks if the expected partial text is present in the text of a specified {@link WebElement}.
     *
     * @param webElement    The {@link WebElement} to check.
     * @param expectedValue The partial text expected to be present in the element.
     * @return {@code true} if the expected partial text is found in the element, {@code false} otherwise.
     */
    public static boolean isPartialTextPresentInElement(WebElement webElement, String expectedValue) {
        return commonMethods.getTextFromElement(webElement).contains(expectedValue);
    }
}