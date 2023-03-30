package org.western;

import java.awt.*;
import com.google.gson.*;

import java.util.LinkedList;

public class Layer {
    
    private int id;
    private Building building;
    private Floor floor;

    public Layer(Building building, Floor floor, String name, Color color) {
        this.building = building;
        this.floor = floor;
        this.id = JsonDB.addLayer(building, floor, name, color).get("id").getAsInt();
    }
    public Layer(JsonObject building, JsonObject floor, JsonObject layer) {
        this.building = new Building(building);
        this.floor = new Floor(building, floor);
        this.id = layer.get("id").getAsInt();
    }
    
    private JsonObject getThis() {
        JsonDB.load();
        return JsonDB.getLayer(building, floor, id);
    }
    
    public int getID() {
        return id;
    }
    public Building getBuilding() {
        return building;
    }
    public Floor getFloor() {
        return floor;
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
    
    public LinkedList<Room> getRooms() {
        
        JsonDB.load();
        JsonArray rooms = JsonDB.getRooms(building, floor, this);
        LinkedList<Room> out = new LinkedList<Room>();
        
        for (JsonElement room:rooms) 
            out.add(new Room(JsonDB.getBuilding(building.getID()),
                    JsonDB.getFloor(building, floor.getID()),
                    getThis(),
                    room.getAsJsonObject()
            ));
        
        return out;
        
    }
    
    public Room getRoom(int id) {
        
        for (Room room: getRooms()) {
            if (room.getID() == id) return room;
        }
        return null;
    }
    
    public Room addRoom(Polygon shape, Point position) {
        return new Room(building, floor, this, shape, position);
    }
    
    public void remove(Room room) {
        getThis().get("rooms").getAsJsonArray().remove(
                JsonDB.getRoom(building, floor, this, room.getID())
        );
    }
    
}