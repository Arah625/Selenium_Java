package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.PageUtilities.WebDriverSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class containing methods that handle elements marked by &lt;select&gt;&lt;/select&gt; tags.
 * Provides utility methods to interact with dropdown lists, allowing selection by index, value, visible text,
 * or randomly selecting an option based on these criteria.
 */
public class SelectOption {

    private static final Random random = new Random();

    private SelectOption() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves a {@link Select} object representing a dropdown list from a specified web element.
     * This method waits until the specified {@code dropdownList} element is visible on the web page before initializing the {@link Select} object.
     *
     * @param dropdownList The {@link WebElement} instance that represents the dropdown list to be interacted with.
     *                     This element should be located using a mechanism that identifies the {@code <select></select>} HTML tag.
     * @return A {@link Select} object that allows interaction with the dropdown list. This object provides methods to select and deselect options.
     * @throws org.openqa.selenium.TimeoutException If the {@code dropdownList} element is not visible within the wait time specified by {@link WebDriverWait}.
     */
    public static Select getDropdownList(WebElement dropdownList) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(dropdownList));
        return new Select(dropdownList);
    }

    /**
     * Calculates the size of the options list for a given dropdown element.
     * This method utilizes {@code getDropdownList} to fetch the dropdown list specified by the {@code dropdownList} parameter.
     * It then returns the number of options available within this dropdown.
     *
     * @param dropdownList The {@link WebElement} representing the dropdown list whose options size is to be determined.
     * @return An integer representing the number of options within the specified dropdown list.
     */
    public static int getElementListSize(WebElement dropdownList) {
        return getDropdownList(dropdownList).getOptions().size();
    }

    /**
     * Selects an option from the specified dropdown list by randomly choosing an index.
     * This method first calculates the size of the dropdown list to determine the range of valid indices.
     * It then generates a random index within this range and selects the corresponding option in the dropdown list.
     * Assumes that the dropdown list contains at least one option.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list from which an option will be selected.
     * @throws IllegalArgumentException If the dropdown list is empty, resulting in an illegal argument for {@code Random.nextInt()}.
     */
    public static void byRandomIndexFromList(WebElement dropdownList) {
        int size = getElementListSize(dropdownList);
        if (size == 0) {
            throw new IllegalArgumentException("The dropdown list is empty.");
        }
        int randomIndex = random.nextInt(size);
        getDropdownList(dropdownList).selectByIndex(randomIndex);
    }

    /**
     * Selects an option from the specified dropdown list by its index.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list.
     * @param index        The zero-based index of the option to select.
     */
    public static void byIndexFromList(WebElement dropdownList, int index) {
        getDropdownList(dropdownList).selectByIndex(index);
    }

    /**
     * Selects an option from the specified dropdown list by its value attribute.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list.
     * @param value        The value attribute of the option to select.
     */
    public static void byValueFromList(WebElement dropdownList, String value) {
        getDropdownList(dropdownList).selectByValue(value);
    }

    /**
     * Selects a random option from the specified dropdown list by its value attribute.
     * This method filters out options without a value attribute or with an empty value.
     * If no options with non-empty values are found, an IllegalArgumentException is thrown.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list.
     * @throws IllegalArgumentException If there are no selectable options with non-empty values in the dropdown list.
     */
    public static void byRandomValueFromList(WebElement dropdownList) {
        Select select = getDropdownList(dropdownList);
        List<WebElement> options = select.getOptions();

        List<String> values = new ArrayList<>();
        for (WebElement option : options) {
            String value = option.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                values.add(value);
            }
        }

        if (!values.isEmpty()) {
            String randomValue = values.get(random.nextInt(values.size()));
            select.selectByValue(randomValue);
        } else {
            throw new IllegalArgumentException("No selectable options with non-empty values.");
        }
    }

    /**
     * Selects a random option from the specified dropdown list by its visible text.
     * This method filters out options without visible text or with empty visible text.
     * If no options with visible text are found, an IllegalArgumentException is thrown.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list.
     * @throws IllegalArgumentException If there are no selectable options with visible text in the dropdown list.
     */
    public static void byRandomVisibleTextFromList(WebElement dropdownList) {
        Select select = getDropdownList(dropdownList);
        List<WebElement> options = select.getOptions();

        List<String> visibleTexts = new ArrayList<>();
        for (WebElement option : options) {
            String text = option.getText();
            if (text != null && !text.isEmpty()) {
                visibleTexts.add(text);
            }
        }
        if (!visibleTexts.isEmpty()) {
            String randomVisibleText = visibleTexts.get(random.nextInt(visibleTexts.size()));
            select.selectByVisibleText(randomVisibleText);
        }
    }

    /**
     * Selects an option from the specified dropdown list by its visible text.
     *
     * @param dropdownList The {@link WebElement} instance representing the dropdown list.
     * @param text         The visible text of the option to select.
     */
    public static void byTextFromList(WebElement dropdownList, String text) {
        getDropdownList(dropdownList).selectByVisibleText(text);
    }
}