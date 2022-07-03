package WirtualnaPolska.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SqlQueries {
    WebDriver driver;
    WebDriverWait webDriverWait;

    private static final int TIMEOUT = 10;

    public SqlQueries(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, TIMEOUT);
    }

    /**
     * Zapytania do bazy CSODB i PersonalInfoDB
     */
    public String getFemaleFirstName() {
        return "SELECT first_name FROM female_first_name ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getFemaleLastName() {
        return "SELECT last_name FROM female_last_name ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getMaleFirstName() {
        return "SELECT first_name FROM male_first_name ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getMaleLastName() {
        return "SELECT last_name FROM male_last_name ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    /**
     * Zapytania do bazy Selenium
     */

    public String getCityName() {
        return "SELECT city_name FROM usa_cities ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getStateId() {
        return "SELECT state_id FROM usa_cities ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getStateName() {
        return "SELECT state_name FROM usa_cities ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getCountyName() {
        return "SELECT county_name FROM usa_cities ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getFullStreetName() {
        return "SELECT full_street_name FROM usa_streets ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getZipCode() {
        return "SELECT zipcode FROM usa_streets ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getCompanyName() {
        return "SELECT company_name FROM companies ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String getOrangeHRMLogin() {
        return "SELECT user_name FROM website_users WHERE user_role = 'OrangeHRM';";
    }

    public String getOrangeHRMUserPassword() {
        return "SELECT user_password FROM website_users WHERE user_role = 'OrangeHRM';";
    }

    public String getPhpTravelsUserLogin() {
        return "SELECT user_name FROM website_users WHERE user_role = 'PhpTravelsUser';";
    }

    public String getPhpTravelsUserPassword() {
        return "SELECT user_password FROM website_users WHERE user_role = 'PhpTravelsUser';";
    }

    public String getPhpTravelsAdminLogin() {
        return "SELECT user_name FROM website_users WHERE user_role = 'PhpTravelsAdmin';";
    }

    public String getPhpTravelsAdminPassword() {
        return "SELECT user_password FROM website_users WHERE user_role = 'PhpTravelsAdmin';";
    }

    public String getPhpTravelsSupplierLogin() {
        return "SELECT user_name FROM website_users WHERE user_role = 'PhpTravelsSupplier';";
    }

    public String getPhpTravelsSupplierPassword() {
        return "SELECT user_password FROM website_users WHERE user_role = 'PhpTravelsSupplier';";
    }

    public String getGoogleWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Google';";
    }

    public String getWirtualnaPolskaWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Wirtualna Polska';";
    }

    public String getOnetWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Onet';";
    }

    public String getBankierWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Bankier';";
    }

    public String getMyBankWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'MyBank';";
    }

    public String getNBPWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'NBP';";
    }

    public String getAutomationPracticeWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Automation Practice';";
    }

    public String getPhpTravelsWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Php Travels';";
    }

    public String getOrangeHRMWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'OrangeHRM';";
    }

    public String getWakacjeWebUrl() {
        return "SELECT website_addres FROM website_links WHERE website_name = 'Wakacje';";
    }

    /**
     * Zapytania do bazy DBDT
     */

    public String getDBDTParameterValue(String testerAlias, String parametr) {
        return "SELECT wartosc FROM namiary WHERE testerAlias ='" + testerAlias + "' AND parametr = '" + parametr + "'";
    }

    /**
     * Zapytania do bazy PersonalElectronicData
     */

    public String getEmailAddress() {
        return "SELECT email_address FROM personal_electronic_data WHERE email_address NOT LIKE '%@%' ORDER BY RANDOM() FETCH FIRST ROWS ONLY;";
    }

    public String updateEmailAddress(String fullEmailAddress, String partialEmailAddress){
        return "UPDATE personal_electronic_data SET email_address = '" + fullEmailAddress + "' WHERE email_address = '" + partialEmailAddress +"';";
    }

    public String getEmailAddressWithoutNotificationSent(){
        return "SELECT email_address FROM personal_electronic_data WHERE email_address LIKE '%@wp.pl' AND recovery_email_address_notified IS NULL FETCH FIRST ROW ONLY;";
    }

    public String getEmailAddressWithoutLastLoginDate(){
        return "SELECT email_address FROM personal_electronic_data WHERE email_address LIKE '%@wp.pl' AND last_login_date IS NULL FETCH FIRST ROW ONLY";
    }

    public String updateNotificationDetails(String currentDate, String currentTime, String emailAddress){
        return "UPDATE personal_electronic_data " +
                "SET recovery_email_address_notified = 'true', " +
                "notification_sent_date = '" + currentDate + "', " +
                "notification_sent_time = '" + currentTime + "'" +
                "WHERE email_address = '" + emailAddress +"';";
    }

    public String updateLastLoginDetails(String currentDate, String currentTime, String emailAddress){
        return "UPDATE personal_electronic_data " +
                "SET last_login_date = to_date('" + currentDate + "', 'YYYY-MM-DD'), " +
                "last_login_time = to_timestamp('" + currentTime + "', 'HH24:MI:SS')" +
                "WHERE email_address = '" + emailAddress +"';";
    }

    public String selectCountWhereColumnRecordsAreNull(String tableName, String columnName){
        return "select count(*) FROM " + tableName + " WHERE " + columnName + " is NULL;";
    }

    public String selectCountWhereLastLoginDateIsOlderThan30Days(String tableName, String columnName){
        return "select count(*) FROM " + tableName + " WHERE to_timestamp(" + columnName + ", 'YYYY-MM-DD') < NOW() - INTERVAL '30 days'";
    }

    public String selectAllFromTableWhereDateIsGreaterThan(String tableName, String dateColumnName, String dateFormat, String interval){
        return "SELECT * FROM " + tableName + " WHERE to_timestamp(" + dateColumnName + ", '" + dateFormat + "') < NOW() - INTERVAL '" + interval + "'";
    }

    public String selectAllFromTableWhereDateIsGreaterThan30Days(String tableName, String dateColumnName){
        return "SELECT * from " + tableName + " WHERE to_timestamp(" + dateColumnName + ", 'YYYY-MM-DD') < NOW() - INTERVAL '30 days'";
    }

    public String alterPersonalElectronicDataTableColumnsFromDateToVarchar(){
        return "ALTER TABLE personal_electronic_data \n" +
                "ALTER COLUMN creation_date TYPE varchar (50),\n" +
                "ALTER COLUMN notification_sent_date TYPE varchar (50),\n" +
                "ALTER COLUMN last_login_date TYPE varchar (50),\n" +
                "ALTER COLUMN date_of_birth TYPE varchar (50);";
    }

    public String alterPersonalElectronicDataTableColumnsFromTimestampToVarchar(){
        return "ALTER TABLE personal_electronic_data \n" +
                "ALTER COLUMN notification_sent_time TYPE varchar (50),\n" +
                "ALTER COLUMN last_login_time TYPE varchar (50),\n" +
                "ALTER COLUMN creation_time TYPE varchar (50);";
    }

    public String alterPersonalElectronicDataTableColumnsFromVarcharToTimestamp(){
        return "ALTER TABLE personal_electronic_data \n" +
                "ALTER COLUMN creation_time TYPE timestamp USING to_timestamp(creation_time, 'HH24:MI:SS'),\n" +
                "ALTER COLUMN notification_sent_time TYPE timestamp USING to_timesta mp(notification_sent_time, 'HH24:MI:SS'),\n" +
                "ALTER COLUMN last_login_time TYPE timestamp USING to_timestamp(last_login_time, 'HH24:MI:SS');";
    }

    public String alterPersonalElectronicDataTableColumnsFromTimestampToTime(){
        return "ALTER TABLE personal_electronic_data \n" +
                "ALTER COLUMN creation_time TYPE TIME,\n" +
                "ALTER COLUMN notification_sent_time TYPE TIME,\n" +
                "ALTER COLUMN last_login_time TYPE TIME;";
    }

    public String alterPersonalElectronicDataTableColumnsFromVarcharToDate(){
        return "ALTER TABLE personal_electronic_data\n" +
                "    ALTER COLUMN creation_date TYPE DATE USING to_date(creation_date, 'DD-MM-YYYY'),\n" +
                "    ALTER COLUMN notification_sent_date TYPE DATE USING to_date(notification_sent_date, 'DD-MM-YYYY'),\n" +
                "    ALTER COLUMN last_login_date TYPE DATE USING to_date(last_login_date, 'DD-MM-YYYY'),\n" +
                "    ALTER COLUMN date_of_birth TYPE DATE USING to_date(date_of_birth, 'DD-MM-YYYY');";
    }





}