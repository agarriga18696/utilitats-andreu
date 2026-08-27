package io.github.agarriga18696.andreuutils.swing;

import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * Utility class for creating and configuring Swing menus.
 *
 * @author Andreu
 * @version 2.0
 */
public final class MenusSwing {

    private MenusSwing() {
        // Utility class
    }

    // ----------------------------------------
    // MENU BARS AND MENUS
    // ----------------------------------------

    /**
     * Creates a new {@link JMenuBar}.
     *
     * @return Created menu bar.
     */
    public static JMenuBar menuBar() {
        return new JMenuBar();
    }

    /**
     * Creates a {@link JMenu} with the specified text.
     *
     * @param text Menu text.
     * @return Created menu.
     */
    public static JMenu menu(String text) {
        return new JMenu(text);
    }

    /**
     * Creates a {@link JMenu} with the specified text and mnemonic.
     *
     * @param text     Menu text.
     * @param mnemonic Keyboard mnemonic.
     * @return Created menu.
     */
    public static JMenu menu(String text, int mnemonic) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    /**
     * Creates a {@link JMenu} with the specified text and mnemonic.
     *
     * @param text     Menu text.
     * @param mnemonic Keyboard mnemonic.
     * @return Created menu.
     */
    public static JMenu menu(String text, char mnemonic) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    /**
     * Creates a {@link JMenu} with the specified text and icon.
     *
     * @param text Menu text.
     * @param icon Menu icon.
     * @return Created menu.
     */
    public static JMenu menu(String text, Icon icon) {
        JMenu menu = new JMenu(text);
        menu.setIcon(icon);
        return menu;
    }

    /**
     * Creates a {@link JMenu} with the specified text, icon and mnemonic.
     *
     * @param text     Menu text.
     * @param icon     Menu icon.
     * @param mnemonic Keyboard mnemonic.
     * @return Created menu.
     */
    public static JMenu menu(
            String text,
            Icon icon,
            int mnemonic
    ) {

        JMenu menu = new JMenu(text);
        menu.setIcon(icon);
        menu.setMnemonic(mnemonic);

        return menu;
    }

    /**
     * Creates a {@link JMenu} with the specified text, icon and mnemonic.
     *
     * @param text     Menu text.
     * @param icon     Menu icon.
     * @param mnemonic Keyboard mnemonic.
     * @return Created menu.
     */
    public static JMenu menu(
            String text,
            Icon icon,
            char mnemonic
    ) {

        JMenu menu = new JMenu(text);
        menu.setIcon(icon);
        menu.setMnemonic(mnemonic);

        return menu;
    }

    // ----------------------------------------
    // MENU ITEMS
    // ----------------------------------------

    /**
     * Creates a {@link JMenuItem} with the specified text.
     *
     * @param text Menu item text.
     * @return Created menu item.
     */
    public static JMenuItem item(String text) {
        return new JMenuItem(text);
    }

    /**
     * Creates a {@link JMenuItem} with the specified text and action.
     *
     * @param text   Menu item text.
     * @param action Action executed when the item is selected.
     * @return Created menu item.
     */
    public static JMenuItem item(
            String text,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "The action cannot be null."
        );

        JMenuItem item = new JMenuItem(text);
        item.addActionListener(_ -> action.run());

        return item;
    }

    /**
     * Creates a {@link JMenuItem} with the specified text, icon and action.
     *
     * @param text   Menu item text.
     * @param icon   Menu item icon.
     * @param action Action executed when the item is selected.
     * @return Created menu item.
     */
    public static JMenuItem item(
            String text,
            Icon icon,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "The action cannot be null."
        );

        JMenuItem item = new JMenuItem(text, icon);
        item.addActionListener(_ -> action.run());

        return item;
    }

    /**
     * Creates a {@link JMenuItem} with the specified text, icon, keyboard
     * accelerator and action.
     *
     * @param text        Menu item text.
     * @param icon        Menu item icon.
     * @param accelerator Keyboard accelerator.
     * @param action      Action executed when the item is selected.
     * @return Created menu item.
     */
    public static JMenuItem item(
            String text,
            Icon icon,
            KeyStroke accelerator,
            Runnable action
    ) {

        JMenuItem item = item(text, icon, action);
        item.setAccelerator(accelerator);

        return item;
    }

    // ----------------------------------------
    // RADIO ITEMS
    // ----------------------------------------

    /**
     * Creates a {@link JRadioButtonMenuItem}.
     *
     * @param text     Menu item text.
     * @param selected Whether the item is initially selected.
     * @param action   Action executed when the item is selected.
     * @return Created radio menu item.
     */
    public static JRadioButtonMenuItem radioItem(
            String text,
            boolean selected,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "The action cannot be null."
        );

        JRadioButtonMenuItem item =
                new JRadioButtonMenuItem(text, selected);

        item.addActionListener(_ -> action.run());

        return item;
    }

    /**
     * Creates a {@link JRadioButtonMenuItem} with an icon.
     *
     * @param text     Menu item text.
     * @param icon     Menu item icon.
     * @param selected Whether the item is initially selected.
     * @param action   Action executed when the item is selected.
     * @return Created radio menu item.
     */
    public static JRadioButtonMenuItem radioItem(
            String text,
            Icon icon,
            boolean selected,
            Runnable action
    ) {

        Objects.requireNonNull(
                action,
                "The action cannot be null."
        );

        JRadioButtonMenuItem item =
                new JRadioButtonMenuItem(
                        text,
                        icon,
                        selected
                );

        item.addActionListener(_ -> action.run());

        return item;
    }

}
