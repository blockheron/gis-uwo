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
    private User user;
    //private String name;
    //private String description;
    //private String building;
    //private String floor;
    //private int[] user;

    private static HashMap<Integer, POI> loadedPOIs = new HashMap<Integer, POI>();
    
    public POI(Building building, Floor floor, Layer layer, Room room, User user, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = layer;
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, layer, room, user, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        this.user = user;
        if (user != null) user.addPOI(this);
        
    }
    public POI(Building building, Floor floor, Layer layer, Room room, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = layer;
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, layer, room, null, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        
    }
    public POI(Building building, Floor floor, Room room, User user, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = Map.getLayer("unassigned");
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, this.layer, room, user, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        this.user = user;
        user.addPOI(this);
        
    }
    public POI(Building building, Floor floor, Room room, String name, String description, Point location) {
        this.building = building;
        this.floor = floor;
        this.layer = Map.getLayer("unassigned");
        this.room = room;
        this.id = JsonDB.addPOI(building, floor, this.layer, room, null, name, description, location).get("id").getAsInt();
        loadedPOIs.put(id, this);
        layer.addPOI(this);
        
    }
    
    private POI(JsonObject building, JsonObject floor, JsonObject layer, JsonObject room, JsonObject POI) {
        this.building = Building.getBuilding(building);
        this.floor = Floor.getFloor(building, floor);
        this.layer = Layer.getLayer(layer);
        this.room = Room.getRoom(building, floor, room);
        this.id = POI.get("id").getAsInt();
        loadedPOIs.put(id, this);
    }
    
    /*public static POI getPOI(JsonObject building, JsonObject floor, JsonObject room, JsonObject POI) {
        int POIID = POI.get("id").getAsInt();
        if (loadedPOIs.containsKey(POIID))
            return loadedPOIs.get(POIID);
        
        return new POI(building, floor, JsonDB.getLayer("unassigned"), room, POI);
    }*/
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
        getThis().addProperty("layerID", layer.getID());
        JsonDB.save();
        this.layer = layer;
    }
    
    public boolean isFavourite(User user) {
        if (user == null) return false;
        return user.isFavorite(this);
    }
    
    public boolean isUser(User user) {
        return user.getID() == getThis().get("user").getAsInt();
    }
    
    public boolean isUser() {
        return getThis().get("user").getAsInt() != -1;
    }
    
    public User getUser() {
        return Map.getUser(getThis().get("user").getAsInt());
    }
    
    public void setName(String name) {
        getThis().addProperty("name", name);
        JsonDB.save();
    }
    public void setDescription(String desc) {
        getThis().addProperty("description", desc);
        JsonDB.save();
    }
    
    public void delete() {
        //remove references in users/layers
        for(User user : Map.getUsers()) {
            user.removeFavouritePOI(this);
        }
        if (isUser()) {
            getUser().removePOI(this);
        }
        if (layer != null) {
            layer.removePOI(this);
        }
        //
        //remove actual database entry
        if (room != null) 
            room.removePOI(this);
        else 
            floor.removePOI(this);
        //
    }
    
    public void toggleFavourite(User user) {
        if (user == null) return;
        if (user.isFavorite(this))
            user.removeFavouritePOI(this);
        else user.addFavouritePOI(this);
    }

    /*public float[][] getCoordinates() {
        return coordinates;
    }

    public int[] getUser() {
        return user;
    }*/
    
}
