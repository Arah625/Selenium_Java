package Onet.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailPage extends BasePage{
    public EmailPage(WebDriver driver){
        super(driver);
    }


    @FindBy (xpath = "//*[@id = 'loginForm']")
    WebElement loginForm;

    @FindBy (xpath = "//a[contains(text(),'Załóż konto')]")
    WebElement createAccountButton;




    public boolean isLoginFormVisible() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(loginForm)).isDisplayed();
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public CreateAccountPage createAccountButtonClick() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
        return new CreateAccountPage(driver);
    }

}
