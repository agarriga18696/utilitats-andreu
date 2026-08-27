package io.github.agarriga18696.andreuutils.swing;

import java.util.Objects;
import java.util.function.Function;

/**
 * Defines a column for {@link TableModelSwing}.
 * <p>
 * Groups the column header name and the function used to extract the column
 * value from an object of type {@code T}.
 *
 * @param <T>       Type of object representing each table row.
 * @param name      Column header name.
 * @param extractor Function used to extract the column value from the row object.
 * @author Andreu
 * @version 2.0
 */
public record ColumnSwing<T>(
        String name,
        Function<T, Object> extractor
) {

    /**
     * Creates a column definition and validates its arguments.
     */
    public ColumnSwing {

        Objects.requireNonNull(
                name,
                "Column name cannot be null."
        );

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Column name cannot be blank."
            );
        }

        Objects.requireNonNull(
                extractor,
                "Extractor cannot be null."
        );
    }

}