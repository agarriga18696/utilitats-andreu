package io.github.agarriga18696.andreuutils.swing;

import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

/**
 * Utility class for creating language selection menus.
 *
 * @author Andreu
 * @version 1.4
 */
public final class LanguageMenuSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final String LANGUAGE_PROPERTY =
            LanguageMenuSwing.class.getName() + ".language";

    private LanguageMenuSwing() {
        // Utility class
    }

    // ----------------------------------------
    // LANGUAGE MENU
    // ----------------------------------------

    /**
     * Creates a language selection menu.
     *
     * @return Created language menu.
     */
    public static JMenu create() {
        return create(
                _ -> {
                }
        );
    }

    /**
     * Creates a language selection menu.
     *
     * @param onLanguageChanged Action executed after the language changes.
     * @return Created language menu.
     */
    public static JMenu create(
            Consumer<Language> onLanguageChanged
    ) {

        Objects.requireNonNull(
                onLanguageChanged,
                "The language-changed action cannot be null."
        );

        JMenu languageMenu =
                MenusSwing.menu(
                        I18nSwing.text(
                                "menu.language"
                        ),
                        IconsSwing.load(
                                IconsFugue.GLOBE
                        ),
                        mnemonic(
                                "menu.language.mnemonic"
                        )
                );

        ButtonGroup languageGroup =
                new ButtonGroup();

        for (Language language : Language.values()) {

            JRadioButtonMenuItem item =
                    MenusSwing.radioItem(
                            getLanguageName(
                                    language
                            ),
                            getLanguageIcon(
                                    language
                            ),
                            language
                                    == LanguageManager.getLanguage(),
                            () -> applyLanguage(
                                    language,
                                    onLanguageChanged
                            )
                    );

            item.putClientProperty(
                    LANGUAGE_PROPERTY,
                    language
            );

            languageGroup.add(
                    item
            );

            languageMenu.add(
                    item
            );
        }

        I18nSwing.bind(
                languageMenu,
                LanguageMenuSwing::refreshMenu
        );

        return languageMenu;
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static void applyLanguage(
            Language language,
            Consumer<Language> onLanguageChanged
    ) {

        if (language
                == LanguageManager.getLanguage()) {

            return;
        }

        LanguageManager.setLanguage(
                language
        );

        onLanguageChanged.accept(
                language
        );
    }

    private static void refreshMenu(
            JMenu languageMenu,
            Language currentLanguage
    ) {

        languageMenu.setText(
                I18nSwing.text(
                        "menu.language"
                )
        );

        languageMenu.setMnemonic(
                mnemonic(
                        "menu.language.mnemonic"
                )
        );

        for (int index = 0;
             index < languageMenu.getItemCount();
             index++) {

            if (!(languageMenu.getItem(index)
                    instanceof JRadioButtonMenuItem item)) {

                continue;
            }

            Object value =
                    item.getClientProperty(
                            LANGUAGE_PROPERTY
                    );

            if (!(value instanceof Language language)) {
                continue;
            }

            item.setText(
                    getLanguageName(
                            language
                    )
            );

            item.setSelected(
                    language
                            == currentLanguage
            );
        }
    }

    private static String getLanguageName(
            Language language
    ) {

        return switch (language) {
            case ENGLISH -> I18nSwing.text(
                    "language.english"
            );

            case SPANISH -> I18nSwing.text(
                    "language.spanish"
            );

            case CATALAN -> I18nSwing.text(
                    "language.catalan"
            );
        };
    }

    private static Icon getLanguageIcon(
            Language language
    ) {

        return switch (language) {
            case ENGLISH -> IconsSwing.loadFlag(
                    IconsFlags.GB
            );

            case SPANISH -> IconsSwing.loadFlag(
                    IconsFlags.ES
            );

            case CATALAN -> IconsSwing.loadFlag(
                    IconsFlags.CATALONIA
            );
        };
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
}