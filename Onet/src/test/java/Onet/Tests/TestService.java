package Onet.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestService {

    WebDriver driver;
    WebDriverWait webDriverWait;

    SqlQueries sqlQueries;


    private static final int TIMEOUT = 15;

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

    //TODO: Fix this method

    public RemoteWebDriver prepareChromeDriver(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(false);
        driver = new ChromeDriver();
        TestService testService = new TestService(driver);
        System.setProperty(chromeDriver(), chromeDriverLocation());
        driver.get(onetUrl());
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return (RemoteWebDriver) driver;
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


    public String executeQueryOnPersonalData(String sqlQuery) throws SQLException {

        String url = getCredentialValue("personalDataUrl");
        String username = getCredentialValue("personalDataUsername");
        String password = getCredentialValue("personalDataPassword");
        String queryResult = null;
        Connection dbConnection = DriverManager.getConnection(url, username, password);
        Statement st = dbConnection.createStatement();
        ResultSet rs = st.executeQuery(sqlQuery);

        try {

            System.out.println("Executing query: " + sqlQuery);
            while (rs.next()) {
                queryResult = rs.getString(1);
                System.out.println("Result: " + queryResult);
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Executing finally block and closing database (" + username + ") connection");
            rs.close();
            st.close();
            dbConnection.close();
        }
        return queryResult;
    }

    public String executeQueryOnElectronicData(String sqlQuery) throws SQLException {

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");
        String queryResult = null;
        Connection dbConnection = DriverManager.getConnection(url, username, password);
        Statement st = dbConnection.createStatement();
        ResultSet rs = st.executeQuery(sqlQuery);

        try {

            System.out.println("Executing query: " + sqlQuery);
            while (rs.next()) {
                queryResult = rs.getString(1);
                System.out.println("Result: " + queryResult);
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Executing finally block and closing database (" + username + ") connection");
            rs.close();
            st.close();
            dbConnection.close();
        }
        return queryResult;
    }


    public void insertInformationToPersonalElectronicDataTable(String first_name, String last_name, String gender, String email_address, String email_password, String recovery_email_address) throws SQLException {

        String sqlQuery = "INSERT INTO personal_electronic_data(first_name, last_name, gender, email_address, email_password, recovery_email_address, creation_date, creation_time) VALUES(?,?,?,?,?,?,?,?)";

        String creation_date = currentDate();
        String creation_time = currentTime();

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");

        Connection dbConnection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = preparedStatement.getGeneratedKeys();
        try {

            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, email_address);
            preparedStatement.setString(5, email_password);
            preparedStatement.setString(6, recovery_email_address);
            preparedStatement.setString(7, creation_date);
            preparedStatement.setString(8, creation_time);

            preparedStatement.execute();

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
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Executing finally block and closing database (" + username + ") connection");
            rs.close();
            preparedStatement.close();
            dbConnection.close();
        }
    }

    public void insertInformationToPersonalEDataTable(String first_name, String last_name, String gender, String email_address, String email_password, String recovery_email_address) throws SQLException {

        String sqlQuery = "INSERT INTO personal_e_data(first_name, last_name, gender, email_address, email_password, recovery_email_address, creation_date, creation_time) VALUES(?,?,?,?,?,?,?,?)";

        String creation_date = currentDate();
        String creation_time = currentTime();

        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");

        Connection dbConnection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = preparedStatement.getGeneratedKeys();

        try {

            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, email_address);
            preparedStatement.setString(5, email_password);
            preparedStatement.setString(6, recovery_email_address);
            preparedStatement.setString(7, creation_date);
            preparedStatement.setString(8, creation_time);

            preparedStatement.execute();

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

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Executing finally block and closing database connection");
            rs.close();
            preparedStatement.close();
            dbConnection.close();
        }
    }

    public void updateInformationToPersonalElectronicDataTable(String email_address, String dateOfBirth) throws SQLException {
        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");
        Vector<String> columnNames = new Vector<>();

        Connection dbConnection = DriverManager.getConnection(url, username, password);
        dbConnection.setAutoCommit(false);
        Statement statement = dbConnection.createStatement();
        ResultSet rs = null;

        try {

            String sql = "UPDATE personal_electronic_data SET last_login_date = '" + currentDate() + "', last_login_time = '" + currentTime() + "', date_of_birth = '" + dateOfBirth + "' WHERE email_address = '" + email_address + "';";
            statement.executeUpdate(sql);
            dbConnection.commit();

            rs = statement.executeQuery("SELECT * FROM personal_electronic_data;");


            ResultSetMetaData columns = rs.getMetaData();
            int i = 0;
            while (i < columns.getColumnCount()) {
                i++;
                System.out.print(columns.getColumnName(i) + "\t");
                columnNames.add(columns.getColumnName(i));
            }
            System.out.print("\n");

            while (rs.next()) {
                for (i = 0; i < columnNames.size(); i++) {
                    System.out.print(rs.getString(columnNames.get(i))
                            + "\t");

                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            System.out.println("Executing finally block and closing database (" + username + ") connection");
            rs.close();
            statement.close();
            dbConnection.close();
        }
        System.out.println("Operation done successfully");
    }

    public void updateOnPersonalElectronicDataTable(String query) throws SQLException {
        String url = getCredentialValue("electronicDataUrl");
        String username = getCredentialValue("electronicDataUsername");
        String password = getCredentialValue("electronicDataPassword");
        Vector<String> columnNames = new Vector<>();

        Connection dbConnection = DriverManager.getConnection(url, username, password);
        dbConnection.setAutoCommit(false);
        Statement statement = dbConnection.createStatement();
        ResultSet rs = null;

        try {

            statement.executeUpdate(query);
            dbConnection.commit();

            rs = statement.executeQuery("SELECT * FROM personal_electronic_data;");


            ResultSetMetaData columns = rs.getMetaData();
            int i = 0;
            while (i < columns.getColumnCount()) {
                i++;
                System.out.print(columns.getColumnName(i) + "\t");
                columnNames.add(columns.getColumnName(i));
            }
            System.out.print("\n");

            while (rs.next()) {
                for (i = 0; i < columnNames.size(); i++) {
                    System.out.print(rs.getString(columnNames.get(i))
                            + "\t");

                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            System.out.println("Executing finally block and closing database (" + username + ") connection");
            rs.close();
            statement.close();
            dbConnection.close();
        }
        System.out.println("Operation done successfully");
    }

    public String currentDate() {
        String currentDatePattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentDatePattern);
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("Current date: " + currentDate);
        return currentDate;
    }

    public String currentDate(String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("Current date: " + currentDate);
        return currentDate;
    }

    public String currentTime() {
        String currentTimePattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(currentTimePattern);
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("Current time: " + currentTime);
        return currentTime;
    }

    public String currentTime(String timePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("Current time: " + currentTime);
        return currentTime;
    }

    public String timestamp(String text) {
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
    public String generateRandomEmailAddressPersonalInfoDB(String gender) throws SQLException {
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

    public String notifyRecoveryEmailMessageFileLocation() {
        return "src/main/resources/NotifyRecoveryEmailMessage.txt";
    }

    public String credentialsPasswordForTests() {
        return "passwordForTests";
    }

    public String credentialsRecoveryEmailAddress() {
        return "recoveryEmailAddress";
    }

    public String credentialsVerificationPhoneNumber() {
        return "verificationPhoneNumber";
    }

    public String removeLeadingZeros(String string) {
        return string.replaceFirst("^0+(?!$)", "");
    }

    public String randomDateInGivenRange(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay, String dateFormat) {
        LocalDate from = LocalDate.of(startYear, startMonth, startDay);
        LocalDate to = LocalDate.of(endYear, endMonth, endDay);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        String generatedDate = randomDate.format(DateTimeFormatter.ofPattern(dateFormat));
        System.out.println("Generating date between " + startDay + "-" + startMonth + "-" + startYear + " and " + endDay + "-" + endMonth + "-" + endYear);
        System.out.println("in format: '" + dateFormat + "'");
        System.out.println("Generated date: " + generatedDate);
        return generatedDate;
    }

}
