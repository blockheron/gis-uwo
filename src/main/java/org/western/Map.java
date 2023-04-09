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
    
    /**
     * gets a list of all the building in the database
     * @return a LinkedList<Building> representing the buildings in the database
     */
    public static LinkedList<Building> getBuildings() {
        JsonArray buildings = JsonDB.getBuildings();
        LinkedList<Building> out = new LinkedList<Building>();
        
        for (JsonElement building:buildings) 
            out.add(Building.getBuilding(building.getAsJsonObject()));
        
        return out;
        
    }
    
    /**
     * gets a specific building from the database
     * @param name the name of the building to get
     * @return the building if it exists in the database, otherwise null
     */
    public static Building getBuilding(String name) {
        
        for (Building building: getBuildings()) {
            if (building.getName().equals(name)) return building;
            if (building.getShortName().equals(name)) return building;
        }
        return null;
        
    }
    
    /**
     * adds a building to the database
     * @param name the name of the building to add
     * @param shortName the short form of the name of the building
     * @return the newly created building
     */
    public static Building addBuilding(String name, String shortName) {
        return new Building(name, shortName);
    }
    
    /**
     * gets all the users in the database
     * @return a LinkedList<User> where each element represents a user in the database
     */
    public static LinkedList<User> getUsers() {
        JsonArray users = JsonDB.getUsers();
        LinkedList<User> out = new LinkedList<User>();
        
        for (JsonElement user:users) 
            out.add(User.getUser(user.getAsJsonObject()));
        
        return out;
        
    }
    
    /**
     * gets a specific user from the database based on their username
     * @param username the username of the user to get
     * @return the user if that user is in the database, otherwise null
     */
    public static User getUser(String username) {
        
        for (User user: getUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
        
    }
    
    /**
     * get a specific user with a given id from the database
     * @param id the id of the user to get
     * @return the user if it exists, otherwise null
     */
    public static User getUser(int id) {
        
        for (User user: getUsers()) {
            if (user.getID() == id) return user;
        }
        return null;
        
    }
    
    /**
     * adds a user to the database given it's username and password
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @return the newly created user
     */
    public static User addUser(String username, String password) {
        return new User(username, password, false);
    }
    /**
     * adds a user to the database given it's username and password
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @param admin whether or not the user is an admin
     * @return the newly created user
     */
    public static User addUser(String username, String password, boolean admin) {
        return new User(username, password, admin);
    }
    
    /**
     * gets all the layers in the database
     * @return a LinkedList<Layer> where each element is a layer in the database
     */
    public static LinkedList<Layer> getLayers() {
        JsonArray layers = JsonDB.getLayers();
        LinkedList<Layer> out = new LinkedList<Layer>();
        
        for (JsonElement layer:layers) 
            out.add(Layer.getLayer(layer.getAsJsonObject()));
        
        return out;
        
    }
    
    /**
     * gets a specific layer based on the layer's ID
     * @param name the name of the layer to get
     * @return the layer if it exists, otherwise null
     */
    public static Layer getLayer(String name) {
        
        for (Layer layer: getLayers()) {
            if (layer.getName().equals(name)) return layer;
        }
        return null;
        
    }
    /**
     * get a specific layer from the database based on its id
     * @param id the id of the layer to get
     * @return the layer if it exists, otherwise null
     */
    public static Layer getLayer(int id) {
        
        for (Layer layer: getLayers()) {
            if (layer.getID() == id) return layer;
        }
        return null;
        
    }
    
    /**
     * adds a new layer to the database
     * @param name the name of the layer to add
     * @param color the color of the layer to add
     * @return the newly created layer
     */
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
