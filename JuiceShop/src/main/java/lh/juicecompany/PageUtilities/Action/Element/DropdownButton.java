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
 * Manages interactions with dropdown button elements, enabling actions such as expanding and collapsing
 * the dropdown. This class implements the {@link ElementState} interface for determining the expanded or collapsed
 * state of a dropdown button based on the "aria-expanded" attribute.
 */
public class DropdownButton implements ElementState {
    private static final String ARIA_EXPANDED = "aria-expanded";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final CommonMethods commonMethods;
    private final ElementFinder elementFinder;

    /**
     * Initializes a new instance of the DropdownButton class, setting up the necessary utilities for element interaction.
     */
    public DropdownButton() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    /**
     * Determines whether the specified {@link WebElement} representing a dropdown button is currently expanded.
     *
     * @param webElement The {@link WebElement} to check.
     * @return {@code true} if the dropdown button is expanded, {@code false} otherwise.
     */
    public boolean isExpanded(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Determines whether the dropdown button identified by the given locator is currently expanded.
     *
     * @param locator The {@link By} locator to identify the dropdown button element.
     * @return {@code true} if the dropdown button is expanded, {@code false} otherwise.
     */
    public boolean isExpanded(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Expands the specified dropdown button {@link WebElement} if it is not already expanded.
     *
     * @param webElement The {@link WebElement} representing the dropdown button to expand.
     */
    public void expand(WebElement webElement) {
        if (!isExpanded(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    /**
     * Expands the dropdown button identified by the given locator if it is not already expanded.
     *
     * @param locator The {@link By} locator to identify the dropdown button element.
     */
    public void expand(By locator) {
        if (!isExpanded(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    /**
     * Collapses the specified dropdown button {@link WebElement} if it is currently expanded.
     *
     * @param webElement The {@link WebElement} representing the dropdown button to collapse.
     */
    public void collapse(WebElement webElement) {
        if (isExpanded(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    /**
     * Collapses the dropdown button identified by the given locator if it is currently expanded.
     *
     * @param locator The {@link By} locator to identify the dropdown button element.
     */
    public void collapse(By locator) {
        if (isExpanded(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(ARIA_EXPANDED).toLowerCase();
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.locateElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(ARIA_EXPANDED).toLowerCase();
        }, 3);
    }
}