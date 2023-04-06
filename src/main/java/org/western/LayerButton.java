/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.western;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Liam
 */
public class LayerButton extends JToggleButton {
    
    public static int HEIGHT = 28;
    private Layer layer;
    private boolean pressed = false;
    
    public LayerButton(Layer layer) {
        
        super(layer.getName());
        this.layer = layer;
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {;
                pressed = !pressed;
                
                if (pressed) {
                    for (Room room : layer.getRooms(MainWindow.curFloor)) {
                        System.out.println(room.getID());
                        room.hightlight(layer.getColor());
                    }
                }
                else {
                    for (Room room : layer.getRooms(MainWindow.curFloor)) {
                        room.dehightlight(layer.getColor());
                    }
                }
                        
            }
            
        });
    
    }
        
}
