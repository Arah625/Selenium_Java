package lh.juicecompany.System;

import lh.juicecompany.Logger.ErrorMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Utility class for accessing key system properties. It encapsulates the retrieval of system properties such as
 * operating system name, architecture, Java version, and vendor, providing these as publicly accessible constants.
 */
public class SystemProperties {

    public static final String OS_NAME = SystemProperties.getSystemProperties().get("os.name");
    public static final String OS_ARCHITECTURE = SystemProperties.getSystemProperties().get("os.arch");
    public static final String JAVA_VERSION = SystemProperties.getSystemProperties().get("java.version");
    public static final String JAVA_VENDOR = SystemProperties.getSystemProperties().get("java.vendor");

    private SystemProperties() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves all system properties and returns them in a map where each key is the property name.
     * This method consolidates system properties into a single map for easy access.
     *
     * @return A {@link Map} containing system property names as keys and their corresponding values as map values.
     * @throws SecurityException    If a security manager exists and its {@code checkPropertiesAccess} method doesn't allow access.
     * @throws NullPointerException If any key in the system properties is null. This is unlikely but included for completeness.
     */
    public static Map<Object, String> getSystemProperties() {
        Map<Object, String> map = new HashMap<>();
        try {
            Properties properties = System.getProperties();
            Set<Object> sysPropertiesKeys = properties.keySet();
            for (Object key : sysPropertiesKeys) {
                map.put(key, properties.getProperty((String) key));
            }
        } catch (SecurityException | NullPointerException exception) {
            ErrorMessage.caughtElementException(exception, ErrorMessage.SECURITY_ACCESS_DENIED_OR_NULL_POINTER);
        }

        return map;
    }
}