package Setup;

/**
 * Defines constants for categorizing test groups and priorities.
 * This class contains predefined constants used to categorize tests by their purpose
 * such as regression testing, or by their priority levels ranging from low to critical.
 * Additionally, it includes a category for prerequisite tests that must be run before others.
 */
public class TestGroup {

    /**
     * Constant for tests categorized under regression testing.
     */
    public static final String REGRESSION = "regression";

    /**
     * Constant for tests categorized as having low priority.
     */
    public static final String LOW = "low";

    /**
     * Constant for tests categorized as having medium priority.
     */
    public static final String MEDIUM = "medium";

    /**
     * Constant for tests categorized as having high priority.
     */
    public static final String HIGH = "high";

    /**
     * Constant for tests categorized as having critical priority.
     */
    public static final String CRITICAL = "critical";

    /**
     * Constant for tests that are prerequisites for other tests.
     */
    public static final String PREREQUISITE = "prerequisite";
}