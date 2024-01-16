package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.Exceptions.MaximumNumberOfRepetitionsException;
import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
    private static final int COUNTER_BASE_VALUE = 0;
    private static final int REPETITIONS = 3;
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();

    public void sendKeysToElement(WebElement webElement, String stringValue) {
        int counter = COUNTER_BASE_VALUE;
        while (true) {
            try {
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                InfoMessage.waitingForElementToBeClickable(webElement);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.clear();
                InfoMessage.sendingKeysToElement(stringValue, webElement);
                webElement.sendKeys(stringValue);
                break;
            } catch (StaleElementReferenceException | ElementNotInteractableException exception) {
                ErrorMessage.caughtElementException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
    }

    public void clickElement(WebElement webElement) {
        int counter = COUNTER_BASE_VALUE;
        while (true) {
            try {
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                InfoMessage.waitingForElementToBeClickable(webElement);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                InfoMessage.clickingElement(webElement);
                webElement.click();
                break;
            } catch (StaleElementReferenceException | ElementClickInterceptedException exception) {
                ErrorMessage.caughtElementException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
    }

    public void clickElement(By locator) {
        int counter = COUNTER_BASE_VALUE;
        while (true) {
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                InfoMessage.waitingForElementToBeClickable(webElement);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                InfoMessage.clickingElement(webElement);
                webElement.click();
                break;
            } catch (StaleElementReferenceException | ElementClickInterceptedException exception) {
                ErrorMessage.caughtElementException(exception, locator);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
    }

    public String getTextFromElement(WebElement webElement) {
        String text;
        int counter = COUNTER_BASE_VALUE;
        while (true) {
            try {
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                text = webElement.getText();
                break;
            } catch (StaleElementReferenceException exception) {
                ErrorMessage.caughtElementException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
        InfoMessage.retrievedTextFromElement(text, webElement);
        return text;
    }

    public String getTextFromElement(By locator) {
        String text;
        int counter = COUNTER_BASE_VALUE;
        while (true) {
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                text = webElement.getText();
                break;
            } catch (StaleElementReferenceException exception) {
                ErrorMessage.caughtElementException(exception, locator);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
        InfoMessage.retrievedTextFromElement(text, locator);
        return text;
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
