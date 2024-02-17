package lh.juicecompany.Pages;

import lh.juicecompany.Pages.AboutUsAndSocialMedia.AboutUs;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MyPaymentOptions;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.MySavedAddresses;
import lh.juicecompany.Pages.Account.PrivacyAndSecurity.RequestDataErasure;
import lh.juicecompany.Pages.Components.NavigationBar.SideNavBar.SideNavigationBar;
import lh.juicecompany.Pages.Components.NavigationBar.TopNavBar.TopNavigationBarAfterLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends BasicPage {

    private SideNavigationBar sideNavigationBar;
    private TopNavigationBarAfterLogin topNavigationBarAfterLogin;

//    @FindBy(xpath = "//div[contains(text(),'All Products')]")
//    private WebElement allProductsHeader;

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    private WebElement dismissWelcomeBannerButton;

    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    private WebElement acceptCookiesButton;

    public Home(WebDriver webDriver) {
        super(webDriver);
        this.topNavigationBarAfterLogin = new TopNavigationBarAfterLogin(webDriver);
        this.sideNavigationBar = new SideNavigationBar(webDriver);
    }

    private static By allProductsHeader(String frazeInForeignLanguage) {
        return By.xpath("//div[contains(text(),'" + frazeInForeignLanguage + "')]");
    }

    public boolean isAllProductsHeaderVisible() {
        return elementVisibilityHandler.isElementVisible(allProductsHeader("All Products"));
    }

    public boolean isAllProductsHeaderVisible(String frazeInForeignLanguage) {
        return elementVisibilityHandler.isElementVisible(allProductsHeader(frazeInForeignLanguage));
    }

    public Home dismissWelcomeBannerButtonClick() {
        commonMethods.clickElement(dismissWelcomeBannerButton);
        return this;
    }

    public Home dismissPopUps() {
        dismissWelcomeBannerButtonClick();
        acceptCookiesButtonClick();
        if (isForcePageReloadButtonVisible()) {
            forcePageReloadButtonClick();
        }
        return this;
    }

    public Home acceptCookiesButtonClick() {
        commonMethods.clickElement(acceptCookiesButton);
        return this;
    }

    public Login goToLogin() {
        topNavigationBarAfterLogin.loginButtonClick();
        return new Login(webDriver);
    }

    public boolean isLoginButtonVisible() {
        return topNavigationBarAfterLogin.isLoginButtonVisible();
    }

    public boolean isLogoutButtonVisible() {
        return topNavigationBarAfterLogin.isLogoutButtonVisible();
    }

    public boolean isYourBasketButtonVisible() {
        return topNavigationBarAfterLogin.isYourBasketButtonVisible();
    }

    public CustomerFeedback goToCustomerFeedback() {
        sideNavigationBar = topNavigationBarAfterLogin.openSideNavbar();
        sideNavigationBar.customerFeedbackButtonClick();
        return new CustomerFeedback(webDriver);
    }

    public AboutUs goToAboutUs() {
        sideNavigationBar = topNavigationBarAfterLogin.openSideNavbar();
        sideNavigationBar.aboutUsButtonClick();
        return new AboutUs(webDriver);
    }

    public PhotoWall goToPhotoWall() {
        sideNavigationBar = topNavigationBarAfterLogin.openSideNavbar();
        sideNavigationBar.photoWallButtonClick();
        return new PhotoWall(webDriver);
    }

    public Home selectLanguage(String language) throws Exception {
        topNavigationBarAfterLogin.selectLanguage(language);
        return this;
    }

    public boolean isLanguageChangedAlertVisible(String language) {
        return topNavigationBarAfterLogin.isLanguageChangedAlertVisible(language);
    }

    public boolean isForcePageReloadButtonVisible() {
        return topNavigationBarAfterLogin.isForcePageReloadButtonVisible();
    }

    public Home forcePageReloadButtonClick() {
        topNavigationBarAfterLogin.forcePageReloadButtonClick();
        return this;
    }

    public String getLanguageDropdownButtonCode() {
        return topNavigationBarAfterLogin.getLanguageCode();
    }

    public MySavedAddresses goToMySavedAddresses() {
        topNavigationBarAfterLogin.mySavedAddressesButtonClick();
        return new MySavedAddresses(webDriver);
    }

    public MyPaymentOptions goTomyPaymentOptions() {
        topNavigationBarAfterLogin.myPaymentOptionsButtonClick();
        return new MyPaymentOptions(webDriver);
    }

    public RequestDataErasure goToRequestDataErasure() {
        topNavigationBarAfterLogin.requestDataErasureButtonClick();
        return new RequestDataErasure(webDriver);
    }
}
