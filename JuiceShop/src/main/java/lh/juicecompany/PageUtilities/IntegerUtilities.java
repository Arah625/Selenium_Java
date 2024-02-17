package lh.juicecompany.PageUtilities;

import java.util.Arrays;
import java.util.Random;

public class IntegerUtilities {

    static Random random = new Random();

    private IntegerUtilities() {
    }

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

    public static int getRandomExclusive(int min, int max) {
        return random.nextInt(max - min - 1) + min + 1;
    }

    public static int getRandomInclusive(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
