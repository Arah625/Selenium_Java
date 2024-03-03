package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provides utilities for interacting with checkbox elements on a web page, including checking, unchecking,
 * and determining the checked state of checkboxes. This class implements the {@link ElementState} interface
 * to utilize the common method of fetching an element's state.
 */
public class Checkbox implements ElementState {

    private static final String ARIA_CHECKED = "aria-checked";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final CommonMethods commonMethods;
    private final ElementFinder elementFinder;

    /**
     * Constructs a Checkbox instance, initializing the necessary utilities for element interaction.
     */
    public Checkbox() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    /**
     * Checks if the specified {@link WebElement} representing a checkbox is checked.
     *
     * @param webElement The {@link WebElement} to check.
     * @return {@code true} if the checkbox is checked, {@code false} otherwise.
     */
    public boolean isChecked(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Checks if the checkbox identified by the given locator is checked.
     *
     * @param locator The {@link By} locator to identify the checkbox element.
     * @return {@code true} if the checkbox is checked, {@code false} otherwise.
     */
    public boolean isChecked(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Checks (selects) the specified checkbox {@link WebElement} if it is not already checked.
     *
     * @param webElement The {@link WebElement} representing the checkbox to check.
     */
    public void check(WebElement webElement) {
        if (!isChecked(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    /**
     * Checks (selects) the checkbox identified by the given locator if it is not already checked.
     *
     * @param locator The {@link By} locator to identify the checkbox element.
     */
    public void check(By locator) {
        if (!isChecked(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    /**
     * Unchecks (deselects) the specified checkbox {@link WebElement} if it is currently checked.
     *
     * @param webElement The {@link WebElement} representing the checkbox to uncheck.
     */
    public void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    /**
     * Unchecks (deselects) the checkbox identified by the given locator if it is currently checked.
     *
     * @param locator The {@link By} locator to identify the checkbox element.
     */
    public void uncheck(By locator) {
        if (isChecked(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(ARIA_CHECKED).toLowerCase();
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.locateElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(ARIA_CHECKED).toLowerCase();
        }, 3);
    }
}