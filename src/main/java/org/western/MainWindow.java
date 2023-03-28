/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.western;

import org.kordamp.ikonli.remixicon.RemixiconAL;
import org.kordamp.ikonli.remixicon.RemixiconMZ;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import java.util.Dictionary;
import java.util.LinkedList;

/**
 * @author m
 */
public class MainWindow extends javax.swing.JFrame {
    private int session = -1; // -1 for guest, 0 for admin, 1 for user
    private int building = 0;

    private int x, y, initialX, initialY, deltaX, deltaY;
    private boolean editMode = false;
    private boolean addingRoom = false;
    private LinkedList<Room> rooms;
    private Icon editButtonEnabled, editButtonDisabled;
    private Icon addRoomButtonEnabled, addRoomButtonDisabled;
    private Room draftRoom;
    private Polygon draftPoly;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        
        rooms = new LinkedList<Room>();
        
        initComponents();
        initMainWindow();
        initSearchBox();
        initButtons();
        renderFrame();
        prepareIcon();
        renderRooms();
    }


    public MainWindow(int session) {
        this.session = session;
        initComponents();
        initMainWindow();
        initSearchBox();
        renderFrame();
        prepareIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPanel = new javax.swing.JLayeredPane();
        Frame = new javax.swing.JPanel();
        editButton = new javax.swing.JToggleButton();
        addRoomButton = new javax.swing.JToggleButton();
        searchPanel = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        onSearch = new javax.swing.JButton();
        dropDownPanel = new javax.swing.JPanel();
        filterPanel = new javax.swing.JPanel();
        filterIcon = new javax.swing.JLabel();
        filterText = new javax.swing.JLabel();
        selectBox = new javax.swing.JComboBox<>();
        resultContainer = new javax.swing.JPanel();
        resultPanel = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        layerPanel.setPreferredSize(new java.awt.Dimension(1200, 800));

        Frame.setForeground(new java.awt.Color(130, 130, 130));
        Frame.setPreferredSize(new java.awt.Dimension(1200, 800));

        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editButtonMousePressed(evt);
            }
        });
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        addRoomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRoomButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout FrameLayout = new javax.swing.GroupLayout(Frame);
        Frame.setLayout(FrameLayout);
        FrameLayout.setHorizontalGroup(
            FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addRoomButton)
                .addContainerGap(1122, Short.MAX_VALUE))
        );
        FrameLayout.setVerticalGroup(
            FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrameLayout.createSequentialGroup()
                .addContainerGap(787, Short.MAX_VALUE)
                .addGroup(FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addRoomButton)
                    .addComponent(editButton))
                .addContainerGap())
        );

        searchPanel.setForeground(new java.awt.Color(13, 17, 23));
        searchPanel.setPreferredSize(new java.awt.Dimension(280, 40));

        searchBox.setText("Search");
        searchBox.setInheritsPopupMenu(true);
        searchBox.setName(""); // NOI18N
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });

        onSearch.setBorder(null);
        onSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSearchActionPerformed(evt);
            }
        });

        dropDownPanel.setBackground(new java.awt.Color(255, 255, 255));
        dropDownPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        dropDownPanel.setPreferredSize(new java.awt.Dimension(280, 40));

        filterPanel.setBackground(new java.awt.Color(255, 255, 255));
        filterPanel.setPreferredSize(new java.awt.Dimension(280, 40));
        filterPanel.add(filterIcon);

        filterText.setText("Filter by: ");
        filterPanel.add(filterText);

        selectBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectBox.setAutoscrolls(true);
        selectBox.setBorder(null);
        selectBox.setMinimumSize(new java.awt.Dimension(80, 23));
        selectBox.setPreferredSize(new java.awt.Dimension(180, 32));
        filterPanel.add(selectBox);

        resultContainer.setBackground(new java.awt.Color(245, 245, 247));

        resultList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        resultList.setPreferredSize(new java.awt.Dimension(280, 120));
        resultPanel.setViewportView(resultList);

        resultContainer.add(resultPanel);

        javax.swing.GroupLayout dropDownPanelLayout = new javax.swing.GroupLayout(dropDownPanel);
        dropDownPanel.setLayout(dropDownPanelLayout);
        dropDownPanelLayout.setHorizontalGroup(
            dropDownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(resultContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dropDownPanelLayout.setVerticalGroup(
            dropDownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropDownPanelLayout.createSequentialGroup()
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(resultContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        filterPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(onSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dropDownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(onSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dropDownPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        layerPanel.setLayer(Frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPanel.setLayer(searchPanel, javax.swing.JLayeredPane.POPUP_LAYER);

        javax.swing.GroupLayout layerPanelLayout = new javax.swing.GroupLayout(layerPanel);
        layerPanel.setLayout(layerPanelLayout);
        layerPanelLayout.setHorizontalGroup(
            layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPanelLayout.createSequentialGroup()
                .addGroup(layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layerPanelLayout.createSequentialGroup()
                        .addGap(915, 915, 915)
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        layerPanelLayout.setVerticalGroup(
            layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPanelLayout.createSequentialGroup()
                .addGroup(layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        getContentPane().add(layerPanel, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void onSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_onSearchActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonActionPerformed

    private void editButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMousePressed
        editMode = !editMode;
        if (editMode){
            editButton.setIcon(editButtonEnabled);
            addRoomButton.setEnabled(true);
            addRoomButton.setVisible(true);
        }
        else {
            editButton.setIcon(editButtonDisabled);
            addRoomButtonMouseClicked(evt);
            addRoomButton.setEnabled(false);
            addRoomButton.setVisible(false);
        }
    }//GEN-LAST:event_editButtonMousePressed

    private void addRoomButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRoomButtonMouseClicked
        if (editMode && !addingRoom) {
            
            addRoomButton.setIcon(addRoomButtonEnabled);
            addingRoom = true;
            
        }
        else {
            
            addRoomButton.setIcon(addRoomButtonDisabled);
            addingRoom = false;
            //save new room
            draftRoom = null;
            draftPoly = null;
            //
        }
        
    }//GEN-LAST:event_addRoomButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    /**
     * Initialize main window
     * Relocate window to center of screen
     * Set window size
     * Set window background color
     */
    private void initMainWindow() {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setLocation(center.x - this.getWidth() / 2, center.y - this.getHeight() / 2);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        
        layerPanel.addMouseListener(new MouseHandler());
        layerPanel.addMouseMotionListener(new MouseMotionHandler());
        
        layerPanel.setSize(this.getSize());
        //layerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 500));
    }

    /**
     * Initialize search box
     * Set search box to transparent
     * Set placeholder of search box
     * Create hover effect for search button
     */
    private void initSearchBox() {
        int padding = 10;
        // https://stackoverflow.com/questions/10274750/java-swing-setting-margins-on-textarea-with-line-border
        Border defaultBorder = BorderFactory.createLineBorder(Color.decode("#eaeaea")), // create default line border for searchBox
                focusBorder = BorderFactory.createLineBorder(Color.decode("#666666")); // create focused line border for searchBox
        searchPanel.setOpaque(false); // make searchPanel transparent
        dropDownPanel.setVisible(false); // hide dropDownPanel
        searchBox.setText("Search"); // set default text of searchBox
        searchBox.setForeground(Color.decode("#999999")); // set default color of searchBox
        searchBox.setBorder(
                BorderFactory.createCompoundBorder(
                        defaultBorder, BorderFactory.createEmptyBorder(0, padding, 0, padding)
                )
        ); // set inset padding of searchBox
        selectBox.setBackground(Color.decode("#ffffff")); // set background color of searchBox
        selectBox.setForeground(Color.decode("#999999")); // set default color of searchBox

        searchBox.addKeyListener(new KeyAdapter() { // add key listener to searchBox
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleSearch();
                }
            }
        });
        searchBox.addFocusListener(new FocusAdapter() { // add placeholder effect to searchBox
            @Override
            public void focusGained(FocusEvent e) {
                dropDownPanel.setVisible(true); // show dropDownPanel when searchBox is focused
                if (searchBox.getText().equals("Search")) {
                    searchBox.setText("");
                    searchBox.setForeground(Color.decode("#000000"));
                    searchBox.setBorder(
                            BorderFactory.createCompoundBorder(
                                    focusBorder, BorderFactory.createEmptyBorder(0, padding, 0, padding)
                            )
                    ); // set inset padding of searchBox
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBox.getText().isEmpty()) {
                    searchBox.setText("Search");
                    searchBox.setForeground(Color.decode("#999999"));
                    searchBox.setBorder(
                            BorderFactory.createCompoundBorder(
                                    defaultBorder, BorderFactory.createEmptyBorder(0, padding, 0, padding)
                            )
                    ); // set inset padding of searchBox
                }
            }
        });
        onSearch.setBackground(Color.WHITE); // set background color of onSearch button
        onSearch.setBorder(BorderFactory.createEmptyBorder()); // remove border of onSearch button
        onSearch.setContentAreaFilled(false); // remove background of onSearch button
        onSearch.setOpaque(true); // make onSearch button opaque
        onSearch.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set cursor to hand cursor

        // add hover effect to onSearch button
        onSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onSearch.setBackground(Color.decode("#F5F5F5"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onSearch.setBackground(Color.WHITE);
            }
        });
        onSearch.addActionListener(e -> handleSearch());
    }

    public void initButtons() {
        
        //initialize edit button
        editButtonDisabled = FontIcon.of(RemixiconAL.EDIT_LINE, 24);
        editButtonEnabled = FontIcon.of(RemixiconAL.EDIT_FILL, 24);
        
        editButton.setIcon(editButtonDisabled);
        //
        
        //initialize add room button
        addRoomButtonDisabled = FontIcon.of(RemixiconAL.ADD_BOX_LINE, 24);
        addRoomButtonEnabled = FontIcon.of(RemixiconAL.ADD_BOX_FILL, 24);
        
        addRoomButton.setIcon(addRoomButtonDisabled);
        addRoomButton.setVisible(false);
        addRoomButton.setEnabled(false);
        //
        
    }
    
    /**
     * Render frame
     */
    private void renderFrame() {
        canvas = new Canvas("assets/MC-BF-1.png", this.getWidth(), this.getHeight());
        Frame.add(canvas);
        Frame.setFocusable(true);
        System.out.println("User: " + session + " logged in");

        JsonDB db = new JsonDB("poi", "mc");
//        db.getData().get("data").getAsJsonArray().get(0).getAsJsonObject().get("floor").getAsInt();
        // get floor ^
//        db.getData().get("data").getAsJsonArray().get(0).getAsJsonObject().get("layer").getAsJsonArray();
    }

    /**
     * Prepare icon for onSearch button
     */
    private void prepareIcon() {
        try {
            // Load icon from font library (currently using RemixIcon)
            FontIcon searchIcon = FontIcon.of(RemixiconMZ.SEARCH_LINE, 20, Color.decode("#828282")),
                    filterIcon = FontIcon.of(RemixiconAL.FILTER_2_LINE, 20, Color.decode("#828282"));
            onSearch.setIcon(searchIcon);
            this.filterIcon.setIcon(filterIcon);
        } catch (Exception e) {
            System.out.printf("Error: icons failed to load\n%s", e.getMessage());
        }
    }
    
    private void renderRooms() {
        
        int[] xpoints = {300, 500, 500};
        int[] ypoints = {50, 100, 200};
        int npoints = 3;
        
        Polygon room1Shape = new Polygon(xpoints, ypoints, npoints);
        
        rooms.add(new Room(room1Shape));
        attachRoom(rooms.get(0));
        
        int[] xpoints2 = {100, 200, 300};
        int[] ypoints2 = {100, 200, 250};
        int npoints2 = 3;
        
        Polygon room2Shape = new Polygon(xpoints2, ypoints2, npoints2);
        
        rooms.add(new Room(room2Shape));
        attachRoom(rooms.get(1));
             
    }

    private int handleSearch() {
        JsonDB db; // database instance
        String query = searchBox.getText(); // get query from searchBox
        String[] w; // array of words in query
        StringBuilder sb = new StringBuilder(); // string builder for acronym
        if (query.isEmpty() || query.equals("Search")) {
            System.out.println("Empty query");
            return 1;
        }
        String filter = selectBox.getSelectedItem().toString();
        db = new JsonDB("poi", query);
        if(db.getData().get("status").getAsInt() != 200) {
            w = query.split(" ");
            for (String word : w) {
                sb.append(word.charAt(0));
            }
            db = new JsonDB("poi", sb.toString().toLowerCase());
            if(db.getData().get("status").getAsInt() != 200) {
                System.out.printf("No result found for %s\n", query);
                return 1;
            }
            else {
                System.out.println(db.getData().toString());
            }
        } else {
            System.out.println(db.getData().toString());
        }
        return 0;
    }
    
    private void attachRoom(Room room)
    {
        
        layerPanel.add(room, JLayeredPane.PALETTE_LAYER);
        room.setSize(layerPanel.getSize());
        
    }
    
    public void attachComponent(JComponent comp)
    {
        
        layerPanel.add(comp, JLayeredPane.POPUP_LAYER);
        
    }
    
    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            initialX = e.getX();
            initialY = e.getY();
            
            if (addingRoom) {
                
                
                if(draftRoom == null) {
                    draftPoly = new Polygon();
                    draftPoly.addPoint(e.getX(), e.getY());
                    draftRoom = new Room(draftPoly, new Point(0,0));
                    attachRoom(draftRoom);
                }
                else {
                    
                    //remove previous iteration of the draft
                    layerPanel.remove(draftRoom);
                    rooms.remove(draftRoom);
                    //
                    
                    //add new draft of room
                    draftPoly.addPoint(e.getX()-draftRoom.getX(), e.getY()-draftRoom.getY());                
                    draftRoom = new Room(draftPoly, draftRoom.getLocation()); //fix coordinates off  
                    rooms.add(draftRoom);
                    attachRoom(draftRoom);
                    //
                    
                }
                
            }
            
        }
        
        public void mouseEntered(MouseEvent e) {
            //e = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX()-getX(), e.getY()-getY(), e.getClickCount(), e.isPopupTrigger());
            for (Room room:rooms) room.mouseEntered(e);
            
        }
        
        public void mouseExited(MouseEvent e) {
            //e = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX()-getX(), e.getY()-getY(), e.getClickCount(), e.isPopupTrigger());
            for (Room room:rooms) room.mouseExited(e);
            
        }
        
        public void mouseClicked(MouseEvent e) {
            for (Room room:rooms) room.mouseClicked(e, layerPanel);
        }
        
    }

    class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
            
            for (Room room:rooms) room.mouseMoved(e);
            
        }
        
        public void mouseDragged(MouseEvent e) {
            int currentX = e.getX();
            int currentY = e.getY();
            int deltaX = currentX - initialX;
            int deltaY = currentY - initialY;

            x += deltaX;
            y += deltaY;
            
            canvas.translate(deltaX, deltaY);
            for (Room room:rooms) room.translate(deltaX, deltaY);           

            initialX = currentX;
            initialY = currentY;

            repaint();
        }
    }

    private Canvas canvas;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Frame;
    private javax.swing.JToggleButton addRoomButton;
    private javax.swing.JPanel dropDownPanel;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JLabel filterIcon;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel filterText;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLayeredPane layerPanel;
    private javax.swing.JButton onSearch;
    private javax.swing.JPanel resultContainer;
    private javax.swing.JList<String> resultList;
    private javax.swing.JScrollPane resultPanel;
    private javax.swing.JTextField searchBox;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> selectBox;
    // End of variables declaration//GEN-END:variables
}
