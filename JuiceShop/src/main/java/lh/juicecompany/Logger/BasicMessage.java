package lh.juicecompany.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicMessage {

    public static String getXpathFromWebElement(WebElement webElement) {
        return webElement.toString().replaceAll(".*-> xpath: ", "").replaceAll("\\]$", "");
    }

    public static List<String> getXpathsFromWebElements(List<WebElement> webElements) {
        List<String> xpaths = new ArrayList<>();
        for (WebElement webElement : webElements) {
            String xpath = getXpathFromWebElement(webElement);
            xpaths.add(xpath);
        }
        return xpaths;
    }

    public static String getXpathFromLocator(By locator) {
        return locator.toString().replaceAll(".*-> xpath: ", "").replaceAll("\\]$", "");
    }

    public static List<String> getXpathsFromLocators(List<By> locators) {
        List<String> xpaths = new ArrayList<>();
        for (By locator : locators) {
            String xpath = getXpathFromLocator(locator);
            xpaths.add(xpath);
        }
        return xpaths;
    }
}
