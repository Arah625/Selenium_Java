package lh.juicecompany.PageUtilities.Action.Retry;

import lh.juicecompany.Exceptions.ExceptionNotCaughtException;
import lh.juicecompany.Exceptions.MaximumNumberOfRepetitionsException;

import java.util.Arrays;
import java.util.function.Supplier;

public class ActionRetry {

    private static final int COUNTER_BASE_VALUE = 0;

    private ActionRetry() {
    }

    @SafeVarargs
    public static void doActionRetry(Runnable action, Class<? extends Exception>... ignoredExceptions) {
        doActionRetry(action, 1, ignoredExceptions);
    }

    @SafeVarargs
    public static void doActionRetry(Runnable action, int retryCount, Class<? extends Exception>... ignoredExceptions) {
        doActionRetry(() -> {
            action.run();
            return null;
        }, retryCount, ignoredExceptions);
    }

    @SafeVarargs
    public static <T> T doActionRetry(Supplier<T> action, Class<? extends Exception>... ignoredExceptions) {
        return doActionRetry(action, 1, ignoredExceptions);
    }

    @SafeVarargs
    public static <T> T doActionRetry(Supplier<T> action, int numberOfRepetitions, Class<? extends Exception>... ignoredExceptions) {
        int retry = COUNTER_BASE_VALUE;
        while (true) {
            try {
                return action.get();
            } catch (Exception caughtException) {
                boolean isIgnoredException = Arrays.stream(ignoredExceptions)
                                                   .anyMatch(ignored -> ignored.isInstance(caughtException));
                if (!isIgnoredException) {
                    throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
                }
                if (retry >= numberOfRepetitions) {
                    throw new MaximumNumberOfRepetitionsException(String.format("Task failed after %s attempts", numberOfRepetitions), caughtException);
                }
                retry++;
            }
        }
    }
}