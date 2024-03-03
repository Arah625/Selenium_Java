package lh.juicecompany.PageUtilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

/**
 * Provides capabilities for managing and interacting with browser tabs during a Selenium WebDriver session.
 * This class abstracts common operations related to browser tabs, such as switching between tabs and waiting for
 * new tabs to become active, to support more readable and maintainable test scripts.
 */
public class TabHandler extends PageSetup {
//TODO: Test all methods, if they work properly

    /**
     * Constructs a TabHandler with a reference to the current WebDriver.
     *
     * @param webDriver The WebDriver instance being used for the test.
     */
    public TabHandler(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Retrieves the window handle of the current tab.
     *
     * @return A string representing the window handle of the current tab.
     */
    //TODO: Add logger
    public String getCurrentWindowId() {
        return webDriver.getWindowHandle();
    }

    /**
     * Waits for a specific number of browser tabs to be visible within the session.
     *
     * @param numberOfTabsExpected The expected number of open tabs.
     * @return true if the expected number of tabs becomes visible within the wait time, false otherwise.
     */
    //TODO: Add logger
    public boolean isNumberOfTabsVisible(int numberOfTabsExpected) {
        try {
            webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(numberOfTabsExpected));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    /**
     * Waits for a new tab to load by checking for a specific tab title.
     *
     * @param tabTitle The title of the tab to wait for.
     * @return true if a tab with the specified title loads within the wait time, false otherwise.
     */
    //TODO: Add logger
    public boolean waitForNewTabToLoadByTitle(String tabTitle) {
        try {
            webDriverWait.until(ExpectedConditions.titleIs(tabTitle));
            return true;
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Retrieves the set of window handles for all open tabs in the current session.
     *
     * @return A set of strings, each representing a window handle of an open tab.
     */
    public Set<String> getAllTabsIds() {
        return webDriver.getWindowHandles();
    }

    /**
     * Switches context to a new tab that is not the main tab.
     *
     * @param mainTab    The window handle of the main tab.
     * @param allTabsIds A set of window handles for all open tabs.
     */
    public void switchToChildTab(String mainTab, Set<String> allTabsIds) {
        for (String handle : allTabsIds) {
            if (!handle.equals(mainTab)) {
                webDriver.switchTo().window(handle);
            }
        }
    }

    /**
     * Automatically switches to a new tab, assuming there are exactly two tabs open.
     */
    public void switchToChildTab() {
        isNumberOfTabsVisible(2);
        switchToChildTab(getCurrentWindowId(), getAllTabsIds());
    }
}