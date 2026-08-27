package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Utility class for creating and configuring Swing frames.
 *
 * @author Andreu
 * @version 2.0
 */
public final class FramesSwing {

    // ----------------------------------------
    // CONSTANTS
    // ----------------------------------------

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private FramesSwing() {
        // Utility class
    }

    // ----------------------------------------
    // FRAME BUILDER
    // ----------------------------------------

    /**
     * Creates a new {@link FrameBuilder}.
     *
     * @param title Frame title.
     * @return Builder used to configure the frame.
     */
    public static FrameBuilder builder(
            String title
    ) {

        return new FrameBuilder(title);
    }

    // ----------------------------------------
    // FRAME CREATION
    // ----------------------------------------

    /**
     * Creates a configured {@link JFrame}.
     *
     * @param title          Frame title.
     * @param width          Frame width.
     * @param height         Frame height.
     * @param relativeTo     Component relative to which the frame should be centered.
     *                       If {@code null}, the frame is centered on the screen.
     * @param closeOperation Frame close operation.
     * @param icon           Frame icon. If {@code null}, the default icon is used.
     * @return Configured frame.
     */
    public static JFrame frame(
            String title,
            int width,
            int height,
            Component relativeTo,
            int closeOperation,
            ImageIcon icon
    ) {

        return builder(title)
                .size(
                        width,
                        height
                )
                .relativeTo(relativeTo)
                .closeOperation(closeOperation)
                .icon(icon)
                .build();
    }

    /**
     * Creates a configured {@link JFrame}.
     *
     * @param title          Frame title.
     * @param width          Frame width.
     * @param height         Frame height.
     * @param relativeTo     Component relative to which the frame should be centered.
     *                       If {@code null}, the frame is centered on the screen.
     * @param closeOperation Frame close operation.
     * @return Configured frame.
     */
    public static JFrame frame(
            String title,
            int width,
            int height,
            Component relativeTo,
            int closeOperation
    ) {

        return frame(
                title,
                width,
                height,
                relativeTo,
                closeOperation,
                null
        );
    }

    /**
     * Creates a basic frame centered on the screen.
     * <p>
     * The frame uses {@link WindowConstants#EXIT_ON_CLOSE} as its default close
     * operation.
     *
     * @param title  Frame title.
     * @param width  Frame width.
     * @param height Frame height.
     * @return Configured frame.
     */
    public static JFrame frame(
            String title,
            int width,
            int height
    ) {

        return frame(
                title,
                width,
                height,
                null,
                WindowConstants.EXIT_ON_CLOSE
        );
    }

    /**
     * Sets the default button of a frame.
     * <p>
     * The default button is activated when the user presses {@code Enter},
     * provided that another component does not consume the key event.
     *
     * @param frame  Frame to configure.
     * @param button Button to use as the default button.
     * @throws NullPointerException if {@code frame} or {@code button} is {@code null}.
     */
    public static void setDefaultButton(
            JFrame frame,
            JButton button
    ) {

        Objects.requireNonNull(
                frame,
                "Frame cannot be null."
        );

        Objects.requireNonNull(
                button,
                "Button cannot be null."
        );

        frame.getRootPane()
                .setDefaultButton(button);
    }

    // ----------------------------------------
    // FRAME CONFIGURATION
    // ----------------------------------------

    private static void validateSize(
            int width,
            int height
    ) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be positive."
            );
        }
    }

    // ----------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------

    /**
     * Fluent builder for creating configured {@link JFrame} instances.
     * <p>
     * Instances are created through {@link FramesSwing#builder(String)}.
     * <p>
     * Default values are:
     * <ul>
     *     <li>Size: 800 × 600 pixels</li>
     *     <li>Centered on screen</li>
     *     <li>Resizable</li>
     *     <li>Close operation: {@link WindowConstants#EXIT_ON_CLOSE}</li>
     * </ul>
     */
    public static final class FrameBuilder {

        private final String title;

        private int width = DEFAULT_WIDTH;
        private int height = DEFAULT_HEIGHT;

        private Dimension minimumSize;
        private Component relativeTo;

        private int closeOperation = WindowConstants.EXIT_ON_CLOSE;

        private boolean resizable = true;

        private ImageIcon icon;
        private List<Image> iconImages;

        private FrameBuilder(String title) {
            this.title = title;
        }

        /**
         * Sets the frame size.
         *
         * @param width  Width in pixels.
         * @param height Height in pixels.
         * @return This builder.
         */
        public FrameBuilder size(
                int width,
                int height
        ) {

            this.width = width;
            this.height = height;

            return this;
        }

        /**
         * Sets the minimum frame size.
         *
         * @param width  Minimum width in pixels.
         * @param height Minimum height in pixels.
         * @return This builder.
         */
        public FrameBuilder minimumSize(
                int width,
                int height
        ) {

            this.minimumSize =
                    new Dimension(
                            width,
                            height
                    );

            return this;
        }

        /**
         * Centers the frame relative to the specified component.
         * <p>
         * If {@code component} is {@code null}, the frame is centered on the
         * screen.
         *
         * @param component Component relative to which the frame should be centered.
         * @return This builder.
         */
        public FrameBuilder relativeTo(
                Component component
        ) {

            this.relativeTo = component;

            return this;
        }

        /**
         * Centers the frame on the screen.
         *
         * @return This builder.
         */
        public FrameBuilder centered() {

            this.relativeTo = null;

            return this;
        }

        /**
         * Makes the frame non-resizable.
         *
         * @return This builder.
         */
        public FrameBuilder nonResizable() {

            this.resizable = false;

            return this;
        }

        /**
         * Sets the frame close operation.
         *
         * @param operation Close operation, such as
         *                  {@link WindowConstants#DISPOSE_ON_CLOSE}.
         * @return This builder.
         */
        public FrameBuilder closeOperation(
                int operation
        ) {

            this.closeOperation = operation;

            return this;
        }

        /**
         * Sets the frame icon.
         * <p>
         * If {@code icon} is {@code null}, it is ignored.
         *
         * @param icon Icon to apply.
         * @return This builder.
         */
        public FrameBuilder icon(
                ImageIcon icon
        ) {

            this.icon = icon;

            return this;
        }

        /**
         * Sets multiple frame icon images at different sizes.
         * <p>
         * The operating system can select the most appropriate image for each
         * context, such as the title bar, taskbar or window switcher.
         * <p>
         * If the list is {@code null} or empty, it is ignored.
         *
         * @param iconImages Icon images at different sizes.
         * @return This builder.
         */
        public FrameBuilder iconImages(
                List<Image> iconImages
        ) {

            this.iconImages = iconImages;

            return this;
        }

        /**
         * Builds and returns the configured {@link JFrame}.
         *
         * @return Configured frame.
         * @throws IllegalArgumentException if width or height is not positive.
         */
        public JFrame build() {

            validateSize(
                    width,
                    height
            );

            JFrame frame =
                    new JFrame(title);

            frame.setSize(
                    width,
                    height
            );

            frame.setResizable(resizable);

            frame.setLocationRelativeTo(
                    relativeTo
            );

            frame.setDefaultCloseOperation(
                    closeOperation
            );

            if (minimumSize != null) {
                frame.setMinimumSize(
                        minimumSize
                );
            }

            if (iconImages != null &&
                    !iconImages.isEmpty()) {

                frame.setIconImages(
                        iconImages
                );

            } else if (icon != null &&
                    icon.getImage() != null) {

                frame.setIconImage(
                        icon.getImage()
                );
            }

            return frame;
        }
    }

}