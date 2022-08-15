package Onet.Tests;

import Onet.Pages.EmailAccountPage;
import Onet.Pages.LoginPage;
import Onet.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginToEmailTests {

    WebDriver driver;
    TestService testService;
    SqlQueries sqlQueries;
    MainPage mainPage;
    LoginPage loginPage;
    EmailAccountPage emailAccountPage;



    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        testService = new TestService(driver);
        System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
        driver.get(testService.onetUrl());
        driver.manage().window().maximize();
        driver.close();
    }

/*    @BeforeMethod(alwaysRun = true)
    public void driverSetupIncognito() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(chromeOptions);
        testService = new TestService(driver);
        System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
        driver.get(testService.onetUrl());
        driver.manage().window().maximize();
    }*/



    @Test(priority = 1, groups = {"group_1"})
    public void firstLoginToEmailAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutLastLoginDate());
            loginPage.fillEmailAddress(emailAddress);
            loginPage.submitEmailAddressButtonClick();
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.loginButtonClick();
            Assert.assertTrue(emailAccountPage.isWriteMessageButtonVisible(), "Button 'Napisz wiadomość' is not visible");
            Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Button 'Logout' is not visible");
            testService.updateOnPersonalElectronicDataTable(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

    @Test(priority = 2, groups = {"group_2"})
    public void loginToEmailAccountWithLoginDateGreaterThan30Days() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressFromTableWhereLastLoginDateIsGreaterThan30Days(testService.currentDate("yyyy-MM-dd")));
            loginPage.fillEmailAddress(emailAddress);
            loginPage.submitEmailAddressButtonClick();
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.loginButtonClick();
            Assert.assertTrue(emailAccountPage.isWriteMessageButtonVisible(), "Button 'Napisz wiadomość' is not visible");
            Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Button 'Logout' is not visible");
            testService.updateOnPersonalElectronicDataTable(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

    @Test(priority = 3, groups = "repeatable")
    public void loginToEmailAccountWithLoginDateGreaterThan30DaysLoop() throws Exception {
        testService = new TestService(driver);
        sqlQueries = new SqlQueries(driver);
        int attempts = Integer.parseInt(testService.executeQueryOnElectronicData(sqlQueries.selectCountEmailAddressFromTableWhereLastLoginDateIsGreaterThan30Days(testService.currentDate())));
        int counter = 0;
        while (attempts > counter) {
            try {
                driver = testService.prepareChromeDriver(testService.onetUrl());
                mainPage = new MainPage(driver);
                mainPage.closePopUpIfVisible();
                loginPage = mainPage.emailButtonClick();
                Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
                String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressFromTableWhereLastLoginDateIsGreaterThan30Days(testService.currentDate()));
                loginPage.fillEmailAddress(emailAddress);
                loginPage.submitEmailAddressButtonClick();
                String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
                loginPage.fillEmailPassword(emailPassword);
                emailAccountPage = loginPage.loginButtonClick();
                emailAccountPage.clickThroughDataConfirmation();
                Assert.assertTrue(emailAccountPage.isWriteMessageButtonVisible(), "Button 'Napisz wiadomość' is not visible");
                Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Button 'Logout' is not visible");
                testService.updateOnPersonalElectronicDataTable(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));
                attempts--;
                System.out.println("Attempts left: " + attempts);


            } catch (Exception exception) {
                System.out.println("Error occurred");
                throw exception;

            }
            driver.close();
            driver.quit();
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
        System.out.println("Calling: driver.quit()");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.close();
        System.out.println("Calling: driver.close()");
    }
}
