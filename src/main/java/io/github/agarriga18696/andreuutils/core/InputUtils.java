package io.github.agarriga18696.andreuutils.core;

import java.util.Scanner;

/**
 * Utility class for reading and validating user input from the console.
 *
 * @author Andreu
 * @version 1.1
 */
public final class InputUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // INTEGERS
    //-------------------------------

    /**
     * Reads an integer from the user.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered integer.
     */
    public static int readInt(String prompt) {
        while (true) {
            printPrompt(prompt);
            String line = SCANNER.nextLine().strip();

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                MessageUtils.error(
                        LanguageManager.text("input.invalid_integer")
                );
            }
        }
    }

    /**
     * Reads an integer within the specified range.
     *
     * @param prompt Prompt displayed before reading the input.
     * @param min    Minimum accepted value.
     * @param max    Maximum accepted value.
     * @return The entered integer.
     */
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);

            if (value >= min && value <= max) {
                return value;
            }

            MessageUtils.error(
                    LanguageManager.text(
                            "input.value_out_of_range",
                            min,
                            max
                    )
            );
        }
    }

    /**
     * Reads a positive integer greater than zero.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered positive integer.
     */
    public static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);

            if (value > 0) {
                return value;
            }

            MessageUtils.error(
                    LanguageManager.text("input.value_must_be_positive")
            );
        }
    }

    //-------------------------------
    // DECIMALS
    //-------------------------------

    /**
     * Reads a decimal number from the user.
     * Both commas and periods are accepted as decimal separators.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered decimal value.
     */
    public static double readDouble(String prompt) {
        while (true) {
            printPrompt(prompt);
            String line = SCANNER.nextLine().strip().replace(",", ".");

            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                MessageUtils.error(
                        LanguageManager.text("input.invalid_decimal")
                );
            }
        }
    }

    /**
     * Reads a decimal number within the specified range.
     *
     * @param prompt Prompt displayed before reading the input.
     * @param min    Minimum accepted value.
     * @param max    Maximum accepted value.
     * @return The entered decimal value.
     */
    public static double readDoubleInRange(
            String prompt,
            double min,
            double max
    ) {
        while (true) {
            double value = readDouble(prompt);

            if (value >= min && value <= max) {
                return value;
            }

            MessageUtils.error(
                    LanguageManager.text(
                            "input.value_out_of_range",
                            min,
                            max
                    )
            );
        }
    }

    /**
     * Reads a positive decimal number greater than zero.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered positive decimal value.
     */
    public static double readPositiveDouble(String prompt) {
        while (true) {
            double value = readDouble(prompt);

            if (value > 0) {
                return value;
            }

            MessageUtils.error(
                    LanguageManager.text("input.value_must_be_positive")
            );
        }
    }

    //-------------------------------
    // STRINGS
    //-------------------------------

    /**
     * Reads a non-blank string from the user.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered string.
     */
    public static String readString(String prompt) {
        while (true) {
            printPrompt(prompt);
            String line = SCANNER.nextLine().strip();

            if (!line.isBlank()) {
                return line;
            }

            MessageUtils.error(
                    LanguageManager.text("input.blank")
            );
        }
    }

    /**
     * Reads a string that may be blank.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered string.
     */
    public static String readOptionalString(String prompt) {
        printPrompt(prompt);
        return SCANNER.nextLine().strip();
    }

    /**
     * Reads a non-blank string with the specified minimum length.
     *
     * @param prompt    Prompt displayed before reading the input.
     * @param minLength Minimum accepted length.
     * @return The entered string.
     */
    public static String readStringWithMinLength(
            String prompt,
            int minLength
    ) {
        while (true) {
            String value = readString(prompt);

            if (value.length() >= minLength) {
                return value;
            }

            MessageUtils.error(
                    LanguageManager.text(
                            "input.min_length",
                            minLength
                    )
            );
        }
    }

    //-------------------------------
    // BOOLEANS AND CONFIRMATION
    //-------------------------------

    /**
     * Reads a yes/no confirmation from the user.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return {@code true} for yes, or {@code false} for no.
     */
    public static boolean confirm(String prompt) {
        while (true) {
            printPrompt(
                    LanguageManager.text(
                            "input.confirm_prompt",
                            prompt
                    )
            );

            String response = SCANNER.nextLine()
                    .strip()
                    .toLowerCase();

            String yes = LanguageManager.text("input.confirm_yes");
            String no = LanguageManager.text("input.confirm_no");

            if (response.equals(yes)) return true;
            if (response.equals(no)) return false;

            MessageUtils.error(
                    LanguageManager.text("input.confirm_invalid")
            );
        }
    }

    //-------------------------------
    // CHARACTERS
    //-------------------------------

    /**
     * Reads a single character from the user.
     *
     * @param prompt Prompt displayed before reading the input.
     * @return The entered character.
     */
    public static char readChar(String prompt) {
        while (true) {
            printPrompt(prompt);
            String line = SCANNER.nextLine().strip();

            if (line.length() == 1) {
                return line.charAt(0);
            }

            MessageUtils.error(
                    LanguageManager.text("input.invalid_character")
            );
        }
    }

    //-------------------------------
    // UTILITIES
    //-------------------------------

    /**
     * Closes the input scanner.
     * This method should be called when console input is no longer needed.
     */
    public static void closeInput() {
        SCANNER.close();
    }

    /**
     * Prints an input prompt if it is not null or blank.
     *
     * @param prompt Prompt to display.
     */
    private static void printPrompt(String prompt) {
        if (prompt != null && !prompt.isBlank()) {
            System.out.print(prompt);
        }
    }

    /**
     * Pauses execution until the user presses Enter.
     */
    public static void waitForEnter() {
        System.out.println();
        System.out.println(
                LanguageManager.text("input.press_enter")
        );
        SCANNER.nextLine();
    }

}