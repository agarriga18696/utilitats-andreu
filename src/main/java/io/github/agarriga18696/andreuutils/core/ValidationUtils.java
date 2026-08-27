package io.github.agarriga18696.andreuutils.core;

import java.util.regex.Pattern;

/**
 * Utility class for validating data.
 * All methods return a boolean value.
 *
 * @author Andreu
 * @version 1.2
 */
public final class ValidationUtils {

    private ValidationUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // NUMBERS
    //-------------------------------

    /**
     * Checks whether a string represents a valid integer.
     *
     * @param value String to validate.
     * @return {@code true} if the string represents a valid integer.
     */
    public static boolean isInteger(String value) {
        if (StringUtils.isNullOrBlank(value)) return false;

        try {
            Integer.parseInt(value.strip());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether a string represents a valid decimal number.
     * Both commas and periods are accepted as decimal separators.
     *
     * @param value String to validate.
     * @return {@code true} if the string represents a valid decimal number.
     */
    public static boolean isDecimal(String value) {
        if (StringUtils.isNullOrBlank(value)) return false;

        try {
            Double.parseDouble(value.strip().replace(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether an integer is greater than zero.
     *
     * @param value Value to validate.
     * @return {@code true} if the value is greater than zero.
     */
    public static boolean isPositive(int value) {
        return value > 0;
    }

    /**
     * Checks whether a decimal number is greater than zero.
     *
     * @param value Value to validate.
     * @return {@code true} if the value is greater than zero.
     */
    public static boolean isPositive(double value) {
        return value > 0;
    }

    /**
     * Checks whether an integer is within the specified inclusive range.
     *
     * @param value Value to validate.
     * @param min   Minimum value, inclusive.
     * @param max   Maximum value, inclusive.
     * @return {@code true} if the value is within the specified range.
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Checks whether a decimal number is within the specified inclusive range.
     *
     * @param value Value to validate.
     * @param min   Minimum value, inclusive.
     * @param max   Maximum value, inclusive.
     * @return {@code true} if the value is within the specified range.
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    //-------------------------------
    // EMAIL
    //-------------------------------

    /*
     * Pattern for validating email addresses.
     *
     * Local part:
     * letters, digits, underscores, periods, percent signs,
     * plus signs and hyphens.
     *
     * Domain:
     * letters, digits, periods and hyphens.
     *
     * Top-level domain:
     * a period followed by at least two letters.
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

    /**
     * Checks whether a string represents a valid email address.
     *
     * @param value String to validate.
     * @return {@code true} if the string matches the supported email format.
     */
    public static boolean isEmail(String value) {
        if (StringUtils.isNullOrBlank(value)) return false;
        return EMAIL_PATTERN.matcher(value.strip()).matches();
    }

    //-------------------------------
    // PHONE
    //-------------------------------

    /**
     * Checks whether a string represents a valid 9-digit Spanish phone number.
     * Valid numbers must begin with 6, 7, or 9.
     *
     * @param value String to validate.
     * @return {@code true} if the string matches the supported Spanish phone format.
     */
    public static boolean isSpanishPhoneNumber(String value) {
        if (StringUtils.isNullOrBlank(value)) return false;

        // Nine digits in total:
        // - Starts with 6, 7, or 9.
        // - Followed by eight digits from 0 to 9.
        return value.strip().matches("[679]\\d{8}");
    }

    //-------------------------------
    // DNI
    //-------------------------------

    private static final String DNI_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     * Checks whether a string represents a valid Spanish DNI,
     * including both its format and control letter.
     */
    public static boolean isSpanishDni(String value) {
        if (StringUtils.isNullOrBlank(value)) return false;

        String dni = value.strip().toUpperCase();

        if (!dni.matches("\\d{8}[A-Z]")) return false;

        int number = Integer.parseInt(dni.substring(0, 8));
        char expectedLetter = DNI_LETTERS.charAt(number % 23);

        return dni.charAt(8) == expectedLetter;
    }

}