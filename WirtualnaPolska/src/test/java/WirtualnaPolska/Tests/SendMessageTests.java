package WirtualnaPolska.Tests;

import WirtualnaPolska.Pages.*;
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
    WriteMessagePage writeMessagePage;


    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        testService = new TestService(driver);
        System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
        driver.get(testService.wirtualnaPolskaUrl());
        driver.manage().window().maximize();
        driver.close();
    }

    @Test(priority = 1, groups = {"normal"})
    public void sendNotificationMessageFromFileToRecoveryEmail() throws Exception {
        testService = new TestService(driver);
        sqlQueries = new SqlQueries(driver);
        try {
            driver = testService.prepareChromeDriver(testService.wirtualnaPolskaUrl());
            mainPage = new MainPage(driver);
            mainPage.acceptTermsIfVisible();

            loginPage = mainPage.emailButtonClick();
            String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutNotificationSent());
            loginPage.fillLogin(emailAddress);
            String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
            loginPage.fillPassword(emailPassword);
            emailAccountPage = loginPage.loginButtonClick();
            emailAccountPage.acceptTermsOf1LoginFromWpIfVisible();
            Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Logout button is not visible");
            writeMessagePage = emailAccountPage.writeMessageButtonClick();
            writeMessagePage.fillRecipientEmailAddress("asdas@wp.pl");
            String messageTitle = testService.timestamp("Notification: Recovery e-mail address: ");
            writeMessagePage.fillMessageTitle(messageTitle);
            String message = testService.readStringFromFile(testService.notifyRecoveryEmailMessageFileLocation());
            writeMessagePage.fillMessage(message);
            emailAccountPage = writeMessagePage.sendMessageButtonClick();
            Thread.sleep(3000);
            Assert.assertTrue(emailAccountPage.isMessageSentNotificationVisible(), "Notification 'Wiadomość została wysłana' is not visible");
            testService.updateOnPersonalElectronicDataTable(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));
        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        } finally {
            driver.close();
            driver.quit();
        }
    }

    @Test(priority = 1, groups = {"repeatable"})
    public void sendNotificationMessageFromFileToRecoveryEmailLoop() throws Exception {
        testService = new TestService(driver);
        sqlQueries = new SqlQueries(driver);
        int attempts = Integer.parseInt(testService.executeQueryOnElectronicData(sqlQueries.selectCountWhereColumnRecordsAreNull("personal_electronic_data", "recovery_email_address_notified")));
        int counter = 0;
        while (attempts > counter) {
            try {
                driver = testService.prepareChromeDriver(testService.wirtualnaPolskaUrl());
                mainPage = new MainPage(driver);
                mainPage.acceptTermsIfVisible();

                loginPage = mainPage.emailButtonClick();
                String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutNotificationSent());
                loginPage.fillLogin(emailAddress);
                String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
                loginPage.fillPassword(emailPassword);
                emailAccountPage = loginPage.loginButtonClick();
                emailAccountPage.acceptTermsOf1LoginFromWpIfVisible();
                Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Logout button is not visible");
                writeMessagePage = emailAccountPage.writeMessageButtonClick();
                writeMessagePage.fillRecipientEmailAddress("asdas@wp.pl");
                String messageTitle = testService.timestamp("Notification: Recovery e-mail address: ");
                writeMessagePage.fillMessageTitle(messageTitle);
                String message = testService.readStringFromFile(testService.notifyRecoveryEmailMessageFileLocation());
                writeMessagePage.fillMessage(message);
                emailAccountPage = writeMessagePage.sendMessageButtonClick();
                Thread.sleep(3000);
                Assert.assertTrue(emailAccountPage.isMessageSentNotificationVisible(), "Notification 'Wiadomość została wysłana' is not visible");
                testService.updateOnPersonalElectronicDataTable(sqlQueries.updateLastLoginDetails(testService.currentDate(), testService.currentTime(), emailAddress));

                attempts--;
                System.out.println("Attempts: " + attempts);

            } catch (Exception exception) {
                System.out.println("Error occurred");
                throw exception;
            } finally {
                driver.close();
                driver.quit();
            }
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
        } catch (Exception e) {
            System.out.println("Caught exception " + e.getMessage());
        }
    }
}
