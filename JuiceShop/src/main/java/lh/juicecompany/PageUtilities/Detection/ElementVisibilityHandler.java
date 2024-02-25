package lh.juicecompany.PageUtilities.Detection;

import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.Detection.Wait.Element.WaitForAllElements;
import lh.juicecompany.PageUtilities.Detection.Wait.Element.WaitForFirstElement;
import lh.juicecompany.PageUtilities.Detection.Wait.Locator.WaitForAllLocators;
import lh.juicecompany.PageUtilities.Detection.Wait.Locator.WaitForFirstLocator;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementVisibilityHandler {

    private static final ElementFinder elementFinder = new ElementFinder();

    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();

    public boolean areAllElementsVisible(WebElement... webElements) {
        return WaitForAllElements.waitForAllElementsPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(), webElements);
    }

    public boolean areAllElementsVisible(Duration pollingInterval, WebElement... webElements) {
        return WaitForAllElements.waitForAllElementsPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(pollingInterval), webElements);
    }

    public boolean areAllElementsVisible(Duration timeout, Duration pollingInterval, WebElement... webElements) {
        return WaitForAllElements.waitForAllElementsPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(timeout, pollingInterval), webElements);
    }

    public boolean areAllElementsVisible(List<WebElement> webElementsList) {
        return WaitForAllElements.waitForAllElementsFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(), webElementsList);
    }

    public boolean areAllElementsVisible(Duration pollingInterval, List<WebElement> webElementsList) {
        return WaitForAllElements.waitForAllElementsFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(pollingInterval), webElementsList);
    }

    public boolean areAllElementsVisible(Duration timeout, Duration pollingInterval, List<WebElement> webElementsList) {
        return WaitForAllElements.waitForAllElementsFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(timeout, pollingInterval), webElementsList);
    }

    public boolean areAllElementsByLocatorsVisible(By... locators) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(), locators);
    }

    public boolean areAllElementsByLocatorsVisible(Duration pollingInterval, By... locators) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(pollingInterval), locators);
    }

    public boolean areAllElementsByLocatorsVisible(Duration timeout, Duration pollingInterval, By... locators) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(timeout, pollingInterval), locators);
    }

    public boolean areAllElementsByLocatorsVisible(List<By> locatorsList) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(), locatorsList);
    }

    public boolean areAllElementsByLocatorsVisible(Duration pollingInterval, List<By> locatorsList) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(pollingInterval), locatorsList);
    }

    public boolean areAllElementsByLocatorsVisible(Duration timeout, Duration pollingInterval, List<By> locatorsList) {
        return WaitForAllLocators.waitForAllLocatorsVisibility(WebDriverSetup.getInstance()
                                                                             .getFluentWait(timeout, pollingInterval), locatorsList);
    }

    public boolean isAnyElementVisible(WebElement... webElements) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(), webElements);
    }

    public boolean isAnyElementVisible(Duration pollingInterval, WebElement... webElements) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(pollingInterval), webElements);
    }

    public boolean isAnyElementVisible(Duration timeout, Duration pollingInterval, WebElement... webElements) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(timeout, pollingInterval), webElements);
    }

    public boolean isAnyElementVisible(List<WebElement> webElementsList) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(), webElementsList);
    }

    public boolean isAnyElementVisible(Duration pollingInterval, List<WebElement> webElementsList) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(pollingInterval), webElementsList);
    }

    public boolean isAnyElementVisible(Duration timeout, Duration pollingInterval, List<WebElement> webElementsList) {
        return WaitForFirstElement.waitForAnyElementPresence(WebDriverSetup.getInstance()
                                                                           .getFluentWait(timeout, pollingInterval), webElementsList);
    }

    public boolean isAnyElementByLocatorVisible(By... locators) {
        return WaitForFirstLocator.waitForAnyElementByPresence(WebDriverSetup.getInstance()
                                                                             .getFluentWait(), locators);
    }

    public boolean isAnyElementByLocatorVisible(Duration pollingInterval, By... locators) {
        return WaitForFirstLocator.waitForAnyElementByPresence(WebDriverSetup.getInstance()
                                                                             .getFluentWait(pollingInterval), locators);
    }

    public boolean isAnyElementByLocatorVisible(Duration timeout, Duration pollingInterval, By... locators) {
        return WaitForFirstLocator.waitForAnyElementByPresence(WebDriverSetup.getInstance()
                                                                             .getFluentWait(timeout, pollingInterval), locators);
    }

    public boolean isAnyElementByLocatorVisible(List<By> locatorsList) {
        return WaitForFirstLocator.waitForAnyElementFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(), locatorsList);
    }

    public boolean isAnyElementByLocatorVisible(Duration pollingInterval, List<By> locatorsList) {
        return WaitForFirstLocator.waitForAnyElementFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(pollingInterval), locatorsList);
    }

    public boolean isAnyElementByLocatorVisible(Duration timeout, Duration pollingInterval, List<By> locatorsList) {
        return WaitForFirstLocator.waitForAnyElementFromListPresence(WebDriverSetup.getInstance()
                                                                                   .getFluentWait(timeout, pollingInterval), locatorsList);
    }

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

    public boolean isNumberOfElementsByWebElementsVisible(List<WebElement> webElementsList) {
        try {
            InfoMessage.waitingForVisibilityOfAllElementsByWebElement(webElementsList);
            List<WebElement> elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElementsList));
            boolean areAllElementsVisible = elements.size() == webElementsList.size();
            InfoMessage.elementsAreVisibleByWebElements(webElementsList);
            return areAllElementsVisible;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    public boolean isNumberOfElementsByLocatorsVisible(List<By> locatorsList, int expectedNumberOfElements) {
        try {
            InfoMessage.waitingForVisibilityOfAllElementsByLocator(locatorsList);
            int visibleElementsCount = 0;
            for (By locator : locatorsList) {
                List<WebElement> elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                visibleElementsCount += elements.size();
            }
            boolean areAllElementsVisible = visibleElementsCount == expectedNumberOfElements;
            if (areAllElementsVisible) {
                InfoMessage.elementsAreVisibleByWebLocators(locatorsList);
            }
            return areAllElementsVisible;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    public boolean isNumberOfElementsByLocatorsVisible(List<By> locatorsList) {
        try {
            InfoMessage.waitingForVisibilityOfAllElementsByLocator(locatorsList);
            int visibleElementsCount = 0;
            for (By locator : locatorsList) {
                List<WebElement> elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                visibleElementsCount += elements.size();
            }
            boolean areAllElementsVisible = visibleElementsCount == locatorsList.size();
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
}