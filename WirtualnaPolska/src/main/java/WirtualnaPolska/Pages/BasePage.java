package WirtualnaPolska.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWaitQuick;
    protected WebDriverWait webDriverWaitShort;
    protected WebDriverWait webDriverWaitDefault;
    protected WebDriverWait webDriverWaitLong;

    private static final int QUICK_TIMEOUT = 10;
    private static final int SHORT_TIMEOUT = 15;
    private static final int DEFAULT_TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 45;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWaitQuick = new WebDriverWait(driver, QUICK_TIMEOUT);
        webDriverWaitShort = new WebDriverWait(driver, SHORT_TIMEOUT);
        webDriverWaitDefault = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        webDriverWaitLong = new WebDriverWait(driver, LONG_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void clearAndSendKeysToElement(WebElement webElement, String stringValue) {
        try {
//            System.out.println("Waiting until element '" + webElement.toString() + "' is visible");
            webDriverWaitQuick.until(ExpectedConditions.visibilityOf(webElement));
//            System.out.println("Element '" + webElement + "' is visible!");
//            System.out.println("Waiting until element '" + webElement + "' is clickable");
            webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(webElement));
//            System.out.println("Element '" + webElement + "' is clickable!");
//            System.out.println("Performing '.clear()' operation on element '" + webElement + "'");
            webElement.clear();
//            System.out.println("'.clear()' operation on element '" + webElement + "' has been performed!");
//            System.out.println("Performing '.sendKeys()' operation on element '" + webElement + "'");
            webElement.sendKeys(stringValue);
//            System.out.println("'.sendKeys()' operation on element '" + webElement + "' has been performed!");
        } catch (StaleElementReferenceException | ElementNotInteractableException | NoSuchElementException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isElementVisible(WebElement webElement) {
        try {
//            System.out.println("Waiting until element '" + webElement.toString() + "' is visible");
            webDriverWaitQuick.until(ExpectedConditions.visibilityOf(webElement));
//            System.out.println("Element '" + webElement + "' is visible!");
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
//            System.out.println("Element '" + webElement + "' is not visible!");
            exception.printStackTrace();
            return false;
        }
    }

    public void clickElement(WebElement webElement) throws Exception {
        try {
//            System.out.println("Waiting until element '" + webElement.toString() + "' is visible");
            webDriverWaitQuick.until(ExpectedConditions.visibilityOf(webElement));
//            System.out.println("Element '" + webElement + "' is visible!");
//            System.out.println("Waiting until element '" + webElement + "' is clickable");
            webDriverWaitDefault.until(ExpectedConditions.elementToBeClickable(webElement));
//            System.out.println("Element '" + webElement + "' is clickable!");
//            System.out.println("Performing '.click()' operation on element '" + webElement + "'");
            webElement.click();
//            System.out.println("'.click()' operation on element '" + webElement + "' has been performed!");
        } catch (ElementNotInteractableException | NoSuchElementException exception) {
            exception.printStackTrace();
        }
    }
}