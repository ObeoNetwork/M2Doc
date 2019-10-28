/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Images test utility class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class ImageTestUtils {

    /**
     * The green shift.
     */
    private static final int GREEN_SHIFT = 8;

    /**
     * The red shift.
     */
    private static final int RED_SHIFT = 16;

    /**
     * The red shift.
     */
    private static final int ALPHA_SHIFT = 24;

    /**
     * The blue mask.
     */
    private static final int BLUE_MASK = 0x000000ff;

    /**
     * The green mask.
     */
    private static final int GREEN_MASK = 0x0000ff00;

    /**
     * The red mask.
     */
    private static final int RED_MASK = 0x00ff0000;

    /**
     * The alpha mask.
     */
    private static final int ALPHA_MASK = 0xff000000;

    /**
     * Red index.
     */
    private static final int R = 0;

    /**
     * Green index.
     */
    private static final int G = 1;

    /**
     * Blue index.
     */
    private static final int B = 2;

    /**
     * Size of a RGB.
     */
    private static final int RGB_SIZE = 3;

    /**
     * The maximum RGB difference.
     */
    private static final int MAX_RGB_DIFF = 255 * RGB_SIZE;

    /**
     * Constructor.
     */
    private ImageTestUtils() {
        // nothing to do here
    }

    /**
     * Asserts that the given expected and actual GIFs look like each other.
     * 
     * @param diffOutput
     *            the difference GIF or <code>null</code>
     * @param expectedIs
     *            the expected GIF
     * @param actualIs
     *            the actual GIF
     * @param threshold
     *            the difference threshold of a block
     */
    public static void assertGIF(File diffOutput, InputStream expectedIs, InputStream actualIs, double threshold) {
        try {
            assertTrue(fuzzyCompareImage("GIF", diffOutput, expectedIs, actualIs, threshold));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Asserts that the given expected and actual PNGs look like each other.
     * 
     * @param diffOutput
     *            the difference PNG or <code>null</code>
     * @param expectedIs
     *            the expected PNG
     * @param actualIs
     *            the actual PNG
     * @param threshold
     *            the difference threshold of a block
     */
    public static void assertPNG(File diffOutput, InputStream expectedIs, InputStream actualIs, double threshold) {
        try {
            assertTrue(fuzzyCompareImage("PNG", diffOutput, expectedIs, actualIs, threshold));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Asserts that the given expected and actual JPGs look like each other.
     * 
     * @param diffOutput
     *            the difference JPG or <code>null</code>
     * @param expectedIs
     *            the expected JPG
     * @param actualIs
     *            the actual JPG
     * @param threshold
     *            the difference threshold of a block
     */
    public static void assertJPG(File diffOutput, InputStream expectedIs, InputStream actualIs, double threshold) {
        try {
            assertTrue(fuzzyCompareImage("JPG", diffOutput, expectedIs, actualIs, threshold));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tells if the actual image look like the expected image.
     * 
     * @param imageFormat
     *            GIF,PNG, or JPG
     * @param diffOutput
     *            the difference {@link File} or <code>null</code>
     * @param expectedIs
     *            the expected image {@link InputStream}
     * @param actualIs
     *            the actual image {@link InputStream}
     * @param threshold
     *            the difference threshold of a block
     * @return <code>true</code> if the actual image look like the expected image, <code>false</code> otherwise
     * @throws IOException
     *             if some streams can't be read/written
     */
    private static boolean fuzzyCompareImage(String imageFormat, File diffOutput, InputStream expectedIs,
            InputStream actualIs, double threshold) throws IOException {
        boolean result = true;

        final BufferedImage expectedImage = ImageIO.read(expectedIs);
        final BufferedImage actualImage = ImageIO.read(actualIs);
        if (expectedImage == null) {
            result = actualImage == null;
        } else if (actualImage == null) {
            result = false;
        } else {
            final BufferedImage actualImageResized = resize(actualImage, expectedImage.getWidth(),
                    expectedImage.getHeight());
            final BufferedImage diffImage;
            if (diffOutput != null) {
                diffImage = createDiffImage(expectedImage, actualImageResized);
            } else {
                diffImage = null;
            }
            final Graphics2D outImgGraphics = diffImage.createGraphics();
            outImgGraphics.setColor(Color.RED);

            final int blockWidth = expectedImage.getWidth() / 20;
            final int blockHeight = expectedImage.getWidth() / 20;
            final int nbBlocksX = (int) Math.ceil((float) expectedImage.getWidth() / blockWidth);
            final int nbBlocksY = (int) Math.ceil((float) expectedImage.getHeight() / blockHeight);

            for (int blockIndexX = 0; blockIndexX < nbBlocksX; blockIndexX++) {
                for (int blockIndexY = 0; blockIndexY < nbBlocksY; blockIndexY++) {
                    final int sampleWidth = getsampleSize(blockWidth, blockIndexX, expectedImage.getWidth());
                    final int sampleHeight = getsampleSize(blockHeight, blockIndexY, expectedImage.getHeight());
                    final double[] avgRgbExpected = getAverageRgb(expectedImage.getSubimage(blockIndexX * blockWidth,
                            blockIndexY * blockHeight, sampleWidth, sampleHeight));
                    final double[] avgRgbActual = getAverageRgb(actualImageResized.getSubimage(blockIndexX * blockWidth,
                            blockIndexY * blockHeight, sampleWidth, sampleHeight));
                    if (getDiffPercentage(avgRgbExpected, avgRgbActual) > threshold) {
                        final int markerX = blockIndexX * blockWidth + expectedImage.getWidth(null);
                        final int markerY = blockIndexY * blockHeight;
                        outImgGraphics.drawRect(markerX, markerY, blockWidth - 1, blockHeight - 1);
                        result = false;
                        if (diffImage == null) {
                            break;
                        }
                    }
                }
            }
            if (!result && diffImage != null) {
                if (!diffOutput.exists()) {
                    diffOutput.createNewFile();
                }
                ImageIO.write(diffImage, imageFormat, diffOutput);
            }
        }

        return result;
    }

    /**
     * Gets the block size according to the block index and default size and limit.
     * 
     * @param defaultBlockSize
     *            the default block size
     * @param blockIndex
     *            the current block index
     * @param limit
     *            the image limit
     * @return the block size according to the block index and default size and limit
     */
    private static int getsampleSize(int defaultBlockSize, int blockIndex, int limit) {
        final int result;
        if (defaultBlockSize * (blockIndex + 1) > limit) {
            result = limit % defaultBlockSize;
        } else {
            result = defaultBlockSize;
        }

        return result;
    }

    /**
     * Resize the given {@link BufferedImage}.
     * 
     * @param image
     *            the {@link BufferedImage}
     * @param newWidth
     *            the new width
     * @param newHeight
     *            the new height
     * @return the resized {@link BufferedImage}
     */
    public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
        final BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return result;
    }

    /**
     * Gets the difference percentage for the given RGB.
     * 
     * @param rgb1
     *            the first RGB
     * @param rgb2
     *            the second RGB
     * @return the difference percentage for the given RGB
     */
    private static double getDiffPercentage(double[] rgb1, double[] rgb2) {
        double difference = Math.abs(rgb1[R] - rgb2[R]) + Math.abs(rgb1[G] - rgb2[G]) + Math.abs(rgb1[B] - rgb2[B]);

        return difference / MAX_RGB_DIFF;
    }

    /**
     * Gets the average RGB for the given {@link BufferedImage}.
     * 
     * @param image
     *            the {@link BufferedImage}
     * @return the average RGB for the given {@link BufferedImage}
     */
    private static double[] getAverageRgb(BufferedImage image) {
        final double[] result = new double[] {0, 0, 0 };

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                final int rgb = image.getRGB(x, y);
                if (((rgb & ALPHA_MASK) >> ALPHA_SHIFT) != 0) {
                    result[R] += (rgb & RED_MASK) >> RED_SHIFT;
                    result[G] += (rgb & GREEN_MASK) >> GREEN_SHIFT;
                    result[B] += +rgb & BLUE_MASK;
                }
            }
        }

        final int sampleSize = image.getHeight() * image.getWidth();
        result[R] = result[R] / sampleSize;
        result[G] = result[G] / sampleSize;
        result[B] = result[B] / sampleSize;

        return result;
    }

    /**
     * Clones the given {@link BufferedImage}.
     * 
     * @param expected
     *            the expected {@link BufferedImage}
     * @param actual
     *            the actual {@link BufferedImage}
     * @return the clone of the given {@link BufferedImage}
     */
    private static BufferedImage createDiffImage(BufferedImage expected, BufferedImage actual) {
        final int resultWidth = expected.getWidth(null) + actual.getWidth(null) * 2;
        final int resultHeight = Math.max(expected.getHeight(null), actual.getHeight(null));
        final BufferedImage result = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = result.createGraphics();
        g2.drawImage(expected, 0, 0, null);
        g2.drawImage(expected, expected.getWidth(null), 0, null);
        g2.drawImage(actual, expected.getWidth(null) * 2, 0, null);

        return result;
    }

}
