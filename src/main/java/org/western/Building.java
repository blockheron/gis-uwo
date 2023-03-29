package org.western;
import com.google.gson.*;

public class Building {
    private int id;
    //private String name;
    //private String shortName;
    //private POI[] POIs;
    //private JsonObject data;

    public Building(String name, String shortName, int floorNum) {
        
        id = JsonDB.addBuilding(name, shortName, floorNum).get("id").getAsInt();
        
    }
    
    public Building(JsonObject building) {
        
        this.id = building.get("id").getAsInt();
        
    }
    
    public String getName() {
        
        JsonDB.load();
        String name = JsonDB.getBuilding(id).get("name").getAsString();
        JsonDB.save();
        return name;
        
    }
    public String getShortName() {
        
        JsonDB.load();
        String shortName = JsonDB.getBuilding(id).get("shortName").getAsString();
        JsonDB.save();
        return shortName;
        
    }
    
    public void setName(String name) {
        
        JsonDB.load();
        JsonObject building = JsonDB.getBuilding(id);
        building.addProperty("name", name);
        JsonDB.save();
        
    }
    public void setShortName (String shortName) {
        
        JsonDB.load();
        JsonObject building = JsonDB.getBuilding(id);
        building.addProperty("shortName", shortName);
        JsonDB.save();
        
    }
    
    //public String[] getFloors() {
    //    return floors;
    //}
    
}