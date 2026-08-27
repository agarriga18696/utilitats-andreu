package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class LanguageManagerTest {

    // ----------------------------------------
    // CLEANUP
    // ----------------------------------------

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    // ----------------------------------------
    // LANGUAGE
    // ----------------------------------------

    @Test
    void loadsEnglishLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);

        assertEquals(
                "English",
                LanguageManager.text("test.language")
        );
    }

    @Test
    void loadsSpanishLanguage() {
        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals(
                "Español",
                LanguageManager.text("test.language")
        );
    }

    @Test
    void loadsCatalanLanguage() {
        LanguageManager.setLanguage(Language.CATALAN);

        assertEquals(
                "Català",
                LanguageManager.text("test.language")
        );
    }

    // ----------------------------------------
    // LANGUAGE CHANGE LISTENERS
    // ----------------------------------------

    @Test
    void notifiesListenerWhenLanguageChanges() {

        LanguageManager.setLanguage(Language.ENGLISH);

        AtomicReference<Language> notifiedLanguage =
                new AtomicReference<>();

        Consumer<Language> listener =
                notifiedLanguage::set;

        LanguageManager.addLanguageChangeListener(listener);

        try {

            LanguageManager.setLanguage(
                    Language.SPANISH
            );

            assertEquals(
                    Language.SPANISH,
                    notifiedLanguage.get()
            );

        } finally {

            LanguageManager.removeLanguageChangeListener(
                    listener
            );
        }
    }

    @Test
    void doesNotNotifyListenerWhenLanguageDoesNotChange() {

        LanguageManager.setLanguage(Language.ENGLISH);

        AtomicInteger notificationCount =
                new AtomicInteger();

        Consumer<Language> listener =
                _ -> notificationCount.incrementAndGet();

        LanguageManager.addLanguageChangeListener(listener);

        try {

            LanguageManager.setLanguage(
                    Language.ENGLISH
            );

            assertEquals(
                    0,
                    notificationCount.get()
            );

        } finally {

            LanguageManager.removeLanguageChangeListener(
                    listener
            );
        }
    }

    @Test
    void removedListenerIsNotNotified() {

        LanguageManager.setLanguage(Language.ENGLISH);

        AtomicInteger notificationCount =
                new AtomicInteger();

        Consumer<Language> listener =
                _ -> notificationCount.incrementAndGet();

        LanguageManager.addLanguageChangeListener(listener);
        LanguageManager.removeLanguageChangeListener(listener);

        LanguageManager.setLanguage(
                Language.CATALAN
        );

        assertEquals(
                0,
                notificationCount.get()
        );
    }

}