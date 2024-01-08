package lh.juicecompany.PageUtilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class TabHandler extends PageSetup {


    public TabHandler(WebDriver webDriver) {
        super(webDriver);
    }

    //TODO: Add logger

    public String getCurrentWindowId() {
        return webDriver.getWindowHandle();
    }

    //TODO: Add logger
    public boolean isNumberOfTabsVisible(int numberOfTabsExpected) {
        try {
            webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(numberOfTabsExpected));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    //TODO: Add logger
    public boolean waitForNewTabToLoadByTitle(String tabTitle) {
        try {
            webDriverWait.until(ExpectedConditions.titleIs(tabTitle));
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    public Set<String> getAllTabsIds() {
        return webDriver.getWindowHandles();
    }

    public void switchToChildTab(String mainTab, Set<String> allTabsIds) {
        for (String handle : allTabsIds) {
            if (!handle.equals(mainTab)) {
                webDriver.switchTo().window(handle);
            }
        }
    }

    public void switchToChildTab() {
        isNumberOfTabsVisible(2);
        switchToChildTab(getCurrentWindowId(), getAllTabsIds());
    }

}
