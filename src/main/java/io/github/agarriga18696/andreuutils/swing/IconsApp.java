package io.github.agarriga18696.andreuutils.swing;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Utility class for loading the application icon.
 * <p>
 * Application icons are bundled with the library at several resolutions.
 * Use {@link #loadAll()} to retrieve all available sizes and pass them to
 * {@code JFrame.setIconImages()}, allowing the operating system to select
 * the most appropriate resolution for each context.
 *
 * @author Andreu
 * @version 2.0
 */
public final class IconsApp {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final String BASE_PATH = "/io/github/agarriga18696/andreuutils/icons/app/andreu-";

    private static final int[] SUPPORTED_SIZES = {
			16,
            32,
            64,
            128,
            256,
            512
    };

    private IconsApp() {
        // Utility class
    }

    // ----------------------------------------
    // LOADING
    // ----------------------------------------

    /**
     * Loads the application icon at the specified size.
     * <p>
     * The requested size must correspond to one of the icon files bundled
     * with the library.
     *
     * @param size Icon size in pixels, such as {@code 64} for a 64 × 64 icon.
     * @return Loaded icon, or {@code null} if the corresponding resource
     * cannot be found.
     */
    public static ImageIcon load(int size) {

        String resourcePath =
                BASE_PATH
                        + size
                        + "x"
                        + size
                        + ".png";

        URL resource =
                IconsApp.class.getResource(
                        resourcePath
                );

        if (resource == null) {
            return null;
        }

        return new ImageIcon(resource);
    }

    /**
     * Loads all available application icon sizes.
     * <p>
     * The returned images can be passed directly to
     * {@code JFrame.setIconImages()}, allowing the operating system to choose
     * the most suitable resolution for each context.
     *
     * @return Images for all available application icon sizes.
     */
    public static List<Image> loadAll() {

        List<Image> images =
                new ArrayList<>(
                        SUPPORTED_SIZES.length
                );

        for (int size : SUPPORTED_SIZES) {

            ImageIcon icon = load(size);

            if (icon != null) {
                images.add(
                        icon.getImage()
                );
            }
        }

        return images;
    }

}