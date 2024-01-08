package lh.juicecompany.Pages;

import lh.juicecompany.Logger.InfoMessage;
import lh.juicecompany.PageUtilities.BasicMethods;
import org.openqa.selenium.WebDriver;


public abstract class BasicPage extends BasicMethods {

    public BasicPage(WebDriver webDriver) {
        super(webDriver);
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
