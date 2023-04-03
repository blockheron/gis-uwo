package org.western;

import java.util.Dictionary;
import java.util.LinkedList;

import java.awt.*;
import com.google.gson.*;
import java.util.HashMap;

public class Floor {
    
    private int id;
    private Building building;
    private static HashMap<Integer, Floor> loadedFloors = new HashMap<Integer, Floor>();  
    
    /**
     * creates a new floor as the top floor of the passed building
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image file of the floor
     */
    public Floor(Building building, String name, String filePath) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath).get("id").getAsInt();
        addLayer("Unassigned", new Color(200, 0, 0, 50));
        loadedFloors.put(id, this);
    }
    
    /**
     * inserts a floor above the floor given by prevFloorID
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image file of the floor
     * @param prevFloorID the ID of the floor to add the new floor above (use -1 to add a new bottom floor)
     */
    public Floor(Building building, String name, String filePath, int prevFloorID) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath, prevFloorID).get("id").getAsInt();
        addLayer("Unassigned", new Color(200, 0, 0, 50));
        loadedFloors.put(id, this); 
    }
    
    private Floor(JsonObject building, JsonObject floor) {
        this.building = Building.getBuilding(building);
        this.id = floor.get("id").getAsInt();
        loadedFloors.put(id, this);
    }
    
    public static Floor getFloor(JsonObject building, JsonObject floor) {
        int floorID = floor.get("id").getAsInt();
        if (loadedFloors.containsKey(floorID))
            return loadedFloors.get(floorID);
        
        return new Floor(building, floor);
    }
    
    private JsonObject getThis() {
        
        return JsonDB.getFloor(building, id);
        
    }
    
    public int getID() {
        return id;
    }
    public Building getBuilding() {
        return building;
    }
    public String getName() {
        return getThis().get("name").getAsString();
    }
    public String getFilePath() {
        return getThis().get("filePath").getAsString();
    }
    public int getLayerNum() {
        return getThis().get("count").getAsInt();
    }
    
    public LinkedList<Layer> getLayers() {
        
        JsonArray layers = JsonDB.getLayers(building, this);
        LinkedList<Layer> out = new LinkedList<Layer>();
        
        for (JsonElement layer:layers) 
            out.add(Layer.getLayer(JsonDB.getBuilding(building.getID()),
                    getThis(),
                    layer.getAsJsonObject()
            ));
        
        return out;
        
    }
    
    /**
     * gets a layer in the floor based on the layer's id
     * @param id the id of the layer to get 
     * @return the layer if it exists, otherwise null
     */
    public Layer getLayer(int id) {
        for (Layer layer: getLayers()) {
            if (layer.getID() == id) return layer;
        }
        return null;
    }
    
    /**
     * gets a layer in the floor based on the layer's id
     * @param name the name of the layer to get 
     * @return the layer if it exists, otherwise null
     */
    public Layer getLayer(String name) {
        for (Layer layer: getLayers()) {
            if (layer.getName().equals(name)) return layer;
        }
        return null;
    }
    
    /**
     * adds a new layer to the floor
     * @param name the name of the layer to add
     * @param color the color of the layer to add
     * @return the layer on success, otherwise null
     */
    public Layer addLayer(String name, Color color) {
        return new Layer(building, this, name, color);
    }
    
    public LinkedList<Room> getRooms() {
        LinkedList<Room> rooms = new LinkedList<Room>();
        for (Layer layer : getLayers()) {
            for(Room room : layer.getRooms())
                rooms.add(room);
        }
        return rooms;
    }
    
    /*public void addRoom(Room room) {
        
    }*/
    
    /**
     * remove a room from the floor
     * @param room the room to remove
     */
    public void removeRoom(Room room) {
        for (Layer layer : getLayers()) {
            layer.remove(room);
        }
    }
    
}