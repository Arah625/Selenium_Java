package lh.juicecompany.PageUtilities.Action.Retry;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ActionRetry {

    public static void doActionRetry(Runnable action) {
        doActionRetry(action, 1);
    }

    public static void doActionRetry(Runnable action, Class<? extends Exception>... ignoredExceptions) {
        doActionRetry(action, 1, ignoredExceptions);
    }

    public static void doActionRetry(Runnable action, int retryCount) {
        doActionRetry(action, retryCount);
    }

    public static void doActionRetry(Runnable action, int retryCount, Class<? extends Exception>... ignoredExceptions) {
        doActionRetry(() -> {
            action.run();
            return null;
        }, retryCount, ignoredExceptions);
    }

    public static <T> T doActionRetry(Supplier<T> action) {
        return doActionRetry(action, 1);
    }

    public static <T> T doActionRetry(Supplier<T> action, Class<? extends Exception>... ignoredExceptions) {
        return doActionRetry(action, 1, ignoredExceptions);
    }

    public static <T> T doActionRetry(Supplier<T> action, int retryCount) {
        if (retryCount < 0) {
            throw new IllegalArgumentException("Requested number of repetitions is negative");
        }
        try {
            return action.get();
        } catch (StaleElementReferenceException | NotFoundException | ElementClickInterceptedException |
                 TimeoutException exception) {
            if (retryCount > 0) {
                System.out.println("Action ended in failure. Retrying! Atempt left: {}" + retryCount);
                return (doActionRetry(action, retryCount - 1));
            } else {
                throw exception;
            }
        }
    }

    public static <T> T doActionRetry(Supplier<T> action, int retryCount, Class<? extends Exception>... ignoredExceptions) {
        if (Arrays.stream(ignoredExceptions).collect(Collectors.toList()).isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (retryCount < 0) {
            throw new IllegalArgumentException("Requested number of repetitions is negative");
        }
        try {
            return action.get();
        } catch (Exception caughtException) {
            for (Class<? extends Exception> exception : ignoredExceptions) {
                if (exception.isInstance(caughtException) && (retryCount > 0)) {
                    System.out.println("Action ended in failure. Retrying! Attempts left: " + retryCount);
                    return (doActionRetry(action, retryCount - 1, ignoredExceptions));
                }
            }
            throw caughtException;
        }
    }
}