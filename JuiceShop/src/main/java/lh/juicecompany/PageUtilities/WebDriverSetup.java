package lh.juicecompany.PageUtilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

/**
 * Singleton class for managing {@link WebDriver} instances and providing flexible wait configurations.
 * Offers utility methods to obtain {@link WebDriverWait} and {@link FluentWait} instances with customizable
 * timeouts, polling intervals, and exception handling strategies. Ensures that wait conditions can be tailored
 * to the specific needs of tests, enhancing reliability and robustness.
 */
public class WebDriverSetup {
    //TODO: Add logging in throws
    private static final Duration WAIT_TIME = Duration.ofSeconds(10);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(250);
    private static final WebDriverSetup instance = new WebDriverSetup();
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private WebDriverSetup() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves the singleton instance of WebDriverSetup.
     *
     * @return The singleton instance of WebDriverSetup.
     */
    public static WebDriverSetup getInstance() {
        return instance;
    }

    /**
     * Provides the current {@link WebDriver} instance.
     *
     * @return The current {@link WebDriver} instance.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    public WebDriver getWebDriver() {
        ensureWebDriverInitialized();
        return webDriver;
    }

    /**
     * Sets the {@link WebDriver} instance and initializes {@link WebDriverWait} with a default wait time.
     *
     * @param driver The {@link WebDriver} instance to set.
     */
    public void setWebDriver(WebDriver driver) {
        this.webDriver = driver;
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIME);
    }

    /**
     * Provides a {@link WebDriverWait} instance using the default wait time.
     *
     * @return A {@link WebDriverWait} instance.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    public WebDriverWait getWebDriverWait() {
        ensureWebDriverInitialized();
        return webDriverWait;
    }

    /**
     * Provides a WebDriverWait instance with a customizable timeout.
     *
     * @param timeout The duration to set as the timeout.
     * @return A {@link WebDriverWait} instance with the specified timeout.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    public WebDriverWait getWebDriverWait(Duration timeout) {
        ensureWebDriverInitialized();
        return new WebDriverWait(webDriver, timeout);
    }

    /**
     * Configures and returns a {@link FluentWait} instance with customizable timeout and polling interval,
     * allowing for specific exceptions to be ignored during the wait. This method provides flexibility in defining
     * wait conditions, especially useful for conditions where certain exceptions can be anticipated as part of normal
     * operations.
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * FluentWait<WebDriver> wait = WebDriverSetup.getInstance().getFluentWait(Duration.ofSeconds(10), Duration.ofMillis(500), NoSuchElementException.class, TimeoutException.class);
     * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someId")));
     * }</pre>
     *
     * @param timeout            The maximum time to wait for a condition to become true.
     * @param pollingInterval    The frequency with which to check the condition.
     * @param exceptionsToIgnore The types of exceptions to ignore while polling for a condition.
     * @return A {@link FluentWait} instance configured with the specified options.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    //TODO: Add logs for debug maybe
    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration timeout, Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        ensureWebDriverInitialized();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval);
        fluentWait.ignoreAll(Arrays.asList(exceptionsToIgnore));
        return fluentWait;
    }

    /**
     * Provides a {@link FluentWait} instance with a customizable polling interval and exceptions to ignore,
     * using the default wait time. This variant allows for quick configuration of polling behavior while
     * maintaining a standard wait duration.
     *
     * @param pollingInterval    The interval at which to poll for the condition.
     * @param exceptionsToIgnore The types of exceptions to ignore while polling for a condition.
     * @return A {@link FluentWait} instance configured with the specified options.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        return getFluentWait(WAIT_TIME, pollingInterval, exceptionsToIgnore);
    }

    /**
     * Provides a {@link FluentWait} instance using the default wait time and polling interval, with exceptions to ignore.
     * This method simplifies the configuration by using predefined wait and polling intervals, focusing on exception handling.
     *
     * @param exceptionsToIgnore The types of exceptions to ignore while polling for a condition.
     * @return A {@link FluentWait} instance configured with default timing and specified exceptions.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Class<? extends Throwable>... exceptionsToIgnore) {
        return getFluentWait(WAIT_TIME, POLLING_INTERVAL, exceptionsToIgnore);
    }

    /**
     * Provides a {@link FluentWait} instance using the default wait time and polling interval.
     * This default configuration is tailored for the most common use cases, with simplified setup.
     *
     * @return A {@link FluentWait} instance with default configuration.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    public final FluentWait<WebDriver> getFluentWait() {
        return getFluentWait(WAIT_TIME, POLLING_INTERVAL, NoSuchElementException.class);
    }

    /**
     * Provides a {@link FluentWait} instance with a customizable polling interval, using the default wait time.
     * This method focuses on adjusting the frequency of condition checks, useful for fine-tuning wait strategies.
     *
     * @param pollingInterval The interval at which to poll for the condition.
     * @return A {@link FluentWait} instance configured with the specified polling interval and default wait time.
     * @throws IllegalStateException if the {@link WebDriver} has not been initialized.
     */
    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval) {
        return getFluentWait(WAIT_TIME, pollingInterval, NoSuchElementException.class);
    }

    /**
     * Closes the current {@link WebDriver} session.
     */
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
            webDriver = null;
        }
    }

    /**
     * Quits the {@link WebDriver}, effectively closing the browser session.
     */
    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    /**
     * Ensures that the WebDriver has been initialized before attempting to perform any operations that require it.
     * Throws {@link IllegalStateException} if the WebDriver is not initialized.
     */
    private void ensureWebDriverInitialized() {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver not initialized in WebDriverSetup.");
        }
    }
}