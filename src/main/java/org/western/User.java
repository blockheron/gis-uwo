package org.western;

import com.google.gson.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


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
    
    /**
     * get all the User's POIs
     * @return a LinkedList of the user's rooms
     */
    public LinkedList<POI> getPOIs() {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("userPOIIDs").getAsJsonArray()) {
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
     * get all the user's POIs in a building
     * @param building the building to search in
     * @return a LinkedList of the user's rooms
     */
    public LinkedList<POI> getPOIs(Building building) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("userPOIIDs").getAsJsonArray()) {
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
     * get all the user's POIs on a floor
     * @param floor the building to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<POI> getPOIs(Floor floor) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("userPOIIDs").getAsJsonArray()) {
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
     * get all the rooms the user has POIs in
     * @return a LinkedList of the rooms
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
     * get all the rooms storing a user POI in a building
     * @param building the building to search in
     * @return a LinkedList of the rooms
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
     * get all the rooms storing a user POI in a floor
     * @param floor the floor to search in
     * @return a LinkedList of the rooms
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
     * get user room from id
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
        getThis().get("userPOIIDs").getAsJsonArray().add(poi.getID());
        getThis().addProperty("userPOICount", getThis().get("userPOICount").getAsInt() + 1);
        JsonDB.save();
    }
    
    /**
     * removes the given POI from the list of POIs in this layer
     * @param poi the POI to remove
     */
    public void removePOI(POI poi) {
        
        JsonArray POIList = getThis().get("userPOIIDs").getAsJsonArray();
        for (JsonElement poiID : POIList) {
            if (poiID.getAsInt() == poi.getID()) {
                POIList.remove(poiID);
                getThis().addProperty("userPOICount", getThis().get("userPOICount").getAsInt() - 1);
                JsonDB.save();
                break;
            }
        }
        
    }
    
    public boolean isFavorite(POI poi) {
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("favouriteIDs").getAsJsonArray()) {
            POIIDs.add(i.getAsInt());
        }
        //
        return POIIDs.contains(poi.getID());
    }
    
    /**
     * get all the User's POIs
     * @return a LinkedList of the rooms user's favourite POIs
     */
    public LinkedList<POI> getFavouritePOIs() {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("favouriteIDs").getAsJsonArray()) {
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
     * get all the user's POIs in a building
     * @param building the building to search in
     * @return a LinkedList of the user's rooms
     */
    public LinkedList<POI> getFavouritePOIs(Building building) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("favouriteIDs").getAsJsonArray()) {
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
     * get all the user's POIs on a floor
     * @param floor the building to search in
     * @return a LinkedList of the rooms in the layer
     */
    public LinkedList<POI> getFavouritePOIs(Floor floor) {
        //LinkedList<JsonObject> POIs
        LinkedList<POI> out = new LinkedList<POI>();
        
        //load the poi ids into a hashset for fast contains method
        HashSet<Integer> POIIDs = new HashSet<Integer>();
        for(JsonElement i : getThis().get("favouriteIDs").getAsJsonArray()) {
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
     * get all the rooms the user has POIs in
     * @return a LinkedList of the rooms
     */
    public LinkedList<Room> getFavoriteRooms() {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getFavouritePOIs();
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    
    /**
     * get all the rooms storing a user POI in a building
     * @param building the building to search in
     * @return a LinkedList of the rooms
     */
    public LinkedList<Room> getFavouriteRooms(Building building) {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getFavouritePOIs(building);
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    
    /**
     * get all the rooms storing a user POI in a floor
     * @param floor the floor to search in
     * @return a LinkedList of the rooms
     */
    public LinkedList<Room> getFavouriteRooms(Floor floor) {
        
        LinkedList<Room> out = new LinkedList<Room>();
        
        LinkedList<POI> pois = getFavouritePOIs(floor);
        
        for (POI poi : pois) {
            if (!out.contains(poi.getRoom()))
                out.add(poi.getRoom());
        }
        
        return out;
        
    }
    
    /**
     * adds the given POI to the list of POIs in the layer
     * @param poi the POI to add
     */
    public void addFavouritePOI(POI poi) {
        getThis().get("favouriteIDs").getAsJsonArray().add(poi.getID());
        getThis().addProperty("favouriteCount", getThis().get("favouriteCount").getAsInt() + 1);
        JsonDB.save();
    }
    
    /**
     * removes the given POI from the list of POIs in this layer
     * @param poi the POI to remove
     */
    public void removeFavouritePOI(POI poi) {
        
        JsonArray POIList = getThis().get("favouriteIDs").getAsJsonArray();
        for (JsonElement poiID : POIList) {
            if (poiID.getAsInt() == poi.getID()) {
                POIList.remove(poiID);
                getThis().addProperty("favouriteCount", getThis().get("favouriteCount").getAsInt() - 1);
                JsonDB.save();
                break;
            }
        }
        
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
