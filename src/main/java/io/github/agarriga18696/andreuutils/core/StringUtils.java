package io.github.agarriga18696.andreuutils.core;

import java.text.Normalizer;

/**
 * Utility class for working with strings.
 *
 * @author Andreu
 * @version 1.2
 */
public final class StringUtils {

    private StringUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // TRANSFORMATIONS
    //-------------------------------

    /**
     * Reverses a string.
     *
     * @param text Text to reverse.
     * @return The reversed string, or {@code null} if the input is {@code null}.
     */
    public static String reverse(String text) {
        if (text == null) return null;
        return new StringBuilder(text).reverse().toString();
    }

    /**
     * Removes accents and diacritical marks from a string.
     *
     * @param text Text to normalize.
     * @return The text without accents or diacritical marks,
     * or {@code null} if the input is {@code null}.
     */
    public static String removeAccents(String text) {
        if (text == null) return null;
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{Mn}", "");
    }

    /**
     * Removes all whitespace from a string.
     *
     * @param text Text to process.
     * @return The text without whitespace,
     * or {@code null} if the input is {@code null}.
     */
    public static String removeWhitespace(String text) {
        if (text == null) return null;
        return text.replaceAll("\\s+", "");
    }

    //-------------------------------
    // VALIDATION
    //-------------------------------

    /**
     * Checks whether a string is {@code null}, empty, or contains only whitespace.
     *
     * @param value String to check.
     * @return {@code true} if the string is {@code null} or blank.
     */
    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    /**
     * Checks whether a string has the specified length.
     *
     * @param value  String to check.
     * @param length Required length.
     * @return {@code true} if the string has the specified length.
     */
    public static boolean hasLength(String value, int length) {
        return value != null && value.length() == length;
    }

    /**
     * Checks whether a string length is within the specified inclusive range.
     *
     * @param value String to check.
     * @param min   Minimum length, inclusive.
     * @param max   Maximum length, inclusive.
     * @return {@code true} if the string length is within the specified range.
     */
    public static boolean hasLengthBetween(String value, int min, int max) {
        if (value == null) return false;
        return value.length() >= min && value.length() <= max;
    }

    /**
     * Checks whether a string contains only letters.
     *
     * @param value String to check.
     * @return {@code true} if the string contains only Unicode letters.
     */
    public static boolean isLettersOnly(String value) {
        if (isNullOrBlank(value)) return false;
        return value.matches("[\\p{L}]+"); // Matches any Unicode letter.
    }

    /**
     * Checks whether a string contains only letters and spaces.
     *
     * @param value String to check.
     * @return {@code true} if the string contains only Unicode letters and spaces.
     */
    public static boolean isLettersAndSpacesOnly(String value) {
        if (isNullOrBlank(value)) return false;
        return value.matches("[\\p{L} ]+"); // Matches Unicode letters and spaces.
    }

    /**
     * Checks whether a string contains letters and digits,
     * optionally including hyphens or underscores.
     * <p>
     * At least one letter and one digit are required.
     *
     * @param value String to check.
     * @return {@code true} if the string matches the required format.
     */
    public static boolean isAlphanumericWithSeparators(String value) {
        if (isNullOrBlank(value)) return false;
        return value.matches("^(?=.*[\\p{L}])(?=.*[0-9])[\\p{L}0-9_-]+$");
    }

    /**
     * Checks whether a string is a palindrome.
     * <p>
     * Letter case, accents, and whitespace are ignored.
     *
     * @param text Text to check.
     * @return {@code true} if the text is a palindrome.
     */
    public static boolean isPalindrome(String text) {
        if (text == null) return false;

        String normalized = removeAccents(removeWhitespace(text)).toLowerCase();

        return normalized.equals(reverse(normalized));
    }

    /**
     * Checks whether a string starts with the specified prefix,
     * ignoring letter case.
     *
     * @param text   Text to check.
     * @param prefix Prefix to search for.
     * @return {@code true} if the text starts with the specified prefix.
     */
    public static boolean startsWithIgnoreCase(String text, String prefix) {
        if (text == null || prefix == null) return false;
        return text.toLowerCase().startsWith(prefix.toLowerCase());
    }

    /**
     * Checks whether a string ends with the specified suffix,
     * ignoring letter case.
     *
     * @param text   Text to check.
     * @param suffix Suffix to search for.
     * @return {@code true} if the text ends with the specified suffix.
     */
    public static boolean endsWithIgnoreCase(String text, String suffix) {
        if (text == null || suffix == null) return false;
        return text.toLowerCase().endsWith(suffix.toLowerCase());
    }

}