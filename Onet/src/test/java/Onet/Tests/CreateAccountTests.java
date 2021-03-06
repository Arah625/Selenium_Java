package Onet.Tests;

import Onet.Pages.CreateAccountPage;
import Onet.Pages.LoginPage;
import Onet.Pages.MainPage;
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
    public void createMaleAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Login to email account form is not visible");
            createAccountPage = loginPage.createAccountButtonClick();
            String firstName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getMaleFirstName()));
            String lastName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getMaleLastName()));
            String partOfEmailAddress = testService.generateRandomEmailAddress(firstName, lastName);
            createAccountPage.fillEmailAddress2(partOfEmailAddress);
            String newEmailAddress = createAccountPage.getEmailAddressFromSetPasswordSubheader();
            System.out.println("New email address is: " + newEmailAddress);
            String password = testService.getCredentialValue(testService.credentialsPasswordForTests());
            createAccountPage.fillNewPassword(password);
            createAccountPage.fillRepeatNewPassword(password);
            createAccountPage.submitButtonClick();
            String recoveryEmailAddress = testService.getCredentialValue(testService.credentialsRecoveryEmailAddress());
            createAccountPage.fillRecoveryEmailAddress(recoveryEmailAddress);
            createAccountPage.submitButtonClick();
            String gender = "male";
            createAccountPage.genderButtonClick(gender);
            createAccountPage.fillFirstNameAndLastName(firstName + " " + lastName);
            createAccountPage.selectDateOfBirth("13", "4", "1989");
            String postalCode = testService.polishPostalCodeGenerator();
            createAccountPage.fillPostalCode(postalCode);
            createAccountPage.submitButtonClick();
            createAccountPage.onetMailFreePlanButtonClick();
            createAccountPage.acceptAllCheckboxes();
            createAccountPage.submitButtonClick();
            Assert.assertTrue(createAccountPage.isRegistrationCompletedHeaderVisible());
            loginPage = createAccountPage.goToEmailPageButtonClick();
            testService.insertInformationToPersonalElectronicDataTable(firstName,lastName, gender, newEmailAddress, password, recoveryEmailAddress);
            driver.quit();

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }

    @Test(priority = 2, groups = {"basic"})
    public void createFemaleAccount() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            mainPage.closePopUpIfVisible();
            loginPage = mainPage.emailButtonClick();
            Assert.assertTrue(loginPage.isLoginFormVisible(), "Login to email account form is not visible");
            createAccountPage = loginPage.createAccountButtonClick();
            String firstName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getFemaleFirstName()));
            String lastName = testService.minimizeString(testService.executeQueryOnPersonalData(sqlQueries.getFemaleLastName()));
            String partOfEmailAddress = testService.generateRandomEmailAddress(firstName, lastName);
            createAccountPage.fillEmailAddress2(partOfEmailAddress);
            String newEmailAddress = createAccountPage.getEmailAddressFromSetPasswordSubheader();
            System.out.println("New email address is: " + newEmailAddress);
            String password = testService.getCredentialValue(testService.credentialsPasswordForTests());
            createAccountPage.fillNewPassword(password);
            createAccountPage.fillRepeatNewPassword(password);
            createAccountPage.submitButtonClick();
            String recoveryEmailAddress = testService.getCredentialValue(testService.credentialsRecoveryEmailAddress());
            createAccountPage.fillRecoveryEmailAddress(recoveryEmailAddress);
            createAccountPage.submitButtonClick();
            String gender = "female";
            createAccountPage.genderButtonClick(gender);
            createAccountPage.fillFirstNameAndLastName(firstName + " " + lastName);
            createAccountPage.selectDateOfBirth("13", "4", "1989");
            String postalCode = testService.polishPostalCodeGenerator();
            createAccountPage.fillPostalCode(postalCode);
            createAccountPage.submitButtonClick();
            createAccountPage.onetMailFreePlanButtonClick();
            createAccountPage.acceptAllCheckboxes();
            createAccountPage.submitButtonClick();
            Assert.assertTrue(createAccountPage.isRegistrationCompletedHeaderVisible());
            loginPage = createAccountPage.goToEmailPageButtonClick();
            testService.insertInformationToPersonalElectronicDataTable(firstName,lastName, gender, newEmailAddress, password, recoveryEmailAddress);
            driver.quit();

        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }



/*    @Test(priority = 1)
    public void successfulLogIn2() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            String firstName = testService.executeQueryOnPersonalData(sqlQueries.getMaleFirstName());
            String lastName = testService.executeQueryOnPersonalData(sqlQueries.getMaleLastName());
            String partOfEmailAddress = testService.generateRandomEmailAddress(firstName, lastName);
            String password = testService.getCredentialValue("passwordForTests");
            String recoveryEmailAddress = testService.getCredentialValue("recoveryEmailAddress");
            String gender = "male";
            testService.insertInformationToElectronicData(firstName,lastName, gender, partOfEmailAddress, password, recoveryEmailAddress);


        } catch (Exception exception) {
            System.out.println("Error occurred");
            throw exception;
        }
    }*/

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
