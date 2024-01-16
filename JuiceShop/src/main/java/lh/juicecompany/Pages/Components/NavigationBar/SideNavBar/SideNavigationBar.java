package lh.juicecompany.Pages.Components.NavigationBar.SideNavBar;

import lh.juicecompany.Pages.AboutUsAndSocialMedia.AboutUs;
import lh.juicecompany.Pages.BasicPage;
import lh.juicecompany.Pages.CustomerFeedback;
import lh.juicecompany.Pages.PhotoWall;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideNavigationBar extends BasicPage {

    @FindBy(xpath = "//a[@aria-label='Go to contact us page']")
    private WebElement customerFeedbackButton;

    @FindBy(xpath = "//a[@aria-label='Go to about us page']")
    private WebElement aboutUsButton;

    @FindBy(xpath = "//a[@aria-label='Go to photo wall']")
    private WebElement photoWallButton;

    @FindBy(xpath = "//a[@aria-label='Launch beginners tutorial']")
    private WebElement helpGettingStartedButton;

    @FindBy(xpath = "//a[@aria-label='Go to OWASP Juice Shop GitHub page']")
    private WebElement gitHubButton;

    public SideNavigationBar(WebDriver webDriver) {
        super(webDriver);
    }

    public CustomerFeedback customerFeedbackButtonClick() {
        commonMethods.clickElement(customerFeedbackButton);
        return new CustomerFeedback(webDriver);
    }

    public AboutUs aboutUsButtonClick() {
        commonMethods.clickElement(aboutUsButton);
        return new AboutUs(webDriver);
    }

    public PhotoWall photoWallButtonClick() {
        commonMethods.clickElement(photoWallButton);
        return new PhotoWall(webDriver);
    }

//TODO: komponenty nie powinny dziedziczyc po BasicPage
}
