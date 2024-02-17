package lh.juicecompany.PageUtilities.Detection.NewWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Arrays;
import java.util.List;

public class WaitForAllLocators extends WaitingUtilities {

    //TODO: OK - WORKS
    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, List<By> locatorsList) {
        return waitForCondition(fluentWait, driver -> areAllLocatorsVisible(locatorsList));
    }
    //TODO: OK - WORKS

    public static boolean waitForAllLocatorsVisibility(FluentWait<WebDriver> fluentWait, By... locators) {
        return waitForAllLocatorsVisibility(fluentWait, Arrays.asList(locators));
    }
}