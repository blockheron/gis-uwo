package org.western;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CanvasTest {
    private CanvasGUI canvasGUI;
    private ImageIcon testImageIcon;

    @BeforeEach
    public void setUp() throws Exception {
        String testImagePath = "assets/MC-BF-1.png"; // assuming the test image file is in the resources/test directory
        int canvasWidth = 500;
        int canvasHeight = 500;
        canvasGUI = new CanvasGUI(testImagePath, canvasWidth, canvasHeight);
        assertNotNull(canvasGUI);

        // create a test ImageIcon to use for setImage test
        Image testImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(testImagePath))).getImage();
        testImage = testImage.getScaledInstance(canvasWidth, testImage.getHeight(canvasGUI) * canvasWidth / testImage.getWidth(canvasGUI), Image.SCALE_SMOOTH);
        testImageIcon = new ImageIcon(testImage);
        canvasGUI.setImage(testImageIcon);
    }

    @Test
    public void testConstructor() {
        assertEquals(500, canvasGUI.getWidth());
        assertEquals(500, canvasGUI.getHeight());
        assertNotNull(canvasGUI.image);
        assertNotNull(canvasGUI.imageIcon);
    }

    @Test
    public void testSetImage() {
        assertEquals(testImageIcon.getIconWidth(), canvasGUI.imageIcon.getIconWidth());
    }
}
