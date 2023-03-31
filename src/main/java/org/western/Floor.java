package org.western;

import java.util.Dictionary;
import java.util.LinkedList;

import java.awt.*;
import com.google.gson.*;

public class Floor {
    
    //private JsonObject data;
    //private LinkedList<Layer> layers;
    private int id;
    private Building building;
    //private String name;
    
    
    public Floor(Building building, String name, String filePath) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath).get("id").getAsInt();
        addLayer("Unassigned", new Color(200, 0, 0, 50));
    }
    
    public Floor(JsonObject building, JsonObject floor) {
        this.building = new Building(building);
        this.id = floor.get("id").getAsInt();
    }
    
    private JsonObject getThis() {
        return JsonDB.getFloor(building, id);       
    }
    
    public void invalidate() {
        JsonDB.invalidateFloor(this);
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
        
        JsonDB.load();
        JsonArray layers = JsonDB.getLayers(building, this);
        LinkedList<Layer> out = new LinkedList<Layer>();
        
        for (JsonElement layer:layers) 
            out.add(new Layer(JsonDB.getBuilding(building.getID()),
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