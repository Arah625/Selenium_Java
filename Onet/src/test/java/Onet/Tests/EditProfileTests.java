package Onet.Tests;

import Onet.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditProfileTests {

    WebDriver driver;
    TestService testService;
    SqlQueries sqlQueries;
    MainPage mainPage;
    LoginPage loginPage;
    EmailAccountPage emailAccountPage;
    ProfilePage profilePage;
    EditPersonalDataPage editPersonalDataPage;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        try {
            driver = new ChromeDriver();
            testService = new TestService(driver);
            System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
            driver.get(testService.onetUrl());
            driver.manage().window().maximize();
        } catch (Exception exception) {
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
    public void editDateOfBirthInProfileSettings() throws Exception {
        testService = new TestService(driver);
        sqlQueries = new SqlQueries(driver);
        int attempts = Integer.parseInt(testService.executeQueryOnElectronicData(sqlQueries.selectCountWhereColumnRecordsAreNull("personal_electronic_data", "date_of_birth")));
        int counter = 0;
        while (attempts > counter) {
            try {
                driver = testService.prepareChromeDriver(testService.onetUrl());
                mainPage = new MainPage(driver);
                mainPage.closePopUpIfVisible();
                loginPage = mainPage.emailButtonClick();
                String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWhereDateOfBirthIsNull());
                Assert.assertTrue(loginPage.isLoginToOnetMailHeaderVisible(), "Login to email account form is not visible");
                loginPage.fillEmailAddress(emailAddress);
                loginPage.submitEmailAddressButtonClick();
                String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
                loginPage.fillEmailPassword(emailPassword);
                emailAccountPage = loginPage.loginButtonClick();
                profilePage = emailAccountPage.avatarIconClick(emailAddress);
                editPersonalDataPage = profilePage.editPersonalDataButtonClick();
                String dateOfBirth = testService.randomDateInGivenRange(1989, 1, 1, 2004, 1, 1, "dd-MM-yyyy");
                editPersonalDataPage.selectDateOfBirth(dateOfBirth);
                editPersonalDataPage.saveChangesButtonClick();
                Assert.assertTrue(profilePage.isPersonalDataSavedCorrectlyHeaderVisible(), "Popup message 'Poprawnie zapisano dane personalne.' is not visible");
                testService.updateInformationToPersonalElectronicDataTable(emailAddress, dateOfBirth);

                attempts--;
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
        } catch (Exception exception) {
            System.out.println("driver.close() Error: " + exception.getMessage());
            System.out.println("driver.close() Error ST: " + exception.getStackTrace());
        }
    }
}
