package io.github.agarriga18696.andreuutils.core;

/**
 * Utility class for type conversions.
 *
 * @author Andreu
 * @version 1.1
 */
public final class ConversionUtils {

    private ConversionUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // NUMBERS
    //-------------------------------

    /**
     * Parses a string as an integer.
     *
     * @param value String to parse.
     * @return The parsed integer, or {@code null} if the value is blank
     * or cannot be parsed.
     */
    public static Integer parseInteger(String value) {
        if (StringUtils.isNullOrBlank(value)) return null;

        try {
            return Integer.parseInt(value.strip());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses a string as a decimal number.
     * Both commas and periods are accepted as decimal separators.
     *
     * @param value String to parse.
     * @return The parsed decimal value, or {@code null} if the value is blank
     * or cannot be parsed.
     */
    public static Double parseDouble(String value) {
        if (StringUtils.isNullOrBlank(value)) return null;

        try {
            return Double.parseDouble(value.strip().replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //-------------------------------
    // STRINGS
    //-------------------------------

    /**
     * Converts a value to its string representation.
     *
     * @param value Value to convert.
     * @return The string representation of the value,
     * or {@code null} if the value is {@code null}.
     */
    public static String toString(Object value) {
        if (value == null) return null;
        return value.toString();
    }

    /**
     * Converts a boolean value to {@code "Yes"} or {@code "No"}.
     *
     * @param value Boolean value to convert.
     * @return {@code "Yes"} if {@code true}, otherwise {@code "No"}.
     */
    public static String toYesNo(boolean value) {
        return LanguageManager.text(
                value ? "conversion.yes" : "conversion.no"
        );
    }

}