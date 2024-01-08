package lh.juicecompany.Pages.Components.Dropdowns;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropdownButton extends BasicPage {
    public DropdownButton(WebDriver webDriver) {
        super(webDriver);
    }

    public String isDropdownButtonExpanded(WebElement webElement) {
        return webElement.getAttribute("aria-expanded").toLowerCase();
    }

    public void expandDropdown(WebElement webElement) {
        if (!isDropdownButtonExpanded(webElement).equals("true")) {
            clickElement(webElement);
        }
    }

    public void closeDropdown(WebElement webElement) {
        if (!isDropdownButtonExpanded(webElement).equals("false")) {
            clickElement(webElement);
        }
    }
}