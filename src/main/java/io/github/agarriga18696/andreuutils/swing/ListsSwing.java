package io.github.agarriga18696.andreuutils.swing;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Utility class for creating and managing generic Swing lists.
 *
 * @author Andreu
 * @version 2.0
 */
public final class ListsSwing {

    private ListsSwing() {
        // Utility class
    }

    // ----------------------------------------
    // SELECTION
    // ----------------------------------------

    /**
     * Returns the selected element from a {@link JList} wrapped in an
     * {@link Optional}.
     * <p>
     * If no element is selected, returns {@link Optional#empty()}.
     *
     * @param <T>  Type of list elements.
     * @param list List whose selection should be queried.
     * @return Selected element, or an empty {@link Optional} if no element is selected.
     * @throws NullPointerException if {@code list} is {@code null}.
     */
    public static <T> Optional<T> getSelected(JList<T> list) {

        Objects.requireNonNull(
                list,
                "List cannot be null."
        );

        return Optional.ofNullable(
                list.getSelectedValue()
        );
    }

    // ----------------------------------------
    // DATA
    // ----------------------------------------

    /**
     * Replaces the contents of a {@link JList} with the specified elements.
     * <p>
     * A new {@link DefaultListModel} is created and assigned to the list,
     * replacing any existing model.
     *
     * @param <T>      Type of list elements.
     * @param list     List to update.
     * @param elements Elements to display.
     * @throws NullPointerException if {@code list} or {@code elements} is {@code null}.
     */
    public static <T> void update(
            JList<T> list,
            List<T> elements
    ) {

        Objects.requireNonNull(
                list,
                "List cannot be null."
        );

        Objects.requireNonNull(
                elements,
                "Elements cannot be null."
        );

        DefaultListModel<T> model =
                new DefaultListModel<>();

        model.addAll(elements);

        list.setModel(model);
    }

}