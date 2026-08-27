package io.github.agarriga18696.andreuutils.swing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JMenu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

class LanguageMenuSwingTest {

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    @Test
    void createsMenuUsingCurrentLanguage() {

        LanguageManager.setLanguage(Language.ENGLISH);

        JMenu menu =
                LanguageMenuSwing.create();

        assertEquals(
                "Language",
                menu.getText()
        );

        assertEquals(
                3,
                menu.getItemCount()
        );

        assertEquals(
                "English",
                menu.getItem(0).getText()
        );

        assertEquals(
                "Spanish",
                menu.getItem(1).getText()
        );

        assertEquals(
                "Catalan",
                menu.getItem(2).getText()
        );

        assertTrue(
                menu.getItem(0).isSelected()
        );
    }

    @Test
    void changesLanguageAndRefreshesMenuTexts() {

        LanguageManager.setLanguage(Language.ENGLISH);

        AtomicReference<Language> changedLanguage =
                new AtomicReference<>();

        JMenu menu =
                LanguageMenuSwing.create(
                        changedLanguage::set
                );

        menu.getItem(1).doClick();

        assertEquals(
                Language.SPANISH,
                LanguageManager.getLanguage()
        );

        assertEquals(
                Language.SPANISH,
                changedLanguage.get()
        );

        assertEquals(
                "Idioma",
                menu.getText()
        );

        assertEquals(
                "Inglés",
                menu.getItem(0).getText()
        );

        assertEquals(
                "Español",
                menu.getItem(1).getText()
        );

        assertEquals(
                "Catalán",
                menu.getItem(2).getText()
        );

        assertTrue(
                menu.getItem(1).isSelected()
        );
    }

    @Test
    void refreshesMenuWhenLanguageChangesExternally() {

        LanguageManager.setLanguage(Language.ENGLISH);

        JMenu menu =
                LanguageMenuSwing.create();

        LanguageManager.setLanguage(
                Language.CATALAN
        );

        assertEquals(
                "Idioma",
                menu.getText()
        );

        assertEquals(
                "Anglès",
                menu.getItem(0).getText()
        );

        assertEquals(
                "Espanyol",
                menu.getItem(1).getText()
        );

        assertEquals(
                "Català",
                menu.getItem(2).getText()
        );

        assertTrue(
                menu.getItem(2).isSelected()
        );
    }

}