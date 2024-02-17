package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.IntegerUtilities;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ToggleSwitch {

    public static Actions getSliderAction() {
        WebDriver webDriver = WebDriverSetup.getInstance().getWebDriver();
        return new Actions(webDriver);
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

    private int calculateHorizontalOffset(WebElement slider, int minValue, int maxValue) {
        return getSliderWidth(slider) * (IntegerUtilities.getRandomInclusive(minValue, maxValue) - minValue) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    private int calculateHorizontalOffset(WebElement slider, int desiredValue) {
        return getSliderWidth(slider) * (desiredValue - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    private int calculateRandomHorizontalOffsetWithinSliderRange(WebElement slider) {
        return getSliderWidth(slider) * (IntegerUtilities.getRandomInclusive(getSliderMinValue(slider), getSliderMaxValue(slider)) - getSliderMinValue(slider)) / (getSliderMaxValue(slider) - getSliderMinValue(slider));
    }

    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateRandomHorizontalOffsetWithinSliderRange(slider), 0)
                         .perform();
    }

    public void moveSliderRandomly(WebElement sliderThumb, WebElement slider, int minValue, int maxValue) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateHorizontalOffset(slider, minValue, maxValue), 0)
                         .perform();
    }

    public void moveSlider(WebElement sliderThumb, WebElement slider, int desiredValue) {
        getSliderAction().dragAndDropBy(sliderThumb, calculateHorizontalOffset(slider, desiredValue), 0).perform();
    }
}
