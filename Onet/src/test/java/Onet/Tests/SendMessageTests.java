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

public class SendMessageTests {

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


    @Test(priority = 1)
    public void LoginToEmailAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Login to email account form is not visible");
            String emailAddress = "";
            loginPage.fillEmailAddress(emailAddress);
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.LoginButtonClick();
            emailAccountPage.writeMessageButtonClick();
            String recipient = testService.getCredentialValue(testService.credentialsRecoveryEmailAddress());
            emailAccountPage.fillRecipient(recipient);
            String subject = testService.timestamp("Test subject: ");
            emailAccountPage.fillSubject(subject);
            String message = testService.readStringFromFile(testService.notifyRecoveryEmailMessageFileLocation());
            emailAccountPage.useTextEditorButtonClick();
            emailAccountPage.fillMessage(message);
            emailAccountPage.sendEmailButtonClick();
            emailAccountPage.refreshInboxButtonClick();
            emailAccountPage.communityTabClick();
            emailAccountPage.receivedMessagesTabClick();


        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

    @Test(priority = 1)
    public void sendNotificationFromFile() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Login to email account form is not visible");
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutNotificationSent());
            loginPage.fillEmailAddress(emailAddress);
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.LoginButtonClick();
            emailAccountPage.writeMessageButtonClick();
            String recipient = testService.getCredentialValue(testService.credentialsRecoveryEmailAddress());
            emailAccountPage.fillRecipient(recipient);
            String subject = testService.timestamp("Notification: Recovery e-mail address: ");
            emailAccountPage.fillSubject(subject);
            String message = testService.readStringFromFile(testService.notifyRecoveryEmailMessageFileLocation());
            emailAccountPage.useTextEditorButtonClick();
            emailAccountPage.fillMessage(message);
            emailAccountPage.sendEmailButtonClick();
            Assert.assertTrue(emailAccountPage.isEmailSentNotificationVisible(), "Notification 'Mail został wysłany' is not visible");
            testService.executeQueryOnElectronicData(sqlQueries.updateNotificationDetails(testService.currentDate(), testService.currentTime(), emailAddress));

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
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
