package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkbox implements ElementState {

    private final WebDriver webDriver = WebDriverSetup.getInstance().getWebDriver();

    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();

    private CommonMethods commonMethods;
    private ElementFinder elementFinder;

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
            return webElement.getAttribute("aria-checked").toLowerCase();
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.findElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute("aria-checked").toLowerCase();
        }, 3);
    }
}
