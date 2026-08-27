package io.github.agarriga18696.andreuutils.swing;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class for loading and scaling Swing icons from classpath resources.
 * <p>
 * Provides convenient loaders for the icon packages bundled with the library,
 * as well as generic resource loading for external applications.
 * Loaded icons are cached according to their resource location and requested
 * size.
 *
 * @author Andreu
 * @version 3.0
 */
public final class IconsSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final Logger LOGGER = Logger.getLogger(IconsSwing.class.getName());

    private static final int DEFAULT_ICON_SIZE = 16;

    private static final String FUGUE_ICONS_PATH = "/io/github/agarriga18696/andreuutils/icons/fugue/";

    private static final String FATCOW_ICONS_PATH = "/io/github/agarriga18696/andreuutils/icons/fatcow/";

    private static final String FLAG_ICONS_PATH = "/io/github/agarriga18696/andreuutils/icons/flagicons/";

    private static final String GAME_ICONS_PATH = "/io/github/agarriga18696/andreuutils/icons/game/";

    // ----------------------------------------
    // CACHE
    // ----------------------------------------

    private static final Map<CacheKey, Icon> CACHE = new ConcurrentHashMap<>();

    private IconsSwing() {
        // Utility class
    }

    // ----------------------------------------
    // FUGUE ICONS
    // ----------------------------------------

    /**
     * Loads a Fugue icon using the default icon size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFugue}.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon load(String fileName) {
        return load(
                IconsSwing.class,
                FUGUE_ICONS_PATH,
                fileName
        );
    }

    /**
     * Loads a Fugue icon using the specified size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFugue}.
     * @param size     Icon size in pixels.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon load(String fileName, int size) {
        return load(
                IconsSwing.class,
                FUGUE_ICONS_PATH,
                fileName,
                size
        );
    }

    // ----------------------------------------
    // FATCOW ICONS
    // ----------------------------------------

    /**
     * Loads a FatCow icon using the default icon size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFatCow}.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadFatCow(String fileName) {
        return load(
                IconsSwing.class,
                FATCOW_ICONS_PATH,
                fileName
        );
    }

    /**
     * Loads a FatCow icon using the specified size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFatCow}.
     * @param size     Icon size in pixels.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadFatCow(String fileName, int size) {
        return load(
                IconsSwing.class,
                FATCOW_ICONS_PATH,
                fileName,
                size
        );
    }

    // ----------------------------------------
    // FLAG ICONS
    // ----------------------------------------

    /**
     * Loads a flag icon using the default icon size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFlags}.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadFlag(String fileName) {
        return load(
                IconsSwing.class,
                FLAG_ICONS_PATH,
                fileName
        );
    }

    /**
     * Loads a flag icon using the specified size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsFlags}.
     * @param size     Icon size in pixels.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadFlag(String fileName, int size) {
        return load(
                IconsSwing.class,
                FLAG_ICONS_PATH,
                fileName,
                size
        );
    }

    // ----------------------------------------
    // GAME ICONS
    // ----------------------------------------

    /**
     * Loads a Game Icons resource using the default icon size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsGame}.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadGame(String fileName) {
        return load(
                IconsSwing.class,
                GAME_ICONS_PATH,
                fileName
        );
    }

    /**
     * Loads a Game Icons resource using the specified size.
     *
     * @param fileName Icon file name, typically a constant from
     *                 {@link IconsGame}.
     * @param size     Icon size in pixels.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon loadGame(String fileName, int size) {
        return load(
                IconsSwing.class,
                GAME_ICONS_PATH,
                fileName,
                size
        );
    }

    // ----------------------------------------
    // GENERIC RESOURCE LOADING
    // ----------------------------------------

    /**
     * Loads an icon from a classpath resource using the default icon size.
     *
     * @param baseClass Base class used to locate the resource.
     * @param path      Resource directory inside the classpath.
     * @param fileName  Icon file name.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    public static Icon load(
            Class<?> baseClass,
            String path,
            String fileName
    ) {

        return load(
                baseClass,
                path,
                fileName,
                DEFAULT_ICON_SIZE
        );
    }

    /**
     * Loads and scales an icon from a classpath resource.
     * <p>
     * Loaded icons are cached using the base class, normalized resource path,
     * file name and requested size as the cache key.
     *
     * @param baseClass Base class used to locate the resource.
     * @param path      Resource directory inside the classpath.
     * @param fileName  Icon file name.
     * @param size      Icon size in pixels.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     * @throws IllegalArgumentException if any argument is invalid.
     */
    public static Icon load(
            Class<?> baseClass,
            String path,
            String fileName,
            int size
    ) {

        if (size <= 0) {
            throw new IllegalArgumentException(
                    "Icon size must be positive."
            );
        }

        if (baseClass == null) {
            throw new IllegalArgumentException(
                    "Base class cannot be null."
            );
        }

        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException(
                    "Resource path cannot be blank."
            );
        }

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException(
                    "File name cannot be blank."
            );
        }

        String normalizedPath = normalizePath(path);

        CacheKey key = new CacheKey(
                baseClass,
                normalizedPath,
                fileName,
                size
        );

        return CACHE.computeIfAbsent(
                key,
                IconsSwing::loadIcon
        );
    }

    // ----------------------------------------
    // ICON LOADING
    // ----------------------------------------

    /**
     * Loads an icon represented by a cache key.
     *
     * @param key Icon cache key.
     * @return Loaded icon, or {@code null} if the resource cannot be loaded.
     */
    private static Icon loadIcon(CacheKey key) {

        String resource =
                key.path() + key.fileName();

        try (InputStream inputStream =
                     key.baseClass().getResourceAsStream(resource)) {

            if (inputStream == null) {
                LOGGER.log(
                        Level.WARNING,
                        () -> "Icon not found: " + resource
                );

                return null;
            }

            BufferedImage original =
                    ImageIO.read(inputStream);

            if (original == null) {
                LOGGER.log(
                        Level.WARNING,
                        () -> "Unsupported image format: " + resource
                );

                return null;
            }

            return new ImageIcon(
                    scaleHighQuality(
                            original,
                            key.size()
                    )
            );

        } catch (IOException exception) {
            LOGGER.log(
                    Level.WARNING,
                    exception,
                    () -> "Error reading icon: " + resource
            );

            return null;
        }
    }

    // ----------------------------------------
    // HIGH-QUALITY SCALING
    // ----------------------------------------

    /**
     * Scales an image to the specified size using progressive bicubic scaling.
     * <p>
     * Large reductions are performed progressively by repeatedly halving the
     * image dimensions before applying the final target size.
     *
     * @param original Original image.
     * @param size     Target size in pixels.
     * @return High-quality scaled image.
     */
    private static BufferedImage scaleHighQuality(
            BufferedImage original,
            int size
    ) {

        BufferedImage source;

        if (original.getType() == BufferedImage.TYPE_INT_ARGB) {
            source = original;

        } else {
            source = new BufferedImage(
                    original.getWidth(),
                    original.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );

            Graphics2D graphics =
                    source.createGraphics();

            graphics.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC
            );

            graphics.drawImage(
                    original,
                    0,
                    0,
                    null
            );

            graphics.dispose();
        }

        int width = source.getWidth();
        int height = source.getHeight();

        while (width / 2 > size || height / 2 > size) {

            width = Math.max(
                    width / 2,
                    size
            );

            height = Math.max(
                    height / 2,
                    size
            );

            source = scaleStep(
                    source,
                    width,
                    height
            );
        }

        return scaleStep(
                source,
                size,
                size
        );
    }

    /**
     * Scales an image to the specified dimensions in a single bicubic step.
     *
     * @param source Source image.
     * @param width  Target width.
     * @param height Target height.
     * @return Scaled image.
     */
    private static BufferedImage scaleStep(
            BufferedImage source,
            int width,
            int height
    ) {

        BufferedImage destination =
                new BufferedImage(
                        width,
                        height,
                        BufferedImage.TYPE_INT_ARGB
                );

        Graphics2D graphics =
                destination.createGraphics();

        graphics.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
        );

        graphics.drawImage(
                source,
                0,
                0,
                width,
                height,
                null
        );

        graphics.dispose();

        return destination;
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    /**
     * Normalizes a resource path so that it starts and ends with {@code /}.
     *
     * @param path Resource path.
     * @return Normalized resource path.
     */
    private static String normalizePath(String path) {

        String normalizedPath =
                path.startsWith("/")
                        ? path
                        : "/" + path;

        return normalizedPath.endsWith("/")
                ? normalizedPath
                : normalizedPath + "/";
    }

    // ----------------------------------------
    // INTERNAL TYPES
    // ----------------------------------------

    /**
     * Identifies a cached icon uniquely.
     *
     * @param baseClass Base class used to locate the resource.
     * @param path      Normalized resource path.
     * @param fileName  Icon file name.
     * @param size      Requested icon size.
     */
    private record CacheKey(
            Class<?> baseClass,
            String path,
            String fileName,
            int size
    ) {
    }

}