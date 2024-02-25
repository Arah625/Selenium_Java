package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExactTextChecker {

    private static final ElementFinder elementFinder;
    private static final CommonMethods commonMethods;

    static {
        commonMethods = new CommonMethods();
        elementFinder = new ElementFinder();
    }

    private ExactTextChecker() {
    }

    public static boolean isExactTextPresentInAnyElementLocatedBy(By locator, String expectedValue) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        for (WebElement element : elements) {
            if (commonMethods.getTextFromElement(element).equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExactTextPresentInAnyElement(List<WebElement> elements, String expectedValue) {
        for (WebElement element : elements) {
            if (commonMethods.getTextFromElement(element).equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyExactTextFromListPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        for (WebElement element : elements) {
            if (valuesToFindSet.contains(commonMethods.getTextFromElement(element))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyExactTextFromListPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        for (WebElement element : elements) {
            if (valuesToFindSet.contains(commonMethods.getTextFromElement(element))) {
                return true;
            }
        }
        return false;
    }

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

    public static boolean isExactTextPresentInElementLocatedBy(By locator, String expectedValue) {
        return commonMethods.getTextFromElement(elementFinder.locateElementBy(locator)).equals(expectedValue);
    }

    public static boolean isExactTextPresentInElement(WebElement webElement, String expectedValue) {
        return commonMethods.getTextFromElement(webElement).equals(expectedValue);
    }
}