/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.western;

/**
 *
 * @author valen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Objects;

// Modified and added mouse press event listener to make initial location correct
// Adapted from http://www.java2s.com/Code/Java/2D-Graphics-GUI/Imagewithmousedragandmoveevent.htm

class Canvas2 extends JPanel {
    int x, y;
    BufferedImage bi;

    Canvas2(String f, int w, int h) {
        setBackground(Color.white);
        setSize(w, h);
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(f))).getImage();
        // scale image to fit window
        image = image.getScaledInstance(w, image.getHeight(this) * w / image.getWidth(this), Image.SCALE_SMOOTH);

        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 1);
        try {
            mt.waitForAll();
        } catch (Exception e) {
            System.out.println("Exception while loading image.");
        }

        if (image.getWidth(this) == -1) {
            System.out.println("no image file");
            System.exit(0);
        }

        bi = new BufferedImage(image.getWidth(this), image.getHeight(this),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bi.createGraphics();
        big.drawImage(image, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bi, x, y, this);
    }

    

}

