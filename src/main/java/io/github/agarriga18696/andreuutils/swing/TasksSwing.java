package io.github.agarriga18696.andreuutils.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

/**
 * Utility class for executing background tasks in Swing applications.
 * <p>
 * Wraps the {@link SwingWorker} pattern to simplify the execution of
 * long-running tasks without blocking the Swing Event Dispatch Thread (EDT).
 * <p>
 * Completion and error callbacks are always executed on the EDT, making it
 * safe to update Swing components from them.
 *
 * @author Andreu
 * @version 2.0
 */
public final class TasksSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final Logger LOGGER =
            Logger.getLogger(TasksSwing.class.getName());

    private static final int PROGRESS_DIALOG_WIDTH = 320;
    private static final int PROGRESS_DIALOG_HEIGHT = 90;
    private static final int PROGRESS_DIALOG_GAP = 10;

    private TasksSwing() {
        // Utility class
    }

    // ----------------------------------------
    // BACKGROUND EXECUTION
    // ----------------------------------------

    /**
     * Executes a task in a background thread and invokes the success callback
     * on the EDT when the task completes.
     * <p>
     * If the task throws an exception, the exception is logged but is not
     * propagated. Use
     * {@link #runInBackground(Supplier, Consumer, Consumer)}
     * to handle failures explicitly.
     *
     * @param <T>       Result type.
     * @param task      Task to execute in the background.
     * @param onSuccess Callback invoked on the EDT with the task result.
     */
    public static <T> void runInBackground(
            Supplier<T> task,
            Consumer<T> onSuccess
    ) {

        runInBackground(
                task,
                onSuccess,
                null
        );
    }

    /**
     * Executes a task in a background thread and invokes either the success or
     * error callback on the EDT when the task finishes.
     *
     * @param <T>       Result type.
     * @param task      Task to execute in the background.
     * @param onSuccess Callback invoked on the EDT when the task completes successfully.
     * @param onError   Callback invoked on the EDT with the failure cause.
     *                  May be {@code null} if explicit error handling is not required.
     */
    public static <T> void runInBackground(
            Supplier<T> task,
            Consumer<T> onSuccess,
            Consumer<Throwable> onError
    ) {

        Objects.requireNonNull(
                task,
                "Task cannot be null."
        );

        Objects.requireNonNull(
                onSuccess,
                "Success callback cannot be null."
        );

        new SwingWorker<T, Void>() {

            @Override
            protected T doInBackground() {
                return task.get();
            }

            @Override
            protected void done() {
                handleResult(
                        this,
                        onSuccess,
                        onError
                );
            }

        }.execute();
    }

    // ----------------------------------------
    // PROGRESS DIALOG
    // ----------------------------------------

    /**
     * Executes a task in the background while displaying a modal dialog with
     * an indeterminate progress bar.
     * <p>
     * When the task completes, the dialog is closed and the success callback
     * is invoked on the EDT.
     * <p>
     * If the task throws an exception, the exception is logged but is not
     * propagated. Use
     * {@link #runWithProgressDialog(Component, String, Supplier, Consumer, Consumer)}
     * to handle failures explicitly.
     *
     * @param <T>       Result type.
     * @param parent    Parent component for the progress dialog. May be {@code null}.
     * @param title     Progress dialog title.
     * @param task      Task to execute in the background.
     * @param onSuccess Callback invoked on the EDT with the task result.
     */
    public static <T> void runWithProgressDialog(
            Component parent,
            String title,
            Supplier<T> task,
            Consumer<T> onSuccess
    ) {

        runWithProgressDialog(
                parent,
                title,
                task,
                onSuccess,
                null
        );
    }

    /**
     * Executes a task in the background while displaying a modal dialog with
     * an indeterminate progress bar.
     * <p>
     * When the task finishes, the dialog is closed and either the success or
     * error callback is invoked on the EDT.
     * <p>
     * The complete UI lifecycle is scheduled on the EDT using
     * {@link EdtSwing#runLater(Runnable)}. The worker is started before the
     * modal dialog is shown so that its completion can be processed by Swing's
     * nested modal event loop.
     *
     * @param <T>       Result type.
     * @param parent    Parent component for the progress dialog. May be {@code null}.
     * @param title     Progress dialog title.
     * @param task      Task to execute in the background.
     * @param onSuccess Callback invoked on the EDT when the task completes successfully.
     * @param onError   Callback invoked on the EDT with the failure cause.
     *                  May be {@code null} if explicit error handling is not required.
     */
    public static <T> void runWithProgressDialog(
            Component parent,
            String title,
            Supplier<T> task,
            Consumer<T> onSuccess,
            Consumer<Throwable> onError
    ) {

        Objects.requireNonNull(
                task,
                "Task cannot be null."
        );

        Objects.requireNonNull(
                onSuccess,
                "Success callback cannot be null."
        );

        EdtSwing.runLater(() -> {

            JDialog dialog =
                    createProgressDialog(
                            parent,
                            title
                    );

            SwingWorker<T, Void> worker =
                    new SwingWorker<>() {

                        @Override
                        protected T doInBackground() {
                            return task.get();
                        }

                        @Override
                        protected void done() {

                            /*
                             * Close the modal dialog before invoking callbacks so
                             * that the nested modal event loop is released first.
                             */
                            dialog.dispose();

                            handleResult(
                                    this,
                                    onSuccess,
                                    onError
                            );
                        }
                    };

            /*
             * Start the worker before showing the modal dialog. Once setVisible()
             * enters its nested event loop, done() can be processed on the EDT
             * and close the dialog when the task finishes.
             */
            worker.execute();

            dialog.setVisible(true);
        });
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

	/**
	 * Retrieves the result of a completed {@link SwingWorker} and forwards it
	 * to the appropriate callback.
	 * <p>
	 * This method is intended to be called from the worker's {@code done()}
	 * method, which runs on the EDT.
	 * <p>
	 * If {@link SwingWorker#get()} throws an {@link ExecutionException}, the
	 * exception is unwrapped so that the original cause is forwarded to the
	 * error callback.
	 *
	 * @param <T> Result type.
	 * @param worker Worker whose result should be retrieved.
	 * @param onSuccess Callback invoked when the task completes successfully.
	 * @param onError Callback invoked when the task fails. May be {@code null}.
	 */
    private static <T> void handleResult(
            SwingWorker<T, Void> worker,
            Consumer<T> onSuccess,
            Consumer<Throwable> onError
    ) {

        try {

            onSuccess.accept(
                    worker.get()
            );

        } catch (InterruptedException exception) {

            Thread.currentThread().interrupt();

            LOGGER.log(
                    Level.WARNING,
                    exception,
                    () -> "Background task interrupted."
            );

            if (onError != null) {
                onError.accept(exception);
            }

        } catch (ExecutionException exception) {

            /*
             * ExecutionException wraps the exception originally thrown by
             * doInBackground(), so forward its actual cause to the callback.
             */
            Throwable cause =
                    exception.getCause();

            LOGGER.log(
                    Level.SEVERE,
                    cause,
                    () -> "Error while executing background task."
            );

            if (onError != null) {
                onError.accept(cause);
            }
        }
    }

    /**
     * Creates a modal dialog containing an indeterminate progress bar.
     * <p>
     * The dialog cannot be closed manually.
     *
     * @param parent Parent component. May be {@code null}.
     * @param title  Dialog title.
     * @return Created dialog, not yet visible.
     */
    private static JDialog createProgressDialog(
            Component parent,
            String title
    ) {

        Window owner =
                parent != null
                        ? SwingUtilities.getWindowAncestor(parent)
                        : null;

        JDialog dialog =
                owner != null
                        ? new JDialog(
                        owner,
                        title,
                        Dialog.ModalityType.APPLICATION_MODAL
                )
                        : new JDialog(
                        (Frame) null,
                        title,
                        true
                );

        JLabel label =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        JProgressBar progressBar =
                new JProgressBar();

        progressBar.setIndeterminate(true);

        dialog.setDefaultCloseOperation(
                WindowConstants.DO_NOTHING_ON_CLOSE
        );

        dialog.setLayout(
                new BorderLayout(
                        PROGRESS_DIALOG_GAP,
                        PROGRESS_DIALOG_GAP
                )
        );

        dialog.add(
                label,
                BorderLayout.NORTH
        );

        dialog.add(
                progressBar,
                BorderLayout.CENTER
        );

        dialog.setSize(
                PROGRESS_DIALOG_WIDTH,
                PROGRESS_DIALOG_HEIGHT
        );

        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        return dialog;
    }

}