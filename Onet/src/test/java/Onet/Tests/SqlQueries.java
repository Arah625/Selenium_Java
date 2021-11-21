package Onet.Tests;

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
}