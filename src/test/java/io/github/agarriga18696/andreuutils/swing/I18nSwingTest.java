package io.github.agarriga18696.andreuutils.swing;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link I18nSwing}.
 *
 * @author Andreu
 * @version 1.0
 */
class I18nSwingTest {

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    @Test
    void returnsEnglishText() {
        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals(
                "Close",
                I18nSwing.text("dialog.close")
        );
    }

    @Test
    void returnsSpanishText() {
        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals(
                "Cerrar",
                I18nSwing.text("dialog.close")
        );
    }

    @Test
    void returnsCatalanText() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "Tancar",
                I18nSwing.text("dialog.close")
        );
    }

    @Test
    void formatsCatalanMessageWithApostrophe() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "El fitxer \"novel.txt\" ja existeix.\nVols sobreescriure'l?",
                I18nSwing.text(
                        "dialog.confirm_overwrite_message",
                        "novel.txt"
                )
        );
    }

    @Test
    void formatsCatalanUnexpectedErrorMessage() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "S'ha produït un error inesperat:\nBoom",
                I18nSwing.text(
                        "dialog.unexpected_error_message",
                        "Boom"
                )
        );
    }

    @Test
    void loadsEnglishLanguageMenuTexts() {

        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals(
                "Language",
                I18nSwing.text("menu.language")
        );

        assertEquals(
                "English",
                I18nSwing.text("language.english")
        );

        assertEquals(
                "Spanish",
                I18nSwing.text("language.spanish")
        );

        assertEquals(
                "Catalan",
                I18nSwing.text("language.catalan")
        );
    }

    @Test
    void loadsSpanishLanguageMenuTexts() {

        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals(
                "Idioma",
                I18nSwing.text("menu.language")
        );

        assertEquals(
                "Inglés",
                I18nSwing.text("language.english")
        );

        assertEquals(
                "Español",
                I18nSwing.text("language.spanish")
        );

        assertEquals(
                "Catalán",
                I18nSwing.text("language.catalan")
        );
    }

    @Test
    void loadsCatalanLanguageMenuTexts() {

        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "Idioma",
                I18nSwing.text("menu.language")
        );

        assertEquals(
                "Anglès",
                I18nSwing.text("language.english")
        );

        assertEquals(
                "Espanyol",
                I18nSwing.text("language.spanish")
        );

        assertEquals(
                "Català",
                I18nSwing.text("language.catalan")
        );
    }

    @Test
    void loadsEnglishApplicationMenuTexts() {

        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals(
                "File",
                I18nSwing.text("menu.file")
        );

        assertEquals(
                "Help",
                I18nSwing.text("menu.help")
        );
    }

    @Test
    void loadsSpanishApplicationMenuTexts() {

        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals(
                "Archivo",
                I18nSwing.text("menu.file")
        );

        assertEquals(
                "Ayuda",
                I18nSwing.text("menu.help")
        );
    }

    @Test
    void loadsCatalanApplicationMenuTexts() {

        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "Fitxer",
                I18nSwing.text("menu.file")
        );

        assertEquals(
                "Ajuda",
                I18nSwing.text("menu.help")
        );
    }

    @Test
    void loadsEnglishBaseMenuTexts() {

        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals("View", I18nSwing.text("menu.view"));
        assertEquals("Settings", I18nSwing.text("menu.settings"));
        assertEquals("Home", I18nSwing.text("menu.home"));
        assertEquals("Exit to desktop", I18nSwing.text("menu.exit_desktop"));
        assertEquals("About...", I18nSwing.text("menu.about"));
    }

    @Test
    void loadsSpanishBaseMenuTexts() {

        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals("Ver", I18nSwing.text("menu.view"));
        assertEquals("Configuración", I18nSwing.text("menu.settings"));
        assertEquals("Inicio", I18nSwing.text("menu.home"));
        assertEquals("Salir al escritorio", I18nSwing.text("menu.exit_desktop"));
        assertEquals("Acerca de...", I18nSwing.text("menu.about"));
    }

    @Test
    void loadsCatalanBaseMenuTexts() {

        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals("Visualitza", I18nSwing.text("menu.view"));
        assertEquals("Configuració", I18nSwing.text("menu.settings"));
        assertEquals("Inici", I18nSwing.text("menu.home"));
        assertEquals("Sortir a l'escriptori", I18nSwing.text("menu.exit_desktop"));
        assertEquals("Quant a...", I18nSwing.text("menu.about"));
    }

}
