package org.western;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonDB {
    
    private static Gson gson;
    private static String filePath;
    private static JsonObject db;
    
    public static void main(String[] args) {
        
        //db check
        new Map();
        
        Building mc1 = Map.getBuilding("Middlesex College");
        Building mc2 = Map.getBuilding("Middlesex College");
        
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
    
    public JsonDB() {
        
        filePath = getClass().getResource("db/db.json").getFile();
        gson = new GsonBuilder().setPrettyPrinting().create();
        
    }
    
    public static boolean load() {
        
        FileReader reader = null;
        
        try {
        
            try {
                //System.out.println(filePath);
                reader = new FileReader(filePath);
                db = JsonParser.parseReader(reader).getAsJsonObject();
            }
            catch (FileNotFoundException e) {
                System.out.println("database not found");
                return false;
            }
            finally {
                if (reader != null) reader.close();
            }
        
        }
        catch (IOException e)
        {
            
            System.out.println("FileReader error");
            return false;
            
        }
        
        return true;
        
    }
    
    public static boolean save() {
        
        try {
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(db, writer);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("FileWriter error");
            return false;
        }
        return true;
        
    }
    
    //return building on success, null on failure
    public static JsonObject addBuilding(String name, String shortName) {
        
        load();
        JsonArray buildingArray = db.get("Buildings").getAsJsonArray();
        JsonObject building = new JsonObject();
        building.addProperty("name", name);
        building.addProperty("shortName", shortName);
        building.addProperty("count", 0);
        building.add("floors", new JsonArray());
        
        //add id
        //todo check if name/shortname is unique
        
        int count = db.get("count").getAsInt();
        db.addProperty("count", count+1);
        
        save();
        
        return building;
        
    }
    
    public static JsonArray getBuildings() {
        return db.get("buildings").getAsJsonArray();       
    }
    
    public static JsonObject getBuilding(int id) {
        
        int buildingCount = db.get("count").getAsInt();
        JsonArray buildings = db.get("buildings").getAsJsonArray();
        
        for (int i = 0; i < buildingCount; ++i) {
            
            JsonObject _building = buildings.get(i).getAsJsonObject();
            
            if (_building.get("id").getAsInt() == id)
                return _building;
            
        }
        
        return null;
        
    }
    
    public static JsonArray getFloors(Building building) {
        return getBuilding(building.getID()).get("floors").getAsJsonArray();
    }
    
    public static JsonObject getFloor (Building building, int id) {
        
        int floorCount = building.getFloorNum();
        JsonArray floors = getFloors(building);
        
        for (int i = 0; i < floorCount; ++i) {
            
            JsonObject _floor = floors.get(i).getAsJsonObject();
            
            if (_floor.get("id").getAsInt() == id)
                return _floor;
            
        }
        
        return null;
        
    }
    
    public static JsonObject addFloor(Building building, String name, String filePath) {
        
        load();
        JsonObject jsonBuilding = getBuilding(building.getID());
        
        JsonObject floor = new JsonObject();
        floor.addProperty("name", name);
        floor.addProperty("filePath", filePath);
        floor.addProperty("count", 0);
        floor.add("layers", new JsonArray());
        
        //assign unique id
        
        int count = jsonBuilding.get("count").getAsInt();
        db.addProperty("count", count+1);
        save();
        
        return floor;
        
    }
    
    //public static JsonObject getFloor()
    
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
