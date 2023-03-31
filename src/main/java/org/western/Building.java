package org.western;
import com.google.gson.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Building {
    private int id;
    //private String name;
    //private String shortName;
    //private POI[] POIs;
    //private JsonObject data;

    private static HashMap<Integer, Building> loadedBuildings = new HashMap<Integer, Building>();
    
    public Building(String name, String shortName) {
        
        id = JsonDB.addBuilding(name, shortName).get("id").getAsInt();
        loadedBuildings.put(id, this);
        
    }
    private Building(JsonObject building) {
        
        this.id = building.get("id").getAsInt();
        loadedBuildings.put(id, this);
        
    }
    public static Building getBuilding(JsonObject building) {
        int buildingID = building.get("id").getAsInt();
        if (loadedBuildings.containsKey(buildingID))
            return loadedBuildings.get(buildingID);
        
        return new Building(building);
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
    
    public int getFloorNum() {
        return getThis().get("count").getAsInt();
    }
    
    public LinkedList<Floor> getFloors() {
        
        JsonArray floors = JsonDB.getFloors(this);
        LinkedList<Floor> out = new LinkedList<Floor>();
        
        for (JsonElement floor:floors) 
            out.add(Floor.getFloor(getThis(), floor.getAsJsonObject()));
        
        return out;
        
    }
    
    public Floor getFloor(int id) {
        for (Floor floor: getFloors()) {
            if (floor.getID() == id) return floor;
        }
        return null;
    }
    
    public Floor addFloor(String name, String filePath) {
        return new Floor(this, name, filePath);
    }
    
    //public String[] getFloors() {
    //    return floors;
    //}
    
}