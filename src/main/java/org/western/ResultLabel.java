package org.western;

import org.kordamp.ikonli.remixicon.RemixiconMZ;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class ResultLabel extends JPanel {
    private JLabel pLabel = new JLabel(); // placeholder label
    private JLabel bLabel = new JLabel(); // building label
    private JLabel fLabel = new JLabel(); // floor label
    private JLabel rLabel = new JLabel(); // room label
    private JLabel sLabel = new JLabel(); // search label
    private FontIcon favIcon = FontIcon.of(RemixiconMZ.STAR_FILL, 20, Color.decode("#ffcc00"));
    private FontIcon unfavIcon = FontIcon.of(RemixiconMZ.STAR_LINE, 20, Color.BLACK);
    ResultLabel() {
        super();
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        bLabel.setText("No Results");
        this.add(bLabel);
    }
    ResultLabel(POI poi) {
        super();
        if(poi == null)
        {
            this.setPreferredSize(new java.awt.Dimension(280, 40));
            bLabel.setText("Unknown POI");
            this.add(bLabel);
            return;
        }
        bLabel.setText("Building");
        fLabel.setText("Floor");
        rLabel.setText("Room");
        bLabel.setFont(new java.awt.Font("Inter", 0, 14));
        fLabel.setFont(new java.awt.Font("Inter", 0, 14));
        rLabel.setFont(new java.awt.Font("Inter", 0, 14));
        this.setBackground(new java.awt.Color(234, 234, 234));
        this.setPreferredSize(new java.awt.Dimension(280, 40));
        this.setLayout(new java.awt.GridBagLayout());
        pLabel.setPreferredSize(new java.awt.Dimension(20, 40));
        this.add(pLabel, new java.awt.GridBagConstraints());
        if(poi.getBuilding() != null && poi.getBuilding().getShortName() != null)
        {
            bLabel.setText(poi.getBuilding().getShortName());
        }
        bLabel.setPreferredSize(new java.awt.Dimension(40, 17));
        this.add(bLabel, new java.awt.GridBagConstraints());
        if(poi.getFloor() != null && poi.getFloor().getName() != null)
        {
            String[] words = poi.getFloor().getName().split(" ");
            StringBuilder floorName = new StringBuilder();
            for(String word : words)
            {
                floorName.append(word.charAt(0));
            }
            fLabel.setText(floorName.toString());
        }
        fLabel.setPreferredSize(new java.awt.Dimension(40, 17));
        this.add(fLabel, new java.awt.GridBagConstraints());
        if(poi.getRoom() != null && poi.getRoom().getName() != null)
        {
            rLabel.setText(poi.getRoom().getName());
        }
        rLabel.setPreferredSize(new java.awt.Dimension(140, 17));
        this.add(rLabel, new java.awt.GridBagConstraints());
        sLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        sLabel.setIcon(unfavIcon);
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
