package io.github.agarriga18696.andreuutils.swing;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Internal utility for retrieving localized Swing messages.
 *
 * @author Andreu
 * @version 1.0
 */
final class I18nSwing {

    private static final String BUNDLE = "io.github.agarriga18696.andreuutils.i18n.swing";

    private I18nSwing() {
        // Utility class
    }

    static String text(String key, Object... arguments) {

        Language language = LanguageManager.getLanguage();

        ResourceBundle bundle =
                ResourceBundle.getBundle(BUNDLE, language.locale());

        String pattern = bundle.getString(key);

        if (arguments.length == 0) {
            return pattern;
        }

        MessageFormat formatter =
                new MessageFormat(pattern, language.locale());

        return formatter.format(arguments);
    }

}