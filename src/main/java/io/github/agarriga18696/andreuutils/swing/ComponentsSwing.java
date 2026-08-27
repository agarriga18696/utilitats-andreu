package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Utility class for creating common Swing components.
 *
 * @author Andreu
 * @version 2.0
 */
public final class ComponentsSwing {

    private ComponentsSwing() {
        // Utility class
    }

    // ----------------------------------------
    // BUTTONS
    // ----------------------------------------

    /**
     * Creates a {@link JButton} with the specified text.
     *
     * @param text Button text.
     * @return Created button.
     */
    public static JButton button(String text) {
        return new JButton(text);
    }

    /**
     * Creates a {@link JButton} with the specified text and action.
     *
     * @param text   Button text.
     * @param action Action executed when the button is clicked.
     * @return Created button.
     */
    public static JButton button(
            String text,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "Action cannot be null."
        );

        JButton button = new JButton(text);

        button.addActionListener(
                _ -> action.run()
        );

        return button;
    }

    /**
     * Creates a {@link JButton} with text, icon and action.
     *
     * @param text   Button text.
     * @param icon   Button icon.
     * @param action Action executed when the button is clicked.
     * @return Created button.
     */
    public static JButton button(
            String text,
            Icon icon,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "Action cannot be null."
        );

        JButton button =
                new JButton(
                        text,
                        icon
                );

        button.addActionListener(
                _ -> action.run()
        );

        return button;
    }

    /**
     * Creates a {@link JButton} with text, mnemonic and action.
     *
     * @param text     Button text.
     * @param mnemonic Mnemonic key code.
     * @param action   Action executed when the button is clicked.
     * @return Created button.
     */
    public static JButton button(
            String text,
            int mnemonic,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "Action cannot be null."
        );

        JButton button = new JButton(text);

        button.setMnemonic(mnemonic);

        button.addActionListener(
                _ -> action.run()
        );

        return button;
    }

    /**
     * Creates a {@link JButton} with text, icon, mnemonic and action.
     *
     * @param text     Button text.
     * @param icon     Button icon.
     * @param mnemonic Mnemonic key code.
     * @param action   Action executed when the button is clicked.
     * @return Created button.
     */
    public static JButton button(
            String text,
            Icon icon,
            int mnemonic,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "Action cannot be null."
        );

        JButton button =
                new JButton(
                        text,
                        icon
                );

        button.setMnemonic(mnemonic);

        button.addActionListener(
                _ -> action.run()
        );

        return button;
    }

    /**
     * Creates a {@link JButton} with the specified text and action listener.
     *
     * @param text     Button text.
     * @param listener Action listener executed when the button is clicked.
     * @return Created button.
     */
    public static JButton button(
            String text,
            ActionListener listener
    ) {

        Objects.requireNonNull(
                listener,
                "Action listener cannot be null."
        );

        JButton button = new JButton(text);

        button.addActionListener(listener);

        return button;
    }

    // ----------------------------------------
    // LABELS
    // ----------------------------------------

    /**
     * Creates a {@link JLabel} with the specified text.
     *
     * @param text Label text.
     * @return Created label.
     */
    public static JLabel label(String text) {
        return new JLabel(text);
    }

    /**
     * Creates a {@link JLabel} associated with a component and mnemonic.
     *
     * @param text      Label text.
     * @param component Component associated with the label.
     * @param mnemonic  Mnemonic key code.
     * @return Configured label.
     */
    public static JLabel labelFor(
            String text,
            JComponent component,
            int mnemonic
    ) {

        Objects.requireNonNull(
                component,
                "Component cannot be null."
        );

        JLabel label = label(text);

        label.setLabelFor(component);
        label.setDisplayedMnemonic(mnemonic);

        return label;
    }

    /**
     * Creates a centered {@link JLabel}.
     *
     * @param text Label text.
     * @return Centered label.
     */
    public static JLabel centeredLabel(String text) {
        return new JLabel(
                text,
                SwingConstants.CENTER
        );
    }

    /**
     * Creates a {@link JLabel} aligned to the trailing edge.
     *
     * @param text Label text.
     * @return Trailing-aligned label.
     */
    public static JLabel trailingLabel(String text) {
        return new JLabel(
                text,
                SwingConstants.TRAILING
        );
    }

    // ----------------------------------------
    // TEXT COMPONENTS
    // ----------------------------------------

    /**
     * Creates a {@link JTextField} with the specified number of columns.
     *
     * @param columns Number of columns.
     * @return Created text field.
     */
    public static JTextField textField(int columns) {
        return new JTextField(columns);
    }

    /**
     * Creates a {@link JPasswordField} with the specified number of columns.
     * <p>
     * Use {@link JPasswordField#getPassword()} to retrieve the password as a
     * {@code char[]}. The array should be cleared after use:
     * <pre>
     * Arrays.fill(password, '\0');
     * </pre>
     *
     * @param columns Number of columns.
     * @return Created password field.
     */
    public static JPasswordField passwordField(
            int columns
    ) {

        return new JPasswordField(columns);
    }

    /**
     * Creates a {@link JTextArea} with the specified number of rows and columns.
     *
     * @param rows    Number of rows.
     * @param columns Number of columns.
     * @return Created text area.
     */
    public static JTextArea textArea(
            int rows,
            int columns
    ) {

        return new JTextArea(
                rows,
                columns
        );
    }

    // ----------------------------------------
    // CHECK BOXES
    // ----------------------------------------

    /**
     * Creates a {@link JCheckBox} with the specified text.
     *
     * @param text Check box text.
     * @return Created check box.
     */
    public static JCheckBox checkBox(String text) {
        return new JCheckBox(text);
    }

    // ----------------------------------------
    // COMBO BOXES
    // ----------------------------------------

    /**
     * Creates a {@link JComboBox} containing the specified items.
     *
     * @param <T>   Item type.
     * @param items Items to display.
     * @return Created combo box.
     */
    public static <T> JComboBox<T> comboBox(T[] items) {

        Objects.requireNonNull(
                items,
                "Items cannot be null."
        );

        return new JComboBox<>(items);
    }

    // ----------------------------------------
    // LISTS
    // ----------------------------------------

    /**
     * Creates an empty generic {@link JList}.
     *
     * @param <T> Element type.
     * @return Created list.
     */
    public static <T> JList<T> list() {
        return new JList<>();
    }

    /**
     * Creates a generic {@link JList} containing the specified elements.
     *
     * @param <T>      Element type.
     * @param elements Elements to display.
     * @return Created list.
     */
    public static <T> JList<T> list(T[] elements) {

        Objects.requireNonNull(
                elements,
                "Elements cannot be null."
        );

        return new JList<>(elements);
    }

    // ----------------------------------------
    // SCROLL PANES
    // ----------------------------------------

    /**
     * Creates a {@link JScrollPane} containing the specified component.
     *
     * @param component Component displayed inside the scroll pane.
     * @return Created scroll pane.
     */
    public static JScrollPane scrollPane(
            Component component
    ) {

        Objects.requireNonNull(
                component,
                "Component cannot be null."
        );

        return new JScrollPane(component);
    }

}