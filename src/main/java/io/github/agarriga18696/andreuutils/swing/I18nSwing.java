package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

/**
 * Internal utility for retrieving localized Swing messages and binding Swing
 * components to language changes.
 *
 * @author Andreu
 * @version 1.1
 */
final class I18nSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final String BUNDLE_NAME =
            "io.github.agarriga18696.andreuutils.i18n.swing";

    private I18nSwing() {
        // Utility class
    }

    // ----------------------------------------
    // TRANSLATION
    // ----------------------------------------

    static String text(
            String key,
            Object... arguments
    ) {

        Language language =
                LanguageManager.getLanguage();

        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        BUNDLE_NAME,
                        language.locale()
                );

        String pattern =
                bundle.getString(key);

        if (arguments.length == 0) {
            return pattern;
        }

        MessageFormat formatter =
                new MessageFormat(
                        pattern,
                        language.locale()
                );

        return formatter.format(arguments);
    }

    // ----------------------------------------
    // LANGUAGE BINDING
    // ----------------------------------------

    /**
     * Binds a Swing component to language changes.
     * <p>
     * The refresh action is executed on the EDT whenever the active language
     * changes.
     * <p>
     * The component is stored using a weak reference so that registering the
     * listener does not prevent the component from being garbage collected.
     * Once the component is no longer available, the listener automatically
     * unregisters itself.
     *
     * @param <T>           Component type.
     * @param component     Component to bind.
     * @param refreshAction Action used to refresh the component.
     */
    static <T extends Component> void bind(
            T component,
            BiConsumer<T, Language> refreshAction
    ) {

        Objects.requireNonNull(
                component,
                "Component cannot be null."
        );

        Objects.requireNonNull(
                refreshAction,
                "Refresh action cannot be null."
        );

        LanguageManager.addLanguageChangeListener(
                new LanguageBinding<>(
                        component,
                        refreshAction
                )
        );
    }

    // ----------------------------------------
    // LANGUAGE BINDING
    // ----------------------------------------

    private record LanguageBinding<T extends Component>(
            WeakReference<T> componentReference,
            BiConsumer<T, Language> refreshAction
    ) implements Consumer<Language> {

            private LanguageBinding(
                    T component,
                    BiConsumer<T, Language> refreshAction
            ) {

                this(new WeakReference<>(component), refreshAction);
            }

            @Override
            public void accept(
                    Language language
            ) {

                T component =
                        componentReference.get();

                if (component == null) {

                    LanguageManager.removeLanguageChangeListener(
                            this
                    );

                    return;
                }

                EdtSwing.runAndWait(
                        () -> refreshAction.accept(
                                component,
                                language
                        )
                );
            }
        }

}