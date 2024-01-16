package lh.juicecompany.Pages.Components.NavigationBar.TopNavBar;

import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.DigitalWallet;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MyPaymentOptions;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.MySavedAddresses;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.OrderHistory;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.Recycle;
import lh.juicecompany.Pages.Account.PrivacyAndSecurity.RequestDataErasure;
import lh.juicecompany.Pages.BasicPage;
import lh.juicecompany.Pages.Components.NavigationBar.SideNavBar.SideNavigationBar;
import lh.juicecompany.Pages.Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopNavigationBar extends BasicPage {

    @FindBy(xpath = "//button[@aria-label='Open Sidenav']")
    private WebElement hamburgerMenuButton;
    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    private WebElement yourBasketButton;
    @FindBy(xpath = "//button[@id='navbarAccount']")
    private WebElement accountButton;
    @FindBy(xpath = "mat-menu-panel-0")
    private WebElement accountDropdownList;
    @FindBy(xpath = "//button[@id='navbarLoginButton']")
    private WebElement loginButton;
    @FindBy(xpath = "//button[@aria-label='Go to user profile']")
    private WebElement userProfileButton;
    @FindBy(xpath = "//button[@id='navbarLogoutButton']")
    private WebElement logoutButton;
    @FindBy(xpath = "//button[@aria-label='Language selection menu']")
    private WebElement languageDropdownButton;
    @FindBy(xpath = "//button/span[contains(text(),'Force page reload')]")
    private WebElement forcePageReloadButton;
    @FindBy(xpath = "//button[@aria-label='Show Orders and Payment Menu']")
    private WebElement ordersAndPaymentMenuButton;
    @FindBy(xpath = "//button[@aria-label='Go to order history page']")
    private WebElement ordersHistoryButton;
    @FindBy(xpath = "//button[@aria-label='Go to recycling page']")
    private WebElement recycleButton;
    @FindBy(xpath = "//button[@aria-label='Go to saved address page']")
    private WebElement mySavedAddressesButton;
    @FindBy(xpath = "//button[@aria-label='Go to saved payment methods page']")
    private WebElement myPaymentOptionsButton;
    @FindBy(xpath = "//button[@aria-label='Go to wallet page']")
    private WebElement digitalWalletButton;
    @FindBy(xpath = "//button[@aria-label='Show Privacy and Security Menu']")
    private WebElement privacyAndSecurityMenuButton;
    @FindBy(xpath = "//button[@aria-label='Go to privacy policy page']")
    private WebElement privacyPolicyButton;
    @FindBy(xpath = "//button[@aria-label='Go to data export page']")
    private WebElement requestDataExportButton;
    @FindBy(xpath = "//button[@aria-label='Go to data subject page']")
    private WebElement requestDataErasureButton;
    @FindBy(xpath = "//button[@aria-label='Go to change password page']")
    private WebElement changePasswordButton;
    @FindBy(xpath = "//button[@aria-label='Go to two factor authentication page']")
    private WebElement twoFactorAuthenticationButton;
    @FindBy(xpath = "//button[@aria-label='Go to last login ip page']")
    private WebElement lastLoginButton;

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

    private TopNavigationBar accountButtonClick() {
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

    public boolean isLogoutButtonVisible() {
        accountButtonClick();
        return elementVisibilityHandler.isElementVisible(logoutButton);
    }

    public Home logoutButtonClick() {
        accountButtonClick();
        commonMethods.clickElement(logoutButton);
        return new Home(webDriver);
    }

    public SideNavigationBar openSideNavbar() {
        commonMethods.clickElement(hamburgerMenuButton);
        return new SideNavigationBar(webDriver);
    }

    public boolean isYourBasketButtonVisible() {
        return elementVisibilityHandler.isElementVisible(yourBasketButton);
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
        commonMethods.clickElement(elementFinder.findElementBy(languageRadioButtonXpath(language)));
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


    private TopNavigationBar ordersAndPaymentMenuUnfold() {
        commonMethods.clickElement(ordersAndPaymentMenuButton);
        return this;
    }

    private TopNavigationBar privacyAndSecurityMenuUnfold() {
        commonMethods.clickElement(privacyAndSecurityMenuButton);
        return this;
    }

    public OrderHistory orderHistoryButtonClick() {
        accountButtonClick();
        ordersAndPaymentMenuUnfold();
        commonMethods.clickElement(ordersHistoryButton);
        return new OrderHistory(webDriver);
    }

    public Recycle recycleButtonClick() {
        accountButtonClick();
        ordersAndPaymentMenuUnfold();
        commonMethods.clickElement(recycleButton);
        return new Recycle(webDriver);
    }

    public MySavedAddresses mySavedAddressesButtonClick() {
        accountButtonClick();
        ordersAndPaymentMenuUnfold();
        commonMethods.clickElement(mySavedAddressesButton);
        return new MySavedAddresses(webDriver);
    }

    public MyPaymentOptions myPaymentOptionsButtonClick() {
        accountButtonClick();
        ordersAndPaymentMenuUnfold();
        commonMethods.clickElement(myPaymentOptionsButton);
        return new MyPaymentOptions(webDriver);
    }

    public DigitalWallet digitalWalletButtonClick() {
        accountButtonClick();
        ordersAndPaymentMenuUnfold();
        commonMethods.clickElement(digitalWalletButton);
        return new DigitalWallet(webDriver);
    }

    public RequestDataErasure requestDataErasureButtonClick() {
        accountButtonClick();
        privacyAndSecurityMenuUnfold();
        commonMethods.clickElement(requestDataErasureButton);
        return new RequestDataErasure(webDriver);
    }

}
