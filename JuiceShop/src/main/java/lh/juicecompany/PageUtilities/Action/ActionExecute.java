package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.Exceptions.ExceptionCaughtException;
import lh.juicecompany.Exceptions.ExceptionNotCaughtException;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class ActionExecute {

    private ActionExecute() {
    }

    @SafeVarargs
    public static boolean executeBooleanAction(BooleanSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        try {
            return executeAction(action::getAsBoolean, exceptionsToHandle);
        } catch (ExceptionCaughtException e) {
            return false;
        }
    }

    @SafeVarargs
    public static void executeVoidAction(Runnable action, Class<? extends Exception>... exceptionsToHandle) {
        executeAction(() -> {
            action.run();
            return null;
        }, exceptionsToHandle);
    }

    @SafeVarargs
    public static String executeStringAction(Supplier<String> action, Class<? extends Exception>... exceptionsToHandle) {
        return executeAction(action::get, exceptionsToHandle);
    }

    @SafeVarargs
    public static int executeIntegerAction(IntSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        return executeAction(action::getAsInt, exceptionsToHandle);
    }

    @SafeVarargs
    public static double executeDoubleAction(DoubleSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        return executeAction(action::getAsDouble, exceptionsToHandle);
    }

    private static void validateExceptionsToHandle(Class<? extends Exception>[] exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
    }

    @SafeVarargs
    public static <T> T executeAction(Callable<T> action, Class<? extends Exception>... exceptionsToHandle) {
        validateExceptionsToHandle(exceptionsToHandle);
        try {
            return action.call();
        } catch (Exception caughtException) {
            boolean isExceptionHandled = Arrays.stream(exceptionsToHandle)
                                               .anyMatch(exceptionClass -> exceptionClass.isInstance(caughtException));
            if (!isExceptionHandled) {
                throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
            }
            throw new ExceptionCaughtException("Handled exception was caught: " + caughtException.getClass(), caughtException);
        }
    }
}