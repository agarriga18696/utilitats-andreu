package io.github.agarriga18696.andreuutils.swing;

/**
 * Represents a Look and Feel theme available to a Swing application.
 *
 * @param name      Display name of the theme.
 * @param className Fully qualified Look and Feel class name.
 * @param icon      Name of the associated icon file.
 * @param external  Whether the theme belongs to an external library.
 * @author Andreu
 * @version 2.0
 */
public record LookAndFeelThemeSwing(
        String name,
        String className,
        String icon,
        boolean external
) {
}