package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides utility methods for checking the presence of exact text within web elements.
 * This class allows for checking exact text matches in both single elements and collections of elements,
 * as well as verifying if any or all from a list of expected text values are precisely present.
 */
public class ExactTextChecker {

    private static final ElementFinder elementFinder;
    private static final CommonMethods commonMethods;

    static {
        commonMethods = new CommonMethods();
        elementFinder = new ElementFinder();
    }

    private ExactTextChecker() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if the specified exact text is present in any of the elements located by a given {@link By} locator.
     *
     * @param locator       The {@link By} locator used to find elements.
     * @param expectedValue The exact text expected to be present.
     * @return {@code true} if the exact text is found in any of the located elements, {@code false} otherwise.
     */
    public static boolean isExactTextPresentInAnyElementLocatedBy(By locator, String expectedValue) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        return elements.stream().anyMatch(element -> commonMethods.getTextFromElement(element).equals(expectedValue));
    }

    /**
     * Checks if the specified exact text is present in any element within a given list of {@link WebElement}s.
     *
     * @param elements      The list of {@link WebElement}s to check.
     * @param expectedValue The exact text expected to be present.
     * @return {@code true} if the exact text is found in any of the provided elements, {@code false} otherwise.
     */
    public static boolean isExactTextPresentInAnyElement(List<WebElement> elements, String expectedValue) {
        return elements.stream().anyMatch(element -> commonMethods.getTextFromElement(element).equals(expectedValue));
    }

    /**
     * Checks if any exact text from a list of expected values is present in elements located by a given {@link By} locator.
     * This method iterates over each element found by the locator and checks if the text from the element exactly matches
     * any text from the list of expected values.
     *
     * @param locator        The {@link By} locator used to find elements.
     * @param expectedValues The list of exact text values expected to be present in any of the elements.
     * @return {@code true} if any exact text from the list is exactly found in the elements, {@code false} otherwise.
     */
    public static boolean isAnyExactTextFromListPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        return elements.stream()
                       .anyMatch(element -> valuesToFindSet.contains(commonMethods.getTextFromElement(element)));
    }

    /**
     * Checks if any exact text from a list of expected values is present in a given list of {@link WebElement}s.
     * Similar to the above method, it checks each element in the provided list to see if its text exactly matches
     * any of the expected exact texts.
     *
     * @param elements       The list of {@link WebElement}s to check.
     * @param expectedValues The list of exact text values expected to be present in any of the elements.
     * @return {@code true} if any exact text from the list is exactly found in the elements, {@code false} otherwise.
     */
    public static boolean isAnyExactTextFromListPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        return elements.stream()
                       .anyMatch(element -> valuesToFindSet.contains(commonMethods.getTextFromElement(element)));
    }

    /**
     * Verifies that all provided exact text values are present within the text of elements located by a given {@link By} locator.
     *
     * @param locator        The {@link By} locator to find elements.
     * @param expectedValues The list of exact text values expected to be present in the elements' text.
     * @return {@code true} if all provided exact text values are found within the elements' text, {@code false} otherwise.
     */
    public static boolean areAllExactTextsPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> remainingTextsToFind = new HashSet<>(expectedValues);

        for (WebElement element : elements) {
            String elementText = commonMethods.getTextFromElement(element);
            remainingTextsToFind.removeIf(elementText::equals);
            if (remainingTextsToFind.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies that all provided exact text values are present within the text of a given list of {@link WebElement}s.
     *
     * @param elements       The list of {@link WebElement}s to check.
     * @param expectedValues The list of exact text values expected to be present in the elements' text.
     * @return {@code true} if all provided exact text values are found within the elements' text, {@code false} otherwise.
     */
    public static boolean areAllExactTextsPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> remainingTextsToFind = new HashSet<>(expectedValues);

        for (WebElement element : elements) {
            String elementText = commonMethods.getTextFromElement(element);
            remainingTextsToFind.removeIf(elementText::equals);
            if (remainingTextsToFind.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified exact text is present in the text of an element located by a given {@link By} locator.
     *
     * @param locator       The {@link By} locator to find the element.
     * @param expectedValue The exact text expected to be present in the element.
     * @return {@code true} if the exact text is found in the element's text, {@code false} otherwise.
     */
    public static boolean isExactTextPresentInElementLocatedBy(By locator, String expectedValue) {
        return commonMethods.getTextFromElement(elementFinder.locateElementBy(locator)).equals(expectedValue);
    }

    /**
     * Checks if the specified exact text is present in the text of a given {@link WebElement}.
     *
     * @param webElement    The {@link WebElement} to check.
     * @param expectedValue The exact text expected to be present in the element.
     * @return {@code true} if the exact text is found in the element's text, {@code false} otherwise.
     */
    public static boolean isExactTextPresentInElement(WebElement webElement, String expectedValue) {
        return commonMethods.getTextFromElement(webElement).equals(expectedValue);
    }
}