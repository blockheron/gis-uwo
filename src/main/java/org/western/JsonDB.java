package org.western;

import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

public class JsonDB {
    
    private static Gson gson;
    private static String filePath;
    private static JsonObject db;
    
    public static void main(String[] args) {
        
        //db testing
        new Map(true);
        
        Building mc = new Building("Middlesex College", "MC");
        Building pab = new Building("Physics and Astronomy Building", "PANDA");
        
        for (Building building : Map.getBuildings()) {
            
            System.out.println("Building id: " + building.getID() + ", name: " + building.getName());
            
        }
        
        System.out.println(db.get("count"));
        
        mc.addFloor("ground", "path/to/floor");
        pab.addFloor("grad lounge", "path/to/lounge");
        
        //LinkedList<Floor> floors = mc.getFloors();
        for (Building building : Map.getBuildings()) {
            
            System.out.println("Building id: " + building.getID() + ", name: " + building.getName());
            
            for (Floor floor : building.getFloors()) {
                System.out.println("id: " + floor.getID() + ", name: " + floor.getName());
            }
            
        }
        
        
        /*Building mc1 = Map.getBuilding("Middlesex College");
        Building mc2 = Map.getBuilding("Middlesex College");
        
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc1.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc2.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());*/
        //
        
    }
    
    public JsonDB() {
        this(false);
    }
    public JsonDB(boolean debug) {
        
        if (debug) {
            filePath = getClass().getResource("db/test_db.json").getFile();
        }
        else {
            filePath = getClass().getResource("db/db.json").getFile();
        }
        gson = new GsonBuilder().setPrettyPrinting().create();
        load();
        
    }
    
    public static boolean load() {
        
        BufferedReader reader = null;
        
        try {
        
            try {
                //System.out.println(filePath);
                reader = new BufferedReader(new FileReader(filePath));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            gson.toJson(db, writer);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("FileWriter error");
            return false;
        }
        return true;
        
    }
    
    private static int incrementCount(String countName) {
        
        int newVal = db.get(countName).getAsInt() + 1;
        db.addProperty(countName, newVal);
        return newVal;
        
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
    
    //return building on success, null on failure
    public static JsonObject addBuilding(String name, String shortName) {
        
        JsonArray buildingArray = db.get("buildings").getAsJsonArray();
        JsonObject building = new JsonObject();
        building.addProperty("id", incrementCount("count"));
        building.addProperty("name", name);
        building.addProperty("shortName", shortName);
        building.addProperty("count", 0);
        building.add("floors", new JsonArray());
        
        //todo check if name/shortname is unique
        
        buildingArray.add(building);
        
        save();
        
        return building;
        
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
        
        JsonObject jsonBuilding = getBuilding(building.getID());
        
        JsonObject floor = new JsonObject();
        floor.addProperty("id", incrementCount("floorCount"));
        floor.addProperty("name", name);
        floor.addProperty("filePath", filePath);
        floor.addProperty("count", 0);
        floor.add("layers", new JsonArray());
        
        jsonBuilding.get("floors").getAsJsonArray().add(floor);
        
        int count = jsonBuilding.get("count").getAsInt();
        jsonBuilding.addProperty("count", count+1);
        save();
        
        return floor;
        
    }
    
    public static JsonArray getLayers(Building building, Floor floor) {
        //return getBuilding(building.getID()).get("floors").getAsJsonArray();
        return getFloor(building, floor.getID()).get("layers").getAsJsonArray();
    }
    
    public static JsonObject getLayer (Building building, Floor floor, int id) {
        
        int layerCount = floor.getLayerNum();
        JsonArray layers = getLayers(building, floor);
        
        for (int i = 0; i < layerCount; ++i) {
            
            JsonObject _layer = layers.get(i).getAsJsonObject();
            
            if (_layer.get("id").getAsInt() == id)
                return _layer;
            
        }
        
        return null;
        
    }
    
    public static JsonObject addLayer(Building building, Floor floor, String name, Color color) {
        
        JsonObject jsonFloor = getFloor(building, floor.getID());
        
        JsonObject layer = new JsonObject();
        layer.addProperty("id", incrementCount("layerCount"));
        layer.addProperty("name", name);
        layer.addProperty("color", color.getRGB());
        layer.addProperty("count", 0);
        layer.add("rooms", new JsonArray());
        
        jsonFloor.get("layers").getAsJsonArray().add(layer);
        
        int count = jsonFloor.get("count").getAsInt();
        jsonFloor.addProperty("count", count+1);
        save();
        
        return layer;
        
    }
    
    public static JsonArray getRooms(Building building, Floor floor, Layer layer) {
        //System.out.println(building.getName() + " " + floor.getName() + " " + layer.getName());
        return getLayer(building, floor, layer.getID()).get("rooms").getAsJsonArray();
    }
    
    public static JsonObject getRoom (Building building, Floor floor, Layer layer, int id) {
        //System.out.println(id);
        int roomCount = layer.getRoomNum();
        JsonArray rooms = getRooms(building, floor, layer);
        
        for (int i = 0; i < roomCount; ++i) {
            
            JsonObject _room = rooms.get(i).getAsJsonObject();
            //System.out.println(_room.get("id"));
            
            if (_room.get("id").getAsInt() == id)
                return _room;
            
        }
        
        return null;
        
    }
    
    public static JsonObject addRoom(Building building, Floor floor, Layer layer, Polygon shape, Point position) {
        
        JsonObject jsonLayer = getLayer(building, floor, layer.getID());
        
        JsonObject room = new JsonObject();
        room.addProperty("id", incrementCount("roomCount"));
        room.addProperty("roomNumber", -1);
        room.addProperty("x", position.getX());
        room.addProperty("y", position.getY());
        
        room.addProperty("npoints", shape.npoints);
        JsonArray xpoints = new JsonArray(shape.npoints);
        JsonArray ypoints = new JsonArray(shape.npoints);
        for (int x : shape.xpoints) xpoints.add(x);
        for (int y : shape.ypoints) ypoints.add(y);
        room.add("xpoints", xpoints);
        room.add("ypoints", ypoints);
        
        room.addProperty("count", 0);
        room.add("POIs", new JsonArray());
        
        jsonLayer.get("rooms").getAsJsonArray().add(room);
        
        int count = jsonLayer.get("count").getAsInt();
        jsonLayer.addProperty("count", count+1);
        save();
        
        return room;
        
    }
    
}
