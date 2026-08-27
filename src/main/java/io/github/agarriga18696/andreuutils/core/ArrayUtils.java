package io.github.agarriga18696.andreuutils.core;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class for working with arrays.
 *
 * @author Andreu
 * @version 2.0
 */
public final class ArrayUtils {
	private ArrayUtils() {
		/* This utility class should not be instantiated */
	}

	//-------------------------------
	// OPERATIONS
	//-------------------------------

	/**
	 * Generates an array of random integers between {@code min} and {@code max}, inclusive.
	 *
	 * @param count Number of elements to generate.
	 * @param min Minimum value, inclusive.
	 * @param max Maximum value, inclusive.
	 * @return An array of random integers.
	 */
	public static int[] randomIntegers(int count, int min, int max) {
		int[] array = new int[count];
		for(int i = 0; i < count; i++) {
			array[i] = (RandomUtils.integer(min, max));
		}
		return array;
	}

	/**
	 * Generates an array of random decimal numbers between {@code min} and {@code max}, inclusive.
	 *
	 * @param count Number of elements to generate.
	 * @param min Minimum value, inclusive.
	 * @param max Maximum value, inclusive.
	 * @return An array of random decimal numbers.
	 */
	public static double[] randomDoubles(int count, double min, double max) {
		double[] array = new double[count];
		for(int i = 0; i < count; i++) {
			array[i] = (RandomUtils.decimal(min, max));
		}
		return array;
	}

	//-------------------------------
	// SEARCH
	//-------------------------------

