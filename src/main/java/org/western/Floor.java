package org.western;

import java.util.Dictionary;
import java.util.LinkedList;
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
        //this.id = building.addFloor(building, name, filePath).get("id");
    }
    
    public Floor(JsonObject building, JsonObject floor) {
        this.building = new Building(building);
        this.id = floor.get("id").getAsInt();
    }
    
    private JsonObject getThis() {
        
        JsonDB.load();
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
    
    /*
    public Layer[] getLayers(String floor) {
        data.get("layers");
    }
    public Layer getLayer(String floor) {
        return layers.get(floor);
    }
    public POI getPOI(String floor, String name) {
        return layers.get(floor).getPOI(name);
    }
    public void display(String floor) {
        layers.get(floor).display();
    }*/
}