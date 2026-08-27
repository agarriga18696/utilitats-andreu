package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageManagerTest {

    @Test
    void loadsEnglishLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
        assertEquals("English", LanguageManager.text("test.language"));
    }

    @Test
    void loadsSpanishLanguage() {
        LanguageManager.setLanguage(Language.SPANISH);
        assertEquals("Español", LanguageManager.text("test.language"));
    }

    @Test
    void loadsCatalanLanguage() {
        LanguageManager.setLanguage(Language.CATALAN);
        assertEquals("Català", LanguageManager.text("test.language"));
    }

}