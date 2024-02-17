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

public class DropdownButton implements ElementState {
    private static final String ARIA_EXPANDED = "aria-expanded";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final CommonMethods commonMethods;
    private final ElementFinder elementFinder;

    public DropdownButton() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    public boolean isExpanded(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isExpanded(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public String getDropdownButtonState(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getAttribute(ARIA_EXPANDED).toLowerCase();
    }

    public void expand(WebElement webElement) {
        if (!isExpanded(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    public void expand(By locator) {
        if (!isExpanded(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    public void collapse(WebElement webElement) {
        if (isExpanded(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

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