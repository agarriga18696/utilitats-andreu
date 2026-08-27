package io.github.agarriga18696.andreuutils.swing;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

/**
 * Base class for creating Swing GUI applications.
 * <p>
 * Provides the basic lifecycle of a Swing application, including hooks before
 * and after initialization, automatic handling of uncaught exceptions on the
 * EDT, initial language configuration and support for applying a Look and Feel
 * before initialization.
 *
 * @author Andreu
 * @version 2.0
 */
public abstract class GuiApplicationBase {

    // ----------------------------------------
    // STATIC ATTRIBUTES
    // ----------------------------------------

    private static final Logger LOGGER =
            Logger.getLogger(GuiApplicationBase.class.getName());

    // ----------------------------------------
    // CONFIGURATION
    // ----------------------------------------

    /**
     * Returns the initial language used by the application.
     *
     * @return The application language.
     */
    protected Language language() {
        return Language.ENGLISH;
    }

    // ----------------------------------------
    // LIFECYCLE HOOKS
    // ----------------------------------------

    /**
     * Hook called immediately before {@link #initialize()}.
     * <p>
     * Subclasses may override this method to perform configuration tasks before
     * initialization, such as loading properties or initializing services.
     * The default implementation does nothing.
     */
    protected void beforeInitialize() {
        // Default implementation does nothing
    }

    /**
     * Initializes and builds the graphical user interface.
     * <p>
     * Components should be created, configured and added to the main container
     * in this method. Subclasses must provide the implementation.
     */
    protected abstract void initialize();

    /**
     * Hook called immediately after {@link #initialize()}.
     * <p>
     * Subclasses may override this method to perform tasks after initialization,
     * such as displaying the main window or starting a timer.
     * The default implementation does nothing.
     */
    protected void afterInitialize() {
        // Default implementation does nothing
    }

    // ----------------------------------------
    // EXECUTION
    // ----------------------------------------

    /**
     * Runs the graphical user interface initialization on the EDT after
     * configuring the application language and installing an uncaught
     * exception handler.
     */
    public final void run() {
        LanguageManager.setLanguage(language());
        installUncaughtExceptionHandler();
        SwingUtilities.invokeLater(this::runLifecycle);
    }

    /**
     * Applies the specified Look and Feel and then initializes the graphical
     * user interface on the EDT, using the same lifecycle as {@link #run()}.
     *
     * @param lookAndFeelClassName Fully qualified class name of the Look and Feel
     *                             to apply.
     */
    public final void runWithLookAndFeel(String lookAndFeelClassName) {
        LanguageManager.setLanguage(language());
        installUncaughtExceptionHandler();

        SwingUtilities.invokeLater(() -> {
            LookAndFeelSwing.aplicar(lookAndFeelClassName);
            runLifecycle();
        });
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    /**
     * Runs the complete application lifecycle: the pre-initialization hook,
     * initialization and the post-initialization hook.
     * <p>
     * This method must always be called from the EDT.
     */
    private void runLifecycle() {
        beforeInitialize();
        initialize();
        afterInitialize();
    }

    /**
     * Installs an uncaught exception handler for all threads, including the EDT.
     * When an uncaught exception occurs, it is logged and an error dialog is
     * displayed to the user.
     */
    private void installUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            LOGGER.log(
                    Level.SEVERE,
                    exception,
                    () -> "Uncaught exception in thread: " + thread.getName()
            );

            EdtSwing.runLater(() ->
                    DialogsSwing.error(
                            null,
                            SwingMessages.text("dialog.unexpected_error_title"),
                            SwingMessages.text(
                                    "dialog.unexpected_error_message",
                                    exception.getMessage()
                            )
                    )
            );
        });
    }

}