package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

/**
 * Utility class for creating Look and Feel theme selection menus.
 *
 * @author Andreu
 * @version 2.1
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
    public static JMenu create(
            Component componentToUpdate
    ) {

        return create(
                componentToUpdate,
                _ -> {
                }
        );
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

        return createMenu(
                componentToUpdate,
                LookAndFeelSwing.getPredefinedThemes(),
                onThemeApplied,
                true
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

        return createMenu(
                componentToUpdate,
                themes,
                onThemeApplied,
                false
        );
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static JMenu createMenu(
            Component componentToUpdate,
            List<LookAndFeelThemeSwing> themes,
            Consumer<String> onThemeApplied,
            boolean localizeSystemTheme
    ) {

        JMenu themeMenu = MenusSwing.menu(
                I18nSwing.text("menu.themes"),
                IconsSwing.load(IconsFugue.PALETTE),
                KeyEvent.VK_T
        );

        ButtonGroup themeGroup =
                new ButtonGroup();

        String currentClassName =
                LookAndFeelSwing.getCurrentClassName();

        JRadioButtonMenuItem systemItem =
                null;

        for (LookAndFeelThemeSwing theme : themes) {

            boolean selected =
                    theme.className().equals(currentClassName);

            boolean compatible =
                    LookAndFeelSwing.isCompatible(
                            theme.className()
                    );

            JRadioButtonMenuItem item =
                    MenusSwing.radioItem(
                            theme.name(),
                            selected,
                            () -> applyTheme(
                                    componentToUpdate,
                                    theme,
                                    onThemeApplied
                            )
                    );

            item.setIcon(
                    IconsSwing.load(
                            theme.icon()
                    )
            );

            item.setEnabled(compatible);

            if (localizeSystemTheme
                    && theme.name().equals(I18nSwing.text("theme.system"))) {

                systemItem = item;
            }

            themeGroup.add(item);
            themeMenu.add(item);
        }

        LanguageManager.addLanguageChangeListener(
                new ThemeMenuLanguageListener(
                        themeMenu,
                        systemItem
                )
        );

        return themeMenu;
    }

    private static void applyTheme(
            Component componentToUpdate,
            LookAndFeelThemeSwing theme,
            Consumer<String> onThemeApplied
    ) {

        if (!LookAndFeelSwing.isCompatible(theme.className())) {
            return;
        }

        if (LookAndFeelSwing.apply(theme.className())) {

            LookAndFeelSwing.update(
                    componentToUpdate
            );

            onThemeApplied.accept(
                    theme.name()
            );
        }
    }

    // ----------------------------------------
    // LANGUAGE LISTENER
    // ----------------------------------------

    private record ThemeMenuLanguageListener(WeakReference<JMenu> menuReference,
                                             WeakReference<JRadioButtonMenuItem> systemItemReference)
                implements Consumer<Language> {

            private ThemeMenuLanguageListener(
                    JMenu themeMenu,
                    JRadioButtonMenuItem systemItem
            ) {

                this(new WeakReference<>(themeMenu), new WeakReference<>(systemItem));
            }

            @Override
            public void accept(
                    Language language
            ) {

                JMenu themeMenu =
                        menuReference.get();

                if (themeMenu == null) {

                    LanguageManager.removeLanguageChangeListener(
                            this
                    );

                    return;
                }

                JRadioButtonMenuItem systemItem =
                        systemItemReference.get();

                EdtSwing.runAndWait(() -> {

                    themeMenu.setText(
                            I18nSwing.text("menu.themes")
                    );

                    if (systemItem != null) {

                        systemItem.setText(
                                I18nSwing.text("theme.system")
                        );
                    }
                });
            }
        }

}