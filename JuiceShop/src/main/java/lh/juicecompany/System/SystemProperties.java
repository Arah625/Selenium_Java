package lh.juicecompany.System;

import lh.juicecompany.Logger.ErrorMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SystemProperties {

    public static final String OS_NAME = SystemProperties.getSystemProperties().get("os.name");
    public static final String OS_ARCHITECTURE = SystemProperties.getSystemProperties().get("os.arch");
    public static final String JAVA_VERSION = SystemProperties.getSystemProperties().get("java.version");
    public static final String JAVA_VENDOR = SystemProperties.getSystemProperties().get("java.vendor");

    private SystemProperties() {
    }

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
