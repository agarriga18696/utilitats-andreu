package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Utility class for managing Look and Feel in Swing applications.
 * <p>
 * Provides methods for applying Look and Feel implementations, updating
 * component trees, checking compatibility and retrieving installed or
 * predefined themes.
 *
 * @author Andreu
 * @version 2.0
 */
public final class LookAndFeelSwing {

    // ----------------------------------------
    // LOOK AND FEEL CONSTANTS
    // ----------------------------------------

    /**
     * System Look and Feel class name.
     */
    public static final String SYSTEM = UIManager.getSystemLookAndFeelClassName();

    /**
     * Metal Look and Feel class name.
     */
    public static final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";

    /**
     * Nimbus Look and Feel class name.
     */
    public static final String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";

    /**
     * Motif Look and Feel class name.
     */
    public static final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

    /**
     * Windows Look and Feel class name.
     */
    public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    /**
     * Windows Classic Look and Feel class name.
     */
    public static final String WINDOWS_CLASSIC = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";

    /**
     * GTK Look and Feel class name.
     */
    public static final String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

    /**
     * macOS Aqua Look and Feel class name.
     */
    public static final String MAC_OS = "com.apple.laf.AquaLookAndFeel";

    private static final Logger LOGGER = Logger.getLogger(LookAndFeelSwing.class.getName());
    private static final Map<String, Boolean> COMPATIBILITY_CACHE = new ConcurrentHashMap<>();

    private LookAndFeelSwing() {
        // Utility class
    }

    // ----------------------------------------
    // LOOK AND FEEL
    // ----------------------------------------

    /**
     * Applies the operating system Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applySystem() {
        return apply(SYSTEM);
    }

    /**
     * Applies the Java Metal Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applyMetal() {
        return apply(METAL);
    }

    /**
     * Applies the Nimbus Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applyNimbus() {
        return apply(NIMBUS);
    }

    /**
     * Applies the Motif Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applyMotif() {
        return apply(MOTIF);
    }

    /**
     * Applies the Windows Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applyWindows() {
        return apply(WINDOWS);
    }

    /**
     * Applies the Windows Classic Look and Feel.
     *
     * @return {@code true} if applied successfully.
     */
    public static boolean applyWindowsClassic() {
        return apply(WINDOWS_CLASSIC);
    }

    /**
     * Applies a Look and Feel using its fully qualified class name.
     * <p>
     * This operation modifies {@link UIManager} and must therefore be executed
     * on the Swing Event Dispatch Thread. Calls made outside the EDT are
     * automatically redirected using {@link EdtSwing#runAndWait(Runnable)}.
     *
     * @param className Fully qualified Look and Feel class name.
     * @return {@code true} if applied successfully.
     */
    public static boolean apply(String className) {

        if (className == null || className.isBlank()) {
            return false;
        }

        AtomicBoolean result = new AtomicBoolean(false);

        EdtSwing.runAndWait(() -> {
            try {
                UIManager.setLookAndFeel(className);
                result.set(true);

            } catch (Exception e) {
                LOGGER.log(
                        Level.WARNING,
                        e,
                        () -> "Could not apply Look and Feel: " + className
                );

                result.set(false);
            }
        });

        return result.get();
    }

    /**
     * Updates a component and all its descendants after changing the Look and
     * Feel.
     * <p>
     * Swing operations are automatically redirected to the EDT when necessary.
     *
     * @param component Root component to update.
     */
    public static void update(Component component) {

        if (component == null) {
            return;
        }

        EdtSwing.runAndWait(() -> {
            SwingUtilities.updateComponentTreeUI(component);
            component.revalidate();
            component.repaint();
        });
    }

    // ----------------------------------------
    // QUERIES
    // ----------------------------------------

