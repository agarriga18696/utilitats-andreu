package io.github.agarriga18696.andreuutils.core;

import java.util.Collection;

/**
 * Utility class for displaying structured data in the console.
 *
 * @author Andreu
 * @version 2.1
 */
public final class ConsoleUtils {

    private ConsoleUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // LISTS
    //-------------------------------

    /**
     * Prints an array of generic objects as a bulleted list using hyphens.
     *
     * @param elements Elements to print.
     * @param <T>      Type of the elements.
     */
    public static <T> void printList(T[] elements) {
        for (T element : elements) {
            System.out.println("- " + element);
        }
    }

    /**
     * Prints a collection of generic objects as a bulleted list using hyphens.
     *
     * @param elements Elements to print.
     * @param <T>      Type of the elements.
     */
    public static <T> void printList(Collection<T> elements) {
        for (T element : elements) {
            System.out.println("- " + element);
        }
    }

    /**
     * Prints an array of integers as a bulleted list using hyphens.
     *
     * @param elements Elements to print.
     */
    public static void printList(int[] elements) {
        for (int element : elements) {
            System.out.println("- " + element);
        }
    }

    /**
     * Prints an array of decimal numbers as a bulleted list using hyphens.
     *
     * @param elements Elements to print.
     */
    public static void printList(double[] elements) {
        for (double element : elements) {
            System.out.println("- " + element);
        }
    }

    /**
     * Prints an array of generic objects as a list using the specified symbol.
     *
     * @param elements Elements to print.
     * @param symbol   Symbol to place before each element.
     * @param <T>      Type of the elements.
     */
    public static <T> void printList(T[] elements, String symbol) {
        for (T element : elements) {
            System.out.println(symbol + " " + element);
        }
    }

    /**
     * Prints a collection of generic objects as a list using the specified symbol.
     *
     * @param elements Elements to print.
     * @param symbol   Symbol to place before each element.
     * @param <T>      Type of the elements.
     */
    public static <T> void printList(Collection<T> elements, String symbol) {
        for (T element : elements) {
            System.out.println(symbol + " " + element);
        }
    }

    /**
     * Prints an array of integers as a list using the specified symbol.
     *
     * @param elements Elements to print.
     * @param symbol   Symbol to place before each element.
     */
    public static void printList(int[] elements, String symbol) {
        for (int element : elements) {
            System.out.println(symbol + " " + element);
        }
    }

    /**
     * Prints an array of decimal numbers as a list using the specified symbol.
     *
     * @param elements Elements to print.
     * @param symbol   Symbol to place before each element.
     */
    public static void printList(double[] elements, String symbol) {
        for (double element : elements) {
            System.out.println(symbol + " " + element);
        }
    }

    /**
     * Prints an array of generic objects as a numbered list starting at 1.
     *
     * @param elements Elements to print.
     * @param <T>      Type of the elements.
     */
    public static <T> void printNumberedList(T[] elements) {
        for (int index = 0; index < elements.length; index++) {
            System.out.println((index + 1) + ". " + elements[index]);
        }
    }

    /**
     * Prints a collection of generic objects as a numbered list starting at 1.
     *
     * @param elements Elements to print.
     * @param <T>      Type of the elements.
     */
    public static <T> void printNumberedList(Collection<T> elements) {
        int index = 1;

        for (T element : elements) {
            System.out.println(index++ + ". " + element);
        }
    }

    /**
     * Prints an array of integers as a numbered list starting at 1.
     *
     * @param elements Elements to print.
     */
    public static void printNumberedList(int[] elements) {
        for (int index = 0; index < elements.length; index++) {
            System.out.println((index + 1) + ". " + elements[index]);
        }
    }

    /**
     * Prints an array of decimal numbers as a numbered list starting at 1.
     *
     * @param elements Elements to print.
     */
    public static void printNumberedList(double[] elements) {
        for (int index = 0; index < elements.length; index++) {
            System.out.println((index + 1) + ". " + elements[index]);
        }
    }

}