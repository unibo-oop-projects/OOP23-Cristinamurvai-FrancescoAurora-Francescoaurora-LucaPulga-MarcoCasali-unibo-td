package it.unibo.model.utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Class to scale images.
 */
public final class ScaledImage {

    /**
     *
     * Scalted Image. TODO reference https://stackoverflow.com/a/6714381 .
     *
     *
     *
     * @param srcImg source Image
     * @param width
     * @param height
     * @return Return ImageIcon scaled
     */
    public static ImageIcon getScaledImage(final Image srcImg, final int width, final int height) {
        final BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ScaledImage() {
        throw new UnsupportedOperationException("Utility class");
    }
}
