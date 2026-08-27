package io.github.agarriga18696.andreuutils.core;

/**
 * Interface for objects that can be converted to CSV format.
 * Classes implementing this interface can be written to CSV files
 * using {@link FileUtils#writeObjectsToCsv(String, CsvSerializable[], String, boolean)}.
 *
 * @author Andreu
 * @version 1.0
 */
public interface CsvSerializable {

    /**
     * Converts this object to a CSV line using the specified separator.
     * Common separators are {@code ";"} and {@code ","}.
     *
     * @param separator Separator used between fields.
     * @return The CSV representation of this object.
     */
    String toCsv(String separator);

}