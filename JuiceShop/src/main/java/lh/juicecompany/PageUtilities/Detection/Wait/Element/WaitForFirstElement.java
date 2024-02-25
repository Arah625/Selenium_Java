package lh.juicecompany.PageUtilities.Detection.Wait.Element;

import lh.juicecompany.PageUtilities.Detection.Wait.WaitUtilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForFirstElement extends WaitUtilities {

    //TODO: Change methods names and add Logging instead of souts

    public static boolean waitForAnyElementPresence(FluentWait<WebDriver> fluentWait, List<WebElement> webElementsList) {
        return waitForCondition(fluentWait, driver -> {
            for (WebElement element : webElementsList) {
                try {
                    if (element != null && element.isDisplayed()) {
                        System.out.println("Element is visible: " + element);
                        return true;
                    }
                } catch (NoSuchElementException ignored) {
                }
            }
            return false;
        });
    }

    public static boolean waitForAnyElementPresence(FluentWait<WebDriver> fluentWait, WebElement... webElements) {
        return waitForAnyElementPresence(fluentWait, Arrays.asList(webElements));
    }
}