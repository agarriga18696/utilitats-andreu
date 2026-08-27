package io.github.agarriga18696.andreuutils.swing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JMenu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

class ThemeMenuSwingTest {

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    @Test
    void refreshesMenuWhenLanguageChanges() {

        LanguageManager.setLanguage(Language.ENGLISH);

        JMenu menu =
                ThemeMenuSwing.create(null);

        assertEquals(
                "Themes",
                menu.getText()
        );

        assertEquals(
                "System",
                menu.getItem(0).getText()
        );

        LanguageManager.setLanguage(
                Language.SPANISH
        );

        assertEquals(
                "Temas",
                menu.getText()
        );

        assertEquals(
                "Sistema",
                menu.getItem(0).getText()
        );
    }

}