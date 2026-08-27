package io.github.agarriga18696.andreuutils.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;

import java.io.File;

import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.github.agarriga18696.andreuutils.core.ArrayUtils;

/**
 * Utility class for displaying Swing dialogs.
 * <p>
 * All methods are safe to call from any thread. If a call is made outside the
 * EDT, it is automatically redirected using
 * {@link EdtSwing#runAndWait(Runnable)}.
 *
 * @author Andreu
 * @version 2.0
 */
public final class DialogsSwing {

    private DialogsSwing() {
        // Utility class
    }

    // ----------------------------------------
    // SIMPLE DIALOGS
    // ----------------------------------------

    /**
     * Displays an informational message.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Message to display.
     */
    public static void info(Component parent, String title, String message) {
        EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        ));
    }

    /**
     * Displays a warning message.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Message to display.
     */
    public static void warning(Component parent, String title, String message) {
        EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.WARNING_MESSAGE
        ));
    }

    /**
     * Displays an error message.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Message to display.
     */
    public static void error(Component parent, String title, String message) {
        EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE
        ));
    }

    /**
     * Displays a confirmation dialog with Yes and No options.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Confirmation message.
     * @return {@code true} if the user selects Yes.
     */
    public static boolean confirm(Component parent, String title, String message) {

        AtomicBoolean result = new AtomicBoolean(false);

        EdtSwing.runAndWait(() -> {
            Object[] options = {
                    I18nSwing.text("dialog.yes"),
                    I18nSwing.text("dialog.no")
            };

            int response = JOptionPane.showOptionDialog(
                    parent,
                    message,
                    title,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            result.set(response == JOptionPane.YES_OPTION);
        });

        return result.get();
    }

    // ----------------------------------------
    // LONG TEXT DIALOGS
    // ----------------------------------------

    /**
     * Displays a modal dialog containing long scrollable text.
     * <p>
     * The dialog is created and displayed on the EDT. If the call is made from
     * another thread, it is automatically redirected using
     * {@link EdtSwing#runAndWait(Runnable)}.
     * <p>
     * Since the dialog is modal, {@code setVisible(true)} blocks the EDT until
     * the user closes it. The calling thread is also blocked when it is not the
     * EDT.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param content Text to display.
     * @param width   Dialog width.
     * @param height  Dialog height.
     */
    public static void showLongText(
            Component parent,
            String title,
            String content,
            int width,
            int height
    ) {
        EdtSwing.runAndWait(() ->
                showLongTextDialog(parent, title, content, width, height)
        );
    }

    /**
     * Creates and displays the long-text dialog.
     * <p>
     * This method must always be called from the EDT.
     */
    private static void showLongTextDialog(
            Component parent,
            String title,
            String content,
            int width,
            int height
    ) {

        Window parentWindow = null;

        if (parent instanceof Window window) {
            parentWindow = window;

        } else if (parent != null) {
            parentWindow = SwingUtilities.getWindowAncestor(parent);
        }

        JDialog dialog = new JDialog(
                parentWindow,
                title,
                Dialog.ModalityType.APPLICATION_MODAL
        );

        dialog.setLayout(new BorderLayout());
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(parent);

        JTextArea contentArea = new JTextArea(content);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setCaretPosition(0);

        dialog.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JButton closeButton = new JButton(I18nSwing.text("dialog.close"));
        closeButton.addActionListener(_ -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // ----------------------------------------
    // INPUT DIALOGS
    // ----------------------------------------

    /**
     * Displays a text input dialog.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Message to display.
     * @return Text entered by the user, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<String> input(
            Component parent,
            String title,
            String message
    ) {

        AtomicReference<String> result = new AtomicReference<>();

        EdtSwing.runAndWait(() -> {
            String response = JOptionPane.showInputDialog(
                    parent,
                    message,
                    title,
                    JOptionPane.QUESTION_MESSAGE
            );

            result.set(response);
        });

        return Optional.ofNullable(result.get());
    }

    /**
     * Displays a text input dialog with a predefined value.
     *
     * @param parent       Parent component of the dialog.
     * @param title        Dialog title.
     * @param message      Message to display.
     * @param defaultValue Predefined value shown in the input field.
     * @return Text entered by the user, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<String> input(
            Component parent,
            String title,
            String message,
            String defaultValue
    ) {

        AtomicReference<String> result = new AtomicReference<>();

        EdtSwing.runAndWait(() -> {
            String response = (String) JOptionPane.showInputDialog(
                    parent,
                    message,
                    title,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    defaultValue
            );

            result.set(response);
        });

        return Optional.ofNullable(result.get());
    }

    // ----------------------------------------
    // OPTION SELECTION
    // ----------------------------------------

    /**
     * Displays a selection dialog containing a drop-down list of options.
     *
     * @param parent  Parent component of the dialog.
     * @param title   Dialog title.
     * @param message Message to display.
     * @param options Options to display.
     * @return Selected option, or {@link Optional#empty()} if cancelled or the
     * array is empty.
     */
    public static Optional<String> select(
            Component parent,
            String title,
            String message,
            String[] options
    ) {

        AtomicReference<String> result = new AtomicReference<>();

        if (ArrayUtils.isEmpty(options)) {
            return Optional.empty();
        }

        EdtSwing.runAndWait(() -> {
            String response = (String) JOptionPane.showInputDialog(
                    parent,
                    message,
                    title,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            result.set(response);
        });

        return Optional.ofNullable(result.get());
    }

    // ----------------------------------------
    // FILE CHOOSERS
    // ----------------------------------------

    /**
     * Displays a dialog for choosing where to save a file without an extension
     * filter.
     *
     * @param parent Parent component of the dialog.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseSaveFile(Component parent) {
        return chooseSaveFile(parent, null, null, null);
    }

    /**
     * Displays a dialog for choosing where to save a file using an extension
     * filter whose description is generated automatically.
     *
     * @param parent    Parent component of the dialog.
     * @param extension File extension without a leading dot, for example
     *                  {@code "txt"}.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseSaveFile(
            Component parent,
            String extension
    ) {
        return chooseSaveFile(
                parent,
                generateFilterDescription(extension),
                extension,
                null
        );
    }

    /**
     * Displays a dialog for choosing where to save a file using an extension
     * filter and description.
     *
     * @param parent      Parent component of the dialog.
     * @param description Filter description, for example {@code "Text files"}.
     * @param extension   File extension without a leading dot, for example
     *                    {@code "txt"}.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseSaveFile(
            Component parent,
            String description,
            String extension
    ) {
        return chooseSaveFile(parent, description, extension, null);
    }

    /**
     * Displays a dialog for choosing where to save a file.
     * <p>
     * If {@code extension} is not {@code null} and the selected file name does
     * not end with that extension, it is appended automatically.
     * <p>
     * If the selected file already exists, confirmation is requested before
     * overwriting it.
     *
     * @param parent      Parent component of the dialog.
     * @param description Filter description, or {@code null} for no filter.
     * @param extension   File extension without a leading dot, or {@code null} for
     *                    no filter.
     * @param defaultName Preselected file name, or {@code null} for none.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseSaveFile(
            Component parent,
            String description,
            String extension,
            String defaultName
    ) {
        return chooseSaveFile(
                parent,
                description,
                extension,
                defaultName,
                null
        );
    }

    /**
     * Displays a dialog for choosing where to save a file using an optional
     * initial directory.
     * <p>
     * If {@code extension} is not {@code null} and the selected file name does
     * not end with that extension, it is appended automatically.
     * <p>
     * If the selected file already exists, confirmation is requested before
     * overwriting it.
     *
     * @param parent           Parent component of the dialog.
     * @param description      Filter description, or {@code null} for no filter.
     * @param extension        File extension without a leading dot, or {@code null} for
     *                         no filter.
     * @param defaultName      Preselected file name, or {@code null} for none.
     * @param initialDirectory Directory initially shown by the chooser, or
     *                         {@code null} for the default directory.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseSaveFile(
            Component parent,
            String description,
            String extension,
            String defaultName,
            File initialDirectory
    ) {

        AtomicReference<File> result = new AtomicReference<>();

        EdtSwing.runAndWait(() -> {

            JFileChooser chooser =
                    createFileChooser(description, extension, defaultName);

            chooser.setDialogTitle(I18nSwing.text("dialog.save_file"));

            if (initialDirectory != null && initialDirectory.exists()) {
                chooser.setCurrentDirectory(initialDirectory);
            }

            int option = chooser.showSaveDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                if (extension != null && !extension.isBlank()) {
                    file = ensureExtension(file, extension);
                }

                if (file.exists()) {
                    int response = JOptionPane.showConfirmDialog(
                            parent,
                            I18nSwing.text(
                                    "dialog.confirm_overwrite_message",
                                    file.getName()
                            ),
                            I18nSwing.text("dialog.confirm_overwrite_title"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (response != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                result.set(file);
            }
        });

        return Optional.ofNullable(result.get());
    }

    /**
     * Displays a dialog for choosing a file to open without an extension filter.
     *
     * @param parent Parent component of the dialog.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseOpenFile(Component parent) {
        return chooseOpenFile(parent, null, null);
    }

    /**
     * Displays a dialog for choosing a file to open using an extension filter
     * whose description is generated automatically.
     *
     * @param parent    Parent component of the dialog.
     * @param extension File extension without a leading dot, for example
     *                  {@code "txt"}.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseOpenFile(
            Component parent,
            String extension
    ) {
        return chooseOpenFile(
                parent,
                generateFilterDescription(extension),
                extension
        );
    }

    /**
     * Displays a dialog for choosing a file to open using an extension filter
     * and description.
     *
     * @param parent      Parent component of the dialog.
     * @param description Filter description, for example {@code "Text files"}.
     * @param extension   File extension without a leading dot, for example
     *                    {@code "txt"}.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseOpenFile(
            Component parent,
            String description,
            String extension
    ) {
        return chooseOpenFile(parent, description, extension, null);
    }

    /**
     * Displays a dialog for choosing a file to open using an extension filter,
     * description and optional initial directory.
     *
     * @param parent           Parent component of the dialog.
     * @param description      Filter description, or {@code null} for no filter.
     * @param extension        File extension without a leading dot, or {@code null} for
     *                         no filter.
     * @param initialDirectory Directory initially shown by the chooser, or
     *                         {@code null} for the default directory.
     * @return Selected file, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseOpenFile(
            Component parent,
            String description,
            String extension,
            File initialDirectory
    ) {

        AtomicReference<File> result = new AtomicReference<>();

        EdtSwing.runAndWait(() -> {

            JFileChooser chooser =
                    createFileChooser(description, extension, null);

            chooser.setDialogTitle(I18nSwing.text("dialog.open_file"));

            if (initialDirectory != null && initialDirectory.exists()) {
                chooser.setCurrentDirectory(initialDirectory);
            }

            int option = chooser.showOpenDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                result.set(chooser.getSelectedFile());
            }
        });

        return Optional.ofNullable(result.get());
    }

    // ----------------------------------------
    // DIRECTORY CHOOSERS
    // ----------------------------------------

    /**
     * Displays a dialog for choosing a directory.
     *
     * @param parent Parent component of the dialog.
     * @return Selected directory, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseDirectory(Component parent) {
        return chooseDirectory(parent, null);
    }

    /**
     * Displays a dialog for choosing a directory with an optional initial
     * directory.
     *
     * @param parent           Parent component of the dialog.
     * @param initialDirectory Directory initially shown by the chooser, or
     *                         {@code null} for the default directory.
     * @return Selected directory, or {@link Optional#empty()} if cancelled.
     */
    public static Optional<File> chooseDirectory(
            Component parent,
            File initialDirectory
    ) {

        AtomicReference<File> result = new AtomicReference<>();

        EdtSwing.runAndWait(() -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (initialDirectory != null && initialDirectory.exists()) {
                chooser.setCurrentDirectory(initialDirectory);
            }

            int option = chooser.showOpenDialog(parent);

            if (option == JFileChooser.APPROVE_OPTION) {
                result.set(chooser.getSelectedFile());
            }
        });

        return Optional.ofNullable(result.get());
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    /**
     * Creates a {@link JFileChooser} configured with the specified filter and
     * default file name.
     *
     * @param description Filter description, or {@code null} for no filter.
     * @param extension   File extension without a leading dot, or {@code null} for
     *                    no filter.
     * @param defaultName Preselected file name, or {@code null} for none.
     * @return Configured {@link JFileChooser}.
     */
    private static JFileChooser createFileChooser(
            String description,
            String extension,
            String defaultName
    ) {

        JFileChooser chooser = new JFileChooser();

        if (description != null && !description.isBlank()
                && extension != null && !extension.isBlank()) {

            chooser.removeChoosableFileFilter(
                    chooser.getAcceptAllFileFilter()
            );

            chooser.setFileFilter(
                    new FileNameExtensionFilter(description, extension)
            );
        }

        if (defaultName != null && !defaultName.isBlank()) {
            String nameWithExtension =
                    (extension != null && !extension.isBlank())
                            ? defaultName + "." + extension
                            : defaultName;

            chooser.setSelectedFile(new File(nameWithExtension));
        }

        return chooser;
    }

    /**
     * Ensures that a file name ends with the specified extension.
     * <p>
     * If the file already has the extension, ignoring case, it is returned
     * unchanged.
     *
     * @param file      File to check.
     * @param extension Extension without a leading dot.
     * @return File with the extension appended when necessary.
     */
    private static File ensureExtension(File file, String extension) {

        String name = file.getName();

        if (!name.toLowerCase(Locale.ROOT)
                .endsWith("." + extension.toLowerCase(Locale.ROOT))) {

            return new File(
                    file.getParentFile(),
                    name + "." + extension
            );
        }

        return file;
    }

    /**
     * Generates the automatic file-filter description from an extension.
     *
     * @param extension Extension without a leading dot, or {@code null}.
     * @return Filter description, or {@code null} if the extension is null or
     * blank.
     */
    private static String generateFilterDescription(String extension) {
        return (extension != null && !extension.isBlank())
                ? I18nSwing.text(
                "dialog.files_filter",
                extension.toLowerCase(Locale.ROOT)
        )
                : null;
    }

}