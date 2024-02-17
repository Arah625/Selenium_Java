package lh.juicecompany.Pages.Components.NavigationBar.SideNavBar;

import lh.juicecompany.Pages.Complaint;
import lh.juicecompany.Pages.DeluxeMembership;
import lh.juicecompany.Pages.SupportChat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideNavigationBarAfterLogin extends SideNavigationBar {

    @FindBy(xpath = "//a[@aria-label='Go to complain page']")
    private WebElement complaintButton;

    @FindBy(xpath = "//a[@aria-label='Go to chatbot page']")
    private WebElement supportChatButton;

    @FindBy(xpath = "//a[@aria-label='Go to deluxe membership page']")
    private WebElement deluxeMembershipButton;

    public SideNavigationBarAfterLogin(WebDriver webDriver) {
        super(webDriver);
    }

    public Complaint complaintButtonClick() {
        commonMethods.clickElement(complaintButton);
        return new Complaint(webDriver);
    }

    public SupportChat supportChatButtonClick() {
        commonMethods.clickElement(supportChatButton);
        return new SupportChat(webDriver);
    }

    public DeluxeMembership deluxeMembershipButtonClick() {
        commonMethods.clickElement(deluxeMembershipButton);
        return new DeluxeMembership(webDriver);
    }

//TODO: komponenty nie powinny dziedziczyc po BasicPage
}
