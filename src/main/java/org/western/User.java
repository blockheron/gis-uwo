package org.western;

import com.google.gson.*;
import java.util.HashMap;


public class User {
    
    private int id;
    //private String username;
    //private String password;
    //private boolean admin;

    private static HashMap<Integer, User> loadedUsers = new HashMap<Integer, User>();
    
    public User(String username, String password) {
        
        id = JsonDB.addUser(username, password, false).get("id").getAsInt();
        loadedUsers.put(id, this);
        
    }
    public User(String username, String password, boolean admin) {
        
        id = JsonDB.addUser(username, password, admin).get("id").getAsInt();
        loadedUsers.put(id, this);
        
    }
    
    private User(JsonObject user) {
        
        this.id = user.get("id").getAsInt();
        loadedUsers.put(id, this);
        
    }
    public static User getUser(JsonObject user) {
        int buildingID = user.get("id").getAsInt();
        if (loadedUsers.containsKey(buildingID))
            return loadedUsers.get(buildingID);
        
        return new User(user);
    }
    
    private JsonObject getThis() {
        return JsonDB.getUser(id);
    }

    public String getUsername() {
        return getThis().get("username").getAsString();
    }    
    public String getPassword() {
        return getThis().get("password").getAsString();
    }
    public boolean isAdmin() {
        return getThis().get("admin").getAsBoolean();
    }
    
    /*public int login(String username, String password) {
        if (Objects.equals(this.username, username) && Objects.equals(this.password, password)) {
            return 0;
        } else {
            return -1;
        }
    }
    public int signup(String username, String password) {
        if (Objects.equals(this.username, username)) {
            return -1;
        } else {
            this.username = username;
            this.password = password;
            return 0;
        }
    }*/

    public int getID() {
        return id;
    }
}
