package lh.juicecompany.PageUtilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class WebDriverSetup {
    //TODO: Add logging in throws
    private static final Duration WAIT_TIME = Duration.ofSeconds(10);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(250);
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
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIME);
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

    //TODO: Add logs for debug maybe
    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration timeout, Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval);

        // Convert varargs to a list and use it with ignoreAll
        fluentWait.ignoreAll(Arrays.asList(exceptionsToIgnore));

        return fluentWait;
    }

    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(WAIT_TIME)
                .pollingEvery(pollingInterval);

        // Convert varargs to a list and use it with ignoreAll
        fluentWait.ignoreAll(Arrays.asList(exceptionsToIgnore));
        return fluentWait;
    }

    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Class<? extends Throwable>... exceptionsToIgnore) {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(WAIT_TIME)
                .pollingEvery(POLLING_INTERVAL);

        // Convert varargs to a list and use it with ignoreAll
        fluentWait.ignoreAll(Arrays.asList(exceptionsToIgnore));
        return fluentWait;
    }

    public final FluentWait<WebDriver> getFluentWait() {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(WAIT_TIME)
                .pollingEvery(POLLING_INTERVAL);

        // Convert varargs to a list and use it with ignoreAll
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval) {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(WAIT_TIME)
                .pollingEvery(pollingInterval);

        // Convert varargs to a list and use it with ignoreAll
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
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
