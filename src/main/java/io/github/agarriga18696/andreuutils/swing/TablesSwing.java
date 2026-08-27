package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Utility class for creating and managing generic Swing tables.
 * <p>
 * Provides factory methods for building a {@link JTable} from a list of
 * {@link ColumnSwing} definitions and a {@link TableModelSwing}.
 * <p>
 * Example:
 * <pre>
 * List&lt;ColumnSwing&lt;Product&gt;&gt; columns = List.of(
 *         new ColumnSwing&lt;&gt;("ID", Product::getId),
 *         new ColumnSwing&lt;&gt;("Name", Product::getName),
 *         new ColumnSwing&lt;&gt;("Price", Product::getPrice)
 * );
 *
 * TableModelSwing&lt;Product&gt; model =
 *         TablesSwing.model(products, columns);
 *
 * JTable table = TablesSwing.table(model);
 *
 * // Update the model data:
 * model.update(newProducts);
 *
 * // Retrieve the selected row object:
 * Optional&lt;Product&gt; selected =
 *         TablesSwing.getSelectedRow(table);
 * </pre>
 *
 * @author Andreu
 * @version 2.0
 */
public final class TablesSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final int DEFAULT_ROW_HEIGHT = 24;
    private static final int COLUMN_PADDING = 10;

    private TablesSwing() {
        // Utility class
    }

    // ----------------------------------------
    // FACTORY
    // ----------------------------------------

    /**
     * Creates a {@link TableModelSwing} with the specified data and column
     * definitions.
     *
     * @param <T>     Type of object representing each row.
     * @param data    Initial list of objects.
     * @param columns Column definitions.
     * @return Created table model.
     */
    public static <T> TableModelSwing<T> model(
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

        return new TableModelSwing<>(
                data,
                columns
        );
    }

    /**
     * Creates a {@link JTable} from an existing {@link TableModelSwing}.
     * <p>
     * The table is configured with single selection, automatic row sorting,
     * fixed column order and a default row height.
     *
     * @param <T>   Type of object representing each row.
     * @param model Table model.
     * @return Configured table.
     */
    public static <T> JTable table(TableModelSwing<T> model) {

        Objects.requireNonNull(
                model,
                "Model cannot be null."
        );

        JTable table = new JTable(model);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(DEFAULT_ROW_HEIGHT);

        table.getTableHeader()
                .setReorderingAllowed(false);

        return table;
    }

    /**
     * Creates a {@link TableModelSwing} and a {@link JTable} in a single step.
     * <p>
     * Equivalent to calling {@link #model(List, List)} followed by
     * {@link #table(TableModelSwing)}.
     * <p>
     * This overload is useful when the caller does not need to keep a direct
     * reference to the model. If the data must be updated later, use the
     * two-step approach instead.
     *
     * @param <T>     Type of object representing each row.
     * @param data    Initial list of objects.
     * @param columns Column definitions.
     * @return Configured table.
     */
    public static <T> JTable table(
            List<T> data,
            List<ColumnSwing<T>> columns
    ) {

        return table(
                model(
                        data,
                        columns
                )
        );
    }

    // ----------------------------------------
    // COLUMN WIDTHS
    // ----------------------------------------

    /**
     * Fits each column width to its widest visible content.
     * <p>
     * Both the column header and all table cells are considered.
     * {@link JTable#AUTO_RESIZE_OFF} is enabled so that the calculated widths
     * are not overridden by the table auto-resize mode.
     * <p>
     * This method should normally be called after the table data has been
     * populated.
     *
     * @param table Table whose column widths should be adjusted.
     */
    public static void fitColumnWidthsToContent(JTable table) {

        Objects.requireNonNull(
                table,
                "Table cannot be null."
        );

        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        for (int column = 0;
             column < table.getColumnCount();
             column++) {

            TableColumn tableColumn =
                    table.getColumnModel()
                            .getColumn(column);

            TableCellRenderer headerRenderer =
                    tableColumn.getHeaderRenderer();

            if (headerRenderer == null) {
                headerRenderer =
                        table.getTableHeader()
                                .getDefaultRenderer();
            }

            Component headerComponent =
                    headerRenderer.getTableCellRendererComponent(
                            table,
                            tableColumn.getHeaderValue(),
                            false,
                            false,
                            0,
                            column
                    );

            int maximumWidth =
                    headerComponent
                            .getPreferredSize()
                            .width
                            + COLUMN_PADDING;

            for (int row = 0;
                 row < table.getRowCount();
                 row++) {

                TableCellRenderer renderer =
                        table.getCellRenderer(
                                row,
                                column
                        );

                Component component =
                        table.prepareRenderer(
                                renderer,
                                row,
                                column
                        );

                maximumWidth = Math.max(
                        maximumWidth,
                        component
                                .getPreferredSize()
                                .width
                                + COLUMN_PADDING
                );
            }

            tableColumn.setPreferredWidth(
                    maximumWidth
            );
        }
    }

    /**
     * Sets the preferred width of a column.
     * <p>
     * The column remains resizable by the user.
     * {@link JTable#AUTO_RESIZE_LAST_COLUMN} is enabled so that the last column
     * fills the remaining table width.
     *
     * @param table  Table containing the column.
     * @param column Column index in view order.
     * @param width  Preferred width in pixels.
     */
    public static void setColumnWidth(
            JTable table,
            int column,
            int width
    ) {

        Objects.requireNonNull(
                table,
                "Table cannot be null."
        );

        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_LAST_COLUMN
        );

        table.getColumnModel()
                .getColumn(column)
                .setPreferredWidth(width);
    }

    /**
     * Sets a fixed width for a column.
     * <p>
     * The column cannot be resized by the user.
     *
     * @param table  Table containing the column.
     * @param column Column index in view order.
     * @param width  Fixed width in pixels.
     */
    public static void setFixedColumnWidth(
            JTable table,
            int column,
            int width
    ) {

        Objects.requireNonNull(
                table,
                "Table cannot be null."
        );

        TableColumn tableColumn =
                table.getColumnModel()
                        .getColumn(column);

        tableColumn.setMinWidth(width);
        tableColumn.setMaxWidth(width);
        tableColumn.setWidth(width);
        tableColumn.setResizable(false);
    }

    // ----------------------------------------
    // SELECTION
    // ----------------------------------------

    /**
     * Returns the object corresponding to the currently selected table row.
     * <p>
     * Active sorting is taken into account by converting the selected view row
     * index to its corresponding model row index using
     * {@link JTable#convertRowIndexToModel(int)}.
     *
     * @param <T>   Type of object representing each row.
     * @param table Table whose selection should be queried.
     * @return Selected object, or {@link Optional#empty()} if no row is selected.
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getSelectedRow(
            JTable table
    ) {

        Objects.requireNonNull(
                table,
                "Table cannot be null."
        );

        int viewRow = table.getSelectedRow();

        if (viewRow < 0) {
            return Optional.empty();
        }

        int modelRow =
                table.convertRowIndexToModel(
                        viewRow
                );

        TableModelSwing<T> model =
                (TableModelSwing<T>) table.getModel();

        return Optional.of(
                model.getRow(modelRow)
        );
    }

}