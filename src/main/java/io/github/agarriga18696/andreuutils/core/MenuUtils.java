package io.github.agarriga18696.andreuutils.core;

/**
 * Utility class for creating and displaying console menus.
 * Supports main menus, submenus and menu option handling.
 *
 * @author Andreu
 * @version 2.1
 */
public final class MenuUtils {

    private MenuUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // MAIN METHODS
    //-------------------------------

    /**
     * Displays a complete menu and returns the selected option.
     * Options are numbered starting at 1.
     * The last option is expected to be the exit option.
     *
     * @param title   Menu title.
     * @param options Menu options.
     * @return The selected option.
     */
    public static int show(String title, String... options) {
        printMenu(title, options);
        return InputUtils.readIntInRange(
                "Select an option: ",
                1,
                options.length
        );
    }

    /**
     * Displays a complete menu with the exit option using
     * the specified option number.
     *
     * @param title      Menu title.
     * @param exitOption Number assigned to the exit option.
     * @param options    Menu options, where the last one is the exit option.
     * @return The selected option.
     */
    public static int show(String title, int exitOption, String... options) {
        MessageUtils.title(title);
        printOptionsWithExit(options, exitOption);
        MessageUtils.newLine();

        return InputUtils.readIntInRange(
                "Select an option: ",
                0,
                options.length - 1
        );
    }

    /**
     * Displays the menu options with the last option assigned
     * to the specified exit option number.
     *
     * @param options    Menu options, where the last one is the exit option.
     * @param exitOption Number assigned to the exit option.
     */
    private static void printOptionsWithExit(String[] options, int exitOption) {
        for (int index = 0; index < options.length - 1; index++) {
            System.out.println((index + 1) + ". " + options[index]);
        }

        System.out.println(
                exitOption + ". " + options[options.length - 1]
        );
    }

    /**
     * Displays a complete menu without validating the selected option range.
     * This allows the caller to handle option validation manually.
     *
     * @param title   Menu title.
     * @param options Menu options.
     * @return The selected option.
     */
    public static int showWithoutRangeValidation(
            String title,
            String... options
    ) {
        printMenu(title, options);
        return InputUtils.readInt("Select an option: ");
    }

    //-------------------------------
    // MENU CONSTRUCTION
    //-------------------------------

    /**
     * Prints a complete menu to the console.
     *
     * @param title   Menu title.
     * @param options Menu options.
     */
    public static void printMenu(String title, String... options) {
        MessageUtils.title(title);
        printOptions(options);
        MessageUtils.newLine();
    }

    /**
     * Prints menu options numbered starting at 1.
     *
     * @param options Menu options.
     */
    private static void printOptions(String... options) {
        for (int index = 0; index < options.length; index++) {
            System.out.println((index + 1) + ". " + options[index]);
        }
    }

    //-------------------------------
    // SUBMENUS
    //-------------------------------

    /**
     * Displays a submenu and returns the selected option.
     *
     * @param subtitle Submenu subtitle.
     * @param options  Submenu options.
     * @return The selected option.
     */
    public static int showSubmenu(String subtitle, String... options) {
        MessageUtils.subtitle(subtitle);
        printOptions(options);
        MessageUtils.newLine();

        return InputUtils.readIntInRange(
                "Select an option: ",
                1,
                options.length
        );
    }

    //-------------------------------
    // MENU UTILITIES
    //-------------------------------

    /**
     * Checks whether the selected option corresponds to the exit option.
     * The exit option is considered to be the last option in the menu.
     *
     * @param option       Selected option.
     * @param totalOptions Total number of menu options.
     * @return {@code true} if the selected option is the exit option.
     */
    public static boolean isExitOption(int option, int totalOptions) {
        return option == totalOptions;
    }

}