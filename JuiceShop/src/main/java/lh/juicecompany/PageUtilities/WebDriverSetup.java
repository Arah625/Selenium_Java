package lh.juicecompany.PageUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverSetup {
    private static final int WAIT_TIME = 10;
    private static final WebDriverSetup instance = new WebDriverSetup();
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private WebDriverSetup() {
    }

    public static WebDriverSetup getInstance() {
        return instance;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver driver) {
        this.webDriver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    public WebDriverWait getWebDriverWait() {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        return webDriverWait;
    }

    public WebDriverWait getWebDriverWait(Duration timeout) {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        return new WebDriverWait(webDriver, timeout);
    }

    public void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
            webDriver = null;
        }
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
