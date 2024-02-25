package lh.juicecompany.PageUtilities.Action;

import lh.juicecompany.Exceptions.ExceptionCaughtException;
import lh.juicecompany.Exceptions.ExceptionNotCaughtException;

import java.util.Arrays;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class ActionExecute {

    private ActionExecute() {
    }

    @SafeVarargs
    public static boolean executeBooleanAction(Runnable action, Class<? extends Exception>... exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
        try {
            action.run();
            return true;
        } catch (Exception caughtException) {
            boolean isExceptionHandled = Arrays.stream(exceptionsToHandle)
                                               .anyMatch(exceptionClass -> exceptionClass.isInstance(caughtException));
            if (isExceptionHandled) {
                return false;
            }
            throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
        }
    }

    @SafeVarargs
    public static void executeVoidAction(Runnable action, Class<? extends Exception>... exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
        try {
            action.run();
        } catch (Exception caughtException) {
            boolean isExceptionHandled = Arrays.stream(exceptionsToHandle)
                                               .anyMatch(exceptionClass -> exceptionClass.isInstance(caughtException));
            if (!isExceptionHandled) {
                throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
            }
        }
    }

    @SafeVarargs
    public static String executeStringAction(Supplier<String> action, Class<? extends Exception>... exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
        try {
            return action.get();
        } catch (Exception caughtException) {
            boolean isExceptionHandled = Arrays.stream(exceptionsToHandle)
                                               .anyMatch(exceptionClass -> exceptionClass.isInstance(caughtException));
            if (!isExceptionHandled) {
                throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
            }
            return null;
        }
    }

    @SafeVarargs
    public static int executeIntegerAction(IntSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
        try {
            return action.getAsInt();
        } catch (Exception caughtException) {
            boolean isExceptionHandled = Arrays.stream(exceptionsToHandle)
                                               .anyMatch(exceptionClass -> exceptionClass.isInstance(caughtException));
            if (!isExceptionHandled) {
                throw new ExceptionNotCaughtException("Not ignored exception was caught: " + caughtException.getClass(), caughtException);
            }
            throw new ExceptionCaughtException("Handled exception was caught: " + caughtException.getClass(), caughtException);
        }
    }

    @SafeVarargs
    public static double executeDoubleAction(DoubleSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        if (Arrays.stream(exceptionsToHandle).toList().isEmpty()) {
            throw new IllegalArgumentException("At least one exception type must be specified.");
        }
        try {
            return action.getAsDouble();
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
