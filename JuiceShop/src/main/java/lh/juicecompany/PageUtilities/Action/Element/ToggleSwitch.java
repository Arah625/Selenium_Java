package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.IntegerUtilities;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Provides methods to interact with toggle switch or slider elements in a web interface,
 * enabling actions such as moving sliders to random or specific positions. This class
 * utilizes the {@link Actions} class for simulating user interactions with slider elements.
 */
public class ToggleSwitch {

    /**
     * Initializes and returns an {@link Actions} object for performing complex user interactions.
     * This utility method is used to facilitate the creation of action sequences for interacting
     * with web elements, particularly sliders in this context.
     *
     * @return An initialized {@link Actions} object associated with the current WebDriver instance.
     */
    public static Actions getSliderAction() {
        WebDriver webDriver = WebDriverSetup.getInstance().getWebDriver();
        return new Actions(webDriver);
    }

    /**
     * Retrieves the maximum allowable value of a slider element, as specified by its "max" attribute.
     *
     * @param ratingSlider The {@link WebElement} representing the slider from which to retrieve the maximum value.
     * @return The maximum value that the slider can represent, as an integer.
     */
    private int getSliderMaxValue(WebElement ratingSlider) {
        return Integer.parseInt(ratingSlider.getAttribute("max"));
    }

    /**
     * Retrieves the minimum allowable value of a slider element, as specified by its "min" attribute.
     *
     * @param ratingSlider The {@link WebElement} representing the slider from which to retrieve the minimum value.
     * @return The minimum value that the slider can represent, as an integer.
     */
    private int getSliderMinValue(WebElement ratingSlider) {
        return Integer.parseInt(ratingSlider.getAttribute("min"));
    }

    /**
     * Determines the pixel width of the slider element. This measurement is crucial for calculating
     * the movement required to set the slider to a specific value.
     *
     * @param ratingSlider The {@link WebElement} representing the slider from which to get the width.
     * @return The width of the slider in pixels.
     */
    private int getSliderWidth(WebElement ratingSlider) {
        return ratingSlider.getSize().getWidth();
    }

    /**
     * Calculates a horizontal offset based on a random value within the slider's range.
     *
     * @param slider   The slider {@link WebElement}.
     * @param minValue The minimum value of the slider's range.
     * @param maxValue The maximum value of the slider's range.
     * @return The calculated horizontal offset in pixels.
     */
    private int calculateHorizontalOffset(WebElement slider, int minValue, int maxValue) {
        return getSliderWidth(slider) * (IntegerUtilities.getRandomInclusive(minValue, maxValue) - minValue) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    /**
     * Calculates a horizontal offset for moving the slider to a specific desired value.
     *
     * @param slider       The slider {@link WebElement}.
     * @param desiredValue The target value to set the slider to.
     * @return The calculated horizontal offset in pixels to achieve the desired value.
     */
    private int calculateHorizontalOffset(WebElement slider, int desiredValue) {
        return getSliderWidth(slider) * (desiredValue - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    /**
     * Calculates a random horizontal offset within the slider's range.
     *
     * @param slider The slider {@link WebElement}.
     * @return The calculated horizontal offset in pixels within the slider's range.
     */
    private int calculateRandomHorizontalOffsetWithinSliderRange(WebElement slider) {
        return getSliderWidth(slider) * (IntegerUtilities.getRandomInclusive(getSliderMinValue(slider), getSliderMaxValue(slider)) - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    /**
     * Moves the slider to a random position within its range.
     *
     * @param sliderThumb The slider thumb {@link WebElement} to be moved.
     * @param slider      The slider {@link WebElement} representing the entire slider.
     */
    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateRandomHorizontalOffsetWithinSliderRange(slider), 0)
                         .perform();
    }

    /**
     * Moves the slider to a random position within a specified range.
     *
     * @param sliderThumb The slider thumb {@link WebElement} to be moved.
     * @param slider      The slider {@link WebElement} representing the entire slider.
     * @param minValue    The minimum value of the specified range.
     * @param maxValue    The maximum value of the specified range.
     */
    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider, int minValue, int maxValue) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateHorizontalOffset(slider, minValue, maxValue), 0)
                         .perform();
    }

    /**
     * Moves the slider to the specified value.
     *
     * @param sliderThumb  The slider thumb {@link WebElement} to be moved.
     * @param slider       The slider {@link WebElement} representing the entire slider.
     * @param desiredValue The target value to set the slider to.
     */
    public void moveSlider(WebElement sliderThumb, WebElement slider, int desiredValue) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateHorizontalOffset(slider, desiredValue), 0).perform();
    }
}