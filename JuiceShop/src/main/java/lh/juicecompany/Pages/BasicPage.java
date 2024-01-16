package lh.juicecompany.Pages;

import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.Action.CommonMethods;
import lh.juicecompany.PageUtilities.Action.Element.*;
import lh.juicecompany.PageUtilities.Detection.ElementFinder;
import lh.juicecompany.PageUtilities.Detection.ElementVisibilityHandler;
import lh.juicecompany.PageUtilities.PageSetup;
import org.openqa.selenium.WebDriver;


public abstract class BasicPage extends PageSetup {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    protected CommonMethods commonMethods;
    protected Button button;
    protected Checkbox checkbox;
    protected RadioButton radioButton;
    protected DropdownButton dropdownButton;
    protected Slider slider;
    protected ElementVisibilityHandler elementVisibilityHandler;
    protected ElementFinder elementFinder;

    protected BasicPage(WebDriver webDriver) {
        super(webDriver);
        this.commonMethods = new CommonMethods();
        this.button = new Button();
        this.checkbox = new Checkbox();
        this.radioButton = new RadioButton();
        this.dropdownButton = new DropdownButton();
        this.slider = new Slider();
        this.elementVisibilityHandler = new ElementVisibilityHandler();
        this.elementFinder = new ElementFinder();
    }

    public static String caseInsensitiveXPath(String xpath, String searchText) {
        return xpath + "[translate(text(), '" + UPPER + "', '" + LOWER + "') = '" + searchText.toLowerCase() + "']";
    }

    public BasicPage goToPreviousPage() {
        webDriver.navigate().back();
        return this;
    }

    public BasicPage goToNextPage() {
        webDriver.navigate().forward();
        return this;
    }

    public BasicPage refreshPage() {
        webDriver.navigate().refresh();
        return this;
    }

    public String getCurrentUrl() {
        String currentUrl = webDriver.getCurrentUrl();
        InfoMessage.currentUrl(currentUrl);
        return currentUrl;
    }

    public String getPageTitle() {
        String title = webDriver.getTitle();
        InfoMessage.pageTitle(title);
        return title;
    }
}
