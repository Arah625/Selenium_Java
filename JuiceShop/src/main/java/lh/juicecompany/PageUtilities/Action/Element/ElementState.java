package lh.juicecompany.PageUtilities.Action.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementState {

    String getElementState(WebElement webElement);

    String getElementState(By locator);
}
