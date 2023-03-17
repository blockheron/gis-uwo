/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.western;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author Liam
 */
//adapted from http://www.java2s.com/Tutorials/Java/Swing/JButton/Polygon_shaped_Button_in_Java.htm
public class Room extends JComponent
{
    
    private Polygon shape;
    private Rectangle bounds;
    private Color color;
    private Color activeColor;
    private boolean active = false;
    
    public Room(Polygon shape, Color color)
    {
        
        this(shape);
        this.color = color;        
        
    }
    
    public Room(Polygon shape) 
    {
        
        this.shape = shape;
        setOpaque(false);
        
        //mouse handlers
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        //
        
        //get the bounding rectangle of the polygon
        bounds = this.shape.getBounds();
        setBounds(bounds);
        //
        
        //set colors
        color = Color.GRAY;
        activeColor = Color.BLUE;
        //
        
        
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        
        if (!active) g2D.setColor(color);
        else g2D.setColor(activeColor);
        g2D.fillPolygon(shape);
        g2D.setColor(Color.BLACK);
        g2D.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        
    }
    
    class MouseHandler implements MouseMotionListener, MouseListener {
        public void mouseMoved(MouseEvent e) {
            //check if the mouse is even in the bounding box
            if (!bounds.contains(e.getX(), e.getY())) {
                if (active) {
                   active = false;
                   repaint();
                }
            return;
            }
            //
            
            //check if the mouse is within the polygon
            boolean contained = shape.contains(e.getX(), e.getY());
            
            //switch whether the button is hovered over accordingly
            if (contained && !active){
                active = true;
                repaint();
            }
            else if (!contained && active) {
                active = false;
                repaint();
            }
            //
            
        }

        public void mouseDragged(MouseEvent e) {

        }
        
        public void mousePressed(MouseEvent e) {
            //if active...
        }

        public void mouseReleased(MouseEvent e) {

        }
        
        public void mouseEntered(MouseEvent e) {
            mouseMoved(e); //covers edge cases where the mouse enters the bounds and the polygon
        }

        public void mouseExited(MouseEvent e) {
            if (active){
                active = false;
                repaint();
            } //covers edge cases where the mouse exits the bounds and the polygon
        }

        public void mouseClicked(MouseEvent e) {

        }

    }
    
    
}
