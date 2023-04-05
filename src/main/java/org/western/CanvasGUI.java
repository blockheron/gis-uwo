package org.western;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author Emma
 * A JPanel to display images in JFrame MainWindow
 */

class CanvasGUI extends JPanel {
    public int x, y;   // initialX, initialY from MainWindow
    int w, h;   // width and height from MainWindow
    ImageIcon imageIcon;
    Image image;
//    BufferedImage bi;
//    Graphics2D big;
    
    /**
     * Constructor to make a CanvasGUI object.
     * @param f path to image file
     * @param w width for CanvasGUI
     * @param h height for CavasGUI
     */
    CanvasGUI(String f, int w, int h) {
        setBackground(Color.white);
        setSize(w, h);
        this.w = w;
        this.h = h;
        System.out.println(f);
        // Load image from file path into imageIcon, transform it, load in transformed image
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(f)));
        image = imageIcon.getImage();
        image = image.getScaledInstance(w, image.getHeight(this) * w / image.getWidth(this), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        
        // Check if the image file exists
        if (image.getWidth(this) == -1) {
            System.out.println("no image file");
            System.exit(0);
        }
    }
    
    /**
     * Draws a graphic on CanvasGUI other than the background colour.
     * @param g graphic to draw
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
     
    /**
     * Setter to change the image this object displays.
     * @param img the ImageIcon to display
     */
    public void setImage(ImageIcon img) {
        this.image = img.getImage();
        this.image = image.getScaledInstance(w, image.getHeight(this) * w / image.getWidth(this), Image.SCALE_SMOOTH);
        repaint();
    }
    
    // To accommodate moving the canvas with mouseDragged
    public void translate (int x, int y) {
        this.x+=x;
        this.y+=y;
    }
}
