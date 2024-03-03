package lh.juicecompany.PageUtilities;

import java.util.Arrays;
import java.util.Random;

/**
 * A utility class for operations related to integers. It includes methods for generating random numbers
 * under various constraints and conditions. This class is designed to facilitate common numerical tasks
 * that require randomness or specific integer manipulations, enhancing code reuse and clarity.
 */
public class IntegerUtilities {

    static Random random = new Random();

    private IntegerUtilities() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a random number within a specified range, excluding specified values.
     *
     * @param min     The minimum bound of the range (inclusive).
     * @param max     The maximum bound of the range (inclusive).
     * @param exclude An array of integers to be excluded from the generated result.
     * @return A random integer between min and max (inclusive), excluding specified values.
     */
    public static int getRandomNumberWithExclusion(int min, int max, int[] exclude) {
        Arrays.sort(exclude);
        int randomNumber = min + random.nextInt(max - min + 1 - exclude.length);
        for (int ex : exclude) {
            if (randomNumber < ex) {
                break;
            }
            randomNumber++;
        }
        return randomNumber;
    }

    /**
     * Generates a random number within a specified range (exclusive).
     *
     * @param min The minimum bound of the range (exclusive).
     * @param max The maximum bound of the range (exclusive).
     * @return A random integer between min and max, excluding both.
     */
    public static int getRandomExclusive(int min, int max) {
        return random.nextInt(max - min - 1) + min + 1;
    }

    /**
     * Generates a random number within a specified range (inclusive).
     *
     * @param min The minimum bound of the range (inclusive).
     * @param max The maximum bound of the range (inclusive).
     * @return A random integer between min and max, including both.
     */
    public static int getRandomInclusive(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}