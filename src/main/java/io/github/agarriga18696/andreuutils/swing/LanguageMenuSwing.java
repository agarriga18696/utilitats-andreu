package io.github.agarriga18696.andreuutils.swing;

import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

/**
 * Utility class for creating language selection menus.
 *
 * @author Andreu
 * @version 1.0
 */
public final class LanguageMenuSwing {

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
        return create(_ -> {
        });
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

        JMenu languageMenu = MenusSwing.menu(
                I18nSwing.text("menu.language"),
                null,
                KeyEvent.VK_L
        );

        ButtonGroup languageGroup =
                new ButtonGroup();

        Map<Language, JRadioButtonMenuItem> items =
                new EnumMap<>(Language.class);

        for (Language language : Language.values()) {

            boolean selected =
                    language == LanguageManager.getLanguage();

            JRadioButtonMenuItem item =
                    MenusSwing.radioItem(
                            getLanguageName(language),
                            selected,
                            () -> applyLanguage(
                                    languageMenu,
                                    items,
                                    language,
                                    onLanguageChanged
                            )
                    );

            items.put(
                    language,
                    item
            );

            languageGroup.add(item);
            languageMenu.add(item);
        }

        return languageMenu;
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static void applyLanguage(
            JMenu languageMenu,
            Map<Language, JRadioButtonMenuItem> items,
            Language language,
            Consumer<Language> onLanguageChanged
    ) {

        if (language == LanguageManager.getLanguage()) {
            return;
        }

        LanguageManager.setLanguage(language);

        refreshTexts(
                languageMenu,
                items
        );

        onLanguageChanged.accept(language);
    }

    private static void refreshTexts(
            JMenu languageMenu,
            Map<Language, JRadioButtonMenuItem> items
    ) {

        languageMenu.setText(
                I18nSwing.text("menu.language")
        );

        for (Map.Entry<Language, JRadioButtonMenuItem> entry : items.entrySet()) {

            entry.getValue().setText(
                    getLanguageName(entry.getKey())
            );
        }
    }

    private static String getLanguageName(
            Language language
    ) {

        return switch (language) {
            case ENGLISH -> I18nSwing.text("language.english");

            case SPANISH -> I18nSwing.text("language.spanish");

            case CATALAN -> I18nSwing.text("language.catalan");
        };
    }

}