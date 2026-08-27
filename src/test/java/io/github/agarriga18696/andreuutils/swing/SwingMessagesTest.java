package io.github.agarriga18696.andreuutils.swing;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link SwingMessages}.
 *
 * @author Andreu
 * @version 1.0
 */
class SwingMessagesTest {

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    @Test
    void returnsEnglishText() {
        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals(
                "Close",
                SwingMessages.text("dialog.close")
        );
    }

    @Test
    void returnsSpanishText() {
        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals(
                "Cerrar",
                SwingMessages.text("dialog.close")
        );
    }

    @Test
    void returnsCatalanText() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "Tancar",
                SwingMessages.text("dialog.close")
        );
    }

    @Test
    void formatsCatalanMessageWithApostrophe() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "El fitxer \"novel.txt\" ja existeix.\nVols sobreescriure'l?",
                SwingMessages.text(
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
                SwingMessages.text(
                        "dialog.unexpected_error_message",
                        "Boom"
                )
        );
    }

}