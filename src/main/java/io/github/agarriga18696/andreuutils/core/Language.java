package io.github.agarriga18696.andreuutils.core;

import java.util.Locale;

/**
 * Languages supported by Andreu Utils.
 *
 * @author Andreu
 * @version 1.0
 */
public enum Language {

    ENGLISH(Locale.ENGLISH),
    SPANISH(Locale.of("es")),
    CATALAN(Locale.of("ca"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns the locale associated with this language.
     *
     * @return The language locale.
     */
    public Locale locale() {
        return locale;
    }

}