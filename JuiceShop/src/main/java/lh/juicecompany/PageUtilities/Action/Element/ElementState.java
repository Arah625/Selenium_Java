package lh.juicecompany.PageUtilities.Action.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Defines the contract for retrieving the state of an element within a web page.
 * This interface allows for the determination and return of the state of elements
 * identified either directly by {@link WebElement} or via {@link By} locators.
 */
public interface ElementState {

    /**
     * Retrieves the state of the specified web element. The definition of "state"
     * is implementation-specific and can refer to attributes such as visibility,
     * enabled/disabled status, or other properties indicative of the element's current condition.
     *
     * @param webElement The {@link WebElement} whose state is to be determined.
     * @return A {@link String} representing the state of the element. The interpretation
     * of the returned value is defined by the implementing class.
     */
    String getElementState(WebElement webElement);

    /**
     * Retrieves the state of the element identified by the given locator. Similar
     * to {@link #getElementState(WebElement)}, the definition of "state" is dependent on the
     * implementation and could pertain to various attributes or conditions of the element.
     *
     * @param locator The {@link By} locator used to find the element whose state is to be determined.
     * @return A {@link String} representing the state of the identified element. The interpretation
     * of the returned value is specific to the implementation.
     */
    String getElementState(By locator);
}