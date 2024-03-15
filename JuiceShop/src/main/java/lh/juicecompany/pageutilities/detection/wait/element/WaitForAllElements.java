package lh.juicecompany.pageutilities.detection.wait.element;

import lh.juicecompany.pageutilities.detection.wait.WaitUtilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A utility class offering methods to wait for specific visibility conditions of web elements within a webpage.
 * It supports waiting for all given elements to become visible simultaneously, ensuring elements are ready for user interactions.
 * This class is especially useful in scenarios such as dynamic content loading where elements like password hints appear sequentially.
 * <p>
 * Utilizes {@link FluentWait} for customizable wait strategies, including setting timeouts and polling intervals,
 * while providing graceful exception handling capabilities.
 */
public class WaitForAllElements extends WaitUtilities {

    //TODO: Add Logging instead of souts

    /**
     * Waits for all elements in a given list to become visible at the same time. Each element's visibility is checked
     * at every polling interval within the total wait time specified.
     * This method is crucial for scenarios where multiple
     * elements, such as password hints that appear one after another, need to be confirmed visible before proceeding.
     *
     * @param fluentWait      The {@link FluentWait} instance specifying the wait conditions.
     * @param webElementsList The list of {@link WebElement} to check for simultaneous visibility.
     * @return True if all specified elements are visible within the wait period, false if at least one is not.
     * <p>
     * Usage example within a page object method:
     * <pre>{@code
     * public boolean arePasswordHintsVisible() {
     *     List<WebElement> passwordHints = Arrays.asList(hint1, hint2, hint3);
     *     return WaitForAllElements.waitForVisibilityOfAllElements(WebDriverSetup.getInstance().getFluentWait(),
     *     passwordHints);
     * }
     * }</pre>
     * <p>
     * This can be utilized in tests as follows:
     * <pre>{@code
     * Assert.assertTrue(registerPage.arePasswordHintsVisible(), "Password hints are not visible as expected.");
     * }</pre>
     */
    public static boolean waitForVisibilityOfAllElements(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
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

    /**
     * Waits for all given web elements to become visible at the same time. This method is a convenient overload,
     * allowing for direct input of multiple {@link WebElement} instances without needing to create a list explicitly.
     * This method is crucial for scenarios where multiple elements,
     * such as password hints that appear one after another, need to be confirmed visible before proceeding.
     *
     * @param fluentWait  The {@link FluentWait} instance specifying the wait conditions,
     *                    such as timeout and polling frequency.
     * @param webElements Varargs array of {@link WebElement} instances to be checked for visibility.
     * @return True if all specified elements become visible within the wait period, false otherwise.
     * <p>
     * Usage example within a page object method for a set of known elements:
     * <pre>{@code
     * public boolean areNavigationButtonsVisible() {
     *     return WaitForAllElements.waitForAllElementsVisibility(WebDriverSetup.getInstance().getFluentWait(),
     *     nextButton, backButton, homeButton);
     * }
     * }</pre>
     * <p>
     * This method can be utilized in tests to verify the visibility of a predefined group of elements:
     * <pre>{@code
     * Assert.assertTrue(page.areNavigationButtonsVisible(), "Navigation buttons are not visible as expected.");
     * }</pre>
     */
    public static boolean waitForVisibilityOfAllElements(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForVisibilityOfAllElements(fluentWait, Arrays.asList(webElements));
    }

    /**
     * Iteratively waits for each element in the provided list to become visible on the webpage,
     * checking at regular intervals within the total specified wait time.
     * The method continuously loops through the list, checking for the visibility of each element.
     * Once an element is found to be visible, it is removed from the list. This loop repeats at each polling
     * interval until either the list is empty (indicating all elements have been found and are visible) or the timeout
     * is reached. It is particularly useful for scenarios where elements are expected to appear
     * and disappear sequentially, such as dynamic form validation messages or tutorial steps
     * that are displayed one after another.
     *
     * @param fluentWait      The {@link FluentWait} instance specifying the wait conditions,
     *                        including timeout and polling frequency.
     * @param webElementsList A list of {@link WebElement} instances to be checked for visibility. The checking
     *                        is performed in sequence and at each polling interval.
     * @return True if all elements in the list become visible within the specified wait period,
     * false if any element remains invisible by the time the timeout is reached.
     * <p>
     * Usage example within a page object method for sequentially appearing elements:
     * <pre>{@code
     * public boolean areSequentialHintsVisible() {
     *     List<WebElement> hints = Arrays.asList(hint1, hint2, hint3);
     *     return WaitForAllElements.waitForEachElementVisibility(WebDriverSetup.getInstance().getFluentWait(), hints);
     * }
     * }</pre>
     * <p>
     * This method can be effectively utilized in tests to verify that sequential elements are visible as expected:
     * <pre>{@code
     * Assert.assertTrue(tutorialPage.areSequentialHintsVisible(), "Sequential hints are not visible as expected.");
     * }</pre>
     */
    public static boolean waitForEachElementToBeVisibleOnce(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        List<WebElement> remainingElements = new ArrayList<>(webElementsList);

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

    /**
     * Iteratively checks for the visibility of each provided {@link WebElement},
     * utilizing varargs for input convenience.
     * This method applies a dynamic polling mechanism within the total specified wait time to sequentially verify
     * the visibility of each element. It is adept for scenarios where elements are expected to sequentially become
     * visible, such as in dynamic content loading or interactive tutorials.
     * <p>
     * At each polling interval, the method iterates through the array of elements, checking for visibility.
     * Once an element is found to be visible, it's effectively "marked" as visible and
     * no longer checked in subsequent iterations, streamlining the process. This iterative removal from
     * the checking queue continues until all elements have been verified as visible or the total wait time expires.
     * <p>
     * Utilizing varargs allows for the direct and convenient specification of elements,
     * bypassing the need for explicit list creation.
     * This method is especially beneficial for scenarios requiring the sequential appearance and validation of a known,
     * finite set of elements.
     *
     * @param fluentWait  The {@link FluentWait} instance specifying the wait conditions,
     *                    such as timeout and polling frequency. This ensures a tailored approach to polling,
     *                    adaptable to the visibility timeline of each element.
     * @param webElements Varargs of {@link WebElement} to be sequentially checked for visibility.
     *                    This design simplifies the method's application, enhancing usability.
     * @return True if each specified element becomes visible within the allocated wait period,
     * false if at least one element remains invisible when the timeout is reached. This outcome ensures
     * that all targeted elements are ready for interaction, thus bolstering test reliability.
     * <p>
     * Usage example within a page object method for sequentially appearing elements:
     * <pre>{@code
     * public boolean areDynamicFormFieldsVisible() {
     *     return WaitForAllElements.waitForEachElementVisibility(WebDriverSetup.getInstance().getFluentWait(),
     *                                                            field1, field2, field3);
     * }
     * }</pre>
     * <p>
     * This method can be effectively leveraged in tests to affirm the sequential visibility of elements, bolstering
     * assertions with clarity:
     * <pre>{@code
     * Assert.assertTrue(formPage.areDynamicFormFieldsVisible(),
     * "Dynamic form fields are not sequentially visible as expected.");
     * }</pre>
     */
    public static boolean waitForEachElementToBeVisibleOnce(FluentWait<WebDriver> fluentWait,
                                                            WebElement... webElements) {
        return waitForEachElementToBeVisibleOnce(fluentWait, Arrays.asList(webElements));
    }
}