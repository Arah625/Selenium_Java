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

public class Button implements ElementState {

    private static final String TRUE = "true";
    private static final String DISABLED = "disabled";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final ElementFinder elementFinder;

    public Button() {
        this.elementFinder = new ElementFinder();
    }

    public boolean isEnabled(WebElement webElement) {
        try {
            return getElementState(webElement) == null || !getElementState(webElement).equalsIgnoreCase(TRUE);
        } catch (TimeoutException | NoSuchElementException exception) {
            return TRUE.equals(getElementState(webElement));
        }
    }

    public boolean isEnabled(By locator) {
        try {
            return getElementState(locator) == null || !getElementState(locator).equalsIgnoreCase(TRUE);
        } catch (TimeoutException | NoSuchElementException exception) {
            return TRUE.equals(getElementState(locator));
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