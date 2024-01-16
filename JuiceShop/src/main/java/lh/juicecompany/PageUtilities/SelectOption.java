package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectOption {

    private static final Random random = new Random();

    public static Select getDropdownList(WebElement dropdownList) {
        WebDriverWait webDriverWait = WebDriverSetup.getInstance().getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(dropdownList));
        return new Select(dropdownList);
    }

    public static int getElementListSize(WebElement dropdownList) {
        return getDropdownList(dropdownList).getOptions().size();
    }

    public static void byRandomIndexFromList(WebElement dropdownList) {
        int randomIndex = random.nextInt(getElementListSize(dropdownList));
        getDropdownList(dropdownList).selectByIndex(randomIndex);
    }

    public static void byIndexFromList(WebElement dropdownList, int index) {
        getDropdownList(dropdownList).selectByIndex(index);
    }

    public static void byValueFromList(WebElement dropdownList, String value) {
        getDropdownList(dropdownList).selectByValue(value);
    }

    public static void byRandomValueFromList(WebElement dropdownList) {
        Select select = getDropdownList(dropdownList);
        List<WebElement> options = select.getOptions();

        // Extract values from options
        List<String> values = new ArrayList<>();
        for (WebElement option : options) {
            String value = option.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                values.add(value);
            }
        }

        // Choose a random value
        if (!values.isEmpty()) {
            String randomValue = values.get(random.nextInt(values.size()));
            select.selectByValue(randomValue);
        }
    }


    public static void byRandomVisibleTextFromList(WebElement dropdownList) {
        Select select = getDropdownList(dropdownList);
        List<WebElement> options = select.getOptions();

        // Extract visible text from options
        List<String> visibleTexts = new ArrayList<>();
        for (WebElement option : options) {
            String text = option.getText();
            if (text != null && !text.isEmpty()) {
                visibleTexts.add(text);
            }
        }

        // Choose a random visible text
        if (!visibleTexts.isEmpty()) {
            String randomVisibleText = visibleTexts.get(random.nextInt(visibleTexts.size()));
            select.selectByVisibleText(randomVisibleText);
        }
    }

    public static void byTextFromList(WebElement dropdownList, String text) {
        getDropdownList(dropdownList).selectByVisibleText(text);
    }
}
