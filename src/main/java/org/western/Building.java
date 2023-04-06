package org.western;
import com.google.gson.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Building extends JComponent {
    private int id;
    private static Color DEFAULT_COLOR = new Color(200,200,200,50); //transparent grey
    private static Color DEFAULT_ACTIVE_COLOR = new Color(102, 178, 255, 50);//light blue with transparency

    private Color curColor;
    private Color curActiveColor;
    private boolean active = false;
    
    private static HashMap<Integer, Building> loadedBuildings = new HashMap<Integer, Building>();
    
    /**
     * create a new building
     * @param name the name of the building
     * @param shortName the short name of the building
     * @param shape the shape of the building button
     */
    public Building(String name, String shortName, Polygon shape, Point position) {
        
        id = JsonDB.addBuilding(name, shortName, shape, position).get("id").getAsInt();
        curColor = DEFAULT_COLOR;
        curActiveColor = DEFAULT_ACTIVE_COLOR;
        loadedBuildings.put(id, this);
        
    }
    /**
     * create a new building
     * @param name the name of the building
     * @param shortName the short name of the building
     */
    public Building(String name, String shortName) {
        
        this(name, shortName,  new Polygon(), new Point(0,0));
        
    }
    private Building(JsonObject building) {
        
        this.id = building.get("id").getAsInt();
        curColor = DEFAULT_COLOR;
        curActiveColor = DEFAULT_ACTIVE_COLOR;
        loadedBuildings.put(id, this);
        
    }
    
    /**
     * get a building from its JsonObject representation
     * @param building the JsonObject representation of the building
     * @return the building object
     */
    public static Building getBuilding(JsonObject building) {
        int buildingID = building.get("id").getAsInt();
        if (loadedBuildings.containsKey(buildingID))
            return loadedBuildings.get(buildingID);
        
        return new Building(building);
    }
    
    public void free() {
        loadedBuildings.remove(id);
    }
    
    private JsonObject getThis() {
        return JsonDB.getBuilding(id);
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return getThis().get("name").getAsString(); 
    }
    public void setName(String name) {
        getThis().addProperty("name", name);
        JsonDB.save();
    }
    
    public String getShortName() {
        return getThis().get("shortName").getAsString();    
    }
    public void setShortName (String shortName) {
        getThis().addProperty("shortName", shortName);
        JsonDB.save();
    }
    
    /**
     * gets the location of the building that is saved in the database (not the current position)
     * @return the position of the building that is saved in the database
     */
    public Point getSavedLocation() {
        return new Point(getThis().get("x").getAsInt(), getThis().get("y").getAsInt());
    }
    
    /**
     * gets the shape of the building button
     * @return a polygon representing the shape of the building button
     */
    public Polygon getShape() {
        
        int npoints = getThis().get("npoints").getAsInt();

        int[] xpoints = new int[npoints];
        JsonArray jsonxpoints = getThis().get("xpoints").getAsJsonArray();
        for (int i = 0; i < npoints; ++i) 
            xpoints[i] = jsonxpoints.get(i).getAsInt();

        int[] ypoints = new int[npoints];
        JsonArray jsonypoints = getThis().get("ypoints").getAsJsonArray();
        for (int i = 0; i < npoints; ++i) 
            ypoints[i] = jsonypoints.get(i).getAsInt();

        //curShape = new Polygon(xpoints, ypoints, npoints);
        return new Polygon(xpoints, ypoints, npoints);
        
    }
    
    /**
     * set the shape of the building button
     * @param shape the shape to set to
     */
    public void setShape(Polygon shape) {
        
        getThis().addProperty("npoints", shape.npoints);
        JsonArray xpoints = new JsonArray(shape.npoints);
        JsonArray ypoints = new JsonArray(shape.npoints);
        
        for (int x : shape.xpoints) xpoints.add(x);
        for (int y : shape.ypoints) ypoints.add(y);
        getThis().add("xpoints", xpoints);
        getThis().add("ypoints", ypoints);
        JsonDB.save();
        
    }
    
    /**
     * add a point to the building button's shape
     * @param x the x coordinate of the point to add
     * @param y the y coordinate of the point to add
     */
    public void addPoint(int x, int y) {
        Polygon shape = getShape();
        shape.addPoint(x, y);
        setShape(shape);
    }
    
    public Rectangle getBounds() {
        return getShape().getBounds();
    }
    
    /**
     * gets the current number of floors the building has
     * @return the current number of floors the building has
     */
    public int getFloorNum() {
        return getThis().get("count").getAsInt();
    }
    
    /**
     * gets the floors in the building from lowest floor to highest floor
     * @return a sorted linked list of floors from lowest to highest
     */
    public LinkedList<Floor> getFloors() {
        
        JsonArray floors = JsonDB.getFloors(this);
        LinkedList<Floor> out = new LinkedList<Floor>();
        
        for (JsonElement floor:floors) 
            out.add(Floor.getFloor(getThis(), floor.getAsJsonObject()));
        
        return out;
        
    }
    
    public LinkedList<Room> getRooms() {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        for (Floor floor : getFloors()) {
            for (Room room : floor.getRooms()) {
                out.add(room);
            }
        }
        return out;
        
    }
    
    public LinkedList<POI> getPOIs() {
    
        LinkedList<POI> out = new LinkedList<POI>();
        
        if (getFloors().isEmpty()) return out;
        for (Floor floor : getFloors()) {
            if (floor.getRooms().isEmpty()) continue;
            for (Room room : floor.getRooms()) {
                if (room.getPOIs().isEmpty()) continue;
                for (POI poi : room.getPOIs()) {
                    out.add(poi);
                }
            }
        }
        
        return out;
        
    }
    
    /**
     * gets a floor in the building
     * @param id the id of the building
     * @return the floor if it exists otherwise null
     */
    public Floor getFloor(int id) {
        if (getFloors().isEmpty()) return null;
        for (Floor floor: getFloors()) {
            if (floor.getID() == id) return floor;
        }
        return null;
    }
    
    /**
     * gets a floor in the building
     * @param name the name of the building
     * @return the floor if it exists otherwise null
     */
    public Floor getFloor(String name) {
        if (getFloors().isEmpty()) return null;
        for (Floor floor: getFloors()) {
            if (floor.getName().equals(name)) return floor;
        }
        return null;
    }
    
    /**
     * adds a floor to this building
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @return the floor if added successfully, null on fail
     */
    public Floor addFloor(String name, String filePath) {
        return new Floor(this, name, filePath);
    }
    /**
     * inserts a floor into this building after the floor with id prevFloorID
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @param prevFloorID the id of the floor to insert after
     * @return the added floor on success, null on fail
     */
    public Floor addFloor(String name, String filePath, int prevFloorID) {
        return new Floor(this, name, filePath, prevFloorID);
    }
    
    /**
     * moves the building to match the canvas movement
     * @param x the change in x coordinate to translate by
     * @param y the change in y coordinate to translate by
     */
    public void translate (int x, int y) {
        setLocation(getX()+x, getY()+y); //set location in scene
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        
        if (!active) g2D.setColor(curColor);
        else g2D.setColor(curActiveColor);
        g2D.fillPolygon(getShape());
        
    }
    
    public void mouseMoved(MouseEvent e) {
        //check if the mouse is even in the bounding box
        if (!getBounds().contains(e.getX()-getX(), e.getY()-getY())) {
            if (active) {
               active = false;
               repaint();
            }
        return;
        }
        //

        //check if the mouse is within the polygon
        boolean contained = getShape().contains(e.getX()-getX(), e.getY()-getY());

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
    
    public void mouseClicked(MouseEvent e, JLayeredPane layerPanel) {
        
    }
    
}