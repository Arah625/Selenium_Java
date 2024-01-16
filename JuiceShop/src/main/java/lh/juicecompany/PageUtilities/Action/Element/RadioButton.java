package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioButton implements ElementState {

    private final WebDriver webDriver = WebDriverSetup.getInstance().getWebDriver();
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private CommonMethods commonMethods;
    private ElementFinder elementFinder;

    public RadioButton() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    public boolean isSelected(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isSelected(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public void select(WebElement webElement) {
        if (!isSelected(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    public void select(By locator) {
        if (!isSelected(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return String.valueOf(webElement.getAttribute("class").contains("mat-radio-checked"));
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.findElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return String.valueOf(webElement.getAttribute("class").contains("mat-radio-checked"));
        }, 3);
    }
}