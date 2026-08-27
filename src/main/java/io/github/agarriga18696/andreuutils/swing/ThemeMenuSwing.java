package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * Utility class for creating Look and Feel theme selection menus.
 *
 * @author Andreu
 * @version 2.0
 */
public final class ThemeMenuSwing {

    private ThemeMenuSwing() {
        // Utility class
    }

    // ----------------------------------------
    // THEME MENU
    // ----------------------------------------

    /**
     * Creates a theme menu using the predefined Look and Feel themes provided
     * by the library.
     *
     * @param componentToUpdate Main component to update after applying a theme.
     * @return Created theme menu.
     */
    public static JMenu create(Component componentToUpdate) {
        return create(componentToUpdate, _ -> {
        });
    }

    /**
     * Creates a theme menu using the predefined Look and Feel themes provided
     * by the library.
     *
     * @param componentToUpdate Main component to update after applying a theme.
     * @param onThemeApplied    Action executed after a theme is successfully applied.
     * @return Created theme menu.
     */
    public static JMenu create(
            Component componentToUpdate,
            Consumer<String> onThemeApplied
    ) {

        Objects.requireNonNull(
                onThemeApplied,
                "The theme-applied action cannot be null."
        );

        return create(
                componentToUpdate,
                LookAndFeelSwing.getPredefinedThemes(),
                onThemeApplied
        );
    }

    /**
     * Creates a theme menu using a custom list of Look and Feel themes.
     *
     * @param componentToUpdate Main component to update after applying a theme.
     * @param themes            Themes to display.
     * @param onThemeApplied    Action executed after a theme is successfully applied.
     * @return Created theme menu.
     */
    public static JMenu create(
            Component componentToUpdate,
            List<LookAndFeelThemeSwing> themes,
            Consumer<String> onThemeApplied
    ) {

        Objects.requireNonNull(
                themes,
                "The theme list cannot be null."
        );

        Objects.requireNonNull(
                onThemeApplied,
                "The theme-applied action cannot be null."
        );

        JMenu themeMenu = MenusSwing.menu(
                SwingMessages.text("menu.themes"),
                IconesSwing.carregar(IconesSwing.TEMES),
                KeyEvent.VK_T
        );

        ButtonGroup themeGroup = new ButtonGroup();

        String currentClassName =
                LookAndFeelSwing.getCurrentClassName();

        for (LookAndFeelThemeSwing theme : themes) {

            boolean selected =
                    theme.className().equals(currentClassName);

            boolean compatible =
                    LookAndFeelSwing.isCompatible(theme.className());

            JRadioButtonMenuItem item = MenusSwing.radioItem(
                    theme.name(),
                    selected,
                    () -> applyTheme(
                            componentToUpdate,
                            theme,
                            onThemeApplied
                    )
            );

            item.setIcon(
                    IconesSwing.carregar(theme.icon())
            );

            item.setEnabled(compatible);

            themeGroup.add(item);
            themeMenu.add(item);
        }

        return themeMenu;
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static void applyTheme(
            Component componentToUpdate,
            LookAndFeelThemeSwing theme,
            Consumer<String> onThemeApplied
    ) {

        if (!LookAndFeelSwing.isCompatible(theme.className())) {
            return;
        }

        if (LookAndFeelSwing.apply(theme.className())) {
            LookAndFeelSwing.update(componentToUpdate);
            onThemeApplied.accept(theme.name());
        }
    }

}