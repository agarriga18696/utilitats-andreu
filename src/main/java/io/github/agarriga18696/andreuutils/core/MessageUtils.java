package io.github.agarriga18696.andreuutils.core;

/**
 * Utility class for displaying console messages.
 *
 * @author Andreu
 * @version 1.1
 */
public final class MessageUtils {

    private static final String LINE_BREAK = System.lineSeparator();
    private static final int DEFAULT_SEPARATOR_LENGTH = 50;

    private MessageUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // STANDARD MESSAGES
    //-------------------------------

    /**
     * Prints a message to the console followed by a line break.
     *
     * @param message Message to print.
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message to the console without adding a line break.
     *
     * @param message Message to print.
     */
    public static void printInline(String message) {
        System.out.print(message);
    }

    /**
     * Prints an error message.
     *
     * @param message Error message to print.
     */
    public static void error(String message) {
        System.out.println(
                LINE_BREAK
                        + LanguageManager.text("message.error_prefix")
                        + ": "
                        + message
                        + LINE_BREAK
        );
    }

    /**
     * Prints a success message.
     *
     * @param message Success message to print.
     */
    public static void success(String message) {
        System.out.println(
                LINE_BREAK
                        + LanguageManager.text("message.success_prefix")
                        + ": "
                        + message
                        + LINE_BREAK
        );
    }

    /**
     * Prints a warning message.
     *
     * @param message Warning message to print.
     */
    public static void warning(String message) {
        System.out.println(
                LINE_BREAK
                        + LanguageManager.text("message.warning_prefix")
                        + ": "
                        + message
                        + LINE_BREAK
        );
    }

    /**
     * Prints an informational message.
     *
     * @param message Information message to print.
     */
    public static void info(String message) {
        System.out.println(
                LINE_BREAK
                        + LanguageManager.text("message.info_prefix")
                        + ": "
                        + message
                        + LINE_BREAK
        );
    }

    //-------------------------------
    // TITLES AND SEPARATORS
    //-------------------------------

    /**
     * Prints a section title between large separators.
     *
     * <pre>
     * ==================================================
     *  TITLE
     * ==================================================
     * </pre>
     *
     * @param text Title to print.
     */
    public static void title(String text) {
        newLine();
        largeSeparator();
        System.out.println(" " + text.toUpperCase().strip());
        largeSeparator();
    }

    /**
     * Prints a section subtitle.
     *
     * <pre>
     * --- Subtitle ---
     * </pre>
     *
     * @param text Subtitle to print.
     */
    public static void subtitle(String text) {
        System.out.println(LINE_BREAK + "--- " + text + " ---");
    }

    /**
     * Prints a separator using hyphens.
     */
    public static void separator() {
        System.out.println("-".repeat(DEFAULT_SEPARATOR_LENGTH));
    }

    /**
     * Prints a separator using the specified character or string.
     *
     * @param character Character or string to repeat.
     */
    public static void separator(String character) {
        System.out.println(character.repeat(DEFAULT_SEPARATOR_LENGTH));
    }

    /**
     * Prints a separator using the specified character or string
     * repeated the specified number of times.
     *
     * @param character   Character or string to repeat.
     * @param repetitions Number of repetitions.
     */
    public static void separator(String character, int repetitions) {
        System.out.println(character.repeat(repetitions));
    }

    /**
     * Prints a large separator using equals signs.
     */
    public static void largeSeparator() {
        System.out.println("=".repeat(DEFAULT_SEPARATOR_LENGTH));
    }

    /**
     * Prints an empty line.
     */
    public static void newLine() {
        System.out.println();
    }

    //-------------------------------
    // COMMON PROGRAM MESSAGES
    //-------------------------------

    /**
     * Prints the standard end-of-program message.
     */
    public static void endProgram() {
        System.out.println(
                LINE_BREAK
                        + LanguageManager.text("message.program_finished")
        );
    }

    /**
     * Prints the standard invalid-option message.
     */
    public static void invalidOption() {
        error(
                LanguageManager.text("message.invalid_option")
        );
    }

    /**
     * Prints the standard empty-list message.
     */
    public static void emptyList() {
        warning(
                LanguageManager.text("message.empty_list")
        );
    }

    /**
     * Prints the standard element-not-found message.
     */
    public static void elementNotFound() {
        warning(
                LanguageManager.text("message.element_not_found")
        );
    }

    /**
     * Prints the standard element-added message.
     */
    public static void elementAdded() {
        success(
                LanguageManager.text("message.element_added")
        );
    }

    /**
     * Prints the standard element-removed message.
     */
    public static void elementRemoved() {
        success(
                LanguageManager.text("message.element_removed")
        );
    }

    /**
     * Prints the standard element-updated message.
     */
    public static void elementUpdated() {
        success(
                LanguageManager.text("message.element_updated")
        );
    }

    /**
     * Prints the standard data-saved message.
     */
    public static void dataSaved() {
        success(
                LanguageManager.text("message.data_saved")
        );
    }

    /**
     * Prints the standard data-loaded message.
     */
    public static void dataLoaded() {
        success(
                LanguageManager.text("message.data_loaded")
        );
    }

}