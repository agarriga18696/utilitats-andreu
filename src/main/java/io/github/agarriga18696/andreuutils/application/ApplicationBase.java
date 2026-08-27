package io.github.agarriga18696.andreuutils.application;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

/**
 * Base class for creating console applications.
 *
 * @author Andreu
 * @version 2.1
 */
public abstract class ApplicationBase {

    /// /////////////////////////////////////////
    /// ABSTRACT METHODS
    /// /////////////////////////////////////////

    protected abstract ControllerBase controller();

    protected abstract ViewBase view(ControllerBase controller);

    ////////////////////////////////////////////
    /// CONFIGURATION
    ////////////////////////////////////////////

    /**
     * Returns the initial language used by the application.
     *
     * @return The application language.
     */
    protected Language language() {
        return Language.ENGLISH;
    }

    ////////////////////////////////////////////
    /// PUBLIC METHODS
    ////////////////////////////////////////////

    /**
     * Runs the application.
     */
    public void run() {
        LanguageManager.setLanguage(language());

        ControllerBase controller = controller();
        controller.initialize();
        view(controller).showMenu();
    }

}