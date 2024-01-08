package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class Selects {

    static Random random = new Random();

    public static int getElementListSize(WebElement dropdownList) {
        Select select = new Select(dropdownList);
        return select.getOptions().size();
    }

    public static void randomValueFromList(WebElement dropdownList) {
        Select select = new Select(dropdownList);
        int randomIndex = random.nextInt(getElementListSize(dropdownList));
        select.selectByIndex(randomIndex);
    }

}
