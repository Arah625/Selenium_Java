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
        try {
            driver = new ChromeDriver();
            testService = new TestService(driver);
            System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
            driver.get(testService.onetUrl());
            driver.manage().window().maximize();
        } catch (Exception exception){
            System.out.println("beforeMethod Error: " + exception.getMessage());
            System.out.println("beforeMethod Error ST: " + exception.getStackTrace());
        }
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
            Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutLastLoginDate());
            loginPage.fillEmailAddress(emailAddress);
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.loginButtonClick();
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
            testService.executeQueryOnElectronicData(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

    //TODO: Add method that opens given URL without @BeforeMethod and add additional tests for e-mail addresses maintenance

    @Test(priority = 1)
    public void sendNotificationFromFile() throws Exception {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            int attempts = Integer.parseInt(testService.executeQueryOnElectronicData(sqlQueries.selectCountWhereColumnRecordsAreNull("personal_electronic_data", "last_login_date")));
            int counter = 0;
            while (attempts > counter) {
                try {
                    driver = testService.prepareChromeDriver(testService.onetUrl());
                    mainPage = new MainPage(driver);
                    mainPage.closePopUpIfVisible();
                    loginPage = mainPage.emailButtonClick();
                    Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
                    String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutNotificationSent());
                    loginPage.fillEmailAddress(emailAddress);
                    loginPage.submitEmailAddressButtonClick();
                    String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
                    loginPage.fillEmailPassword(emailPassword);
                    emailAccountPage = loginPage.loginButtonClick();
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
                    testService.executeQueryOnElectronicData(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));
                    attempts--;


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
        try {
            driver.close();
            System.out.println("Calling: driver.close()");
        } catch (Exception exception) {
            System.out.println("driver.close() Error: " + exception.getMessage());
            System.out.println("driver.close() Error ST: " + exception.getStackTrace());
        }
    }
}
