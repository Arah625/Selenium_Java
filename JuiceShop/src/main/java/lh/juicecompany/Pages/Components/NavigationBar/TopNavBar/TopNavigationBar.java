package lh.juicecompany.Pages.Components.NavigationBar.TopNavBar;

import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.BasicPage;
import lh.juicecompany.Pages.Components.NavigationBar.SideNavBar.SideNavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopNavigationBar extends BasicPage {

    @FindBy(xpath = "//button[@aria-label='Open Sidenav']")
    private WebElement hamburgerMenuButton;
    @FindBy(xpath = "//button[@id='navbarAccount']")
    private WebElement accountButton;
    @FindBy(xpath = "mat-menu-panel-0")
    private WebElement accountDropdownList;
    @FindBy(xpath = "//button[@id='navbarLoginButton']")
    private WebElement loginButton;
    @FindBy(xpath = "//button[@aria-label='Language selection menu']")
    private WebElement languageDropdownButton;
    @FindBy(xpath = "//button/span[contains(text(),'Force page reload')]")
    private WebElement forcePageReloadButton;

    public TopNavigationBar(WebDriver webDriver) {
        super(webDriver);
    }

    private static By languageRadioButtonXpath(String language) {
        return By.xpath("//div[@role='menu']//input[@aria-label='" + language + "']/../..");
    }

    private static By languageChangedAlertXpath(String language) {
        return By.xpath("//span[contains(text(),'Language has been changed to " + language + "')]");
    }

    private String isAccountButtonExpanded() {
        if (accountButton.getAttribute("aria-expanded") != null) {
            return "true";
        } else {
            return "false";
        }
    }

    protected TopNavigationBar accountButtonClick() {
        if (isAccountButtonExpanded().equals("false")) {
            commonMethods.clickElement(accountButton);
        }
        return this;
    }

    public boolean isLoginButtonVisible() {
        accountButtonClick();
        return elementVisibilityHandler.isElementVisible(loginButton);
    }

    public Login loginButtonClick() {
        accountButtonClick();
        commonMethods.clickElement(loginButton);
        return new Login(webDriver);
    }

    public SideNavigationBar openSideNavbar() {
        commonMethods.clickElement(hamburgerMenuButton);
        return new SideNavigationBar(webDriver);
    }

    private TopNavigationBar languageDropdownButtonClick() {
        commonMethods.clickElement(languageDropdownButton);
        return this;
    }

    public String getLanguageCode() {
        return commonMethods.getTextFromElement(languageDropdownButton).replace("language ", "");
    }

    public void selectLanguage(String language) {
        languageDropdownButtonClick();
        commonMethods.clickElement(elementFinder.locateElementBy(languageRadioButtonXpath(language)));
    }

    public boolean isLanguageChangedAlertVisible(String language) {
        return elementVisibilityHandler.isElementVisible(languageChangedAlertXpath(language));
    }

    public boolean isForcePageReloadButtonVisible() {
        return elementVisibilityHandler.isElementVisible(forcePageReloadButton);
    }

    public void forcePageReloadButtonClick() {
        commonMethods.clickElement(forcePageReloadButton);
    }
}
