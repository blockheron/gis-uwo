package org.western;

import java.util.Dictionary;
import java.util.LinkedList;

import java.awt.*;
import com.google.gson.*;
import java.util.HashMap;

public class Floor {
    
    private int id;
    private Building building;
    private static HashMap<Integer, Floor> loadedFloors = new HashMap<Integer, Floor>();  
    
    /**
     * creates a new floor as the top floor of the passed building
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image file of the floor
     */
    public Floor(Building building, String name, String filePath) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath).get("id").getAsInt();
        //addLayer("Unassigned", new Color(200, 0, 0, 50));
        loadedFloors.put(id, this);
    }
    
    /**
     * inserts a floor above the floor given by prevFloorID
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image file of the floor
     * @param prevFloorID the ID of the floor to add the new floor above (use -1 to add a new bottom floor)
     */
    public Floor(Building building, String name, String filePath, int prevFloorID) {
        this.building = building;
        this.id = JsonDB.addFloor(building, name, filePath, prevFloorID).get("id").getAsInt();
        //addLayer("Unassigned", new Color(200, 0, 0, 50));
        loadedFloors.put(id, this); 
    }
    
    private Floor(JsonObject building, JsonObject floor) {
        this.building = Building.getBuilding(building);
        this.id = floor.get("id").getAsInt();
        loadedFloors.put(id, this);
    }
    
    public static Floor getFloor(JsonObject building, JsonObject floor) {
        int floorID = floor.get("id").getAsInt();
        if (loadedFloors.containsKey(floorID))
            return loadedFloors.get(floorID);
        
        return new Floor(building, floor);
    }
    
    private JsonObject getThis() {
        
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
    /**
     * gets the number of rooms on the floor
     * @return the number of rooms on the floor
     */
    public int getRoomNum() {
        return getThis().get("roomCount").getAsInt();
    }
    
    /**
     * gets all the rooms on a floor
     * @return a LinkedList<Floor> where each element represents a floor
     */
    public LinkedList<Room> getRooms() {
        
        JsonArray rooms = JsonDB.getRooms(building, this);
        LinkedList<Room> out = new LinkedList<Room>();
        
        for (JsonElement room:rooms) 
            out.add(Room.getRoom(JsonDB.getBuilding(building.getID()),
                    getThis(),
                    room.getAsJsonObject()
            ));
        
        return out;
        
    }
    
    /**
     * gets all the POIs on a floor
     * @return a LinkedList<POI> of all the POIs on the floor
     */
    public LinkedList<POI> getPOIs() {
        
        LinkedList<POI> out = new LinkedList<POI>();
        
        for(Room room : getRooms()) {
            for (POI poi : room.getPOIs()) {
                out.add(poi);
            }
        }
        return out;
        
    }
    
    /**
     * gets all the POIs on the floor belonging to a given user
     * @param user the user who's POIs to get
     * @return a LinkedList<POI> where each element represents a POI
     */
    public LinkedList<POI> getPOIs(User user) {
        
        LinkedList<POI> out = new LinkedList<POI>();
        
        for(Room room : getRooms()) {
            for (POI poi : room.getPOIs()) {
                if (poi.getUser() == null || poi.getUser().getID() == user.getID())
                    out.add(poi);
            }
        }
        return out;
        
    }
    
    /**
     * removes a POI from the floor
     * @param POI the POI to remove
     */
    public void removePOI(POI POI) {
        JsonArray POIs = getThis().get("POIs").getAsJsonArray();
        for (JsonElement poi: POIs) {
            if (poi.getAsJsonObject().get("id").getAsInt() == POI.getID()) {
                POIs.remove(poi);
                getThis().addProperty("POICount", getThis().get("POICount").getAsInt()-1);
                JsonDB.save();
                break;
            }
        }
    }
    
    
    /**
     * add a room to the database
     * @param shape the shape of the room(the polygon defining its outline)
     * @param position the position of the room
     * @return the room if successful, otherwise null
     */
    public Room addRoom(Polygon shape, Point position) {
        return new Room(building,this, shape, position);
    }
    
    /**
     * remove a room from the database
     * @param room the room to remove
     */
    public void remove(Room room) {
        getThis().get("rooms").getAsJsonArray().remove(
                JsonDB.getRoom(building, this, room.getID())
        );
        int count = getThis().get("roomCount").getAsInt();
        getThis().addProperty("roomCount", count-1);
        
        room.free();
        JsonDB.save();
    }
    
}