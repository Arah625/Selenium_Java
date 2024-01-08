package lh.juicecompany.PageUtilities;

import lh.juicecompany.Exceptions.MaximumNumberOfRepetitionsException;
import lh.juicecompany.Logger.ErrorMessage;
import lh.juicecompany.Logger.InfoMessage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.SecureRandom;
import java.util.List;

public class BasicMethods extends PageSetup {

    private static final int COUNTER = 0;
    private static final int REPETITIONS = 3;

    public BasicMethods(WebDriver webDriver) {
        super(webDriver);
    }

    protected boolean isElementEnabled(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.isElementEnabled(webElement);
            return webElement.isEnabled();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtException(exception, webElement);
            return false;
        }
    }

    protected boolean isElementSelected(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.isElementSelected(webElement);
            return webElement.isSelected();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtException(exception, webElement);
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.elementIsVisible(webElement);
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtException(exception, webElement);
            return false;
        }
    }

    protected boolean isNumberOfElementsVisible(By elementLocator, int numberOfElements) {
        try {
            webDriverWait.until(ExpectedConditions.numberOfElementsToBe(elementLocator, numberOfElements));
            return true;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    protected boolean isElementVisible(By locator) {
        try {
            InfoMessage.waitingForVisibilityOfElement(locator);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            InfoMessage.elementIsVisible(locator);
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtException(exception, locator);
            return false;
        }
    }

    protected boolean invisibilityOfElement(By locator) {
        try {
            InfoMessage.waitingForElementToBecomeInvisible(locator);
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            InfoMessage.elementIsInvisible(locator);
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtException(exception, locator);
            return false;
        }
    }

    protected void sendKeysToElement(WebElement webElement, String stringValue) {
        int counter = COUNTER;
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
                ErrorMessage.caughtException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
    }

    protected void clickElement(WebElement webElement) {
        int counter = COUNTER;
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
                ErrorMessage.caughtException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
    }

    protected String getTextFromElement(WebElement webElement) {
        String text;
        int counter = COUNTER;
        while (true) {
            try {
                InfoMessage.waitingForVisibilityOfElement(webElement);
                webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
                text = webElement.getText();
                break;
            } catch (StaleElementReferenceException exception) {
                ErrorMessage.caughtException(exception, webElement);
                counter++;
            }
            if (counter >= REPETITIONS) {
                throw new MaximumNumberOfRepetitionsException(ErrorMessage.MAXIMUM_REPETITIONS_REACHED);
            }
        }
        InfoMessage.retrievedTextFromElement(text, webElement);
        return text;
    }

    private int getSliderMaxValue(WebElement ratingSlider) {
        return Integer.parseInt(ratingSlider.getAttribute("max"));
    }

    private int getSliderMinValue(WebElement ratingSlider) {
        return Integer.parseInt(ratingSlider.getAttribute("min"));
    }

    private int getSliderWidth(WebElement ratingSlider) {
        return ratingSlider.getSize().getWidth();
    }

    private int randomFromRange(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max) + min;
    }

    private int calculateOffset(WebElement slider, int minValue, int maxValue) {
        return getSliderWidth(slider) * (randomFromRange(minValue, maxValue - minValue) / (getSliderMaxValue(slider) - getSliderMinValue(slider)));
    }

    private int calculateOffset(WebElement slider, int desiredValue) {
        return getSliderWidth(slider) * (desiredValue - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    private int calculateRandomOffsetWithinSliderRange(WebElement slider) {
        return getSliderWidth(slider) * (randomFromRange(getSliderMinValue(slider), getSliderMaxValue(slider)) - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider) {
        Actions actions = new Actions(webDriver);
        actions.dragAndDropBy(sliderThumb, calculateRandomOffsetWithinSliderRange(slider), 0).perform();
    }

    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider, int minValue, int maxValue) {
        Actions actions = new Actions(webDriver);
        actions.dragAndDropBy(sliderThumb, calculateOffset(slider, minValue, maxValue), 0).perform();
    }

    public void moveSlider(WebElement sliderThumb, WebElement slider, int desiredValue) {
        Actions actions = new Actions(webDriver);
        actions.dragAndDropBy(sliderThumb, calculateOffset(slider, desiredValue), 0).perform();
    }

    public void waitForElements(By locator, int expectedNumberOfElements, int timeoutInSeconds) {
        webDriverWait.until(d -> {
            List<WebElement> elements = d.findElements(locator);
            return elements.size() == expectedNumberOfElements;
        });
    }

    public void waitForElements(List<WebElement> elements, int expectedNumberOfElements) {
        webDriverWait.until(d -> {
            long visibleElementCount = elements.stream().filter(WebElement::isDisplayed).count();
            return visibleElementCount == expectedNumberOfElements;
        });
    }


}
