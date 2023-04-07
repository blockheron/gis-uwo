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
    public int getRoomNum() {
        return getThis().get("roomCount").getAsInt();
    }
    
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
    
    public LinkedList<POI> getPOIs() {
        
        LinkedList<POI> out = new LinkedList<POI>();
        
        for(Room room : getRooms()) {
            for (POI poi : room.getPOIs()) {
                out.add(poi);
            }
        }
        return out;
        
    }
    
    public void removePOI(POI POI) {
        JsonArray POIs = getThis().get("POIs").getAsJsonArray();
        for (JsonElement poi: POIs) {
            if (poi.getAsJsonObject().get("id").getAsInt() == POI.getID()) {
                POIs.remove(poi);
                JsonDB.save();
                break;
            }
        }
    }
    
    /**
     * gets a layer in the floor based on the layer's id
     * @param id the id of the layer to get 
     * @return the layer if it exists, otherwise null
     */
    /*public Layer getLayer(int id) {
        for (Layer layer: getLayers()) {
            if (layer.getID() == id) return layer;
        }
        return null;
    }*/
    
    /**
     * gets a layer in the floor based on the layer's id
     * @param name the name of the layer to get 
     * @return the layer if it exists, otherwise null
     */
    /*public Layer getLayer(String name) {
        for (Layer layer: getLayers()) {
            if (layer.getName().equals(name)) return layer;
        }
        return null;
    }*/
    
    /**
     * 
     * @return 
     */
    /*public LinkedList<Room> getRooms() {
        
        JsonArray rooms = JsonDB.getRooms(building, this);
        LinkedList<Room> out = new LinkedList<Room>();
        
        for(JsonElement room : rooms)
            out.add(Room.getRoom(JsonDB.getBuilding(building.getID()),
                    getThis(), room.getAsJsonObject()));
        
        return out;
    }*/
    
    /*public void addRoom(Room room) {
        
    }*/
    
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
        int count = getThis().get("count").getAsInt();
        getThis().addProperty("count", count-1);
        
        room.free();
        JsonDB.save();
    }
    
    /**
     * remove a room from the floor
     * @param room the room to remove
     */
    /*public void removeRoom(Room room) {
        if (getLayers().isEmpty()) return;
        for (Layer layer : getLayers()) {
            layer.remove(room);
        }
    }*/
    
}