package io.github.agarriga18696.andreuutils.core;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the language used by Andreu Utils.
 * <p>
 * Language change listeners can be registered to react when the active
 * language changes.
 * <p>
 * Listeners are executed synchronously on the same thread that calls
 * {@link #setLanguage(Language)}.
 *
 * @author Andreu
 * @version 2.0
 */
public final class LanguageManager {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final Logger LOGGER =
            Logger.getLogger(LanguageManager.class.getName());

    private static final String CORE_BUNDLE =
            "io.github.agarriga18696.andreuutils.i18n.core";

    // ----------------------------------------
    // STATE
    // ----------------------------------------
    private static final CopyOnWriteArrayList<Consumer<Language>> LISTENERS =
            new CopyOnWriteArrayList<>();
    private static volatile LanguageState state =
            createState(Language.ENGLISH);

    private LanguageManager() {
        // Utility class
    }

    // ----------------------------------------
    // LANGUAGE
    // ----------------------------------------

    /**
     * Returns the currently configured language.
     *
     * @return Current language.
     */
    public static Language getLanguage() {
        return state.language();
    }

    /**
     * Sets the language used by Andreu Utils.
     * <p>
     * Registered language change listeners are notified after the language
     * and its resource bundle have been updated.
     * <p>
     * If the specified language is already active, no change is performed and
     * listeners are not notified.
     *
     * @param language Language to use.
     * @throws NullPointerException if {@code language} is {@code null}.
     */
    public static synchronized void setLanguage(
            Language language
    ) {

        Objects.requireNonNull(
                language,
                "Language cannot be null."
        );

        if (state.language() == language) {
            return;
        }

        state = createState(language);

        notifyLanguageChanged(language);
    }

    // ----------------------------------------
    // LISTENERS
    // ----------------------------------------

    /**
     * Registers a listener that is notified whenever the active language
     * changes.
     * <p>
     * The listener receives the newly selected language and is executed on the
     * same thread that calls {@link #setLanguage(Language)}.
     *
     * @param listener Listener to register.
     * @throws NullPointerException if {@code listener} is {@code null}.
     */
    public static void addLanguageChangeListener(
            Consumer<Language> listener
    ) {

        Objects.requireNonNull(
                listener,
                "Language change listener cannot be null."
        );

        LISTENERS.addIfAbsent(listener);
    }

    /**
     * Removes a previously registered language change listener.
     * <p>
     * If the listener is not registered, this method has no effect.
     *
     * @param listener Listener to remove.
     */
    public static void removeLanguageChangeListener(
            Consumer<Language> listener
    ) {

        if (listener == null) {
            return;
        }

        LISTENERS.remove(listener);
    }

    // ----------------------------------------
    // TRANSLATION
    // ----------------------------------------

    /**
     * Returns translated text from the core language bundle.
     *
     * @param key       Translation key.
     * @param arguments Optional values inserted into the translated text.
     * @return Translated text.
     */
    public static String text(
            String key,
            Object... arguments
    ) {

        LanguageState currentState = state;

        String pattern =
                currentState.bundle()
                        .getString(key);

        if (arguments.length == 0) {
            return pattern;
        }

        MessageFormat formatter =
                new MessageFormat(
                        pattern,
                        currentState.language().locale()
                );

        return formatter.format(arguments);
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    private static LanguageState createState(
            Language language
    ) {

        return new LanguageState(
                language,
                ResourceBundle.getBundle(
                        CORE_BUNDLE,
                        language.locale()
                )
        );
    }

    private static void notifyLanguageChanged(
            Language language
    ) {

        for (Consumer<Language> listener : LISTENERS) {

            try {

                listener.accept(language);

            } catch (RuntimeException exception) {

                LOGGER.log(
                        Level.WARNING,
                        exception,
                        () -> "Error while notifying language change listener."
                );
            }
        }
    }

    // ----------------------------------------
    // INTERNAL STATE
    // ----------------------------------------

    private record LanguageState(
            Language language,
            ResourceBundle bundle
    ) {
    }

}