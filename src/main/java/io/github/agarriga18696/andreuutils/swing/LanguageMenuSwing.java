package io.github.agarriga18696.andreuutils.swing;

import java.awt.event.KeyEvent;
import java.lang.ref.WeakReference;
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
 * @version 1.1
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

        LanguageManager.addLanguageChangeListener(
                new LanguageMenuListener(
                        languageMenu,
                        items
                )
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

        if (language == LanguageManager.getLanguage()) {
            return;
        }

        LanguageManager.setLanguage(language);

        onLanguageChanged.accept(language);
    }

    private static void refreshMenu(
            JMenu languageMenu,
            Map<Language, JRadioButtonMenuItem> items,
            Language currentLanguage
    ) {

        languageMenu.setText(
                I18nSwing.text("menu.language")
        );

        for (Map.Entry<Language, JRadioButtonMenuItem> entry : items.entrySet()) {

            JRadioButtonMenuItem item =
                    entry.getValue();

            item.setText(
                    getLanguageName(entry.getKey())
            );

            item.setSelected(
                    entry.getKey() == currentLanguage
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

    // ----------------------------------------
    // LANGUAGE LISTENER
    // ----------------------------------------

    private record LanguageMenuListener(
            WeakReference<JMenu> menuReference,
            Map<Language, WeakReference<JRadioButtonMenuItem>> itemReferences
    ) implements Consumer<Language> {

            private LanguageMenuListener(
                    JMenu languageMenu,
                    Map<Language, JRadioButtonMenuItem> items
            ) {

                this(new WeakReference<>(languageMenu), new EnumMap<>(Language.class));

                for (Map.Entry<Language, JRadioButtonMenuItem> entry : items.entrySet()) {

                    this.itemReferences.put(
                            entry.getKey(),
                            new WeakReference<>(entry.getValue())
                    );
                }
            }

            @Override
            public void accept(
                    Language language
            ) {

                JMenu languageMenu =
                        menuReference.get();

                if (languageMenu == null) {

                    LanguageManager.removeLanguageChangeListener(
                            this
                    );

                    return;
                }

                EdtSwing.runAndWait(() -> {

                    languageMenu.setText(
                            I18nSwing.text("menu.language")
                    );

                    for (
                            Map.Entry<Language, WeakReference<JRadioButtonMenuItem>> entry
                            : itemReferences.entrySet()
                    ) {

                        JRadioButtonMenuItem item =
                                entry.getValue().get();

                        if (item == null) {
                            continue;
                        }

                        item.setText(
                                getLanguageName(entry.getKey())
                        );

                        item.setSelected(
                                entry.getKey() == language
                        );
                    }
                });
            }
        }

}
