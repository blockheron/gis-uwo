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
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Team 22
 * A JFrame to display all visual components of this program
 */
public class MainWindow extends javax.swing.JFrame {
    private int session = -1; // -1 for guest, 0 for admin, 1 for user

    public static Building curBuilding;
    public static Floor curFloor;
    public static User curUser;
    private LinkedList<Floor> floorList;
    //private LinkedList<Layer> layerList;
    //private LinkedList<Room> roomList;
    private CanvasGUI canvas;

    private int x, y, initialX, initialY, deltaX, deltaY;
    private boolean editMode = false;
    private boolean addingRoom = false;
    private boolean addingPOI = false;
    private Icon editButtonEnabled, editButtonDisabled;
    private Icon addRoomButtonEnabled, addRoomButtonDisabled;
    private Icon addPOIButtonEnabled, addPOIButtonDisabled;
    private Room draftRoom;
    private Polygon draftPoly;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow(boolean debug, User user) {
        
        // Print out user session
        if (user != null) System.out.println("User: " + user.getUsername() + " logged in");
        //demo code used to create a new "blank" database
        if (debug == true) {

            // Make Middlesex and its floors, set as current building
            Building mc = new Building("Middlesex College", "MC");
            mc.addFloor("Ground", "assets/MC-BF-1.png");
            mc.addFloor("2", "assets/MC-BF-2.png");
            mc.addFloor("3", "assets/MC-BF-3.png");
            mc.addFloor("4", "assets/MC-BF-4.png");
            mc.addFloor("5", "assets/MC-BF-5.png");
            
            //
            
            //make PAB and floors
            Building pab = new Building("Physics and Astronomy Building", "PAB");
            pab.addFloor("1", "assets/PAB-BF-1.png");
            pab.addFloor("2", "assets/PAB-BF-2.png");
            pab.addFloor("3", "assets/PAB-BF-3.png");
            pab.addFloor("4", "assets/PAB-BF-4.png");

            //
            
            //make WSC and floors
            Building wsc = new Building("Western Science Center", "WSC");
            wsc.addFloor("1", "assets/WSC-BF-1.png");
            wsc.addFloor("2", "assets/WSC-BF-2.png");
            wsc.addFloor("3", "assets/WSC-BF-3.png");
            wsc.addFloor("4", "assets/WSC-BF-4.png");
            //
            
            //add layers
            Map.addLayer("Classrooms", Color.YELLOW);
            Map.addLayer("Washrooms",Color.CYAN);
            Map.addLayer("unassigned", Color.RED);
            //
            
            //add users
            Map.addUser("admin", "password", true);
            Map.addUser("Liam", "1234");
            Map.addUser("Maxwell", "1234");
            Map.addUser("Karen", "1234");
            Map.addUser("Emma", "1234");
            Map.addUser("Valentina", "1234");
            //
            
            /*int[] xpoints = {300, 500, 500};
            int[] ypoints = {50, 100, 200};
            int npoints = 3;
        
            Polygon room1Shape = new Polygon(xpoints, ypoints, npoints);

            Room testRoom = ground.addRoom(room1Shape, new Point(0,0));
            testRoom.addPOI("test", "description", testRoom.getLocation());
            System.out.println(testRoom.getPOIs());*/
            
            // Set lowest floor as default landing floor
//            curFloor = floorList.get(0);
            //layerList = Map.getLayers();
            //roomList = curFloor.getRooms();

//            System.out.println("curFloor index: " + curBuilding.getFloors().indexOf(curFloor));
        }
        //

        // start on middlesex college ground floor
        curBuilding = Map.getBuilding("Middlesex College");
        curFloor = curBuilding.getFloor("Ground");
        curUser = user;
        floorList = curBuilding.getFloors();
        //layerList = Map.getLayers();
        //roomList = curFloor.getRooms();
        //

        initComponents();
//        myInitComponents();
        initMainWindow();
        initSearch();
        initButtons();
        renderFrame();
        prepareIcon();
        initLayers();
        renderRooms();
        
        // Default floor is min floor; can't go lower
        prevFloorButton.setEnabled(false);
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
        layerListDisplay = new javax.swing.JPanel();
        layerListButton = new javax.swing.JToggleButton();
        classroomLayerRadio = new javax.swing.JRadioButton();
        washroomLayerRadio = new javax.swing.JRadioButton();
        userPOILayerRadio = new javax.swing.JRadioButton();
        classroomLayer = new javax.swing.JPanel();
        userPOILayer = new javax.swing.JPanel();
        washroomLayer = new javax.swing.JPanel();
        nextFloorButton = new javax.swing.JButton();
        prevFloorButton = new javax.swing.JButton();
        addPOIButton = new javax.swing.JToggleButton();
        searchPanel = new javax.swing.JPanel();
        filterBox = new javax.swing.JTextField();
        onSearch = new javax.swing.JButton();
        dropDownPanel = new javax.swing.JPanel();
        filterPanel = new javax.swing.JPanel();
        filterIcon = new javax.swing.JLabel();
        filterText = new javax.swing.JLabel();
        selectBox = new javax.swing.JComboBox<>();
        resultContainer = new javax.swing.JPanel();
        resultScroll = new javax.swing.JScrollPane();
        resultPanel = new javax.swing.JPanel();
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

        layerListDisplay.setBackground(new java.awt.Color(255, 255, 255));

        layerListButton.setBackground(new java.awt.Color(242, 242, 242));
        layerListButton.setText("Layers");
        layerListButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                layerListButtonMouseClicked(evt);
            }
        });
        layerListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerListButtonActionPerformed(evt);
            }
        });

        classroomLayerRadio.setText("Classrooms");
        classroomLayerRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                classroomLayerRadioMouseClicked(evt);
            }
        });

        washroomLayerRadio.setText("Washrooms");
        washroomLayerRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                washroomLayerRadioMouseClicked(evt);
            }
        });
        washroomLayerRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                washroomLayerRadioActionPerformed(evt);
            }
        });

        userPOILayerRadio.setText("User POIs");
        userPOILayerRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPOILayerRadioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layerListDisplayLayout = new javax.swing.GroupLayout(layerListDisplay);
        layerListDisplay.setLayout(layerListDisplayLayout);
        layerListDisplayLayout.setHorizontalGroup(
            layerListDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerListDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layerListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layerListDisplayLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layerListDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(washroomLayerRadio)
                    .addComponent(classroomLayerRadio)
                    .addComponent(userPOILayerRadio, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layerListDisplayLayout.setVerticalGroup(
            layerListDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layerListDisplayLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(classroomLayerRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(washroomLayerRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userPOILayerRadio)
                .addGap(18, 18, 18)
                .addComponent(layerListButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout classroomLayerLayout = new javax.swing.GroupLayout(classroomLayer);
        classroomLayer.setLayout(classroomLayerLayout);
        classroomLayerLayout.setHorizontalGroup(
            classroomLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        classroomLayerLayout.setVerticalGroup(
            classroomLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout userPOILayerLayout = new javax.swing.GroupLayout(userPOILayer);
        userPOILayer.setLayout(userPOILayerLayout);
        userPOILayerLayout.setHorizontalGroup(
            userPOILayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        userPOILayerLayout.setVerticalGroup(
            userPOILayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout washroomLayerLayout = new javax.swing.GroupLayout(washroomLayer);
        washroomLayer.setLayout(washroomLayerLayout);
        washroomLayerLayout.setHorizontalGroup(
            washroomLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        washroomLayerLayout.setVerticalGroup(
            washroomLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        nextFloorButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        nextFloorButton.setText("↑");
        nextFloorButton.setPreferredSize(new java.awt.Dimension(40, 40));
        nextFloorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextFloorButtonActionPerformed(evt);
            }
        });

        prevFloorButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        prevFloorButton.setText("↓");
        prevFloorButton.setPreferredSize(new java.awt.Dimension(40, 40));
        prevFloorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevFloorButtonActionPerformed(evt);
            }
        });

        addPOIButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPOIButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout FrameLayout = new javax.swing.GroupLayout(Frame);
        Frame.setLayout(FrameLayout);
        FrameLayout.setHorizontalGroup(
            FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameLayout.createSequentialGroup()
                .addGroup(FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addRoomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addPOIButton))
                    .addGroup(FrameLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prevFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nextFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1080, Short.MAX_VALUE))
        );
        FrameLayout.setVerticalGroup(
            FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrameLayout.createSequentialGroup()
                .addContainerGap(656, Short.MAX_VALUE)
                .addComponent(nextFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prevFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPOIButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        searchPanel.setForeground(new java.awt.Color(13, 17, 23));
        searchPanel.setPreferredSize(new java.awt.Dimension(280, 40));

        filterBox.setText("Search");
        filterBox.setInheritsPopupMenu(true);
        filterBox.setName(""); // NOI18N
        filterBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBoxActionPerformed(evt);
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
        selectBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBoxActionPerformed(evt);
            }
        });
        filterPanel.add(selectBox);

        resultContainer.setBackground(new java.awt.Color(245, 245, 247));
        resultContainer.setPreferredSize(new java.awt.Dimension(282, 154));
        resultContainer.setLayout(new javax.swing.BoxLayout(resultContainer, javax.swing.BoxLayout.LINE_AXIS));

        resultScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        resultPanel.setBackground(new java.awt.Color(255, 255, 255));
        resultPanel.setPreferredSize(new java.awt.Dimension(280, 0));
        resultPanel.setLayout(new java.awt.GridLayout(0, 1));
        resultScroll.setViewportView(resultPanel);

        resultContainer.add(resultScroll);

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
                        .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dropDownPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        layerPanel.setLayer(Frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPanel.setLayer(searchPanel, javax.swing.JLayeredPane.POPUP_LAYER);

        javax.swing.GroupLayout layerPanelLayout = new javax.swing.GroupLayout(layerPanel);
        layerPanel.setLayout(layerPanelLayout);
        layerPanelLayout.setHorizontalGroup(
            layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layerPanelLayout.createSequentialGroup()
                .addGap(915, 915, 915)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layerPanelLayout.setVerticalGroup(
            layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPanelLayout.createSequentialGroup()
                .addGroup(layerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(layerPanel, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Custom initComponents for button action commands
     */
//    private void myInitComponents() {
//        // Action commands for next/prev floor buttons
//        nextFloorButton.setActionCommand("up");
//        prevFloorButton.setActionCommand("down");
//        // Start at lowest floor by default: prev floor doesn't exist
//        prevFloorButton.setEnabled(false);
//
//    }

    /**
     * Switch to a given building, landing on the first floor by default
     * @param build the building object to switch to
     */
    private void switchToBuild(Building build) {
        // Unrender curFloor's rooms
        unrenderRooms();
        disableLayers();
        
        // Load in new building's data
        curBuilding = build;
        floorList = curBuilding.getFloors();
        curFloor = floorList.get(0);
        
        // Render new building's first floor
        renderRooms();
        enableLayers();
        
        // Render floor image
        ImageIcon pic = null;
        try {
            pic = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(curFloor.getFilePath()))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        canvas.setImage(pic);
        
        // Disable prevFloorButton
        prevFloorButton.setEnabled(false);
    }
    
    /**
     * Switch to a given floor in a building
     * @param floor the floor object to switch to
     */
    private void switchToFloor(Floor floor) {
        // If floor is in the same building, switch floors
        if (!floor.getBuilding().equals(curBuilding)) {
            switchToBuild(floor.getBuilding());
        }
        
        // Unrender curFloor's rooms
        unrenderRooms();
        disableLayers();

        // Load in floor obejct
        curFloor = floorList.get(floorList.indexOf(floor));

        //render the new floor's rooms
        renderRooms();
        enableLayers();

        // Render next floor image
        ImageIcon pic = null;
        try {
            pic = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(curFloor.getFilePath()))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        canvas.setImage(pic);
    }
    
    /**
     * TODO: fix help buttons not existing
     */
    private void helpButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseMoved

    }//GEN-LAST:event_helpButtonMouseMoved

    private void helpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseClicked
        CustomTip p = new CustomTip();
        p.run();
    }//GEN-LAST:event_helpButtonMouseClicked

    /**
     * smallMapBtn when receiving an ActionEvent to switch from building to map
     * (except smallMapBtn doesn't exist; pending deletion)
     * @param evt ActionEvent "mapToBuild" triggered by clicking this button
     */
//    private void smallMapBtnActionPerformed(java.awt.event.MouseEvent evt) {
//        if ("mapToBuild".equals(evt.getActionCommand())) {
//            //unrender rooms on the floor
//            unrenderRooms();
//
//            // Render campus map image
//            ImageIcon pic = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/campus_map.png")));
//            canvas.setImage(pic);
//            curBuilding = null;
//            curFloor = null;
//
//            // Hide building floor map buttons
//            smallMapBtn.setVisible(false);
//            smallMap.setVisible(false);
//            nextFloorBtn.setVisible(false);
//            prevFloorBtn.setVisible(false);
//        }
//    }

    private void filterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterBoxActionPerformed

    private void onSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_onSearchActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonActionPerformed

    private void editButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMousePressed
        editButtonMousePressed();
    }//GEN-LAST:event_editButtonMousePressed

        private void editButtonMousePressed() {
        editMode = !editMode;
        if (editMode){
            editButton.setIcon(editButtonEnabled);
            if (curUser.isAdmin()) {
                addRoomButton.setEnabled(true);
                addRoomButton.setVisible(true);
            }
            addPOIButton.setEnabled(true);
            addPOIButton.setVisible(true);
        }
        else {
            editButton.setIcon(editButtonDisabled);
            addRoomButtonMouseClicked();
            addRoomButton.setEnabled(false);
            addRoomButton.setVisible(false);
            addPOIButtonMouseClicked();
            addPOIButton.setEnabled(false);
            addPOIButton.setVisible(false);
        }
    }

    private void addRoomButtonMouseClicked() {
        if (editMode && !addingRoom) {
            
            if(addingPOI) addPOIButtonMouseClicked();
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
    }

    private void addRoomButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRoomButtonMouseClicked
        addRoomButtonMouseClicked();
    }//GEN-LAST:event_addRoomButtonMouseClicked

    private void washroomLayerRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_washroomLayerRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_washroomLayerRadioActionPerformed

    private void layerListButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_layerListButtonMouseClicked

    }//GEN-LAST:event_layerListButtonMouseClicked

    private void washroomLayerRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_washroomLayerRadioMouseClicked
        // Activate washroomLayer when washroom button is clicked
        /*if (washroomLayerRadio.isSelected()){
            washroomLayer.setVisible(true);   
        } else {
            washroomLayer.setVisible(false);
        }*/
        Layer washrooms = Map.getLayer("Washrooms");
        
        if (washroomLayerRadio.isSelected()) {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.highlight(washrooms.getColor());
            }
        }
        else {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.dehighlight(washrooms.getColor());
            }
        }
        
    }//GEN-LAST:event_washroomLayerRadioMouseClicked

    private void layerListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerListButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_layerListButtonActionPerformed

    private void classroomLayerRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classroomLayerRadioMouseClicked
        // Display classroom Layer
        /*if (classroomLayerRadio.isSelected()){
            classroomLayer.setVisible(true);
        } else {
            classroomLayer.setVisible(false);
        }*/
        Layer classrooms = Map.getLayer("Classrooms");
        
        if (classroomLayerRadio.isSelected()) {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.highlight(classrooms.getColor());
            }
        }
        else {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.dehighlight(classrooms.getColor());
            }
        }
        
    }//GEN-LAST:event_classroomLayerRadioMouseClicked

    private void userPOILayerRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPOILayerRadioMouseClicked
        // Display userPOI layer
        /*if (userPOILayerRadio.isSelected()){
            userPOILayer.setVisible(true);
        } else {
            userPOILayer.setVisible(false);
        }*/
        if(curUser == null) return;
        
        if (userPOILayerRadio.isSelected()) {
            for (Room room : curUser.getRooms(curFloor)) {
                room.highlight(Color.MAGENTA);
            }
        }
        else {
            for (Room room : curUser.getRooms(curFloor)) {
                room.dehighlight(Color.MAGENTA);
            }
        }
        
    }//GEN-LAST:event_userPOILayerRadioMouseClicked
    
    /**
     * nextFloorButton actions when receiving an ActionEvent to switch floors
     * @param evt ActionEvent "down" or "up" triggered by clicking nextFloor or prevFloor
     */
    private void nextFloorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextFloorButtonActionPerformed
