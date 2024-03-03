package Utilities;

import lh.juicecompany.Exceptions.NoSuchCredentialVariableException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Manages the retrieval of credentials from a properties file.
 * This utility class provides a method to read and return the value of a specified credential variable
 * from a predefined properties file containing sensitive information like usernames, passwords, API keys, etc.
 */
public class CredentialManager {

    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.properties";

    /**
     * Reads and returns the value of a specified credential variable from the credentials properties file.
     *
     * @param credentialVariable The name of the credential variable to retrieve the value for.
     * @return The value of the specified credential variable.
     * @throws NoSuchCredentialVariableException If the specified credential variable does not exist in the properties file.
     */
    public static String credentialReader(String credentialVariable) {
        String credentialValue = null;
        try (FileReader reader = new FileReader(CREDENTIALS_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(reader);
            credentialValue = properties.getProperty(credentialVariable);

            if (credentialValue == null) {
                throw new NoSuchCredentialVariableException("There is no \"" + credentialVariable + "\" variable in credentials!");
            }
        } catch (IOException ioException) {
            ioException.getStackTrace();
        }
        return credentialValue;
    }
}