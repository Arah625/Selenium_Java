package lh.juicecompany.Logger;

import lh.juicecompany.Colors.Color;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InfoMessage extends BasicMessage {


    public static final String WAITING_FOR_VISIBILITY_OF_ELEMENT = Color.blue("Waiting for visibility of element " + Color.blueBold("{}"));
    public static final String WAITING_FOR_ELEMENT_TO_BE_INVISIBLE = Color.blue("Waiting for element " + Color.blueBold("{}") + Color.blue(" to be invisible"));
    public static final String WAITING_FOR_ELEMENT_TO_BE_CLICKABLE = Color.blue("Waiting for element " + Color.blueBold("{}") + Color.blue(" to be clickable"));
    public static final String CLICKING_ELEMENT = Color.blue("Clicking element " + Color.blueBold("{}"));
    public static final String SENDING_KEYS = Color.blue("Sending " + Color.blueBold("{}") + Color.blue(" to element ") + Color.blueBold("{}"));
    public static final String ELEMENT_IS_VISIBLE = Color.blue("Element " + Color.blueBold("{}") + Color.blue(" is visible!"));
    public static final String ELEMENT_IS_INVISIBLE = Color.blue("Element " + Color.blueBold("{}") + Color.blue(" is invisible!"));
    public static final String IS_ELEMENT_ENABLED = Color.blue("Checking if element " + Color.blueBold("{}") + Color.blue(" is enabled!"));
    public static final String IS_ELEMENT_SELECTED = Color.blue("Checking if element " + Color.blueBold("{}") + Color.blue(" is selected!"));
    public static final String TEXT_FROM_ELEMENT = Color.blue("Retrieved text " + Color.blueBold("{}") + Color.blue(" from element ") + Color.blueBold("{}"));
    public static final String TEST_CASE_NAME = Color.green("Starting Test Case: " + Color.greenBold("{}"));
    public static final String CURRENT_URL = Color.green("Current URL: " + Color.blueBoldUnderlined("{}"));
    public static final String PAGE_TITLE = Color.green("Page title: " + Color.blueBold("{}"));
    private static final Logger logger = LoggerFactory.getLogger(InfoMessage.class);

    public static void waitingForVisibilityOfElement(WebElement webElement) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ELEMENT, getXpathFromWebElement(webElement));
    }

    public static void waitingForVisibilityOfElement(By locator) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ELEMENT, getXpathFromLocator(locator));
    }

    public static void waitingForElementToBecomeInvisible(WebElement webElement) {
        logger.info(WAITING_FOR_ELEMENT_TO_BE_INVISIBLE, getXpathFromWebElement(webElement));
    }

    public static void waitingForElementToBecomeInvisible(By locator) {
        logger.info(WAITING_FOR_ELEMENT_TO_BE_INVISIBLE, getXpathFromLocator(locator));
    }

    public static void elementIsVisible(WebElement webElement) {
        logger.info(ELEMENT_IS_VISIBLE, getXpathFromWebElement(webElement));
    }

    public static void elementIsVisible(By locator) {
        logger.info(ELEMENT_IS_VISIBLE, getXpathFromLocator(locator));
    }

    public static void elementIsInvisible(WebElement webElement) {
        logger.info(ELEMENT_IS_INVISIBLE, getXpathFromWebElement(webElement));
    }

    public static void elementIsInvisible(By locator) {
        logger.info(ELEMENT_IS_INVISIBLE, getXpathFromLocator(locator));
    }

    public static void isElementEnabled(WebElement webElement) {
        logger.info(IS_ELEMENT_ENABLED, getXpathFromWebElement(webElement));
    }

    public static void isElementSelected(WebElement webElement) {
        logger.info(IS_ELEMENT_SELECTED, getXpathFromWebElement(webElement));
    }

    public static void waitingForElementToBeClickable(WebElement webElement) {
        logger.info(WAITING_FOR_ELEMENT_TO_BE_CLICKABLE, getXpathFromWebElement(webElement));
    }

    public static void clickingElement(WebElement webElement) {
        logger.info(CLICKING_ELEMENT, getXpathFromWebElement(webElement));
    }

    public static void sendingKeysToElement(String textValue, WebElement webElement) {
        logger.info(SENDING_KEYS, textValue, getXpathFromWebElement(webElement));
    }

    public static void retrievedTextFromElement(String text, WebElement webElement) {
        logger.info(TEXT_FROM_ELEMENT, text, getXpathFromWebElement(webElement));
    }

    public static void startingTestCase(String testCaseName) {
        logger.info(TEST_CASE_NAME, testCaseName);
    }

    public static void currentUrl(String currentUrl) {
        logger.info(CURRENT_URL, currentUrl);
    }

    public static void pageTitle(String pageTitle) {
        logger.info(PAGE_TITLE, pageTitle);
    }

}
