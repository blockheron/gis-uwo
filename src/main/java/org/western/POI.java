package org.western;

import com.google.gson.JsonObject;
import java.awt.*;
import java.util.HashMap;

public class POI {
    private int id;
    private Building building;
    private Floor floor;
    private Layer layer;
    private Room room;
    //private String name;
    //private String description;
    //private String building;
    //private String floor;
    //private int[] user;

    private static HashMap<Integer, POI> loadedPOIs = new HashMap<Integer, POI>();
    
    public POI(Building building, Floor floor, Layer layer, Room room, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = layer;
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, layer, room, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        
    }
    public POI(Building building, Floor floor, Room room, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = Map.getLayer("unassigned");
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, this.layer, room, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        
    }
    
    private POI(JsonObject building, JsonObject floor, JsonObject layer, JsonObject room, JsonObject POI) {
        this.building = Building.getBuilding(building);
        this.floor = Floor.getFloor(building, floor);
        this.layer = Layer.getLayer(layer);
        this.room = Room.getRoom(building, floor, room);
        this.id = room.get("id").getAsInt();
        loadedPOIs.put(id, this);
    }
    
    public static POI getPOI(JsonObject building, JsonObject floor, JsonObject room, JsonObject POI) {
        int POIID = POI.get("id").getAsInt();
        if (loadedPOIs.containsKey(POIID))
            return loadedPOIs.get(POIID);
        
        return new POI(building, floor, JsonDB.getLayer("unassigned"), room, POI);
    }
    public static POI getPOI(JsonObject building, JsonObject floor, JsonObject layer, JsonObject room, JsonObject POI) {
        int POIID = POI.get("id").getAsInt();
        if (loadedPOIs.containsKey(POIID))
            return loadedPOIs.get(POIID);
        
        return new POI(building, floor, layer, room, POI);
    }

    private JsonObject getThis() {
        return JsonDB.getPOI(building, floor, room, id);
    }
    
    public int getID() {
        return id;
    }

    public String getName() {
        return getThis().get("name").getAsString();
    }

    public String getDescription() {
        return getThis().get("description").getAsString();
    }

    public Building getBuilding() {
        return building;
    }

    public Floor getFloor() {
        return floor;
    }

    public Layer getLayer() {
        return layer;
    }

    public Room getRoom() {
        return room;
    }
    
    /**
     * removes the POI from its current layer and moves it to the given layer
     * @param layer the layer to move the POI to
     */
    public void switchLayer(Layer layer) {
        this.layer.removePOI(this);
        layer.addPOI(this);
    }

    /*public float[][] getCoordinates() {
        return coordinates;
    }

    public int[] getUser() {
        return user;
    }*/
    
}
