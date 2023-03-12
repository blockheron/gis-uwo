package org.western;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Objects;

// Modified and added mouse press event listener to make initial location correct
// Adapted from http://www.java2s.com/Code/Java/2D-Graphics-GUI/Imagewithmousedragandmoveevent.htm

class Canvas extends JPanel {
    int x, y, initialX, initialY;
    BufferedImage bi;

    Canvas(String f, int w, int h) {
        setBackground(Color.white);
        setSize(w, h);
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
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

    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            initialX = e.getX();
            initialY = e.getY();
        }
    }

    class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            int currentX = e.getX();
            int currentY = e.getY();
            int deltaX = currentX - initialX;
            int deltaY = currentY - initialY;

            x += deltaX;
            y += deltaY;

            initialX = currentX;
            initialY = currentY;

            repaint();
        }
    }

}
