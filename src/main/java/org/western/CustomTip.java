/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.western;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.border.Border;

    /**
     * CustomTip class to implement help pop up window
     * @author Valentina
     */
    public class CustomTip implements Runnable {

    private Popup popup;
    private JLabel helpText = new JLabel("<html>1. To access the main map, please double-click on the small map located in the top left corner of the screen.<BR><BR> 2. The up and down toggle button can be found in the bottom left corner of the screen and is used to switch between levels of the building.<BR><BR>3. In order to search for points of interest (POIs), buildings, and floors, please utilize the search function located in the top right corner of the screen.<BR><BR>4. To pan the image, simply press and hold down the mouse button.<BR><BR>5. When clicked on the search button, there will be an option to filter out layers based on the building you are at.<BR><BR>6. Users will have the ability to mark rooms and buildings as favourites.<BR><BR>7.	To filter the map layers, locate and click on the layer button located to the right of the screen. From there, you can select to view specific layers such as washrooms, classrooms, or your saved points of interest (POIs).</html>");
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomTip());
    }
    
    /**
     * Creates pop up window and set necessary design and text
     * @author Valentina
     */
    @Override
    public void run() {
        int padding = 15;
        JPanel panel = new JPanel();
        // creates a help page window 
        JFrame frm = new JFrame("Help Page");
        // sets window background color to white 
        frm.getContentPane().setBackground(new java.awt.Color(255, 255, 255) );
        Border blackline = BorderFactory.createLineBorder(Color.black);
    
        helpText.setBorder(
                BorderFactory.createCompoundBorder(
                        blackline, BorderFactory.createEmptyBorder(0, padding, 0, padding)));
        helpText.setFont(new Font("Serif", Font.PLAIN, 18));
        frm.add(panel);
        frm.setSize(600, 500);
        frm.add(helpText);
        helpText.setSize(600, 500);
        frm.setLocationRelativeTo(null);
        frm.add(helpText);
        frm.setVisible(true);
 
    }
   
}