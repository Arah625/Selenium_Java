package WirtualnaPolska.Tests;

import WirtualnaPolska.Pages.CreateAccountPage;
import WirtualnaPolska.Pages.LoginPage;
import WirtualnaPolska.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests {

    WebDriver driver;
    TestService testService;
    SqlQueries sqlQueries;
    MainPage mainPage;
    LoginPage loginPage;
    CreateAccountPage createAccountPage;



    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        testService = new TestService(driver);
        System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
        driver.get(testService.wirtualnaPolskaUrl());
        driver.manage().window().maximize();
    }


    @Test(priority = 1)
    public void createMaleAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.acceptTermsIfVisible();
            loginPage = mainPage.emailButtonClick();
            createAccountPage = loginPage.createAccountButtonClick();
            String firstName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getMaleFirstName()));
            String lastName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getMaleLastName()));
            createAccountPage.fillFirstName(firstName);
            createAccountPage.fillLastName(lastName);
            createAccountPage.genderMaleRadioButtonClick();
            String dayOfBirth = "15";
//            String monthOfBirth = "01";
            String monthOfBirth = "Styczeń";
            String yearOfBirth = "1997";
            createAccountPage.selectDateOfBirth(dayOfBirth, monthOfBirth,yearOfBirth);
            String partOfEmailAddress = testService.generateRandomEmailAddress(firstName, lastName);
            createAccountPage.fillLogin(partOfEmailAddress);
            String password = testService.getCredentialValue(testService.credentialsPasswordForTests());
            createAccountPage.fillPassword(password);
            createAccountPage.fillRepeatPassword(password);
            String phoneNumber = testService.getCredentialValue(testService.credentialsPhoneNumberForTests());
            createAccountPage.fillMobilePhoneNumber(phoneNumber);
            createAccountPage.freeAccountRadioButtonClick();
            createAccountPage.checkAllCheckboxes();
            createAccountPage.createAccountButtonClick();
            Assert.assertTrue(createAccountPage.isAccountCreatedHeaderVisible(), "Header 'Twoje konto w WP Poczcie zostało założone!' is not visible");
            loginPage = createAccountPage.loginToAccountButtonClick();
            Assert.assertTrue(loginPage.isLoginButtonVisible(), "Button 'Zaloguj się' is not visible");
//            testService.insertInformationToPersonalElectronicDataTable(firstName,lastName, gender, newEmailAddress, password, recoveryEmailAddress);
            driver.quit();

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
        try {
            driver.close();
            System.out.println("Calling: driver.close()");
        }catch (Exception e){
            System.out.println("Caught exception " + e.getMessage());
        }
    }
}
