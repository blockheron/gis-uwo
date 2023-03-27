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
public class POIButton extends JButton {
    
    public static int HEIGHT = 28;
    private POI POI;
    private POIListPopup parentPopup;
    
    public POIButton(POI POI, POIListPopup parentPopup) {
        
        super(POI.getName());
        this.POI = POI;
        this.parentPopup = parentPopup;
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                POIPopup popup = new POIPopup(POI);
                parentPopup.getParent().add(popup, JLayeredPane.POPUP_LAYER);
                popup.setSize(popup.getPreferredSize());
                popup.setLocation(parentPopup.getX(), parentPopup.getY());
                parentPopup.close();
                        
            }
            
        });
        
    }
    
}
