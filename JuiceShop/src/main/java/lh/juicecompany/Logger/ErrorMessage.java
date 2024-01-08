package lh.juicecompany.Logger;

import lh.juicecompany.Colors.Color;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ErrorMessage extends BasicMessage {

    public static final String EXCEPTION_CAUGHT = Color.red("Caught " + Color.redBold("{}") + Color.red(" while trying to interact with element " + Color.redBold("{}")));
    public static final String MAXIMUM_REPETITIONS_REACHED = Color.redBold("Maximum number of repetitions reached!");
    public static final String FAILED_TO_CLOSE_BROWSER = Color.redBold("Failed to close the browser!");
    public static final String FAILED_TO_QUIT_WEBDRIVER = Color.redBold("Failed to quit WebDriver!");
    private static final Logger logger = LoggerFactory.getLogger(ErrorMessage.class);

    public static void caughtException(Exception exception, WebElement webElement) {
        logger.error(EXCEPTION_CAUGHT, exception.getClass().getName(), getXpathFromWebElement(webElement));
    }

    public static void caughtException(Exception exception, By locator) {
        logger.error(EXCEPTION_CAUGHT, exception.getClass().getName(), getXpathFromLocator(locator));
    }

    public static void caughtException(Exception exception, String errorReason) {
        logger.error(EXCEPTION_CAUGHT, exception.getClass().getName(), errorReason);
    }

}
