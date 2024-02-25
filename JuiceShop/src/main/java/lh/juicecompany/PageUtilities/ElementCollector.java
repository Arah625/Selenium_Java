package lh.juicecompany.PageUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementCollector {

    public static List<By> createListOfLocators(By... locators) {
        return Arrays.asList(locators);
    }

    public static List<WebElement> createListOfWebElements(WebElement... webElements) {
        return Arrays.asList(webElements);
    }

    public List<WebElement> convertListOfLocatorsToListOfWebElements(WebDriver driver, List<By> locatorsList) {
        List<WebElement> elementsList = new ArrayList<>();
        for (By locator : locatorsList) {
            elementsList.addAll(driver.findElements(locator));
        }
        return elementsList;
    }

}