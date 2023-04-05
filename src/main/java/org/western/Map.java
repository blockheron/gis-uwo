package org.western;
import com.google.gson.*;

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
    
    public static User addUser(String username, String password) {
        return new User(username, password, false);
    }
    public static User addUser(String username, String password, boolean admin) {
        return new User(username, password, admin);
    }
    
}
