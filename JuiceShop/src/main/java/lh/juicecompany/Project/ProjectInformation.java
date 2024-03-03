package lh.juicecompany.Project;

/**
 * Contains constants for key project information, such as application name, URL, and author.
 * This class serves as a central repository for such data, ensuring consistency and ease of update.
 */
public class ProjectInformation {

    /**
     * The name of the application.
     */
    public static final String APPLICATION_NAME = "JuiceShop";

    /**
     * The base URL of the JuiceShop application.
     */
    public static final String JUICE_SHOP_URL = "http://localhost:3000/#/";

    /**
     * The author of the project.
     */
    public static final String AUTHOR = "Arah625";

    /**
     * Private constructor to prevent instantiation.
     * This class is designed to be a utility class containing constants that should be accessed statically.
     */
    private ProjectInformation() {
    }
}