    /**
     * Returns whether a Look and Feel is installed.
     *
     * @param className Fully qualified Look and Feel class name.
     * @return {@code true} if the Look and Feel appears among the installed
     * implementations.
     */
    public static boolean isInstalled(String className) {

        if (className == null || className.isBlank()) {
            return false;
        }

        for (UIManager.LookAndFeelInfo info :
                UIManager.getInstalledLookAndFeels()) {

            if (info.getClassName().equals(className)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether a Look and Feel can be used on the current system.
     * <p>
     * This check does not apply the Look and Feel. The result is cached using
     * {@link ConcurrentHashMap#computeIfAbsent(Object, java.util.function.Function)}.
     *
     * @param className Fully qualified Look and Feel class name.
     * @return {@code true} if the class exists, represents a {@link LookAndFeel}
     * and is supported by the current system.
     */
    public static boolean isCompatible(String className) {

        if (className == null || className.isBlank()) {
            return false;
        }

        return COMPATIBILITY_CACHE.computeIfAbsent(
                className,
                LookAndFeelSwing::checkCompatibility
        );
    }

    /**
     * Performs the compatibility check without applying the Look and Feel or
     * modifying {@link UIManager}.
     * <p>
     * Installed Look and Feel implementations are considered compatible
     * directly. Other implementations are instantiated through reflection and
     * checked using {@link LookAndFeel#isSupportedLookAndFeel()}.
     *
     * @param className Fully qualified Look and Feel class name.
     * @return {@code true} if the Look and Feel is compatible.
     */
    private static boolean checkCompatibility(String className) {

        if (isInstalled(className)) {
            return true;
        }

        try {
            Class<?> type = Class.forName(className);

            if (!LookAndFeel.class.isAssignableFrom(type)) {
                return false;
            }

            LookAndFeel instance =
                    (LookAndFeel) type.getDeclaredConstructor().newInstance();

            return instance.isSupportedLookAndFeel();

        } catch (ClassNotFoundException _) {
            return false;

        } catch (ReflectiveOperationException |
                 RuntimeException |
                 LinkageError e) {

            LOGGER.log(
                    Level.FINE,
                    e,
                    () -> "Could not check Look and Feel compatibility: "
                            + className
            );

            return false;
        }
    }

    /**
     * Returns the Look and Feel implementations installed on the current system.
     *
     * @return Installed Look and Feel information.
     */
    public static UIManager.LookAndFeelInfo[] getInstalledLookAndFeels() {
        return UIManager.getInstalledLookAndFeels();
    }

    /**
     * Returns the display names of the Look and Feel implementations installed
     * on the current system.
     *
     * @return Installed Look and Feel display names.
     */
    public static List<String> getInstalledLookAndFeelNames() {

        List<String> names = new ArrayList<>();

        for (UIManager.LookAndFeelInfo info :
                UIManager.getInstalledLookAndFeels()) {

            names.add(info.getName());
        }

        return names;
    }

    /**
     * Finds the class name of an installed Look and Feel using its display name.
     *
     * @param name Look and Feel display name.
     * @return Fully qualified class name, or {@code null} if no match is found.
     */
    public static String findClassNameByName(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        for (UIManager.LookAndFeelInfo info :
                UIManager.getInstalledLookAndFeels()) {

            if (info.getName().equalsIgnoreCase(name)) {
                return info.getClassName();
            }
        }

        return null;
    }

    /**
     * Returns the predefined themes provided by the library.
     *
     * @return Predefined themes.
     */
    public static List<LookAndFeelThemeSwing> getPredefinedThemes() {
        return List.of(
                new LookAndFeelThemeSwing(
                        I18nSwing.text("theme.system"),
                        SYSTEM,
                        IconsFugue.CUP,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "Nimbus",
                        NIMBUS,
                        IconsFugue.CUP,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "Metal",
                        METAL,
                        IconsFugue.CUP,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "Motif",
                        MOTIF,
                        IconsFugue.ANIMAL_PENGUIN,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "GTK",
                        GTK,
                        IconsFugue.ANIMAL_PENGUIN,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "Windows",
                        WINDOWS,
                        IconsFugue.WINDOWS,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "Windows Classic",
                        WINDOWS_CLASSIC,
                        IconsFugue.WINDOWS,
                        false
                ),
                new LookAndFeelThemeSwing(
                        "macOS",
                        MAC_OS,
                        IconsFugue.MAC_OS,
                        false
                )
        );
    }

    /**
     * Returns the class name of the currently active Look and Feel.
     *
     * @return Fully qualified class name of the current Look and Feel.
     */
    public static String getCurrentClassName() {
        return UIManager.getLookAndFeel().getClass().getName();
    }

}