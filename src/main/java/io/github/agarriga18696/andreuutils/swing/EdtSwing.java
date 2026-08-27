package io.github.agarriga18696.andreuutils.swing;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

/**
 * Utility class for executing code on the Swing Event Dispatch Thread (EDT).
 * <p>
 * Code that modifies Swing components or {@code UIManager} state should be
 * executed on the EDT. This class simplifies the common pattern of checking
 * whether the current thread is already the EDT and delegating to
 * {@link SwingUtilities} otherwise.
 *
 * @author Andreu
 * @version 2.0
 */
public final class EdtSwing {

    // ----------------------------------------
    // STATIC ATTRIBUTES
    // ----------------------------------------

    private static final Logger LOGGER =
            Logger.getLogger(EdtSwing.class.getName());

    private EdtSwing() {
        // Utility class
    }

    // ----------------------------------------
    // EDT EXECUTION
    // ----------------------------------------

    /**
     * Executes a task synchronously on the EDT.
     * <p>
     * If the current thread is already the EDT, the task is executed immediately.
     * Otherwise, it is delegated to
     * {@link SwingUtilities#invokeAndWait(Runnable)}, blocking the calling thread
     * until the task has finished.
     * <p>
     * This variant is appropriate when the caller needs the task to be completed
     * before continuing, for example when applying a Look and Feel before using
     * its result.
     *
     * @param task Task to execute on the EDT.
     */
    public static void runAndWait(Runnable task) {

        if (task == null) {
            return;
        }

        if (SwingUtilities.isEventDispatchThread()) {
            task.run();
            return;
        }

        try {
            SwingUtilities.invokeAndWait(task);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(
                    Level.WARNING,
                    e,
                    () -> "Thread interrupted while waiting for the EDT."
            );

        } catch (InvocationTargetException e) {
            LOGGER.log(
                    Level.WARNING,
                    e.getCause(),
                    () -> "Exception thrown while executing task on the EDT."
            );
        }
    }

    /**
     * Queues a task for asynchronous execution on the EDT.
     * <p>
     * Equivalent to {@link SwingUtilities#invokeLater(Runnable)}. The task is
     * executed at a later point and the calling thread continues immediately.
     *
     * @param task Task to queue for execution.
     */
    public static void runLater(Runnable task) {

        if (task == null) {
            return;
        }

        SwingUtilities.invokeLater(task);
    }

    // ----------------------------------------
    // QUERIES
    // ----------------------------------------

    /**
     * Returns whether the current thread is the EDT.
     *
     * @return {@code true} if the current thread is the Swing Event Dispatch Thread.
     */
    public static boolean isEdt() {
        return SwingUtilities.isEventDispatchThread();
    }

}
