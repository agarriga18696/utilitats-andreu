package io.github.agarriga18696.andreuutils.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for working with dates and times.
 *
 * @author Andreu
 * @version 1.1
 */
public final class DateTimeUtils {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private DateTimeUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // DATE AND TIME
    //-------------------------------

    /**
     * Returns the current date formatted as {@code dd/MM/yyyy}.
     *
     * @return The current date as a formatted string.
     */
    public static String currentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * Returns the current time formatted as {@code HH:mm:ss}.
     *
     * @return The current time as a formatted string.
     */
    public static String currentTime() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    /**
     * Returns the current date and time formatted as
     * {@code dd/MM/yyyy HH:mm:ss}.
     *
     * @return The current date and time as a formatted string.
     */
    public static String currentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * Returns the current year.
     *
     * @return The current year.
     */
    public static int currentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * Returns the current month number.
     *
     * @return The current month, from 1 to 12.
     */
    public static int currentMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * Returns the current day of the month.
     *
     * @return The current day of the month.
     */
    public static int currentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    //-------------------------------
    // CONVERSIONS
    //-------------------------------

    /**
     * Parses a string formatted as {@code dd/MM/yyyy} into a {@link LocalDate}.
     *
     * @param text Text to parse.
     * @return The parsed date, or {@code null} if the text is blank
     * or cannot be parsed.
     */
    public static LocalDate parseDate(String text) {
        if (StringUtils.isNullOrBlank(text)) return null;

        try {
            return LocalDate.parse(text.strip(), DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats a {@link LocalDate} as {@code dd/MM/yyyy}.
     *
     * @param date Date to format.
     * @return The formatted date, or {@code null} if the date is {@code null}.
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(DATE_FORMATTER);
    }

    //-------------------------------
    // DATE OPERATIONS
    //-------------------------------

    /**
     * Calculates a person's age from their birth date.
     *
     * @param birthDate Birth date.
     * @return The age in complete years.
     */
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    //-------------------------------
    // VALIDATION
    //-------------------------------

    /**
     * Checks whether a person has reached the age of majority
     * based on their birth date.
     *
     * @param birthDate Birth date.
     * @return {@code true} if the person is at least 18 years old.
     */
    public static boolean isAdult(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    /**
     * Checks whether a string formatted as {@code dd/MM/yyyy}
     * represents a valid date.
     *
     * @param text Text to validate.
     * @return {@code true} if the text can be parsed as a date.
     */
    public static boolean isValidDate(String text) {
        return parseDate(text) != null;
    }

}