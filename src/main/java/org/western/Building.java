package org.western;
import com.google.gson.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Building {
    private int id;

    private static HashMap<Integer, Building> loadedBuildings = new HashMap<Integer, Building>();
    
    /**
     * create a new building
     * @param name the name of the building
     * @param shortName the short name of the building
     */
    public Building(String name, String shortName) {
        
        id = JsonDB.addBuilding(name, shortName).get("id").getAsInt();
        loadedBuildings.put(id, this);
        
    }
    private Building(JsonObject building) {
        
        this.id = building.get("id").getAsInt();
        loadedBuildings.put(id, this);
        
    }
    
    /**
     * get a building from its JsonObject representation
     * @param building the JsonObject representation of the building
     * @return the building object
     */
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
    
    /**
     * gets the current number of floors the building has
     * @return the current number of floors the building has
     */
    public int getFloorNum() {
        return getThis().get("count").getAsInt();
    }
    
    /**
     * gets the floors in the building from lowest floor to highest floor
     * @return a sorted linked list of floors from lowest to highest
     */
    public LinkedList<Floor> getFloors() {
        
        JsonArray floors = JsonDB.getFloors(this);
        LinkedList<Floor> out = new LinkedList<Floor>();
        
        for (JsonElement floor:floors) 
            out.add(Floor.getFloor(getThis(), floor.getAsJsonObject()));
        
        return out;
        
    }
    
    public LinkedList<Room> getRooms() {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        for (Floor floor : getFloors()) {
            for (Room room : floor.getRooms()) {
                out.add(room);
            }
        }
        return out;
        
    }
    
    public LinkedList<POI> getPOIs() {
    
        LinkedList<POI> out = new LinkedList<POI>();
        
        if (getFloors().isEmpty()) return out;
        for (Floor floor : getFloors()) {
            if (floor.getRooms().isEmpty()) continue;
            for (Room room : floor.getRooms()) {
                if (room.getPOIs().isEmpty()) continue;
                for (POI poi : room.getPOIs()) {
                    out.add(poi);
                }
            }
        }
        
        return out;
        
    }
    
    /**
     * gets a floor in the building
     * @param id the id of the building
     * @return the floor if it exists otherwise null
     */
    public Floor getFloor(int id) {
        if (getFloors().isEmpty()) return null;
        for (Floor floor: getFloors()) {
            if (floor.getID() == id) return floor;
        }
        return null;
    }
    
    /**
     * gets a floor in the building
     * @param name the name of the building
     * @return the floor if it exists otherwise null
     */
    public Floor getFloor(String name) {
        if (getFloors().isEmpty()) return null;
        for (Floor floor: getFloors()) {
            if (floor.getName().equals(name)) return floor;
        }
        return null;
    }
    
    /**
     * adds a floor to this building
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @return the floor if added successfully, null on fail
     */
    public Floor addFloor(String name, String filePath) {
        return new Floor(this, name, filePath);
    }
    /**
     * inserts a floor into this building after the floor with id prevFloorID
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @param prevFloorID the id of the floor to insert after
     * @return the added floor on success, null on fail
     */
    public Floor addFloor(String name, String filePath, int prevFloorID) {
        return new Floor(this, name, filePath, prevFloorID);
    }
    
}