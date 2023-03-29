package org.western;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class JsonDB {
    
    static Gson gson;
    static String filePath;
    
    public static void main(String[] args) {
        
        JsonDB db;
        
        //demo code
        try {
            
            db = new JsonDB();

            //curBuilding = db.getBuilding("Middlesex College");
            //curFloor = curBuilding.getFloors();
        }
        catch (FileNotFoundException e) {
            System.out.println("db not found");
        }
        //
        
        //db check
        Building mc1 = JsonDB.getBuilding("Middlesex College");
        Building mc2 = JsonDB.getBuilding("Middlesex College");
        
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc1.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc2.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        //
        
    }
    
    private static JsonObject db;
    
    public JsonDB() throws FileNotFoundException {
        
        filePath = getClass().getResource("db/db.json").getFile();
        System.out.println(filePath);
        db = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        gson = new Gson();
        
    }
    
    /*public static boolean save() {
        try {
            gson.toJson(db, new FileWriter(filePath));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }*/
    
    //return building on success, null on failure
    public static JsonObject addBuilding(String name, String shortName, int floorNum) {
        
        JsonArray buildingArray = db.get("Buildings").getAsJsonArray();
        JsonObject building = new JsonObject();
        building.addProperty("name", name);
        building.addProperty("shortName", shortName);
        building.addProperty("count", floorNum);
        
        //todo check if name/shortname is unique
        
        int count = db.get("count").getAsInt();
        db.remove("count");
        db.addProperty("count", count+1);
        return building;
        
    }
    
    public static Building getBuilding(String name) {
        
        int buildingCount = db.get("count").getAsInt();
        JsonArray buildings = db.get("buildings").getAsJsonArray();
        
        for (int i = 0; i < buildingCount; ++i) {
            JsonObject _building = buildings.get(i).getAsJsonObject();
            if (_building.get("name").getAsString().equals(name))
                return new Building(_building);
        }
        
        return null;
        
    }
    
    /*public boolean addPOI(POI POI) {
        
        
        
    }
    
    public boolean addRoom(Room room) {
        
        
        
        if (building == null) return false;
        
        JsonArray floors = building.get("floors").getAsJsonArray();
        
        
    }
    
    public Building getBuilding(String name) {
        
        int buildingCount = db.get("count").getAsInt();
        JsonArray buildings = db.get("buildings").getAsJsonArray();
        
        JsonObject building = null;
        
        for (int i = 0; i < buildingCount; ++i) {
            JsonObject _building = buildings.get(i).getAsJsonObject();
            if (_building.get("name").equals(name))
                return createBuilding(_building);
        }
        
        return null;
        
    }
    
    private Building createBuilding(JsonObject building) {
        return new Building(building.get("id").getAsInt(), building.get("name").getAsString(),
                building.get("shortName").getAsString(), getFloors(building));
    }
    
    private Floor[] getFloors(JsonObject Building) {
        
        int floorCount = Building.get("count").getAsInt();
        Floor[] out = new Floor[floorCount];
        JsonArray floors = Building.get("floors").getAsJsonArray();
        
        for (int i = 0; i < floorCount; ++i) 
            out[i] = createFloor(floors.get(i).getAsJsonObject());
        
        return out;
        
    }
    
    private Floor createFloor(JsonObject floor) {
        
        return new Floor(floor.get("name"), getLayers(floor));
        
    }
    
    private getLayers(JsonObject floor) {
        
        int layerCount = floor.get("count").getAsInt();
        Layer[] out = new Layer[layerCount];
        JsonArray Layers = floor.get("layers").getAsJsonArray();
        
        for (int i = 0; i < layerCount; ++i) {
            out[i] = createLayer(floor.get(""))
        }
        
    }
    
    private Layer createLayer() {
        
    }*/
    
}
