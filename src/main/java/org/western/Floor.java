package org.western;

import java.util.Dictionary;
import java.util.LinkedList;

import java.awt.*;
import com.google.gson.*;
import java.util.HashMap;

public class Floor {
    
    //private JsonObject data;
    //private LinkedList<Layer> layers;
    private int id;
    private Building building;
    private static HashMap<Integer, Floor> loadedFloors = new HashMap<Integer, Floor>();
    //private String name;
    
    
    public Floor(Building building, String name, String filePath) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath).get("id").getAsInt();
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
    
    public Layer getLayer(int id) {
        for (Layer layer: getLayers()) {
            if (layer.getID() == id) return layer;
        }
        return null;
    }
    
    public Layer getLayer(String name) {
        for (Layer layer: getLayers()) {
            if (layer.getName().equals(name)) return layer;
        }
        return null;
    }
    
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
    
    public void addRoom(Room room) {
        
    }
    
    public void removeRoom(Room room) {
        for (Layer layer : getLayers()) {
            layer.remove(room);
        }
    }
    
}