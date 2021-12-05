package Onet.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public String executeQueryOnElectronicData(String sqlQuery) {

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");
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


    public void insertInformationToPersonalElectronicDataTable(String first_name, String last_name, String gender, String email_address, String email_password, String recovery_email_address) {

        String sqlQuery = "INSERT INTO personal_electronic_data(first_name, last_name, gender, email_address, email_password, recovery_email_address, creation_date, creation_time) VALUES(?,?,?,?,?,?,?,?)";

        String creation_date = currentDate();
        String creation_time = currentTime();

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");

        try {
            Connection dbConnection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, email_address);
            preparedStatement.setString(5, email_password);
            preparedStatement.setString(6, recovery_email_address);
            preparedStatement.setString(7, creation_date);
            preparedStatement.setString(8, creation_time);

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            int idValue = 0;
            if (rs.next()) {
                idValue = rs.getInt(1);
            }
            System.out.println("Inserted first name: " + first_name + " to database table personal_electronic_data");
            System.out.println("Inserted last name: " + last_name + " to database table personal_electronic_data");
            System.out.println("Inserted gender: " + gender + " to database table personal_electronic_data");
            System.out.println("Inserted email address: " + email_address + " to database table personal_electronic_data");
            System.out.println("Inserted email password: " + email_password + " to database table personal_electronic_data");
            System.out.println("Inserted recovery email address: " + recovery_email_address + " to database table personal_electronic_data");
            System.out.println("Inserted creation date: " + creation_date + " to database table personal_electronic_data");
            System.out.println("Inserted creation time: " + creation_time + " to database table personal_electronic_data");
            System.out.println("Inserted all above information to table 'personal_electronic_data' in database: " + username + " into row: " + idValue);
            rs.close();
            preparedStatement.close();
            dbConnection.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertInformationToPersonalEDataTable(String first_name, String last_name, String gender, String email_address, String email_password, String recovery_email_address) {

        String sqlQuery = "INSERT INTO personal_e_data(first_name, last_name, gender, email_address, email_password, recovery_email_address, creation_date, creation_time) VALUES(?,?,?,?,?,?,?,?)";

        String creation_date = currentDate();
        String creation_time = currentTime();

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");

        try {
            Connection dbConnection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, email_address);
            preparedStatement.setString(5, email_password);
            preparedStatement.setString(6, recovery_email_address);
            preparedStatement.setString(7, creation_date);
            preparedStatement.setString(8, creation_time);

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            int idValue = 0;
            if (rs.next()) {
                idValue = rs.getInt(1);
            }
            System.out.println("Inserted first name: " + first_name + " to database table personal_e_data");
            System.out.println("Inserted last name: " + last_name + " to database table personal_e_data");
            System.out.println("Inserted gender: " + gender + " to database table personal_e_data");
            System.out.println("Inserted email address: " + email_address + " to database table personal_e_data");
            System.out.println("Inserted email password: " + email_password + " to database table personal_e_data");
            System.out.println("Inserted recovery email address: " + recovery_email_address + " to database table personal_e_data");
            System.out.println("Inserted creation date: " + creation_date + " to database table personal_e_data");
            System.out.println("Inserted creation time: " + creation_time + " to database table personal_e_data");
            System.out.println("Inserted all above information to table 'personal_e_data' in database: " + username + " into row: " + idValue);
            rs.close();
            preparedStatement.close();
            dbConnection.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String currentDate(){
        String currentDatePattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentDatePattern);
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("Current date: " + currentDate);
        return currentDate;
    }

    public String currentDate(String datePattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("Current date: " + currentDate);
        return currentDate;
    }

    public String currentTime(){
        String currentTimePattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentTimePattern);
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("Current time: " + currentTime);
        return currentTime;
    }

    public String currentTime(String timePattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("Current time: " + currentTime);
        return currentTime;
    }

    public String timestamp(String text){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmssyyyyMMdd");
        String timestamp = simpleDateFormat.format(date);
        System.out.println("Generated timestamp: " + timestamp);
        return text + timestamp;
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
        System.out.println("Generated partial e-mail address: " + fullEmail);
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

    public String readStringFromFile(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        return fileContent;
    }

    public String notifyRecoveryEmailMessageFileLocation(){
        return "src/main/resources/NotifyRecoveryEmailMessage.txt";
    }
    public String credentialsPasswordForTests(){
        return "passwordForTests";
    }

    public String credentialsRecoveryEmailAddress(){
        return "recoveryEmailAddress";
    }
}
