package io.github.agarriga18696.andreuutils.swing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

/**
 * Generic table model for {@link javax.swing.JTable}.
 * <p>
 * Uses a list of objects of type {@code T} as its data source and a list of
 * {@link ColumnSwing} definitions to determine how each column value is
 * extracted. Cells are not editable by default.
 * <p>
 * Use {@link #update(List)} to replace the model data and automatically notify
 * the table view.
 *
 * @param <T> Type of object representing each table row.
 * @author Andreu
 * @version 2.0
 */
public final class TableModelSwing<T> extends AbstractTableModel {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final long serialVersionUID = 1L;

    // ----------------------------------------
    // FIELDS
    // ----------------------------------------
    private final transient List<ColumnSwing<T>> columns;
    private transient List<T> data;

    // ----------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------

    /**
     * Creates a table model with the specified data and column definitions.
     *
     * @param data    Initial list of objects.
     * @param columns Column definitions.
     */
    public TableModelSwing(
            List<T> data,
            List<ColumnSwing<T>> columns
    ) {

        Objects.requireNonNull(
                data,
                "Data cannot be null."
        );

        Objects.requireNonNull(
                columns,
                "Columns cannot be null."
        );

        this.data = new ArrayList<>(data);
        this.columns = List.copyOf(columns);
    }

    // ----------------------------------------
    // ABSTRACTTABLEMODEL IMPLEMENTATION
    // ----------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowCount() {
        return this.data.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return this.columns.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnName(int column) {
        return this.columns.get(column).name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(int row, int column) {
        return this.columns
                .get(column)
                .extractor()
                .apply(this.data.get(row));
    }

    /**
     * Returns whether the specified cell is editable.
     *
     * @return Always {@code false}.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Returns the value class of the first row for the specified column.
     * <p>
     * This allows {@link javax.swing.JTable} to select appropriate default
     * renderers, such as right-aligned numbers and checkboxes for boolean values.
     *
     * @return Value class, or {@link Object} if the table is empty or the value
     * is {@code null}.
     */
    @Override
    public Class<?> getColumnClass(int column) {

        if (this.data.isEmpty()) {
            return Object.class;
        }

        Object value = getValueAt(
                0,
                column
        );

        return value != null
                ? value.getClass()
                : Object.class;
    }

    // ----------------------------------------
    // DATA
    // ----------------------------------------

    /**
     * Replaces the model data and notifies the table view.
     *
     * @param newData New list of objects.
     */
    public void update(List<T> newData) {

        Objects.requireNonNull(
                newData,
                "New data cannot be null."
        );

        this.data = new ArrayList<>(newData);

        fireTableDataChanged();
    }

    /**
     * Returns the object at the specified model row.
     * <p>
     * The supplied index refers to the model row, not the view row.
     *
     * @param row Model row index.
     * @return Object stored at the specified row.
     */
    public T getRow(int row) {
        return this.data.get(row);
    }

    /**
     * Returns the number of objects stored in the model.
     *
     * @return Number of model rows.
     */
    public int size() {
        return this.data.size();
    }

}