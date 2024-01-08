package Utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.properties";

    public static String credentialReader(String credentialVariable) {
        String credentialValue = null;
        try (FileReader reader = new FileReader(CREDENTIALS_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(reader);

            credentialValue = properties.getProperty(credentialVariable);

        } catch (IOException ioException) {
            ioException.getStackTrace();
        }
        return credentialValue;
    }
}
