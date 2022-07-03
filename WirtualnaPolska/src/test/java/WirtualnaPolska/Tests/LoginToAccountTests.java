package WirtualnaPolska.Tests;

import WirtualnaPolska.Pages.CreateAccountPage;
import WirtualnaPolska.Pages.EmailAccountPage;
import WirtualnaPolska.Pages.LoginPage;
import WirtualnaPolska.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginToAccountTests {

    WebDriver driver;
    TestService testService;
    SqlQueries sqlQueries;
    MainPage mainPage;
    LoginPage loginPage;
    CreateAccountPage createAccountPage;
    EmailAccountPage emailAccountPage;


    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        testService = new TestService(driver);
        System.setProperty(testService.chromeDriver(), testService.chromeDriverLocation());
        driver.get(testService.wirtualnaPolskaUrl());
        driver.manage().window().maximize();
        driver.close();
    }

    @Test(priority = 1)
    public void firstLoginToAccount() throws Exception {
            testService = new TestService(driver);
            sqlQueries = new SqlQueries(driver);
            int attempts = Integer.parseInt(testService.executeQueryOnElectronicData(sqlQueries.selectCountWhereColumnRecordsAreNull("personal_electronic_data", "last_login_date")));
            int counter = 0;
            while (attempts > counter) {
                try {
                    driver = testService.prepareChromeDriver(testService.wirtualnaPolskaUrl());
                    mainPage = new MainPage(driver);

                    mainPage.acceptTermsIfVisible();
                    Thread.sleep(3000);
                    loginPage = mainPage.emailButtonClick();
                    String emailAddress = testService.executeQueryOnElectronicData(sqlQueries.getEmailAddressWithoutLastLoginDate());
                    loginPage.fillLogin(emailAddress);
                    String emailPassword = testService.getCredentialValue(testService.credentialsPasswordForTests());
                    loginPage.fillPassword(emailPassword);
                    emailAccountPage = loginPage.loginButtonClick();
                    emailAccountPage.acceptTermsOf1LoginFromWpIfVisible();
                    Assert.assertTrue(emailAccountPage.isLogoutButtonVisible(), "Logout button is not visible");
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
