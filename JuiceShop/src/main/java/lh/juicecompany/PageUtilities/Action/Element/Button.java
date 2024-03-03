package lh.juicecompany.PageUtilities.Action.Element;

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
 * Represents a Button element and provides methods to determine its state, such as being enabled or disabled.
 * This class utilizes {@link WebDriverWait} to ensure the element is visible and {@link ElementFinder} for locating elements.
 */
public class Button implements ElementState {

    private static final String TRUE = "true";
    private static final String DISABLED = "disabled";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final ElementFinder elementFinder;

    /**
     * Constructs a Button instance initializing the {@link ElementFinder} for locating elements.
     */
    public Button() {
        this.elementFinder = new ElementFinder();
    }

    /**
     * Determines if the provided {@link WebElement} is enabled by checking its "disabled" attribute.
     *
     * @param webElement The WebElement to check.
     * @return true if the element is enabled (not having a "disabled" attribute set to "true"), false otherwise.
     */
    public boolean isEnabled(WebElement webElement) {
        try {
            return getElementState(webElement) == null || !getElementState(webElement).equalsIgnoreCase(TRUE);
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Determines if the element identified by the given locator is enabled by checking its "disabled" attribute.
     *
     * @param locator The locator to identify the element.
     * @return true if the element is enabled (not having a "disabled" attribute set to "true"), false otherwise.
     */
    public boolean isEnabled(By locator) {
        try {
            return getElementState(locator) == null || !getElementState(locator).equalsIgnoreCase(TRUE);
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(DISABLED);
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.locateElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute(DISABLED);
        }, 3);
    }
}