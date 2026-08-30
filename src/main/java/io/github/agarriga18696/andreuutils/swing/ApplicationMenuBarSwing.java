package io.github.agarriga18696.andreuutils.swing;

import io.github.agarriga18696.andreuutils.core.Language;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Utility for building a standard application menu bar.
 *
 * @author Andreu
 * @version 1.2
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
    public static Builder builder(
            Component componentToUpdate
    ) {
        return new Builder(
                componentToUpdate
        );
    }

    /**
     * Returns the mnemonic associated with an internationalization key.
     *
     * @param key Mnemonic resource key.
     * @return Mnemonic character.
     */
    private static char mnemonic(
            String key
    ) {
        return I18nSwing
                .text(key)
                .charAt(0);
    }

    // ----------------------------------------
    // MNEMONICS
    // ----------------------------------------

    /**
     * Builder for a standard application menu bar.
     */
    public static final class Builder {

        private final Component componentToUpdate;

        private Runnable onHome;
        private Runnable onExit;
        private Runnable onAbout;

        private Consumer<JMenu> fileMenuConfigurator;
        private Consumer<JMenu> viewMenuConfigurator;
        private Consumer<JMenu> settingsMenuConfigurator;
        private Consumer<JMenu> helpMenuConfigurator;
        private Consumer<Language> onLanguageChanged;
        private Consumer<String> onThemeApplied;

        private Builder(
                Component componentToUpdate
        ) {
            this.componentToUpdate =
                    Objects.requireNonNull(
                            componentToUpdate,
                            "The component to update cannot be null."
                    );
        }

        /**
         * Sets a configurator for adding application-specific items
         * to the File menu.
         *
         * @param configurator File menu configurator.
         * @return This builder.
         */
        public Builder configureFileMenu(
                Consumer<JMenu> configurator
        ) {
            this.fileMenuConfigurator =
                    Objects.requireNonNull(
                            configurator,
                            "The File menu configurator cannot be null."
                    );

            return this;
        }

        /**
         * Sets a configurator for adding application-specific items
         * to the View menu.
         *
         * @param configurator View menu configurator.
         * @return This builder.
         */
        public Builder configureViewMenu(
                Consumer<JMenu> configurator
        ) {
            this.viewMenuConfigurator =
                    Objects.requireNonNull(
                            configurator,
                            "The View menu configurator cannot be null."
                    );

            return this;
        }

        /**
         * Sets a configurator for adding application-specific items
         * to the Settings menu.
         *
         * @param configurator Settings menu configurator.
         * @return This builder.
         */
        public Builder configureSettingsMenu(
                Consumer<JMenu> configurator
        ) {
            this.settingsMenuConfigurator =
                    Objects.requireNonNull(
                            configurator,
                            "The Settings menu configurator cannot be null."
                    );

            return this;
        }

        /**
         * Sets a configurator for adding application-specific items
         * to the Help menu.
         *
         * @param configurator Help menu configurator.
         * @return This builder.
         */
        public Builder configureHelpMenu(
                Consumer<JMenu> configurator
        ) {
            this.helpMenuConfigurator =
                    Objects.requireNonNull(
                            configurator,
                            "The Help menu configurator cannot be null."
                    );

            return this;
        }

        /**
         * Sets the action executed when Home is selected.
         *
         * @param action Home action.
         * @return This builder.
         */
        public Builder onHome(
                Runnable action
        ) {
            this.onHome =
                    Objects.requireNonNull(
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
        public Builder onExit(
                Runnable action
        ) {
            this.onExit =
                    Objects.requireNonNull(
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
        public Builder onAbout(
                Runnable action
        ) {
            this.onAbout =
                    Objects.requireNonNull(
                            action,
                            "The About action cannot be null."
                    );

            return this;
        }

        /**
         * Sets the action executed when the application language changes.
         *
         * @param action Language change action.
         * @return This builder.
         */
        public Builder onLanguageChanged(
                Consumer<Language> action
        ) {
            this.onLanguageChanged =
                    Objects.requireNonNull(
                            action,
                            "The language change action cannot be null."
                    );

            return this;
        }

        /**
         * Sets the action executed when a theme is applied.
         *
         * @param action Theme applied action.
         * @return This builder.
         */
        public Builder onThemeApplied(
                Consumer<String> action
        ) {
            this.onThemeApplied =
                    Objects.requireNonNull(
                            action,
                            "The theme applied action cannot be null."
                    );

            return this;
        }

        private void validateActions() {
            if (onHome == null) {
                throw new IllegalStateException(
                        "The Home action must be configured."
                );
            }

            if (onExit == null) {
                throw new IllegalStateException(
                        "The Exit action must be configured."
                );
            }

            if (onAbout == null) {
                throw new IllegalStateException(
                        "The About action must be configured."
                );
            }
        }

        /**
         * Builds the application menu bar.
         *
         * @return Configured application menu bar.
         */
        public JMenuBar build() {
            validateActions();

            JMenuBar menuBar =
                    MenusSwing.menuBar();

            JMenu fileMenu =
                    MenusSwing.menu(
                            I18nSwing.text(
                                    "menu.file"
                            ),
                            mnemonic(
                                    "menu.file.mnemonic"
                            )
                    );

            // MENU ITEMS

            JMenuItem homeItem =
                    MenusSwing.item(
                            I18nSwing.text(
                                    "menu.home"
                            ),
                            IconsSwing.load(
                                    IconsFugue.HOME
                            ),
                            mnemonic(
                                    "menu.home.mnemonic"
                            ),
                            onHome
                    );

            fileMenu.add(
                    homeItem
            );

            if (fileMenuConfigurator != null) {
                fileMenuConfigurator.accept(
                        fileMenu
                );
            }

            fileMenu.addSeparator();

            JMenuItem exitItem =
                    MenusSwing.item(
                            I18nSwing.text(
                                    "menu.exit_desktop"
                            ),
                            IconsSwing.load(
                                    IconsFugue.DOOR_OPEN_OUT
                            ),
                            mnemonic(
                                    "menu.exit_desktop.mnemonic"
                            ),
                            onExit
                    );

            fileMenu.add(
                    exitItem
            );

            JMenu viewMenu =
                    MenusSwing.menu(
                            I18nSwing.text(
                                    "menu.view"
                            ),
                            mnemonic(
                                    "menu.view.mnemonic"
                            )
                    );

            viewMenu.add(
                    onThemeApplied == null
                            ? ThemeMenuSwing.create(
                            componentToUpdate
                    )
                            : ThemeMenuSwing.create(
                            componentToUpdate,
                            onThemeApplied
                    )
            );

            if (viewMenuConfigurator != null) {
                viewMenuConfigurator.accept(
                        viewMenu
                );
            }

            JMenu settingsMenu =
                    MenusSwing.menu(
                            I18nSwing.text(
                                    "menu.settings"
                            ),
                            mnemonic(
                                    "menu.settings.mnemonic"
                            )
                    );

            settingsMenu.add(
                    onLanguageChanged == null
                            ? LanguageMenuSwing.create()
                            : LanguageMenuSwing.create(
                            onLanguageChanged
                    )
            );

            if (settingsMenuConfigurator != null) {
                settingsMenuConfigurator.accept(
                        settingsMenu
                );
            }

            JMenu helpMenu =
                    MenusSwing.menu(
                            I18nSwing.text(
                                    "menu.help"
                            ),
                            mnemonic(
                                    "menu.help.mnemonic"
                            )
                    );

            if (helpMenuConfigurator != null) {
                helpMenuConfigurator.accept(
                        helpMenu
                );
            }

            if (helpMenu.getItemCount() > 0) {
                helpMenu.addSeparator();
            }

            JMenuItem aboutItem =
                    MenusSwing.item(
                            I18nSwing.text(
                                    "menu.about"
                            ),
                            IconsSwing.load(
                                    IconsFugue.QUESTION
                            ),
                            mnemonic(
                                    "menu.about.mnemonic"
                            ),
                            onAbout
                    );

            helpMenu.add(
                    aboutItem
            );

            // BINDINGS

            I18nSwing.bind(
                    fileMenu,
                    (menu, _) -> {
                        menu.setText(
                                I18nSwing.text(
                                        "menu.file"
                                )
                        );

                        menu.setMnemonic(
                                mnemonic(
                                        "menu.file.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    viewMenu,
                    (menu, _) -> {
                        menu.setText(
                                I18nSwing.text(
                                        "menu.view"
                                )
                        );

                        menu.setMnemonic(
                                mnemonic(
                                        "menu.view.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    settingsMenu,
                    (menu, _) -> {
                        menu.setText(
                                I18nSwing.text(
                                        "menu.settings"
                                )
                        );

                        menu.setMnemonic(
                                mnemonic(
                                        "menu.settings.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    helpMenu,
                    (menu, _) -> {
                        menu.setText(
                                I18nSwing.text(
                                        "menu.help"
                                )
                        );

                        menu.setMnemonic(
                                mnemonic(
                                        "menu.help.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    homeItem,
                    (item, _) -> {
                        item.setText(
                                I18nSwing.text(
                                        "menu.home"
                                )
                        );

                        item.setMnemonic(
                                mnemonic(
                                        "menu.home.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    exitItem,
                    (item, _) -> {
                        item.setText(
                                I18nSwing.text(
                                        "menu.exit_desktop"
                                )
                        );

                        item.setMnemonic(
                                mnemonic(
                                        "menu.exit_desktop.mnemonic"
                                )
                        );
                    }
            );

            I18nSwing.bind(
                    aboutItem,
                    (item, _) -> {
                        item.setText(
                                I18nSwing.text(
                                        "menu.about"
                                )
                        );

                        item.setMnemonic(
                                mnemonic(
                                        "menu.about.mnemonic"
                                )
                        );
                    }
            );

            menuBar.add(
                    fileMenu
            );

            menuBar.add(
                    viewMenu
            );

            menuBar.add(
                    settingsMenu
            );

            menuBar.add(
                    helpMenu
            );

            return menuBar;
        }
    }
}