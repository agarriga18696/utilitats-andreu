package io.github.agarriga18696.andreuutils.core;

import java.util.Random;

/**
 * Utility class for generating random values.
 *
 * @author Andreu
 * @version 1.0
 */
public final class RandomUtils {
	private RandomUtils() {
		/* This utility class should not be instantiated */
	}

	private static final Random RANDOM = new Random();

	/**
	 * Returns a random integer between {@code min} and {@code max}, inclusive.
	 */
	public static int integer(int min, int max) {
		return RANDOM.nextInt(max - min + 1) + min;
	}

	/**
	 * Returns a random decimal number between {@code min} and {@code max}, inclusive.
	 */
	public static double decimal(double min, double max) {
		return min + (max - min) * RANDOM.nextDouble();
	}

	/**
	 * Returns a random boolean value.
	 */
	public static boolean booleanValue() {
		return RANDOM.nextBoolean();
	}

}
