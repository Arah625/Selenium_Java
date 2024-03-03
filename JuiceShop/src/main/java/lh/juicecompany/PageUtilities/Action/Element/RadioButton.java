package lh.juicecompany.PageUtilities.Action.Element;

import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Retry.ActionRetry;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Facilitates interactions with radio button elements on a web page. This class allows for checking radio buttons
 * and determining if a radio button is selected by utilizing the {@link ElementState} interface. It provides a common
 * method for fetching a radio button's state based on specific attributes, such as the presence of a "mat-radio-checked"
 * class for Material design radio buttons.
 */
public class RadioButton implements ElementState {

    private final WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
    private final CommonMethods commonMethods;
    private final ElementFinder elementFinder;

    /**
     * Initializes a new instance of the RadioButton class, setting up the necessary utilities for element interaction.
     */
    public RadioButton() {
        this.commonMethods = new CommonMethods();
        this.elementFinder = new ElementFinder();
    }

    /**
     * Determines whether the specified {@link WebElement} representing a radio button is currently selected.
     * The selection state is inferred from the element's class attribute, checking for the presence of "mat-radio-checked".
     *
     * @param webElement The {@link WebElement} to check.
     * @return {@code true} if the radio button is selected, {@code false} otherwise.
     */
    public boolean isSelected(WebElement webElement) {
        try {
            return "true".equals(getElementState(webElement));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Determines whether the radio button identified by the given locator is currently selected.
     * Utilizes the getElementState method to check for the "mat-radio-checked" class attribute.
     *
     * @param locator The {@link By} locator to identify the radio button element.
     * @return {@code true} if the radio button is selected, {@code false} otherwise.
     */
    public boolean isSelected(By locator) {
        try {
            return "true".equals(getElementState(locator));
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Selects the specified radio button {@link WebElement} if it is not already selected.
     * This is achieved by clicking on the radio button element.
     *
     * @param webElement The {@link WebElement} representing the radio button to select.
     */
    public void select(WebElement webElement) {
        if (!isSelected(webElement)) {
            commonMethods.clickElement(webElement);
        }
    }

    /**
     * Selects the radio button identified by the given locator if it is not already selected.
     * The element is clicked to change its selection state.
     *
     * @param locator The {@link By} locator to identify the radio button element.
     */
    public void select(By locator) {
        if (!isSelected(locator)) {
            commonMethods.clickElement(locator);
        }
    }

    @Override
    public String getElementState(WebElement webElement) {
        return ActionRetry.doActionRetry(() -> {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return String.valueOf(webElement.getAttribute("class").contains("mat-radio-checked"));
        }, 3);
    }

    @Override
    public String getElementState(By locator) {
        return ActionRetry.doActionRetry(() -> {
            WebElement webElement = elementFinder.locateElementBy(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return String.valueOf(webElement.getAttribute("class").contains("mat-radio-checked"));
        }, 3);
    }
}