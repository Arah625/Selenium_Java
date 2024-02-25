package lh.juicecompany.PageUtilities.Detection;

import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ElementFinder {

    public WebElement locateElementBy(By locator) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement locateElementBy(By locator, Duration waitTime) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait(waitTime);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElementBy(By locator) {
        return WebDriverSetup.getInstance().getWebDriver().findElement(locator);
    }

    public List<WebElement> findElementsBy(By locator) {
        return WebDriverSetup.getInstance().getWebDriver().findElements(locator);
    }

    public List<WebElement> findElementsBy(List<WebElement> webElement) {
        try {
            WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
            return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElement));
        } catch (TimeoutException e) {
            return Collections.emptyList();
        }
    }
}