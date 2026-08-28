package io.github.agarriga18696.andreuutils.core;

import java.util.Random;

/**
 * Utility class for generating random values.
 *
 * @author Andreu
 * @version 1.0
 */
public final class RandomUtils {

	private static final Random RANDOM = new Random();

    private RandomUtils() {
        /* This utility class should not be instantiated */
    }

    // ----------------------------------------
    // PUBLIC METHODS
    // ----------------------------------------

    /**
     * Returns a random integer between the specified minimum and maximum values.
     *
     * @param min Minimum value.
     * @param max Maximum value.
     * @return Random integer between {@code min} and {@code max}.
     */
    public static int integer(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    /**
     * Returns a random decimal value between the specified minimum and maximum values.
     *
     * @param min Minimum value.
     * @param max Maximum value.
     * @return Random decimal value between {@code min} and {@code max}.
     */
    public static double decimal(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    /**
     * Returns a random boolean value.
     *
     * @return Random boolean value.
     */
    public static boolean booleanValue() {
        return RANDOM.nextBoolean();
    }

}
