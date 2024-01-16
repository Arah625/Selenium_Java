package lh.juicecompany.PageUtilities.Detection;

import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementFinder {

    public WebElement findElementBy(By locator) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElementBy(By locator, Duration waitTime) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait(waitTime);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
