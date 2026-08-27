package io.github.agarriga18696.andreuutils.core;

/**
 * Utility class for mathematical operations.
 *
 * @author Andreu
 * @version 1.1
 */
public final class MathUtils {

    private MathUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // BASIC OPERATIONS
    //-------------------------------

    /**
     * Checks whether a number is even.
     *
     * @param number Number to check.
     * @return {@code true} if the number is even.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Checks whether a number is a multiple of another number.
     *
     * @param number  Number to check.
     * @param divisor Divisor.
     * @return {@code true} if {@code number} is a multiple of {@code divisor}.
     */
    public static boolean isMultipleOf(int number, int divisor) {
        return number % divisor == 0;
    }

    /**
     * Checks whether a number is prime.
     *
     * @param number Number to check.
     * @return {@code true} if the number is prime.
     */
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        for (int divisor = 3; divisor <= Math.sqrt(number); divisor += 2) {
            if (number % divisor == 0) return false;
        }

        return true;
    }

    //-------------------------------
    // BASE CONVERSIONS
    //-------------------------------

    /**
     * Converts an integer to its binary string representation.
     *
     * @param number Number to convert.
     * @return The binary representation of the number.
     */
    public static String toBinary(int number) {
        return Integer.toBinaryString(number);
    }

    /**
     * Converts an integer to its hexadecimal string representation.
     *
     * @param number Number to convert.
     * @return The hexadecimal representation of the number.
     */
    public static String toHexadecimal(int number) {
        return Integer.toHexString(number);
    }

    /**
     * Converts a binary string to a decimal integer.
     *
     * @param binary Binary string to convert.
     * @return The decimal value.
     */
    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

}