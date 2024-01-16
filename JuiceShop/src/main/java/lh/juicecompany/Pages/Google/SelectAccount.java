package lh.juicecompany.Pages.Google;

import lh.juicecompany.Pages.BasicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectAccount extends BasicPage {
    @FindBy(xpath = "//*[@role='presentation']//div[contains(text(),'Zaloguj się przez Google')]")
    WebElement signInWithGoogleAccountModalHeader;

    @FindBy(xpath = "//h1[@id='headingText']//span[contains(text(),'Zaloguj się')]")
    WebElement signInGoogleAccountHeader;

    public SelectAccount(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isSignInWithGoogleAccountModalHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(signInWithGoogleAccountModalHeader);
    }

    public boolean isSignInWithGoogleAccountHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(signInGoogleAccountHeader);
    }


}