//        if ("up".equals(evt.getActionCommand())) {
            // If next floor is max floor, disable. Else, enable
            if (floorList.indexOf(curFloor) + 1 == curBuilding.getFloorNum() - 1) {
                nextFloorButton.setEnabled(false);
                prevFloorButton.setEnabled(true);
            } else {
                nextFloorButton.setEnabled(true);
                prevFloorButton.setEnabled(true);
            }

            //unrender rooms
//            unrenderRooms();
//            disableLayers();
            //if(editMode)

            // Load in next floor obejct (next item in floorList)
//            curFloor = floorList.get(floorList.indexOf(curFloor) + 1);
            switchToFloor(floorList.get(floorList.indexOf(curFloor) + 1));

            //render the next floor's rooms
//            renderRooms();
//            enableLayers();
//
//            // Render next floor image
//            ImageIcon pic = null;
//            try {
//                pic = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(curFloor.getFilePath()))));
//            } catch (IOException ex) {
//                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            canvas.setImage(pic);
//        }
    }//GEN-LAST:event_nextFloorButtonActionPerformed

    /**
     * prevFloorButton actions when receiving an ActionEvent to switch floors
     * @param evt ActionEvent "down" or "up" triggered by clicking nextFloor or prevFloor
     */
    private void prevFloorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevFloorButtonActionPerformed
