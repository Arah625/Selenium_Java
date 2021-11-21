package Onet.Tests;

import Onet.Pages.CreateAccountPage;
import Onet.Pages.EmailPage;
import Onet.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests {

    WebDriver driver;
    TestService testService;
    SqlQueries sqlQueries;
    MainPage mainPage;
    EmailPage emailPage;
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
    public void successfulLogIn() throws Exception {
        try {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.isPopUpHeaderVisible(), "Okno dotyczące ustawień nie jest widoczne");
            mainPage.goToWebsiteButtonClick();
            emailPage = mainPage.emailButtonClick();
            Assert.assertTrue(emailPage.isLoginFormVisible(), "Okno do logowania do poczty nie jest widoczne");
            createAccountPage = emailPage.createAccountButtonClick();
            String firstName = testService.executeQueryOnPersonalData(sqlQueries.getMaleFirstName());
            String lastName = testService.executeQueryOnPersonalData(sqlQueries.getMaleLastName());
            String partOfEmailAddress = testService.generateRandomEmailAddress(firstName, lastName);
            createAccountPage.fillEmailAddress2(partOfEmailAddress);
            String newEmailAddress = createAccountPage.getEmailAddressFromSetPasswordSubheader();
            System.out.println("New email address is: " + newEmailAddress);
            String password = testService.getCredentialValue("passwordForTests");
            createAccountPage.fillNewPassword(password);
            createAccountPage.fillRepeatNewPassword(password);
            createAccountPage.submitButtonClick();
            String recoveryEmailAddress = testService.getCredentialValue("recoveryEmailAddress");
            createAccountPage.fillRecoveryEmailAddress(recoveryEmailAddress);
            createAccountPage.submitButtonClick();
            String gender = "male";
            createAccountPage.genderButtonClick(gender);
            createAccountPage.fillFirstNameAndLastName(testService.minimizeString(firstName) + " " + testService.minimizeString(lastName));
            createAccountPage.selectDateOfBirth("13", "4", "1989");
            String postalCode = testService.polishPostalCodeGenerator();
            createAccountPage.fillPostalCode(postalCode);
            createAccountPage.submitButtonClick();
            createAccountPage.onetMailFreePlanButtonClick();
            createAccountPage.acceptAllCheckboxes();
            createAccountPage.submitButtonClick();
            Assert.assertTrue(createAccountPage.isRegistrationCompletedHeaderVisible());
            emailPage = createAccountPage.goToEmailPageButtonClick();




        } catch (Exception exception) {
            System.out.println("Wystapił błąd");
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
