package io.github.agarriga18696.andreuutils.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Objects;
import java.util.function.Supplier;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Utility class for creating Swing panels with common layout configurations.
 *
 * @author Andreu
 * @version 2.0
 */
public final class PanelsSwing {

    private PanelsSwing() {
        // Utility class
    }

    // ----------------------------------------
    // GENERIC PANELS
    // ----------------------------------------

    /**
     * Creates a {@link JPanel} using the specified layout manager.
     *
     * @param layout Panel layout manager.
     * @return Created panel.
     */
    public static JPanel panel(LayoutManager layout) {
        return new JPanel(layout);
    }

    /**
     * Creates a {@link JPanel} using the specified layout manager and padding.
     *
     * @param layout  Panel layout manager.
     * @param padding Padding in pixels on all sides.
     * @return Created panel.
     */
    public static JPanel panelWithPadding(
            LayoutManager layout,
            int padding
    ) {

        JPanel panel = new JPanel(layout);

        panel.setBorder(
                new EmptyBorder(
                        padding,
                        padding,
                        padding,
                        padding
                )
        );

        return panel;
    }

    // ----------------------------------------
    // BORDERLAYOUT
    // ----------------------------------------

    /**
     * Creates a {@link JPanel} using {@link BorderLayout}.
     *
     * @return Panel using {@link BorderLayout}.
     */
    public static JPanel borderLayout() {
        return new JPanel(
                new BorderLayout()
        );
    }

    // ----------------------------------------
    // FLOWLAYOUT
    // ----------------------------------------

    /**
     * Creates a {@link JPanel} using the default {@link FlowLayout}.
     *
     * @return Panel using {@link FlowLayout}.
     */
    public static JPanel flow() {
        return new JPanel(
                new FlowLayout()
        );
    }

    /**
     * Creates a {@link JPanel} using {@link FlowLayout} with the specified
     * alignment.
     *
     * @param alignment Flow layout alignment.
     * @return Panel using the configured {@link FlowLayout}.
     */
    public static JPanel flow(int alignment) {
        return new JPanel(
                new FlowLayout(alignment)
        );
    }

    // ----------------------------------------
    // GRIDLAYOUT
    // ----------------------------------------

    /**
     * Creates a {@link JPanel} using {@link GridLayout}.
     *
     * @param rows    Number of rows.
     * @param columns Number of columns.
     * @return Panel using {@link GridLayout}.
     */
    public static JPanel grid(
            int rows,
            int columns
    ) {

        validateGrid(
                rows,
                columns
        );

        return new JPanel(
                new GridLayout(
                        rows,
                        columns
                )
        );
    }

    /**
     * Creates a {@link JPanel} using {@link GridLayout} with the specified
     * horizontal and vertical gaps.
     *
     * @param rows          Number of rows.
     * @param columns       Number of columns.
     * @param horizontalGap Horizontal gap in pixels.
     * @param verticalGap   Vertical gap in pixels.
     * @return Panel using the configured {@link GridLayout}.
     */
    public static JPanel grid(
            int rows,
            int columns,
            int horizontalGap,
            int verticalGap
    ) {

        validateGrid(
                rows,
                columns
        );

        return new JPanel(
                new GridLayout(
                        rows,
                        columns,
                        horizontalGap,
                        verticalGap
                )
        );
    }

    /**
     * Creates a {@link JPanel} using {@link GridLayout} and fills it with
     * components created by the specified factory.
     *
     * @param rows    Number of rows.
     * @param columns Number of columns.
     * @param factory Component factory.
     * @return Panel containing the generated components.
     */
    public static JPanel grid(
            int rows,
            int columns,
            Supplier<? extends JComponent> factory
    ) {

        JPanel panel = grid(
                rows,
                columns
        );

        fill(
                panel,
                rows * columns,
                factory
        );

        return panel;
    }

    /**
     * Creates a {@link JPanel} using {@link GridLayout} with the specified
     * gaps and fills it with components created by the supplied factory.
     *
     * @param rows          Number of rows.
     * @param columns       Number of columns.
     * @param horizontalGap Horizontal gap in pixels.
     * @param verticalGap   Vertical gap in pixels.
     * @param factory       Component factory.
     * @return Panel containing the generated components.
     */
    public static JPanel grid(
            int rows,
            int columns,
            int horizontalGap,
            int verticalGap,
            Supplier<? extends JComponent> factory
    ) {

        JPanel panel = grid(
                rows,
                columns,
                horizontalGap,
                verticalGap
        );

        fill(
                panel,
                rows * columns,
                factory
        );

        return panel;
    }

    // ----------------------------------------
    // BOXLAYOUT
    // ----------------------------------------

    /**
     * Creates a {@link JPanel} using a vertical {@link BoxLayout}.
     *
     * @return Panel using {@link BoxLayout#Y_AXIS}.
     */
    public static JPanel verticalBox() {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        return panel;
    }

    /**
     * Creates a {@link JPanel} using a horizontal {@link BoxLayout}.
     *
     * @return Panel using {@link BoxLayout#X_AXIS}.
     */
    public static JPanel horizontalBox() {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.X_AXIS
                )
        );

        return panel;
    }

    // ----------------------------------------
    // COMPONENTS
    // ----------------------------------------

    /**
     * Returns a child component if it exists at the specified index and matches
     * the expected type.
     *
     * @param <T>       Expected component type.
     * @param component Parent component.
     * @param index     Child component index.
     * @param type      Expected component class.
     * @return Child component cast to the expected type, or {@code null} if the
     * index is invalid or the component does not match the expected type.
     */
    public static <T extends Component> T getComponent(
            JComponent component,
            int index,
            Class<T> type
    ) {

        Objects.requireNonNull(
                component,
                "Component cannot be null."
        );

        Objects.requireNonNull(
                type,
                "Component type cannot be null."
        );

        if (index < 0 ||
                index >= component.getComponentCount()) {

            return null;
        }

        Component child =
                component.getComponent(index);

        if (!type.isInstance(child)) {
            return null;
        }

        return type.cast(child);
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static void fill(
            JPanel panel,
            int count,
            Supplier<? extends JComponent> factory
    ) {

        Objects.requireNonNull(
                factory,
                "Component factory cannot be null."
        );

        for (int index = 0;
             index < count;
             index++) {

            panel.add(
                    factory.get()
            );
        }
    }

    private static void validateGrid(
            int rows,
            int columns
    ) {

        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException(
                    "Rows and columns cannot be negative."
            );
        }

        if (rows == 0 && columns == 0) {
            throw new IllegalArgumentException(
                    "Rows and columns cannot both be zero."
            );
        }
    }

}