//        if ("down".equals(evt.getActionCommand())) {
            // If next floor is min floor, disable. Else, enable
            if (floorList.indexOf(curFloor) - 1 == 0) {
                nextFloorButton.setEnabled(true);
                prevFloorButton.setEnabled(false);
            } else {
                nextFloorButton.setEnabled(true);
                prevFloorButton.setEnabled(true);
            }
            
            //unrender this floors rooms
//            unrenderRooms();
//            disableLayers();

            // Load in next floor obejct (prev item in floorList)
//            curFloor = floorList.get(floorList.indexOf(curFloor) - 1);
            switchToFloor(floorList.get(floorList.indexOf(curFloor) - 1));

            //render the new floor's rooms
//            renderRooms();
//            enableLayers();
//
//            // Render next floor image
//            ImageIcon pic = null;
//            try {
//                pic = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(curFloor.getFilePath()))));
//            } catch (IOException ex) {
//                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            canvas.setImage(pic);
//        }
    }//GEN-LAST:event_prevFloorButtonActionPerformed

    private void addPOIButtonMouseClicked() {
        if (editMode && !addingPOI) {
            
            if(addingRoom) addRoomButtonMouseClicked();
            addPOIButton.setIcon(addPOIButtonEnabled);
            addingPOI = true;

        }
        else {

            addPOIButton.setIcon(addPOIButtonDisabled);
            addingPOI = false;

        }
    }
    
    private void addPOIButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPOIButtonMouseClicked
        addPOIButtonMouseClicked();
    }//GEN-LAST:event_addPOIButtonMouseClicked

    private void selectBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectBoxActionPerformed

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
            @Override
            public void run() {
                new JsonDB(false);
                new MainWindow(false, Map.getUser("admin")).setVisible(true);
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
    private void initSearch() {
        int padding = 10;
        // https://stackoverflow.com/questions/10274750/java-swing-setting-margins-on-textarea-with-line-border
        Border defaultBorder = BorderFactory.createLineBorder(Color.decode("#eaeaea")), // create default line border for searchBox
                focusBorder = BorderFactory.createLineBorder(Color.decode("#666666")); // create focused line border for searchBox
        Search s = new Search();
        searchPanel.setOpaque(false); // make searchPanel transparent
        dropDownPanel.setVisible(false); // hide dropDownPanel
        filterBox.setText("Search"); // set default text of searchBox
        filterBox.setForeground(Color.decode("#999999")); // set default color of searchBox
        filterBox.setBorder(
                BorderFactory.createCompoundBorder(
                        defaultBorder, BorderFactory.createEmptyBorder(0, padding, 0, padding)
                )
        ); // set inset padding of searchBox
        selectBox.setBackground(Color.decode("#ffffff")); // set background color of searchBox
        selectBox.setForeground(Color.decode("#999999")); // set default color of searchBox
        selectBox.setFocusable(false);
        selectBox.setFocusTraversalKeysEnabled(false);
        selectBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(new Font("Inter", Font.PLAIN, 12));
                if (isSelected) {
                    setBackground(Color.decode("#eaeaea"));
                }
                return this;
            }
        });
        selectBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                handleSearch();
            }
        });
        selectBox.setModel(new DefaultComboBoxModel<>(s.getFilters()));
        resultScroll.setBorder(null);
        filterBox.addKeyListener(new KeyAdapter() { // add key listener to searchBox
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleSearch();
                }
            }
        });
        filterBox.addFocusListener(new FocusAdapter() { // add placeholder effect to searchBox
            @Override
            public void focusGained(FocusEvent e) {
                dropDownPanel.setVisible(true); // show dropDownPanel when searchBox is focused
                if (filterBox.getText().equals("Search")) {
                    filterBox.setText("");
                    filterBox.setForeground(Color.decode("#000000"));
                    filterBox.setBorder(
                            BorderFactory.createCompoundBorder(
                                    focusBorder, BorderFactory.createEmptyBorder(0, padding, 0, padding)
                            )
                    ); // set inset padding of searchBox
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (filterBox.getText().isEmpty()) {
                    filterBox.setText("Search");
                    filterBox.setForeground(Color.decode("#999999"));
                    filterBox.setBorder(
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

    /**
     * Initialise edit and room buttons
     */
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
        
        //initizlize add POI button
        addPOIButtonDisabled = FontIcon.of(RemixiconAL.ADD_CIRCLE_LINE, 24);
        addPOIButtonEnabled = FontIcon.of(RemixiconAL.ADD_CIRCLE_FILL, 24);

        addPOIButton.setIcon(addPOIButtonDisabled);
        addPOIButton.setVisible(false);
        addPOIButton.setEnabled(false);
        //

    }

    /**
     * Render Frame to display CanvasGUI objects
     */
    private void renderFrame() {
        // Render main window image
        canvas = new CanvasGUI(curFloor.getFilePath(), this.getWidth(), this.getHeight());
        Frame.add(canvas);

        Frame.setFocusable(true);
        System.out.println("User: " + session + " logged in");
    }

    /**
     * Prepare icon for onSearch button
     */
    private void prepareIcon() {
        try {
            // Load icon from font library (currently using RemixIcon)
            FontIcon searchIcon = FontIcon.of(RemixiconMZ.SEARCH_LINE, 20, Color.decode("#828282")),
                    filterIcon = FontIcon.of(RemixiconAL.FILTER_2_LINE, 20, Color.decode("#828282"));
//                    favIcon = FontIcon.of(RemixiconMZ.STAR_LINE, 20, Color.decode("#828282"));
            onSearch.setIcon(searchIcon);
            this.filterIcon.setIcon(filterIcon);
//            this.sLabel.setIcon(favIcon);
        } catch (Exception e) {
            System.out.printf("Error: icons failed to load\n%s", e.getMessage());
        }
    }

    /**
     * Render selectable room boundaries
     */
    private void renderRooms() {       
        for (Room room : curFloor.getRooms()) {  
            attachRoom(room);
        }
             
    }

    public void unrenderRooms() {
        for (Room room : curFloor.getRooms()) {
            detachRoom(room);
        }
    }
    
    private void disableLayers() {
        
        if(curUser != null) {
        
            if (userPOILayerRadio.isSelected()) {
                for (Room room : curUser.getRooms(curFloor)) {
                    room.dehighlight(Color.MAGENTA);
                }
            }
            else {
                for (Room room : curUser.getRooms(curFloor)) {
                    room.highlight(Color.MAGENTA);
                }
            }
                        
        }
        
        Layer washrooms = Map.getLayer("Washrooms");
        
        if (washroomLayerRadio.isSelected()) {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.dehighlight(washrooms.getColor());
            }
        }
        else {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.highlight(washrooms.getColor());
            }
        }
        
        Layer classrooms = Map.getLayer("Classrooms");
        
        if (classroomLayerRadio.isSelected()) {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.dehighlight(classrooms.getColor());
            }
        }
        else {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.highlight(classrooms.getColor());
            }
        }
        
    }
    
    private void enableLayers() {
        
        if(curUser != null) {
        
            if (userPOILayerRadio.isSelected()) {
                for (Room room : curUser.getRooms(curFloor)) {
                    room.highlight(Color.MAGENTA);
                }
            }
            else {
                for (Room room : curUser.getRooms(curFloor)) {
                    room.dehighlight(Color.MAGENTA);
                }
            }
                        
        }
        
        Layer washrooms = Map.getLayer("Washrooms");
        
        if (washroomLayerRadio.isSelected()) {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.highlight(washrooms.getColor());
            }
        }
        else {
            for (Room room : washrooms.getRooms(curFloor)) {
                room.dehighlight(washrooms.getColor());
            }
        }
        
        Layer classrooms = Map.getLayer("Classrooms");
        
        if (classroomLayerRadio.isSelected()) {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.highlight(classrooms.getColor());
            }
        }
        else {
            for (Room room : classrooms.getRooms(curFloor)) {
                room.dehighlight(classrooms.getColor());
            }
        }
        
    }

    private int handleSearch() {
        String q = filterBox.getText(), // get query from searchBox
        f = selectBox.getSelectedItem().toString(), // get filter from filterBox
        bN = ""; // building name
        AtomicReference<String> pN;
        Boolean iB;// whether query is a building name
                // whether there is any result
        AtomicReference<Boolean> hasResult = new AtomicReference<>(false);
        LinkedList<Building> bL = new LinkedList<>(); // get list of buildings
        Building b = null; // building instance
        Search s = new Search(); // search instance
        resultPanel.removeAll();
        resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width, 0));
        if (q.isEmpty() || q.equals("Search")) {
            iB = false;
            bL = Map.getBuildings();
        } else {
            b = s.searchBuilding(q);
            if (b != null) {
                if(q.toUpperCase().equals(b.getShortName()))
                {
                    iB = true;
                    bN = b.getShortName();
                } else {
                    iB = false;
                }
                bL.add(b);
            } else {
                iB = false;
                bL = Map.getBuildings();
            }
        }
        if (bL.size() == 0) {
            resultPanel.add(new ResultLabel());
            return 1;
        }

        if (iB) {
            resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width, resultPanel.getPreferredSize().height + 40));
            resultPanel.add(new ResultLabel(b));
            b.getPOIs().forEach(
                    poi -> {
                        resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width, resultPanel.getPreferredSize().height + 40));
                        resultPanel.add(new ResultLabel(poi));
                        hasResult.set(true);
                    });
        } else {
            // remove building name at the beginning of query
            String finalQ = q.replaceFirst(bN, "");
            bL.forEach(building -> {
                building.getPOIs().forEach(
                        poi -> {
                            if (s.searchPOI(poi, finalQ, f) != null) {
                                resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width, resultPanel.getPreferredSize().height + 40));
                                resultPanel.add(new ResultLabel(poi));
                                hasResult.set(true);
                            }
                        });
            });
        }
        // check if there is any result
        if (!hasResult.get()) {
            resultPanel.add(new ResultLabel());
        }
