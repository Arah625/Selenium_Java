package Onet.Tests;

import Onet.Pages.EmailAccountPage;
import Onet.Pages.LoginPage;
import Onet.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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


/*    @Test(priority = 1)
    public void LoginToEmailAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.isPopUpHeaderVisible(), "Okno dotyczące ustawień nie jest widoczne");
            mainPage.goToWebsiteButtonClick();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Okno do logowania do poczty nie jest widoczne");
            String emailAddress = "yuriy.olejnik666@op.pl";
            loginPage.fillEmailAddress(emailAddress);
            String emailPassword = testService.getCredentialValue("passwordForTests");
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.LoginButtonClick();
            int retry = 0;
            while (retry < 1) {
                emailAccountPage.writeMessageButtonClick();
                String recipient = "yuriy.olejnik666@op.pl";
                emailAccountPage.fillRecipient(recipient);
                String subject = testService.timestamp("Test subject: ");
                emailAccountPage.fillSubject(subject);
                String message = testService.readStringFromFile("src/main/resources/NotifyRecoveryEmailMessage.txt");
                emailAccountPage.useTextEditorButtonClick();
                emailAccountPage.fillMessage(message);
                emailAccountPage.sendEmailButtonClick();
                emailAccountPage.refreshInboxButtonClick();
                emailAccountPage.communityTabClick();
                retry++;
            }
            emailAccountPage.receivedMessagesTabClick();


        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }*/

    @Test(priority = 1)
    public void sendNotificationFromFile() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.isPopUpHeaderVisible(), "Okno dotyczące ustawień nie jest widoczne");
            mainPage.goToWebsiteButtonClick();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Okno do logowania do poczty nie jest widoczne");
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutNotificationSent());
            loginPage.fillEmailAddress(emailAddress);
            String emailPassword = testService.getCredentialValue("passwordForTests");
            loginPage.fillEmailPassword(emailPassword);
            emailAccountPage = loginPage.LoginButtonClick();
            emailAccountPage.writeMessageButtonClick();
            String recipient = testService.getCredentialValue("recoveryEmailAddress");
            emailAccountPage.fillRecipient(recipient);
            String subject = testService.timestamp("Notification: Recovery e-mail address: ");
            emailAccountPage.fillSubject(subject);
            String message = testService.readStringFromFile("src/main/resources/NotifyRecoveryEmailMessage.txt");
            emailAccountPage.useTextEditorButtonClick();
            emailAccountPage.fillMessage(message);
            emailAccountPage.sendEmailButtonClick();
            emailAccountPage.refreshInboxButtonClick();
            testService.executeQueryOnElectronicData(sqlQueries.updateNotificationDetails(testService.currentDate(), testService.currentTime(),emailAddress));

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

/*    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
        System.out.println("Calling: driver.quit()");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.close();
        System.out.println("Calling: driver.close()");
    }*/
}
