package org.western;
import com.google.gson.*;
import java.awt.*;

import java.util.LinkedList;

public class Map {
    
    //private Building[] buildings;
    //private String[] layers;

    public Map() {
        this(false);
    }
    public Map(boolean debug) { 
        new JsonDB(debug);
    }
    
    public static LinkedList<Building> getBuildings() {
        JsonArray buildings = JsonDB.getBuildings();
        LinkedList<Building> out = new LinkedList<Building>();
        
        for (JsonElement building:buildings) 
            out.add(Building.getBuilding(building.getAsJsonObject()));
        
        return out;
        
    }
    
    public static Building getBuilding(String name) {
        
        for (Building building: getBuildings()) {
            if (building.getName().equals(name)) return building;
            if (building.getShortName().equals(name)) return building;
        }
        return null;
        
    }
    
    public static Building addBuilding(String name, String shortName) {
        return new Building(name, shortName);
    }
    
    public static LinkedList<User> getUsers() {
        JsonArray users = JsonDB.getUsers();
        LinkedList<User> out = new LinkedList<User>();
        
        for (JsonElement user:users) 
            out.add(User.getUser(user.getAsJsonObject()));
        
        return out;
        
    }
    
    public static User getUser(String username) {
        
        for (User user: getUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
        
    }
    
    public static User getUser(int id) {
        
        for (User user: getUsers()) {
            if (user.getID() == id) return user;
        }
        return null;
        
    }
    
    public static User addUser(String username, String password) {
        return new User(username, password, false);
    }
    public static User addUser(String username, String password, boolean admin) {
        return new User(username, password, admin);
    }
    
    public static LinkedList<Layer> getLayers() {
        JsonArray layers = JsonDB.getLayers();
        LinkedList<Layer> out = new LinkedList<Layer>();
        
        for (JsonElement layer:layers) 
            out.add(Layer.getLayer(layer.getAsJsonObject()));
        
        return out;
        
    }
    
    public static Layer getLayer(String name) {
        
        for (Layer layer: getLayers()) {
            if (layer.getName().equals(name)) return layer;
        }
        return null;
        
    }
    public static Layer getLayer(int id) {
        
        for (Layer layer: getLayers()) {
            if (layer.getID() == id) return layer;
        }
        return null;
        
    }
    
    public static Layer addLayer(String name, Color color) {
        return new Layer(name, color);
    }
    
    /**
     * gets every poi in the database
     * @return a linked list of every POI in the database
     */
    public static LinkedList<POI> getPOIs() {
        LinkedList<POI> out = new LinkedList<POI>();
        for(Building building : getBuildings()) {
            for (POI poi : building.getPOIs()) {
                out.add(poi);
            }
        }
        return out;
    }
    
    /**
     * gets every room in the database
     * @return a linked list of every room in the database
     */
    public static LinkedList<Room> getRooms() {
        LinkedList<Room> out = new LinkedList<Room>();
        for(Building building : getBuildings()) {
            for (Room room : building.getRooms()) {
                out.add(room);
            }
        }
        return out;
    }
    
}
