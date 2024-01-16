package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button implements ElementState {

    private final WebDriver webDriver = WebDriverSetup.getInstance().getWebDriver();
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private CommonMethods commonMethods;
    private ElementFinder elementFinder;

    public Button() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    public boolean isEnabled(WebElement webElement) {
        try {
            return getElementState(webElement) == null || !getElementState(webElement).equalsIgnoreCase("true");
        } catch (TimeoutException | NoSuchElementException exception) {
            return "true".equals(getElementState(webElement));
        }
    }

    public boolean isEnabled(By locator) {
        try {
            return getElementState(locator) == null || !getElementState(locator).equalsIgnoreCase("true");
        } catch (TimeoutException | NoSuchElementException exception) {
            return "true".equals(getElementState(locator));
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute("disabled");
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.findElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getAttribute("disabled");
        }, 3);
    }
}