package io.github.agarriga18696.andreuutils.swing;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.util.Objects;

/**
 * Utility for building a standard application menu bar.
 *
 * @author Andreu
 * @version 1.0
 */
public final class ApplicationMenuBarSwing {

    private ApplicationMenuBarSwing() {
        // Utility class
    }

    // ----------------------------------------
    // BUILDER
    // ----------------------------------------

    /**
     * Creates a builder for a standard application menu bar.
     *
     * @param componentToUpdate Component updated when the theme changes.
     * @return Application menu bar builder.
     */
    public static Builder builder(Component componentToUpdate) {

        return new Builder(componentToUpdate);
    }

    /**
     * Builder for a standard application menu bar.
     */
    public static final class Builder {

        private final Component componentToUpdate;
        private Runnable onHome;
        private Runnable onExit;
        private Runnable onAbout;

        private Builder(Component componentToUpdate) {

            this.componentToUpdate = Objects.requireNonNull(
                    componentToUpdate,
                    "The component to update cannot be null."
            );
        }

        /**
         * Sets the action executed when Home is selected.
         *
         * @param action Home action.
         * @return This builder.
         */
        public Builder onHome(Runnable action) {

            this.onHome = Objects.requireNonNull(
                    action,
                    "The Home action cannot be null."
            );

            return this;
        }

        /**
         * Sets the action executed when Exit to desktop is selected.
         *
         * @param action Exit action.
         * @return This builder.
         */
        public Builder onExit(Runnable action) {

            this.onExit = Objects.requireNonNull(
                    action,
                    "The exit action cannot be null."
            );

            return this;
        }

        /**
         * Sets the action executed when About is selected.
         *
         * @param action About action.
         * @return This builder.
         */
        public Builder onAbout(Runnable action) {

            this.onAbout = Objects.requireNonNull(
                    action,
                    "The About action cannot be null."
            );

            return this;
        }

        private void validateActions() {

            if (onHome == null) {
                throw new IllegalStateException("The Home action must be configured.");
            }

            if (onExit == null) {
                throw new IllegalStateException("The Exit action must be configured.");
            }

            if (onAbout == null) {
                throw new IllegalStateException("The About action must be configured.");
            }
        }

        /**
         * Builds the application menu bar.
         *
         * @return Configured application menu bar.
         */
        public JMenuBar build() {

            validateActions();

            JMenuBar menuBar = MenusSwing.menuBar();

            JMenu fileMenu = MenusSwing.menu(I18nSwing.text("menu.file"));

            // MENU ITEMS

            JMenuItem homeItem =
                    MenusSwing.item(
                            I18nSwing.text("menu.home"),
                            onHome
                    );

            fileMenu.add(homeItem);

            fileMenu.addSeparator();

            JMenuItem exitItem =
                    MenusSwing.item(
                            I18nSwing.text("menu.exit_desktop"),
                            onExit
                    );

            fileMenu.add(exitItem);

            JMenu viewMenu = MenusSwing.menu(I18nSwing.text("menu.view"));

            viewMenu.add(
                    ThemeMenuSwing.create(componentToUpdate)
            );

            JMenu settingsMenu = MenusSwing.menu(I18nSwing.text("menu.settings"));

            settingsMenu.add(
                    LanguageMenuSwing.create()
            );

            JMenu helpMenu = MenusSwing.menu(I18nSwing.text("menu.help"));

            JMenuItem aboutItem =
                    MenusSwing.item(
                            I18nSwing.text("menu.about"),
                            onAbout
                    );

            helpMenu.add(aboutItem);

            // BINDINGS

            I18nSwing.bind(
                    fileMenu,
                    (menu, _) -> menu.setText(
                            I18nSwing.text("menu.file")
                    )
            );

            I18nSwing.bind(
                    viewMenu,
                    (menu, _) -> menu.setText(
                            I18nSwing.text("menu.view")
                    )
            );

            I18nSwing.bind(
                    settingsMenu,
                    (menu, _) -> menu.setText(
                            I18nSwing.text("menu.settings")
                    )
            );

            I18nSwing.bind(
                    helpMenu,
                    (menu, _) -> menu.setText(
                            I18nSwing.text("menu.help")
                    )
            );

            I18nSwing.bind(
                    homeItem,
                    (item, _) -> item.setText(
                            I18nSwing.text("menu.home")
                    )
            );

            I18nSwing.bind(
                    exitItem,
                    (item, _) -> item.setText(
                            I18nSwing.text("menu.exit_desktop")
                    )
            );

            I18nSwing.bind(
                    aboutItem,
                    (item, _) -> item.setText(
                            I18nSwing.text("menu.about")
                    )
            );

            menuBar.add(fileMenu);
            menuBar.add(viewMenu);
            menuBar.add(settingsMenu);
            menuBar.add(helpMenu);

            return menuBar;
        }

    }
}
