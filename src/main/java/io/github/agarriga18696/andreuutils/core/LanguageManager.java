package io.github.agarriga18696.andreuutils.core;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Manages the language used by Andreu Utils.
 *
 * @author Andreu
 * @version 1.0
 */
public final class LanguageManager {

    private static final String CORE_BUNDLE = "io.github.agarriga18696.andreuutils.i18n.core";

    private static Language language = Language.ENGLISH;
    private static ResourceBundle coreBundle = loadCoreBundle();

    private LanguageManager() {
        /* This utility class should not be instantiated */
    }

    /**
     * Returns the currently configured language.
     *
     * @return The current language.
     */
    public static Language getLanguage() {
        return language;
    }

    /**
     * Sets the language used by Andreu Utils.
     *
     * @param language Language to use.
     */
    public static void setLanguage(Language language) {
        LanguageManager.language = Objects.requireNonNull(language);
        coreBundle = loadCoreBundle();
    }

    /**
     * Returns a translated text from the core language bundle.
     *
     * @param key       Translation key.
     * @param arguments Optional values inserted into the translated text.
     * @return The translated text.
     */
    public static String text(String key, Object... arguments) {
        String pattern = coreBundle.getString(key);

        if (arguments.length == 0) {
            return pattern;
        }

        MessageFormat formatter = new MessageFormat(pattern, language.locale());

        return formatter.format(arguments);
    }

    private static ResourceBundle loadCoreBundle() {
        return ResourceBundle.getBundle(
                CORE_BUNDLE,
                language.locale()
        );
    }

}