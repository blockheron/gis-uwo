/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.western;

/**
 *
 * @author valen
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.*;


public class CustomTip implements Runnable {

    private Popup popup;
    private JLabel helpText = new JLabel("");
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomTip());
    }

    @Override
    public void run() {
        JPanel panel = new JPanel();
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (popup != null) {
//                    popup.hide();
//                }
//                JLabel text = new JLabel("You've clicked at: " + e.getPoint());
//                popup = PopupFactory.getSharedInstance().getPopup(e.getComponent(), text, e.getXOnScreen(), e.getYOnScreen());
//                popup.show();
//            }
//            
//        });

        // creates a help page window 
        JFrame frm = new JFrame("Help Page");
        frm.add(panel);
        frm.setSize(400, 300);
        frm.add(helpText);
        helpText.setSize(400, 300);
        frm.setLocationRelativeTo(null);
//        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.setVisible(true);
//       JTextField textField2 = new JTextField("Enter Name");
//       frm.add(textField2);\
        // creates a grid of size 1x1 to store text 
        
        
        
        
        
    }
   
}