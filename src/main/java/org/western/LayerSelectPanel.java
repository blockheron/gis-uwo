/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.western;

import javax.swing.*;

/**
 *
 * @author Liam
 */
public class LayerSelectPanel extends javax.swing.JPanel {

    /**
     * Creates new form layerSelectPanel
     */
    public LayerSelectPanel() {
        initComponents();
        
        for (Layer layer:Map.getLayers()) {
                       
            JToggleButton button = new LayerButton(layer);
            ButtonContainer.add(button);
            button.setSize(ScrollPane.getPreferredSize().width, LayerButton.HEIGHT);
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        ButtonContainer = new javax.swing.JPanel();

        jLabel1.setText("Layers:");

        javax.swing.GroupLayout ButtonContainerLayout = new javax.swing.GroupLayout(ButtonContainer);
        ButtonContainer.setLayout(ButtonContainerLayout);
        ButtonContainerLayout.setHorizontalGroup(
            ButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        ButtonContainerLayout.setVerticalGroup(
            ButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        ScrollPane.setViewportView(ButtonContainer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonContainer;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}