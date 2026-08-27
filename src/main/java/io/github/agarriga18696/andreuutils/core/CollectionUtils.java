package io.github.agarriga18696.andreuutils.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Utility class for working with collections.
 *
 * @author Andreu
 * @version 2.0
 */
public final class CollectionUtils {

    private CollectionUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // OPERATIONS
    //-------------------------------

    /**
     * Returns the sum of all numeric elements in a collection.
     *
     * @param collection Collection of numbers.
     * @return The sum of all elements.
     */
    public static double sum(Collection<? extends Number> collection) {
        double total = 0;

        for (Number number : collection) {
            total += number.doubleValue();
        }

        return total;
    }

    /**
     * Generates a list of random integers between {@code min} and {@code max}, inclusive.
     *
     * @param count Number of elements to generate.
     * @param min   Minimum value, inclusive.
     * @param max   Maximum value, inclusive.
     * @return A list of random integers.
     */
    public static List<Integer> randomIntegers(int count, int min, int max) {
        List<Integer> collection = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            collection.add(RandomUtils.integer(min, max));
        }

        return collection;
    }

    /**
     * Generates a list of random decimal numbers between {@code min} and {@code max}, inclusive.
     *
     * @param count Number of elements to generate.
     * @param min   Minimum value, inclusive.
     * @param max   Maximum value, inclusive.
     * @return A list of random decimal numbers.
     */
    public static List<Double> randomDoubles(int count, double min, double max) {
        List<Double> collection = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            collection.add(RandomUtils.decimal(min, max));
        }

        return collection;
    }

    //-------------------------------
    // STATISTICS
    //-------------------------------

    /**
     * Returns the maximum element in a collection according to the given comparator.
     *
     * @param collection Collection of elements.
     * @param comparator Comparison criterion.
     * @return The maximum element.
     * @throws java.util.NoSuchElementException if the collection is empty.
     */
    public static <T> T max(Collection<T> collection, Comparator<T> comparator) {
        return collection.stream()
                .max(comparator)
                .orElseThrow();
    }

    /**
     * Returns the minimum element in a collection according to the given comparator.
     *
     * @param collection Collection of elements.
     * @param comparator Comparison criterion.
     * @return The minimum element.
     * @throws java.util.NoSuchElementException if the collection is empty.
     */
    public static <T> T min(Collection<T> collection, Comparator<T> comparator) {
        return collection.stream()
                .min(comparator)
                .orElseThrow();
    }

    /**
     * Returns the average value obtained by applying the given mapper
     * to each element in a collection.
     *
     * @param collection  Collection of elements.
     * @param valueMapper Function used to obtain a numeric value from each element.
     * @return The average value, or {@code 0} if the collection is empty.
     */
    public static <T> double average(
            Collection<T> collection,
            ToDoubleFunction<T> valueMapper) {

        return collection.stream()
                .mapToDouble(valueMapper)
                .average()
                .orElse(0);
    }

    //-------------------------------
    // MANIPULATION
    //-------------------------------

    /**
     * Returns a new list with all occurrences of the specified element removed.
     * The original collection is not modified.
     *
     * @param collection Original collection.
     * @param element    Element to remove.
     * @return A new list without occurrences of the specified element.
     */
    public static <T> List<T> removeAllOccurrences(
            Collection<T> collection,
            T element) {

        List<T> result = new ArrayList<>(collection);
        result.removeAll(Collections.singleton(element));

        return result;
    }

    /**
     * Returns a new list containing all elements from both collections.
     * The elements from the first collection appear before those from the second.
     *
     * @param first  First collection.
     * @param second Second collection.
     * @return A new list containing all elements from both collections.
     */
    public static <T> List<T> concat(
            Collection<T> first,
            Collection<T> second) {

        List<T> result = new ArrayList<>(first);
        result.addAll(second);

        return result;
    }

    /**
     * Returns a new list containing the elements from the first collection
     * that are also present in the second collection.
     * The original collections are not modified.
     *
     * @param first  First collection.
     * @param second Second collection.
     * @return A new list containing the common elements.
     */
    public static <T> List<T> commonElements(
            Collection<T> first,
            Collection<T> second) {

        List<T> result = new ArrayList<>(first);
        result.retainAll(second);

        return result;
    }

    /**
     * Returns a new list containing the elements from the first collection
     * that are not present in the second collection.
     * The original collections are not modified.
     *
     * @param first  Original collection.
     * @param second Collection containing the elements to remove.
     * @return A new list containing the difference between both collections.
     */
    public static <T> List<T> difference(
            Collection<T> first,
            Collection<T> second) {

        List<T> result = new ArrayList<>(first);
        result.removeAll(second);

        return result;
    }

    //-------------------------------
    // FILTERING AND SEARCH
    //-------------------------------

    /**
     * Returns a new list containing the elements that match the given predicate.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition that elements must satisfy.
     * @return A new list containing the matching elements.
     */
    public static <T> List<T> filter(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * Returns the first element that matches the given predicate,
     * or {@code null} if no element matches.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition that the element must satisfy.
     * @return The first matching element, or {@code null} if none is found.
     */
    public static <T> T findFirst(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the number of elements that match the given predicate.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition that elements must satisfy.
     * @return The number of matching elements.
     */
    public static <T> long count(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .filter(predicate)
                .count();
    }

    //-------------------------------
    // CHECKS
    //-------------------------------

    /**
     * Returns {@code true} if any element matches the given predicate.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition to test.
     * @return {@code true} if any element matches the predicate.
     */
    public static <T> boolean anyMatch(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .anyMatch(predicate);
    }

    /**
     * Returns {@code true} if all elements match the given predicate.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition to test.
     * @return {@code true} if all elements match the predicate.
     */
    public static <T> boolean allMatch(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .allMatch(predicate);
    }

    /**
     * Returns {@code true} if no element matches the given predicate.
     *
     * @param collection Collection of elements.
     * @param predicate  Condition to test.
     * @return {@code true} if no element matches the predicate.
     */
    public static <T> boolean noneMatch(
            Collection<T> collection,
            Predicate<T> predicate) {

        return collection.stream()
                .noneMatch(predicate);
    }

    //-------------------------------
    // TRANSFORMATIONS
    //-------------------------------

    /**
     * Returns a new list containing the elements transformed
     * by the given mapper function.
     *
     * @param collection Collection of elements.
     * @param mapper     Transformation function.
     * @return A new list containing the transformed elements.
     */
    public static <T, V> List<V> map(
            Collection<T> collection,
            Function<T, V> mapper) {

        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}