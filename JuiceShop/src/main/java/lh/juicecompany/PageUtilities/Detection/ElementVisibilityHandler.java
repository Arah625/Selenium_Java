package lh.juicecompany.PageUtilities.Detection;

import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementVisibilityHandler {
    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();

    public boolean isElementVisible(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.elementIsVisible(webElement);
            return webElement.isDisplayed();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, webElement);
            return false;
        }
    }

    public boolean isNumberOfElementsByWebElementsVisible(List<WebElement> webElementsList, int numberOfElements) {
        try {
            InfoMessage.waitingForVisibilityOfAllElementsByWebElement(webElementsList);
            List<WebElement> elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementsList));
            boolean areAllElementsVisible = elements.size() == numberOfElements;
            InfoMessage.elementsAreVisibleByWebElements(webElementsList);
            return areAllElementsVisible;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    public boolean isNumberOfElementsByLocatorsVisible(List<By> locatorsList, int numberOfElements) {
        try {
            InfoMessage.waitingForVisibilityOfAllElementsByLocator(locatorsList);
            int visibleElementsCount = 0;
            for (By locator : locatorsList) {
                List<WebElement> elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                visibleElementsCount += elements.size();
            }
            boolean areAllElementsVisible = visibleElementsCount == numberOfElements;
            if (areAllElementsVisible) {
                InfoMessage.elementsAreVisibleByWebLocators(locatorsList);
            }
            return areAllElementsVisible;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            InfoMessage.waitingForVisibilityOfElement(locator);
            boolean isVisible = webDriverWait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(locator), ExpectedConditions.visibilityOfElementLocated(locator)));
            InfoMessage.elementIsVisible(locator);
            return isVisible;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, locator);
            return false;
        }
    }

    public boolean invisibilityOfElement(WebElement webElement) {
        try {
            InfoMessage.waitingForElementToBecomeInvisible(webElement);
            boolean isVisible = webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
            InfoMessage.elementIsInvisible(webElement);
            return isVisible;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, webElement);
            return false;
        }
    }

    public boolean invisibilityOfElement(By locator) {
        try {
            InfoMessage.waitingForElementToBecomeInvisible(locator);
            boolean isVisible = webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            InfoMessage.elementIsInvisible(locator);
            return isVisible;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, locator);
            return false;
        }
    }

    //    public boolean areAllElementsVisible(List<WebElement> elements) {
//        long startTime = System.currentTimeMillis();
//        long maxTimeoutMillis = webDriverWait.getTimeout().toMillis();
//
//        while (System.currentTimeMillis() - startTime < maxTimeoutMillis) {
//            boolean allVisible = true;
//
//            for (WebElement element : elements) {
//                if (!element.isDisplayed()) {
//                    allVisible = false;
//                }
//            }
//
//            if (allVisible) {
//                return true;
//            }
//
//            // Sleep for a short interval before checking again
//            try {
//                Thread.sleep(500); // Adjust the sleep time as needed
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        // Timeout reached, not all elements became visible
//        return false;
//    }
//}
}
