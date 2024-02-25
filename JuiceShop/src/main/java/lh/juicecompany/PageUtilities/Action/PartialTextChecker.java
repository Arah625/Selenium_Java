package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartialTextChecker {

    private static final ElementFinder elementFinder;
    private static final CommonMethods commonMethods;

    static {
        commonMethods = new CommonMethods();
        elementFinder = new ElementFinder();
    }

    private PartialTextChecker() {
    }

    public static boolean isPartialTextPresentInAnyElementLocatedBy(By locator, String expectedValue) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        for (WebElement element : elements) {
            if (commonMethods.getTextFromElement(element).contains(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPartialTextPresentInAnyElement(List<WebElement> elements, String expectedValue) {
        for (WebElement element : elements) {
            if (commonMethods.getTextFromElement(element).contains(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyPartialTextFromListPresentInElementsLocatedBy(By locator, List<String> expectedValues) {
        List<WebElement> elements = elementFinder.findElementsBy(locator);
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        for (WebElement element : elements) {
            if (valuesToFindSet.contains(commonMethods.getTextFromElement(element))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyPartialTextFromListPresentInElements(List<WebElement> elements, List<String> expectedValues) {
        Set<String> valuesToFindSet = new HashSet<>(expectedValues);
        for (WebElement element : elements) {
            if (valuesToFindSet.contains(commonMethods.getTextFromElement(element))) {
                return true;
            }
        }
        return false;
    }

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

    public static boolean isPartialTextPresentInElementLocatedBy(By locator, String expectedValue) {
        return commonMethods.getTextFromElement(elementFinder.locateElementBy(locator)).contains(expectedValue);
    }

    public static boolean isPartialTextPresentInElement(WebElement webElement, String expectedValue) {
        return commonMethods.getTextFromElement(webElement).contains(expectedValue);
    }
}