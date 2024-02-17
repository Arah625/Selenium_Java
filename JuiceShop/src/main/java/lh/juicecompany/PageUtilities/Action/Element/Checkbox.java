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

public class Checkbox implements ElementState {

    private static final String ARIA_CHECKED = "aria-checked";
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final CommonMethods commonMethods;
    private final ElementFinder elementFinder;

    public Checkbox() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    public boolean isChecked(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isChecked(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public void check(WebElement webElement) {
        if (!isChecked(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    public void check(By locator) {
        if (!isChecked(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    public void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

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
