package Onet.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class TestService {

    WebDriver driver;
    WebDriverWait webDriverWait;

    SqlQueries sqlQueries;


    private static final int TIMEOUT = 10;

    public TestService(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    /**
     * method that returns type of webdriver
     */
    public String chromeDriver() {
        return "webdriver.chrome.driver";
    }

    /**
     * Method that returns location of chromedriver
     */
    public String chromeDriverLocation() {
        return "D:/Users/ChromeDriver/chromedriver.exe";
    }

    public String onetUrl() {
        return "http://onet.pl/";
    }

    public String facebookUrl() {
        return "http://facebook.com/";
    }


    public String getCredentialValue(String credentialName) {
        String credentialValue = null;
        try (FileReader reader = new FileReader("credentials")) {
            Properties properties = new Properties();
            properties.load(reader);

            credentialValue = properties.getProperty(credentialName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentialValue;
    }


    public String executeQueryOnPersonalData(String sqlQuery) {

        String url = getCredentialValue("personalDataUrl");
        String username = getCredentialValue("personalDataUsername");
        String password = getCredentialValue("personalDataPassword");
        String queryResult = null;

        try {
            Connection dbConnection = DriverManager.getConnection(url, username, password);
            Statement st = dbConnection.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            System.out.println("Executing query: " + sqlQuery);
            while (rs.next()) {
                queryResult = rs.getString(1);
                System.out.println("Result: " + queryResult);
            }
            rs.close();
            st.close();
            dbConnection.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return queryResult;
    }


    public String replacePolishLetters(String text) {
        return text.replace("ą", "a")
                .replace("Ą", "A")
                .replace("ć", "c")
                .replace("Ć", "C")
                .replace("ę", "e")
                .replace("Ę", "E")
                .replace("ł", "l")
                .replace("Ł", "L")
                .replace("ń", "n")
                .replace("Ń", "N")
                .replace("ó", "o")
                .replace("Ó", "O")
                .replace("ś", "s")
                .replace("Ś", "S")
                .replace("ź", "z")
                .replace("Ź", "Z")
                .replace("ż", "z")
                .replace("Ż", "Z");
    }

    /**
     * Method that generates random number in given range
     */
    public int randomNumberGenerator(int minimum, int maximum) {
        Random random = new Random();
        return random.nextInt(maximum - minimum) + minimum;
    }

    /**
     * Method that generates random partial e-mail address based on gender (PersonalInfoDB database)
     */
    public String generateRandomEmailAddressPersonalInfoDB(String gender) {
        String firstName = null;
        String lastName = null;
        sqlQueries = new SqlQueries(driver);
        if (gender.equalsIgnoreCase("female")) {
            //wyszukaj na bazie imie i nazwisko zenskie
            firstName = executeQueryOnPersonalData(sqlQueries.getFemaleFirstName());
            System.out.println("Selected female first name from database: " + firstName);
            lastName = executeQueryOnPersonalData(sqlQueries.getFemaleLastName());
            System.out.println("Selected female last name from database: " + lastName);
        }
        if (gender.equalsIgnoreCase("male")) {
            //wyszukaj na bazie imie i nazwisko meskie
            firstName = executeQueryOnPersonalData(sqlQueries.getMaleFirstName());
            System.out.println("Selected male first name from database: " + firstName);
            lastName = executeQueryOnPersonalData(sqlQueries.getMaleLastName());
            System.out.println("Selected male last name from database: " + lastName);
        }
        String partialEmail = replacePolishLetters((firstName + "." + lastName + randomNumberGenerator(0, 999))).toLowerCase().replace(" ", "");
        System.out.println("Generated partial e-mail address: " + partialEmail);
        return replacePolishLetters(partialEmail);
    }

    /**
     * Method that generates random partial e-mail address  based on given firstname and lastname
     */
    public String generateRandomEmailAddress(String firstName, String lastName) {
        String fullEmail = replacePolishLetters((firstName + "." + lastName + randomNumberGenerator(0, 999))).toLowerCase().replace(" ", "");
        System.out.println("Generated e-mail address: " + fullEmail);
        return replacePolishLetters(fullEmail);
    }

    public String minimizeString(String stringToMinimize) {
        int end = stringToMinimize.length();
        String firstLetter = stringToMinimize.substring(0, 1);
        String restOfString = stringToMinimize.substring(1, end);
        String minimizedString = firstLetter + restOfString.toLowerCase();
        System.out.println("String after lowering letters: " + minimizedString);
        ;
        return minimizedString;
    }

    public String polishPostalCodeGenerator() {
        String firstPart = String.valueOf(randomNumberGenerator(50, 69));
        String secondPart = String.valueOf(randomNumberGenerator(500, 599));
        String postalCode = firstPart + "-" + secondPart;
        System.out.println("Generated polish postal code: " + postalCode);
        return postalCode;
    }
}
