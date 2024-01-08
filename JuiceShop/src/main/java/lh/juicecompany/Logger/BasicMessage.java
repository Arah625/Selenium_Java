package lh.juicecompany.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasicMessage {

    public static String getXpathFromWebElement(WebElement webElement) {
        return webElement.toString().replaceAll(".*-> xpath: ", "").replaceAll("\\]$", "");
    }

    public static String getXpathFromLocator(By locator) {
        return locator.toString().replaceAll(".*-> xpath: ", "").replaceAll("\\]$", "");
    }

}
