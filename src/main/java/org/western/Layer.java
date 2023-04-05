package org.western;

import java.awt.*;
import com.google.gson.*;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;

public class Layer {
    
    private int id;

    private static HashMap<Integer, Layer> loadedLayers = new HashMap<Integer, Layer>();
    
    /**
     * add a new layer to the database
     * @param building the building the layer is in
     * @param floor the floor the layer is on
     * @param name the name of the layer
     * @param color the color of the layer
     */
    public Layer(String name, Color color) {
        this.id = JsonDB.addLayer(name, color).get("id").getAsInt();
        loadedLayers.put(id, this);
    }
    private Layer(JsonObject layer) {
        this.id = layer.get("id").getAsInt();
        loadedLayers.put(id, this);
    }
    public static Layer getLayer(JsonObject layer) {
        int layerID = layer.get("id").getAsInt();
        if (loadedLayers.containsKey(layerID))
            return loadedLayers.get(layerID);
        
        return new Layer(layer);
    }
    
    private JsonObject getThis() {
        return JsonDB.getLayer(id);
    }
    
    public int getID() {
        return id;
    }

    public String getName() {
        return getThis().get("name").getAsString();
    }
    public Color getColor() {
        return new Color(getThis().get("color").getAsInt());
    }
    public int getRoomNum() {
        return getThis().get("count").getAsInt();
    }
    
    /**
     * get all the POIs in the layer
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<POI> getPOIs() {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("POIs").getAsJsonArray()) {
            POIIDs.add(i.getAsInt());
        }
        //
        
        //check if the POI id is in the poi id array
        for (POI poi : Map.getPOIs()) {
            if (POIIDs.contains(poi.getID())) out.add(poi);
        }
        //
        
        return out;
        
    }
    
    /**
     * get all the POIs in the layer in a given building
     * @param building the building to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<POI> getPOIs(Building building) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("POIs").getAsJsonArray()) {
            POIIDs.add(i.getAsInt());
        }
        //
        
        //check if the POI id is in the poi id array
        for (POI poi : building.getPOIs()) {
            if (POIIDs.contains(poi.getID())) out.add(poi);
        }
        //
        
        return out;
        
    }
    
    /**
     * get all the POIs in the layer in a given building
     * @param floor the building to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<POI> getPOIs(Floor floor) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("POIs").getAsJsonArray()) {
            POIIDs.add(i.getAsInt());
        }
        //
        
        //check if the POI id is in the poi id array
        for (POI poi : floor.getPOIs()) {
            if (POIIDs.contains(poi.getID())) out.add(poi);
        }
        //
        
        return out;
        
    }
    
    /**
     * get all the rooms in the layer
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<Room> getRooms() {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getPOIs();
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    
    /**
     * get all the rooms in the layer
     * @param building the building to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<Room> getRooms(Building building) {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getPOIs(building);
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    /**
     * get all the rooms in the layer
     * @param floor the floor to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<Room> getRooms(Floor floor) {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getPOIs(floor);
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    
    /**
     * get room from id
     * @param id the id of the room
     * @return the room if it exists, otherwise null
     */
    /*public Room getRoom(int id) {
        
        for (Room room: getRooms()) {
            if (room.getID() == id) return room;
        }
        return null;
    }*/
    
    /**
     * adds the given POI to the list of POIs in the layer
     * @param poi the POI to add
     */
    public void addPOI(POI poi) {
        getThis().get("POIs").getAsJsonArray().add(poi.getID());
        getThis().addProperty("count", getThis().get("count").getAsInt() + 1);
        JsonDB.save();
    }
    
    /**
     * removes the given POI from the list of POIs in this layer
     * @param poi the POI to remove
     */
    public void removePOI(POI poi) {
        
        JsonArray POIList = getThis().get("POIs").getAsJsonArray();
        for (JsonElement poiID : POIList) {
            if (poiID.getAsInt() == poi.getID()) {
                POIList.remove(poiID);
                getThis().addProperty("count", getThis().get("count").getAsInt() - 1);
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
    //public Room addRoom(Polygon shape, Point position) {
    //    return new Room(building, floor, this, shape, position);
    //}
    
    /**
     * remove a room from the database
     * @param room the room to remove
     */
    /*public void remove(Room room) {
        getThis().get("rooms").getAsJsonArray().remove(
                JsonDB.getRoom(building, floor, this, room.getID())
        );
        int count = getThis().get("count").getAsInt();
        getThis().addProperty("count", count-1);
        
        room.free();
        JsonDB.save();
    }*/
    
}