//        JsonDB db; // database instance
//        String[] w; // array of words in query
//        StringBuilder sb = new StringBuilder(); // string builder for acronym
//        if (query.isEmpty() || query.equals("Search")) {
//            System.out.println("Empty query");
//            return 1;
//        }
//        db = new JsonDB("poi", query);
//        if(db.getData().get("status").getAsInt() != 200) {
//            w = query.split(" ");
//            for (String word : w) {
//                sb.append(word.charAt(0));
//            }
//            db = new JsonDB("poi", sb.toString().toLowerCase());
//            if(db.getData().get("status").getAsInt() != 200) {
//                System.out.printf("No result found for %s\n", query);
//                return 1;
//            }
//            else {
//                System.out.println(db.getData().toString());
//            }
//        } else {
//            System.out.println(db.getData().toString());
//        }
        return 0;
    }
    
 

    private void detachRoom(Room room) {
        layerPanel.remove(room);
    }
    
       /**
     *
     * @param room
     */
    private void attachRoom(Room room) {
        /*if (room.getLayer().equals("washroomLayer")){
            washroomLayer.add(room);
            room.setSize(washroomLayer.getSize());
        } else if (room.getLayer().equals("classroomLayer")){
            classroomLayer.add(room);
            room.setSize(classroomLayer.getSize());
        } else if (room.getLayer().equals("userPOILayer")) {
            userPOILayer.add(room);
            room.setSize(userPOILayer.getSize());
        }*/
        layerPanel.add(room, JLayeredPane.PALETTE_LAYER);
        room.setSize(layerPanel.getSize());
        room.setLocation(canvas.x + room.getSavedLocation().x,
                canvas.y + room.getSavedLocation().y);
            
    }
    
    /**
     * Method to add a component to a JLayeredPane
     * @param comp component to be added
     */
    public void attachComponent(JComponent comp)   {   
        layerPanel.add(comp, JLayeredPane.POPUP_LAYER);
    }

    /**
     *
     */
    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            initialX = e.getX();
            initialY = e.getY();

            if (addingRoom) {

                boolean overlapping = false;
                for (Room room: curFloor.getRooms()) {
                    if(room.isActive() && (draftRoom == null || room.getID() != draftRoom.getID()))
                        overlapping = true;
                }

                if (!overlapping) {
                    
                    if (draftRoom == null) {

                        draftPoly = new Polygon();
                        draftPoly.addPoint(e.getX()-canvas.x, e.getY()-canvas.y);
                        draftRoom = new Room(curBuilding, curFloor, draftPoly);
                        attachRoom(draftRoom);
                    } else {

                        //remove previous iteration of the draft
                        layerPanel.remove(draftRoom);

                        //add new draft of room
                        draftRoom.addPoint(e.getX()-draftRoom.getX(), e.getY()-draftRoom.getY());    
                        attachRoom(draftRoom);
                        //

                    }
                
                }
                
            }
            else if (addingPOI) {
                
                Room overlappingRoom = null;
                for (Room room: curFloor.getRooms()) {
                    if(room.isActive()) overlappingRoom = room;
                }
                
                if (overlappingRoom != null) {
                    if(curUser.isAdmin())
                        overlappingRoom.addPOI(null, "new POI", "add your description here!", e.getPoint());
                    else
                        overlappingRoom.addPOI(curUser, "new POI", "add your description here!", e.getPoint());
                }
                
            }
            
        }

        /**
         *
         * @param e mouse event
         */
        public void mouseEntered(MouseEvent e) {
            if (curBuilding == null)
                for (Building building : Map.getBuildings()) building.translate(deltaX, deltaY);
            else
                for (Room room:curFloor.getRooms()) room.mouseEntered(e);

        }


        /**
         *
         * @param e mouse event
         */
        public void mouseExited(MouseEvent e) {
            //e = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX()-getX(), e.getY()-getY(), e.getClickCount(), e.isPopupTrigger());
            for (Room room : curFloor.getRooms()) room.mouseExited(e);


            if (curBuilding == null)
                for (Building building : Map.getBuildings()) building.translate(deltaX, deltaY);
            else
                for (Room room:curFloor.getRooms()) room.mouseExited(e);

        }


        /**
         *
         * @param e mouse event
         *
         */
        public void mouseClicked(MouseEvent e) {
            
            if (!addingRoom && !addingPOI)
                for (Room room : curFloor.getRooms()) room.mouseClicked(e, layerPanel);
            dropDownPanel.setVisible(false);

        }
        
    }

    /**
     *
     */
    class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
            
            if (curBuilding == null)
                for (Building building : Map.getBuildings()) building.mouseMoved(e);
            else
                for (Room room:curFloor.getRooms()) room.mouseMoved(e);
            
        }

        public void mouseDragged(MouseEvent e) {
            int currentX = e.getX();
            int currentY = e.getY();
            int deltaX = currentX - initialX;
            int deltaY = currentY - initialY;

            x += deltaX;
            y += deltaY;
            
            canvas.translate(deltaX, deltaY);
            if (curBuilding == null)
                for (Building building : Map.getBuildings()) building.translate(deltaX, deltaY);
            else
                for (Room room:curFloor.getRooms()) room.translate(deltaX, deltaY);

            initialX = currentX;
            initialY = currentY;

            repaint();
        }
    }
    
    //Initialize layers
    private void initLayers(){
        //Hide layers
        washroomLayer.setVisible(false);
        classroomLayer.setVisible(false);
        userPOILayer.setVisible(false);
        classroomLayer.setBackground(new java.awt.Color(255,51,102,20));
        washroomLayer.setBackground(new java.awt.Color(255,255,255,50));
        userPOILayer.setBackground(new java.awt.Color(0,204,0,20));
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Frame;
    private javax.swing.JToggleButton addPOIButton;
    private javax.swing.JToggleButton addRoomButton;
    private javax.swing.JPanel classroomLayer;
    private javax.swing.JRadioButton classroomLayerRadio;
    private javax.swing.JPanel dropDownPanel;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JTextField filterBox;
    private javax.swing.JLabel filterIcon;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel filterText;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToggleButton layerListButton;
    private javax.swing.JPanel layerListDisplay;
    private javax.swing.JLayeredPane layerPanel;
    private javax.swing.JButton nextFloorButton;
    private javax.swing.JButton onSearch;
    private javax.swing.JButton prevFloorButton;
    private javax.swing.JPanel resultContainer;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JScrollPane resultScroll;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> selectBox;
    private javax.swing.JPanel userPOILayer;
    private javax.swing.JRadioButton userPOILayerRadio;
    private javax.swing.JPanel washroomLayer;
    private javax.swing.JRadioButton washroomLayerRadio;
    // End of variables declaration//GEN-END:variables

}

