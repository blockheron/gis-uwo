package org.western;

import org.kordamp.ikonli.remixicon.RemixiconMZ;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class ResultLabel extends JPanel {
    private JTextField pField = new JTextField(); // poi field
    private JLabel sLabel = new JLabel(); // star label
    private FontIcon favIcon = FontIcon.of(RemixiconMZ.STAR_FILL, 20, Color.decode("#ffcc00"));
    private FontIcon unfavIcon = FontIcon.of(RemixiconMZ.STAR_LINE, 20, Color.BLACK);
    ResultLabel() {
        super();
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setBackground(new java.awt.Color(234, 234, 234));
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setLayout(new java.awt.GridBagLayout());
        pField.setPreferredSize(new java.awt.Dimension(240, 40));
        pField.setFont(new java.awt.Font("Inter", 0, 14));
        pField.setEditable(false);
        pField.setBorder(null);
        pField.setBackground(new java.awt.Color(234, 234, 234));
        pField.setText("No Results found.");
        this.add(pField);
    }
    ResultLabel(Building building) {
        super();
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setBackground(new java.awt.Color(234, 234, 234));
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setLayout(new java.awt.GridBagLayout());
        pField.setPreferredSize(new java.awt.Dimension(240, 40));
        pField.setFont(new java.awt.Font("Inter", 0, 14));
        pField.setEditable(false);
        pField.setBorder(null);
        pField.setBackground(new java.awt.Color(234, 234, 234));
        pField.setText(String.format("Building %s", building.getName()));
        this.add(pField);
        sLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        sLabel.setIcon(unfavIcon);
        sLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(sLabel.getIcon() == favIcon)
                {
                    sLabel.setIcon(unfavIcon);
                    // remove from favorites
                }
                else
                {
                    sLabel.setIcon(favIcon);
                    // add to favorites
                }
            }
        });
        this.add(sLabel);
    }
    ResultLabel(POI poi) {
        super();
        StringBuilder sB = new StringBuilder(); // string builder for poi string
        if(poi == null)
        {
            this.setPreferredSize(new java.awt.Dimension(280, 40));
            pField.setText("Unknown POI");
            this.add(pField);
            return;
        }
        this.setBackground(new java.awt.Color(234, 234, 234));
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setLayout(new java.awt.GridBagLayout());
        pField.setPreferredSize(new java.awt.Dimension(240, 40));
        pField.setFont(new java.awt.Font("Inter", 0, 14));
        pField.setEditable(false);
        pField.setBorder(null);
        pField.setBackground(new java.awt.Color(234, 234, 234));
        if(poi.getBuilding() != null && poi.getBuilding().getShortName() != null)
        {
            sB.append(" ").append(poi.getBuilding().getShortName());
        }
        if(poi.getFloor() != null && poi.getFloor().getName() != null)
        {
            String[] words = poi.getFloor().getName().split(" ");
            StringBuilder floorName = new StringBuilder();
            for(String word : words)
            {
                floorName.append(word.charAt(0));
            }
            sB.append(" ").append(floorName);
        }
        if(poi.getRoom() != null && poi.getRoom().getName() != null)
        {
            sB.append(" ").append(poi.getRoom().getName());
        }
        if(poi.getName() != null)
        {
            sB.append(" ").append(poi.getName());
        }
        pField.setText(sB.toString());
        this.add(pField, new java.awt.GridBagConstraints());
        sLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        sLabel.setIcon(unfavIcon);
        // add hover effect
//        addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                setBackground(new java.awt.Color(234, 234, 234));
//                pField.setBackground(new java.awt.Color(234, 234, 234));
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                setBackground(new java.awt.Color(255, 255, 255));
//                pField.setBackground(new java.awt.Color(255, 255, 255));
//            }
//        });
        // add onclick
        sLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(sLabel.getIcon() == favIcon)
                {
                    sLabel.setIcon(unfavIcon);
                    // remove from favorites
                }
                else
                {
                    sLabel.setIcon(favIcon);
                    // add to favorites
                }
            }
        });
        this.add(sLabel, new java.awt.GridBagConstraints());
    }
}
