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

import java.util.LinkedList;

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
    public LinkedList<POI> POIs;
    private POIListPopup ListPopup;
    private Building building;
    private Floor floor;
    
    public Room(Polygon shape, Point position, Building building, Floor floor) 
    {
        
        this(shape, building, floor);
        this.setLocation(position);
        
    }
    
    public Room(Polygon shape, Building building, Floor floor) 
    {
        
        this.shape = shape;
        this.building = building;
        this.floor = floor;
        setOpaque(false);
        POIs = new LinkedList<POI>();
        
        
        //get the bounding rectangle of the polygon
        bounds = this.shape.getBounds();
        setBounds(bounds);
        //
        
        //set colors
        color = new Color(200,200,200,50);//transparent
        activeColor = new Color(102, 178, 255, 50);//light blue with transparency
        //
        
        //add mock POI to list
        //POIs.add(new POI(1, "test_name", "sample_description", null, "1", null));
        //
        
    }
    
    public void translate (int x, int y) {
        setLocation(getX()+x, getY()+y);
        if (ListPopup != null) ListPopup.translate(x, y);
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        
        if (!active) g2D.setColor(color);
        else g2D.setColor(activeColor);
        g2D.fillPolygon(shape);
        
        //System.out.println(this.getX() + ", " + this.getY());
        
        //g2D.setColor(Color.BLACK);
        //g2D.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        
    }
    
    
   /*
    *
    * offsets the shape of the room before drawing it;
    *
    */
    private Polygon offset(Polygon shape)
    {
        
        int[] newXPoints = new int[shape.npoints];
        int[] newYPoints = new int[shape.npoints];
        
        for(int i = 0; i < shape.npoints; ++i) {
            newXPoints[i] = shape.xpoints[i] + this.getX();
            newYPoints[i] = shape.ypoints[i] + this.getY();
        }
        
        return new Polygon(newXPoints, newYPoints, shape.npoints);
    }
    
    public Polygon getPoly() {return shape;}
    
    public void mouseMoved(MouseEvent e) {
        //check if the mouse is even in the bounding box
        if (!bounds.contains(e.getX()-getX(), e.getY()-getY())) {
            if (active) {
               active = false;
               repaint();
            }
        return;
        }
        //

        //check if the mouse is within the polygon
        boolean contained = shape.contains(e.getX()-getX(), e.getY()-getY());

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

        //main.dispatchEvent(e);

    }

    public void mouseDragged(MouseEvent e) {
        //main.dispatchEvent(e);
    }

    public void mousePressed(MouseEvent e) {
        //if active...
        //main.dispatchEvent(e);
        
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

    public void addPOI(POI poi) {
        
        
        
    }
    
    /*public Building getBuilding() {
        
    }*/
    
    public void mouseClicked(MouseEvent e, JLayeredPane layerPanel) {

        if (active && ListPopup == null) {
          
            ListPopup = new POIListPopup(this, POIs);
            layerPanel.add(ListPopup, JLayeredPane.POPUP_LAYER);
            ListPopup.setSize(ListPopup.getPreferredSize());
            ListPopup.setLocation(e.getPoint());
            
        }
        
    }
    
    public void deletePopup() {
        ListPopup = null;
    }
    
}
