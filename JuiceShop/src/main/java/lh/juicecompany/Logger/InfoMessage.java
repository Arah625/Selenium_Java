package lh.juicecompany.Logger;

import lh.juicecompany.Ansi.Colors.AnsiColorPalette;
import lh.juicecompany.Ansi.Colors.Color;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class InfoMessage extends BasicMessage {

    //TODO: Maybe separate to dedicated classes (clicks, waits, and so on)?

    public static final String WAITING_FOR_VISIBILITY_OF_ELEMENT = Color.blue("Waiting for visibility of element " + Color.blueBold("{}"));
    public static final String WAITING_FOR_VISIBILITY_OF_ALL_ELEMENT = Color.blue("Waiting for visibility of all elements " + Color.blueBold("{}"));
    public static final String WAITING_FOR_ELEMENT_TO_BE_INVISIBLE = Color.blue("Waiting for element " + Color.blueBold("{}") + Color.blue(" to be invisible"));
    public static final String WAITING_FOR_ELEMENT_TO_BE_CLICKABLE = Color.blue("Waiting for element " + Color.blueBold("{}") + Color.blue(" to be clickable"));
    public static final String CLICKING_ELEMENT = Color.blue("Clicking element " + Color.blueBold("{}"));
    public static final String SENDING_KEYS = Color.blue("Sending " + Color.blueBold("{}") + Color.blue(" to element ") + Color.blueBold("{}"));
    public static final String ELEMENT_IS_VISIBLE = Color.blue("Element " + Color.blueBold("{}") + Color.blue(" is visible!"));
    public static final String ELEMENTS_ARE_VISIBLE = Color.blue("Elements " + Color.blueBold("{}") + Color.blue(" are visible!"));
    public static final String ELEMENT_IS_INVISIBLE = Color.blue("Element " + Color.blueBold("{}") + Color.blue(" is invisible!"));
    public static final String IS_ELEMENT_ENABLED = Color.blue("Checking if element " + Color.blueBold("{}") + Color.blue(" is enabled!"));
    public static final String IS_ELEMENT_SELECTED = Color.blue("Checking if element " + Color.blueBold("{}") + Color.blue(" is selected!"));
    public static final String TEXT_FROM_ELEMENT = Color.blue("Retrieved text " + Color.blueBold("{}") + Color.blue(" from element ") + Color.blueBold("{}"));
    public static final String TEST_CASE_DESCRIPTION = Color.green("Starting Test Case with description: " + Color.greenBold("{}"));
    public static final String CURRENT_URL = Color.green("Current URL: " + Color.blueBoldUnderlined("{}"));
    public static final String PAGE_TITLE = Color.green("Page title: " + Color.blueBold("{}"));
    public static final String BEFORE_METHOD_START = Color.yellow("Starting: " + Color.yellowBold("@BeforeMethod"));
    public static final String BEFORE_METHOD_END = Color.yellow("Ending: " + Color.yellowBold("@BeforeMethod"));
    public static final String AFTER_METHOD_START = Color.yellow("Starting: " + Color.yellowBold("@AfterMethod"));
    public static final String AFTER_METHOD_END = Color.yellow("Ending: " + Color.yellowBold("@AfterMethod"));
    public static final String AFTER_CLASS_START = Color.yellow("Starting: " + Color.yellowBold("@AfterClass"));
    public static final String AFTER_CLASS_END = Color.yellow("Ending: {}");
    public static final String BROWSER_AND_MODE = AnsiColorPalette.applyColor("Starting {} browser in {} mode.", AnsiColorPalette.MAGENTA_BOLD, AnsiColorPalette.MAGENTA_BOLD);
    private static final Logger logger = LoggerFactory.getLogger(InfoMessage.class);

    public static void waitingForVisibilityOfElement(WebElement webElement) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ELEMENT, getXpathFromWebElement(webElement));
    }

    public static void waitingForVisibilityOfElement(By locator) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ELEMENT, getXpathFromLocator(locator));
    }

    public static void waitingForVisibilityOfAllElementsByWebElement(List<WebElement> webElementsList) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ALL_ELEMENT, getXpathsFromWebElements(webElementsList));
    }

    public static void waitingForVisibilityOfAllElementsByLocator(List<By> locatorsList) {
        logger.info(WAITING_FOR_VISIBILITY_OF_ALL_ELEMENT, getXpathsFromLocators(locatorsList));
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

    public static void elementsAreVisibleByWebElements(List<WebElement> webElementsList) {
        logger.info(ELEMENTS_ARE_VISIBLE, getXpathsFromWebElements(webElementsList));
    }

    public static void elementsAreVisibleByWebLocators(List<By> locatorsList) {
        logger.info(ELEMENTS_ARE_VISIBLE, getXpathsFromLocators(locatorsList));
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

    public static void retrievedTextFromElement(String text, By locator) {
        logger.info(TEXT_FROM_ELEMENT, text, getXpathFromLocator(locator));
    }

    public static void startingTestCaseWithDescription(String testCaseName) {
        logger.info(TEST_CASE_DESCRIPTION, testCaseName);
    }

    public static void currentUrl(String currentUrl) {
        logger.info(CURRENT_URL, currentUrl);
    }

    public static void pageTitle(String pageTitle) {
        logger.info(PAGE_TITLE, pageTitle);
    }

    public static void beforeMethodStart() {
        logger.info(BEFORE_METHOD_START);
    }

    public static void beforeMethodEnd() {
        logger.info(BEFORE_METHOD_END);
    }

    public static void afterMethodStart() {
        logger.info(AFTER_METHOD_START);
    }

    public static void afterMethodEnd() {
        logger.info(AFTER_METHOD_END);
    }

    public static void afterClassStart() {
        logger.info(AFTER_CLASS_START, AnsiColorPalette.YELLOW_BOLD.apply("@AfterClass"));
    }

    public static void afterClassEnd() {
        logger.info(AFTER_CLASS_END, AnsiColorPalette.YELLOW_BOLD.apply("@AfterClass"));
    }

    public static void browserAndMode(String browser, String mode) {
        logger.info(BROWSER_AND_MODE, browser, mode);
    }
}
