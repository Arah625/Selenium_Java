package lh.juicecompany.Pages;

import lh.juicecompany.Pages.AboutUsAndSocialMedia.AboutUs;
import lh.juicecompany.Pages.Account.Login;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MyPaymentOptions;
import lh.juicecompany.Pages.Account.OrdersAndPayment.MySavedAddress.MySavedAdresses.MySavedAddresses;
import lh.juicecompany.Pages.Account.PrivacyAndSecurity.RequestDataErasure;
import lh.juicecompany.Pages.Components.NavigationBar.SideNavBar.SideNavigationBar;
import lh.juicecompany.Pages.Components.NavigationBar.TopNavBar.TopNavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends BasicPage {

    private SideNavigationBar sideNavigationBar;
    private TopNavigationBar topNavigationBar;

//    @FindBy(xpath = "//div[contains(text(),'All Products')]")
//    private WebElement allProductsHeader;

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    private WebElement dismissWelcomeBannerButton;

    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    private WebElement acceptCookiesButton;

    public Home(WebDriver webDriver) {
        super(webDriver);
        this.topNavigationBar = new TopNavigationBar(webDriver);
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
        return this;
    }

    public Home acceptCookiesButtonClick() {
        commonMethods.clickElement(acceptCookiesButton);
        return this;
    }

    public Login goToLogin() {
        topNavigationBar.loginButtonClick();
        return new Login(webDriver);
    }

    public boolean isLoginButtonVisible() {
        return topNavigationBar.isLoginButtonVisible();
    }

    public boolean isLogoutButtonVisible() {
        return topNavigationBar.isLogoutButtonVisible();
    }

    public boolean isYourBasketButtonVisible() {
        return topNavigationBar.isYourBasketButtonVisible();
    }

    public CustomerFeedback goToCustomerFeedback() {
        sideNavigationBar = topNavigationBar.openSideNavbar();
        sideNavigationBar.customerFeedbackButtonClick();
        return new CustomerFeedback(webDriver);
    }

    public AboutUs goToAboutUs() {
        sideNavigationBar = topNavigationBar.openSideNavbar();
        sideNavigationBar.aboutUsButtonClick();
        return new AboutUs(webDriver);
    }

    public PhotoWall goToPhotoWall() {
        sideNavigationBar = topNavigationBar.openSideNavbar();
        sideNavigationBar.photoWallButtonClick();
        return new PhotoWall(webDriver);
    }

    public Home selectLanguage(String language) {
        topNavigationBar.selectLanguage(language);
        return this;
    }

    public boolean isLanguageChangedAlertVisible(String language) {
        return topNavigationBar.isLanguageChangedAlertVisible(language);
    }

    public boolean isForcePageReloadButtonVisible() {
        return topNavigationBar.isForcePageReloadButtonVisible();
    }

    public Home forcePageReloadButtonClick() {
        topNavigationBar.forcePageReloadButtonClick();
        return this;
    }

    public String getLanguageDropdownButtonCode() {
        return topNavigationBar.getLanguageCode();
    }

    public MySavedAddresses goToMySavedAddresses() {
        topNavigationBar.mySavedAddressesButtonClick();
        return new MySavedAddresses(webDriver);
    }

    public MyPaymentOptions goTomyPaymentOptions() {
        topNavigationBar.myPaymentOptionsButtonClick();
        return new MyPaymentOptions(webDriver);
    }

    public RequestDataErasure goToRequestDataErasure() {
        topNavigationBar.requestDataErasureButtonClick();
        return new RequestDataErasure(webDriver);
    }
}
