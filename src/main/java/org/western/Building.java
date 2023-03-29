package org.western;
import com.google.gson.*;

public class Building {
    //private int id;
    //private String name;
    //private String shortName;
    //private POI[] POIs;
    private JsonObject data;

    public Building(String name, String shortName, int floorNum) {
        
        this.data = JsonDB.addBuilding(name, shortName, floorNum);
        
    }
    
    public Building(JsonObject building) {
        
        this.data = building;
        
    }
    
    public String getName() {
        return data.get("name").getAsString();
    }
    public String getShortName() {
        return data.get("shortname").getAsString();
    }
    
    public void setName(String name) {
        
        data.remove("name");
        data.addProperty("name", name);
        //if (JsonDB.save()) System.out.println("saved");
        
    }
    public void setShortName (String shortName) {
        
        data.remove("shortName");
        data.addProperty("shortName", shortName);
        //JsonDB.save();
        
    }
    
    //public String[] getFloors() {
    //    return floors;
    //}
    
}