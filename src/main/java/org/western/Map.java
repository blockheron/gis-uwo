package org.western;
import com.google.gson.*;

import java.util.LinkedList;

public class Map {
    
    //private Building[] buildings;
    //private String[] layers;

    public Map() {
        
        new JsonDB();
        
    }
    
    public static LinkedList<Building> getBuildings() {
        JsonDB.load();
        JsonArray buildings = JsonDB.getBuildings();
        LinkedList<Building> out = new LinkedList<Building>();
        
        for (JsonElement building:buildings) 
            out.add(new Building(building.getAsJsonObject()));
        
        return out;
        
    }
    
    public static Building getBuilding(String name) {
        
        for (Building building: getBuildings()) {
            if (building.getName().equals(name)) return building;
        }
        return null;
        
    }
    
    public static Building addBuilding(String name, String shortName) {
        return new Building(name, shortName);
    }
    
}
