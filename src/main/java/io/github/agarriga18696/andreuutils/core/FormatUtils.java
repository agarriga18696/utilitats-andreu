package io.github.agarriga18696.andreuutils.core;

import java.text.NumberFormat;

/**
 * Utility class for formatting and transforming text and numbers.
 *
 * @author Andreu
 * @version 1.1
 */
public final class FormatUtils {

    private FormatUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // NUMBERS
    //-------------------------------

    /**
     * Formats a decimal number using the specified number of decimal places.
     *
     * @param value    Value to format.
     * @param decimals Number of decimal places.
     * @return The formatted decimal value.
     */
    public static String formatDecimal(double value, int decimals) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(false);
        formatter.setMinimumFractionDigits(decimals);
        formatter.setMaximumFractionDigits(decimals);
        return formatter.format(value);
    }

    //-------------------------------
    // STRINGS
    //-------------------------------

    /**
     * Converts a string to uppercase.
     *
     * @param text Text to convert.
     * @return The uppercase string, or {@code null} if the input is {@code null}.
     */
    public static String toUpperCase(String text) {
        if (text == null) return null;
        return text.toUpperCase();
    }

    /**
     * Converts a string to lowercase.
     *
     * @param text Text to convert.
     * @return The lowercase string, or {@code null} if the input is {@code null}.
     */
    public static String toLowerCase(String text) {
        if (text == null) return null;
        return text.toLowerCase();
    }

    /**
     * Capitalizes a string by converting its first character to uppercase
     * and the remaining characters to lowercase.
     *
     * @param text Text to capitalize.
     * @return The capitalized string.
     */
    public static String capitalize(String text) {
        if (StringUtils.isNullOrBlank(text)) return text;

        return text.substring(0, 1).toUpperCase()
                + text.substring(1).toLowerCase();
    }

    /**
     * Capitalizes every word in a string.
     *
     * @param text Text to capitalize.
     * @return The string with every word capitalized.
     */
    public static String capitalizeWords(String text) {
        if (StringUtils.isNullOrBlank(text)) return text;

        String[] words = text.strip().split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(capitalize(word)).append(" ");
        }

        return builder.toString().strip();
    }

}