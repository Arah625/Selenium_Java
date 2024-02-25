package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Supplier;

public class CommonMethods {
    private static final int REPETITIONS = 3;
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();

    public void sendKeysToElement(WebElement webElement, String stringValue) {
        ActionRetry.doActionRetry(() -> {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.waitingForElementToBeClickable(webElement);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
            InfoMessage.sendingKeysToElement(stringValue, webElement);
            webElement.sendKeys(stringValue);
        }, REPETITIONS, StaleElementReferenceException.class, ElementNotInteractableException.class);
    }

    public void clickElement(WebElement webElement) {
        ActionRetry.doActionRetry(() -> {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.waitingForElementToBeClickable(webElement);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            InfoMessage.clickingElement(webElement);
            webElement.click();
        }, REPETITIONS, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    public void clickElement(By locator) {
        ActionRetry.doActionRetry(() -> {
            WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.waitingForElementToBeClickable(webElement);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            InfoMessage.clickingElement(webElement);
            webElement.click();
        }, REPETITIONS, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    public String getTextFromElement(WebElement webElement) {
        Supplier<String> getTextAction = () -> {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getText();
        };
        return ActionRetry.doActionRetry(getTextAction, REPETITIONS, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    public String getTextFromElement(By locator) {
        Supplier<String> getTextAction = () -> {
            WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getText();
        };
        return ActionRetry.doActionRetry(getTextAction, REPETITIONS, StaleElementReferenceException.class);
    }

    @Deprecated
    public boolean isElementEnabled(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.isElementEnabled(webElement);
            return webElement.isEnabled();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, webElement);
            return false;
        }
    }

    @Deprecated
    public boolean isElementEnabled(By locator) {
        try {
            InfoMessage.waitingForVisibilityOfElement(locator);
            WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            InfoMessage.isElementEnabled(webElement);
            return webElement.isEnabled();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, locator);
            return false;
        }
    }
}