	/**
	 * Searches for an integer in an array using a linear search.
	 *
	 * @return The index of the value, or {@code -1} if it is not found.
	 */
	public static int indexOf(int[] array, int value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == value) return i;
		}
		return -1;
	}

	/**
	 * Searches for a decimal number in an array using a linear search.
	 *
	 * @return The index of the value, or {@code -1} if it is not found.
	 */
	public static int indexOf(double[] array, double value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == value) return i;
		}
		return -1;
	}

	/**
	 * Searches for a string in an array using a case-insensitive linear search.
	 *
	 * @return The index of the value, or {@code -1} if it is not found.
	 */
	public static int indexOf(String[] array, String value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null && array[i].equalsIgnoreCase(value)) return i;
		}
		return -1;
	}

	/**
	 * Searches for an object in an array using a linear search.
	 *
	 * @return The index of the value, or {@code -1} if it is not found.
	 */
	public static <T> int indexOf(T[] array, T value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null && array[i].equals(value)) return i;
		}
		return -1;
	}

	/**
	 * Checks whether an integer exists in an array.
	 */
	public static boolean contains(int[] array, int value) {
		return indexOf(array, value) != -1;
	}

	/**
	 * Checks whether a decimal number exists in an array.
	 */
	public static boolean contains(double[] array, double value) {
		return indexOf(array, value) != -1;
	}

	/**
	 * Checks whether a string exists in an array.
	 */
	public static boolean contains(String[] array, String value) {
		return indexOf(array, value) != -1;
	}

	/**
	 * Checks whether an object exists in an array.
	 */
	public static <T> boolean contains(T[] array, T value) {
		return indexOf(array, value) != -1;
	}

	//-------------------------------
	// MANIPULATION
	//-------------------------------

	/**
	 * Appends an element to the end of an array and returns the new array.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] append(T[] array, T element, Class<T> componentType) {
		T[] newArray = (T[]) Array.newInstance(componentType, array.length + 1);
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		newArray[array.length] = element;
		return newArray;
	}

	/**
	 * Appends an element to the end of an array and returns the new array.
	 */
	public static int[] append(int[] array, int element) {
		int[] newArray = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		newArray[array.length] = element;
		return newArray;
	}

	/**
	 * Appends an element to the end of an array and returns the new array.
	 */
	public static double[] append(double[] array, double element) {
		double[] newArray = new double[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		newArray[array.length] = element;
		return newArray;
	}

	/**
	 * Removes the element at the specified index and returns the new array.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] removeAt(T[] array, int index, Class<T> componentType) {
		T[] newArray = (T[]) Array.newInstance(componentType, array.length - 1);
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) newArray[j++] = array[i];
		}
		return newArray;
	}

	/**
	 * Removes the element at the specified index and returns the new array.
	 */
	public static int[] removeAt(int[] array, int index) {
		int[] newArray = new int[array.length - 1];
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) newArray[j++] = array[i];
		}
		return newArray;
	}

	/**
	 * Removes the element at the specified index and returns the new array.
	 */
	public static double[] removeAt(double[] array, int index) {
		double[] newArray = new double[array.length - 1];
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) newArray[j++] = array[i];
		}
		return newArray;
	}

	//-------------------------------
	// STATISTICS
	//-------------------------------

	/**
	 * Returns the maximum value in an integer array.
	 */
	public static int max(int[] array) {
		int max = array[0];
		for(int v : array) {
			if(v > max) max = v;
		}
		return max;
	}

	/**
	 * Returns the maximum value in a decimal array.
	 */
	public static double max(double[] array) {
		double max = array[0];
		for(double v : array) {
			if(v > max) max = v;
		}
		return max;
	}

	/**
	 * Returns the minimum value in an integer array.
	 */
	public static int min(int[] array) {
		int min = array[0];
		for(int v : array) {
			if(v < min) min = v;
		}
		return min;
	}

	/**
	 * Returns the minimum value in a decimal array.
	 */
	public static double min(double[] array) {
		double min = array[0];
		for(double v : array) {
			if(v < min) min = v;
		}
		return min;
	}

	/**
	 * Returns the sum of all elements in an integer array.
	 */
	public static int sum(int[] array) {
		int total = 0;
		for(int v : array) {
			total += v;
		}
		return total;
	}

	/**
	 * Returns the sum of all elements in a decimal array.
	 */
	public static double sum(double[] array) {
		double total = 0;
		for(double v : array) {
			total += v;
		}
		return total;
	}

	/**
	 * Returns the average of the elements in an integer array.
	 */
	public static double average(int[] array) {
		if(array.length == 0) return 0;
		return (double) sum(array) / array.length;
	}

	/**
	 * Returns the average of the elements in a decimal array.
	 */
	public static double average(double[] array) {
		if(array.length == 0) return 0;
		return (double) sum(array) / array.length;
	}

	//-------------------------------
	// VALIDATION
	//-------------------------------

	/**
	 * Checks whether a generic object array is empty.
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Checks whether an integer array is empty.
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Checks whether a decimal array is empty.
	 */
	public static boolean isEmpty(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Checks whether an index is valid for an array of the specified length.
	 */
	public static boolean isValidIndex(int index, int length) {
		return index >= 0 && index < length;
	}

	//-------------------------------
	// FILTERING AND SEARCH
	//-------------------------------

	/**
	 * Returns a new array containing the elements that match the given predicate.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition that elements must satisfy.
	 * @return A new array containing the matching elements.
	 */
	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.filter(predicate)
				.toArray(n -> java.util.Arrays.copyOf(array, n));
	}

	/**
	 * Returns the first element that matches the given predicate,
	 * or {@code null} if no element matches.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition that the element must satisfy.
	 * @return The first matching element, or {@code null} if none is found.
	 */
	public static <T> T findFirst(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.filter(predicate)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Returns the number of elements that match the given predicate.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition that elements must satisfy.
	 * @return The number of matching elements.
	 */
	public static <T> long count(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.filter(predicate)
				.count();
	}

	//-------------------------------
	// CHECKS
	//-------------------------------

	/**
	 * Returns {@code true} if any element matches the given predicate.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition to test.
	 * @return {@code true} if any element matches the predicate.
	 */
	public static <T> boolean anyMatch(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.anyMatch(predicate);
	}

	/**
	 * Returns {@code true} if all elements match the given predicate.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition to test.
	 * @return {@code true} if all elements match the predicate.
	 */
	public static <T> boolean allMatch(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.allMatch(predicate);
	}

	/**
	 * Returns {@code true} if no element matches the given predicate.
	 *
	 * @param array Array of elements.
	 * @param predicate Condition to test.
	 * @return {@code true} if no element matches the predicate.
	 */
	public static <T> boolean noneMatch(T[] array, Predicate<T> predicate) {
		return java.util.Arrays.stream(array)
				.noneMatch(predicate);
	}

	//-------------------------------
	// TRANSFORMATIONS
	//-------------------------------

	/**
	 * Returns a new array containing the elements transformed by the given mapper function.
	 *
	 * @param array Original array of elements.
	 * @param resultTemplate Array used as a type reference for the result.
	 * @param mapper Transformation function.
	 * @return A new array containing the transformed elements.
	 */
	public static <T, V> V[] map(T[] array, V[] resultTemplate, Function<T, V> mapper) {
		return java.util.Arrays.stream(array)
				.map(mapper)
				.toArray(n -> java.util.Arrays.copyOf(resultTemplate, n));
	}

	//-------------------------------
	// SORTING
	//-------------------------------

	/**
	 * Returns a new array containing the elements sorted according to the given comparator.
	 * The original array is not modified.
	 *
	 * @param array Array of elements.
	 * @param comparator Sorting criterion.
	 * @return A new sorted array.
	 */
	public static <T> T[] sorted(T[] array, Comparator<T> comparator) {
		return java.util.Arrays.stream(array)
				.sorted(comparator)
				.toArray(n -> java.util.Arrays.copyOf(array, n));
	}

	/**
	 * Returns the maximum element according to the given comparator,
	 * or {@code null} if the array is empty.
	 *
	 * @param array Array of elements.
	 * @param comparator Comparison criterion.
	 * @return The maximum element, or {@code null} if the array is empty.
	 */
	public static <T> T max(T[] array, Comparator<T> comparator) {
		if(isEmpty(array)) return null;
		return java.util.Arrays.stream(array)
				.max(comparator)
				.orElse(null);
	}

	/**
	 * Returns the minimum element according to the given comparator,
	 * or {@code null} if the array is empty.
	 *
	 * @param array Array of elements.
	 * @param comparator Comparison criterion.
	 * @return The minimum element, or {@code null} if the array is empty.
	 */
	public static <T> T min(T[] array, Comparator<T> comparator) {
		if (isEmpty(array)) return null;
		return java.util.Arrays.stream(array)
				.min(comparator)
				.orElse(null);
	}

}
