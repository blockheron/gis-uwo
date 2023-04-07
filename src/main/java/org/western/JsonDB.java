package org.western;

import com.google.common.io.BaseEncoding;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;

public class JsonDB {
    
    private static Gson gson;
    private static String filePath;
    private static JsonObject db;
    
    public static void main(String[] args) {
        
        //db testing
        new Map(false);
        
        Building mc = new Building("Middlesex College", "MC");
        Building pab = new Building("Physics and Astronomy Building", "PANDA");
        
        User sampleUser = new User("testUser", "1234");
        
        Layer test = Map.addLayer("newLayer", Color.yellow);
        
        Floor ground = mc.addFloor("ground", "path/to/floor");
        Floor basement = mc.addFloor("basement", "different-file-path", -1); //inserts at beginning of floors
        Floor lg = mc.addFloor("lower ground", "", mc.getFloor("basement").getID());
        Floor lounge = pab.addFloor("grad lounge", "path/to/lounge");
        
        Room gr = ground.addRoom(new Polygon(), new Point(0,0));
        gr.addPOI("gr1", "1st ground room", new Point(0,0));
        gr.addPOI("gr2", "desc2", new Point(0,0));
        gr.addPOI("gr3", "desc3", new Point(0,0)).switchLayer(test);
        Room loungeRoom = lounge.addRoom(new Polygon(), new Point(0,0));
        POI loungePOI = loungeRoom.addPOI(sampleUser, "lng1", "desc4", new Point(0,0));
        loungePOI.switchLayer(test);
        
        
        //LinkedList<Floor> floors = mc.getFloors();
        for (Building building : Map.getBuildings()) {
            
            System.out.println("Building id: " + building.getID() + ", name: " + building.getName());
            
            for (Floor floor : building.getFloors()) {
                System.out.println("id: " + floor.getID() + ", name: " + floor.getName());
            }
            
        }
        
        System.out.println(Map.getLayer("unassigned").getPOIs());
        for(POI poi : Map.getLayer("unassigned").getPOIs()) {
            System.out.println(poi.getName());
        }
        System.out.println(Map.getLayer("newLayer").getPOIs());
        for(POI poi : Map.getLayer("newLayer").getPOIs()) {
            System.out.println(poi.getName());
        }
        
        System.out.println(sampleUser.getPOIs());
        System.out.println(sampleUser.getFavouritePOIs());
        loungePOI.toggleFavourite(sampleUser);
        System.out.println(sampleUser.getPOIs());
        System.out.println(sampleUser.getFavouritePOIs());
        loungePOI.toggleFavourite(sampleUser);
        System.out.println(sampleUser.getPOIs());
        System.out.println(sampleUser.getFavouritePOIs());
        
        
        //mc.getFloor("ground").
        
        
        /*Building mc1 = Map.getBuilding("Middlesex College");
        Building mc2 = Map.getBuilding("Middlesex College");
        
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc1.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());
        
        mc2.setName("test");
        System.out.println(mc1.getName());
        System.out.println(mc2.getName());*/
        //
        
    }
    
    public JsonDB() {
        this(false);
    }
    
    /**
     * initializes the database
     * @param debug creates a blank db if true otherwise loads the actual database
     */
    public JsonDB(boolean debug) {
        
        gson = new GsonBuilder().setPrettyPrinting().create();
        if (debug) { //creates an empty database
            JsonObject cleanDB = new JsonObject();
            cleanDB.addProperty("userCount", 0);
            cleanDB.addProperty("POICount", 0);
            cleanDB.addProperty("roomCount", 0);
            cleanDB.addProperty("layerCount", 0);
            cleanDB.addProperty("curLayerCount", 0);
            cleanDB.addProperty("floorCount", 0);
            cleanDB.addProperty("buildingCount", 0);
            cleanDB.addProperty("curBuildingCount", 0);
            cleanDB.add("layers", new JsonArray());
            cleanDB.add("buildings", new JsonArray());
            cleanDB.add("users", new JsonArray());
            
            filePath = getClass().getResource("db/test_db.json").getFile();
            db = cleanDB;
            save();
            addLayer("unassigned", Color.RED);
        }
        else {
            filePath = getClass().getResource("db/db.json").getFile();
        }
        
        load();
        
    }
    
    /**
     * loads the database
     * @return true if successful false if failed
     */
    public static boolean load() {
        
        BufferedReader reader = null;
        
        try {
        
            try {
                //System.out.println(filePath);
                reader = new BufferedReader(new FileReader(filePath));
                db = JsonParser.parseReader(reader).getAsJsonObject();
            }
            catch (FileNotFoundException e) {
                System.out.println("database not found");
                return false;
            }
            finally {
                if (reader != null) reader.close();
            }
        
        }
        catch (IOException e)
        {
            
            System.out.println("FileReader error");
            return false;
            
        }
        
        return true;
        
    }
    
    /**
     * saves the database
     * @return true if successful false if failed
     */
    public static boolean save() {
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            gson.toJson(db, writer);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("FileWriter error");
            return false;
        }
        return true;
        
    }
    
    private static int incrementCount(String countName) {
        
        int newVal = db.get(countName).getAsInt() + 1;
        db.addProperty(countName, newVal);
        return newVal;
        
    }
    
    /**
     * gets every building in the database
     * @return an editable JsonArray of the buildings
     */
    public static JsonArray getBuildings() {
        return db.get("buildings").getAsJsonArray();       
    }
    
    /**
     * gets a specific building
     * @param id the id of the building to get
     * @return an editable JsonObject representing the building if it exists, otherwise null
     */
    public static JsonObject getBuilding(int id) {
        
        int buildingCount = db.get("curBuildingCount").getAsInt();
        JsonArray buildings = db.get("buildings").getAsJsonArray();
        
        for (int i = 0; i < buildingCount; ++i) {
            
            JsonObject _building = buildings.get(i).getAsJsonObject();
            
            if (_building.get("id").getAsInt() == id)
                return _building;
            
        }
        
        return null;
        
    }
    
    /**
     * adds a new building with a given name and short name
     * @param name the name of the building
     * @param shortName the short name of the building
     * @return an editable JsonObject representing the building on success, null on failure
     */
    public static JsonObject addBuilding(String name, String shortName, Polygon shape, Point position) {
        
        JsonArray buildingArray = db.get("buildings").getAsJsonArray();
        JsonObject building = new JsonObject();
        building.addProperty("id", incrementCount("buildingCount"));
        incrementCount("curBuildingCount");
        building.addProperty("name", name);
        building.addProperty("shortName", shortName);
        building.addProperty("count", 0);
        building.add("floors", new JsonArray());
        
        building.addProperty("x", position.getX());
        building.addProperty("y", position.getY());
        
        building.addProperty("npoints", shape.npoints);
        JsonArray xpoints = new JsonArray(shape.npoints);
        JsonArray ypoints = new JsonArray(shape.npoints);
        for (int x : shape.xpoints) xpoints.add(x);
        for (int y : shape.ypoints) ypoints.add(y);
        building.add("xpoints", xpoints);
        building.add("ypoints", ypoints);
        
        //todo check if name/shortname is unique
        
        buildingArray.add(building);
        
        save();
        
        return building;
        
    }
    
    /**
     * gets all the users in the database
     * @return an editable JsonArray of all the users
     */
    public static JsonArray getUsers() {
        return db.get("users").getAsJsonArray();       
    }
    
    /**
     * gets a specific user with the given id
     * @param id the id of the user to get
     * @return the JsonObject representing the user if it exists, otherwise null
     */
    public static JsonObject getUser(int id) {
        
        int userCount = db.get("userCount").getAsInt();
        JsonArray users = db.get("users").getAsJsonArray();
        
        for (int i = 0; i < userCount; ++i) {
            
            JsonObject _user = users.get(i).getAsJsonObject();
            
            if (_user.get("id").getAsInt() == id)
                return _user;
            
        }
        
        return null;
        
    }
    
    /**
     * adds a new user to the database
     * @param username the username of the user
     * @param password the unhashed password of the user
     * @param admin whether the user is an admin or not
     * @return the JsonObject representing the user on success, null on failure
     */
    public static JsonObject addUser(String username, String password, boolean admin) {
        
        //hash input password
        byte[] b = password.getBytes(StandardCharsets.UTF_8);
        try {
            b = getInstance("MD5").digest(b);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = BaseEncoding.base16().lowerCase().encode(b);
        //
        
        JsonArray userArray = db.get("users").getAsJsonArray();
        JsonObject user = new JsonObject();
        user.addProperty("id", incrementCount("userCount"));
        user.addProperty("username", username);
        user.addProperty("password", hashedPassword);
        user.addProperty("admin", admin);
        user.addProperty("favouriteCount", 0);
        user.add("favouriteIDs", new JsonArray());
        user.addProperty("userPOICount", 0);
        user.add("userPOIIDs", new JsonArray());
        
        //todo check if name/shortname is unique
        
        userArray.add(user);
        
        save();
        
        return user;
        
    }
    
    /**
     * gets all the floors in a given building
     * @param building the building to get the floors of
     * @return the JsonArray representing the floors
     */
    public static JsonArray getFloors(Building building) {
        return getBuilding(building.getID()).get("floors").getAsJsonArray();
    }
    public static JsonArray getFloors (JsonObject building) {
        return building.get("floors").getAsJsonArray();
    }
    
    /**
     * get a specific floor given its building and id
     * @param building the building of the floor
     * @param id the id of the floor
     * @return the JsonObject representation of the floor if it exists, otherwise null
     */
    public static JsonObject getFloor (Building building, int id) {
        
        int floorCount = building.getFloorNum();
        JsonArray floors = getFloors(building);
        
        for (int i = 0; i < floorCount; ++i) {
            
            JsonObject _floor = floors.get(i).getAsJsonObject();
            
            if (_floor.get("id").getAsInt() == id)
                return _floor;
            
        }
        
        return null;
        
    }
    
    /**
     * add a floor to the database
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @return the JsonObject representing the floor on success, null on failure
     */
    public static JsonObject addFloor(Building building, String name, String filePath) {
        
        JsonObject jsonBuilding = getBuilding(building.getID());
        
        JsonObject floor = new JsonObject();
        floor.addProperty("id", incrementCount("floorCount"));
        floor.addProperty("name", name);
        floor.addProperty("filePath", filePath);
        floor.addProperty("roomCount", 0);
        floor.add("rooms", new JsonArray());
        floor.addProperty("POICount", 0);
        floor.add("miscPOIs", new JsonArray());
        
        jsonBuilding.get("floors").getAsJsonArray().add(floor);
        
        int count = jsonBuilding.get("count").getAsInt();
        jsonBuilding.addProperty("count", count+1);
        save();
        
        return floor;
        
    }
    
    /**
     * inserts a floor above another floor base on the floor below's id
     * @param building the building the floor is in
     * @param name the name of the floor
     * @param filePath the path to the image of the floor
     * @param prevFloorID the id of the floor below (use -1 to insert at bottom)
     * @return the JsonObject representing the floor on success, null on failure
     */
    public static JsonObject addFloor(Building building, String name, String filePath, int prevFloorID) {
        
        JsonObject jsonBuilding = getBuilding(building.getID());
        
        JsonObject floor = new JsonObject();
        floor.addProperty("id", incrementCount("floorCount"));
        floor.addProperty("name", name);
        floor.addProperty("filePath", filePath);
        floor.addProperty("count", 0);
        floor.add("rooms", new JsonArray());
        
        JsonArray floorArray = jsonBuilding.get("floors").getAsJsonArray();
        
        //find index of previous floor
        int index = 0;
        for (int i = 0; i < building.getFloorNum(); ++i) {
            if (floorArray.get(i).getAsJsonObject().get("id").getAsInt() == prevFloorID) {
                index = i;
            }
        }
        //
        
        //insert
        floorArray.asList().add(index, floor);
        
        int count = jsonBuilding.get("count").getAsInt();
        jsonBuilding.addProperty("count", count+1);
        save();
        
        return floor;
        
    }
    
    /**
     * gets all the layers in the database
     * @return the JsonArray representing the layers
     */
    public static JsonArray getLayers() {
        //return getBuilding(building.getID()).get("floors").getAsJsonArray();
        return db.get("layers").getAsJsonArray();
    }
    
    /**
     * get a specific layer based on its id
     * @param id the id of the layer
     * @return the JsonObject representing the layer if it exists, otherwise null
     */
    public static JsonObject getLayer (int id) {
        
        int layerCount = db.get("curLayerCount").getAsInt();
        JsonArray layers = db.get("layers").getAsJsonArray();
        
        for (int i = 0; i < layerCount; ++i) {
            
            JsonObject _layer = layers.get(i).getAsJsonObject();
            
            if (_layer.get("id").getAsInt() == id)
                return _layer;
            
        }
        
        return null;
        
    }
    
    /**
     * get a specific layer based on its name
     * @param name the name of the layer
     * @return the JsonObject representing the layer if it exists, otherwise null
     */
    public static JsonObject getLayer (String name) {
        
        int layerCount = db.get("curLayerCount").getAsInt();
        JsonArray layers = db.get("layers").getAsJsonArray();
        
        for (int i = 0; i < layerCount; ++i) {
            
            JsonObject _layer = layers.get(i).getAsJsonObject();
            
            if (_layer.get("name").getAsString().compareTo(name) == 0)
                return _layer;
            
        }
        
        return null;
        
    }
    
    /**
     * add a layer to the database
     * @param name the name of the layer
     * @param color the color of rooms in the layer when enabled
     * @return the JsonObject representing the Layer on success, null on failure
     */
    public static JsonObject addLayer(String name, Color color) {
        
        JsonArray layerArray = db.get("layers").getAsJsonArray();
        JsonObject layer = new JsonObject();
        layer.addProperty("id", incrementCount("layerCount"));
        incrementCount("curLayerCount");
        layer.addProperty("name", name);
        layer.addProperty("color", color.getRGB());
        layer.addProperty("count", 0);
        layer.add("POIs", new JsonArray());
        
        //todo check if name is unique
        
        layerArray.add(layer);
        
        save();
        
        return layer;
        
    }
    
    /**
     * gets all the rooms on a floor
     * @param building the building the rooms are in
     * @param floor the floor the rooms are on
     * @return the JsonArray representing the rooms if they exist, otherwise null
     */
    public static JsonArray getRooms(Building building, Floor floor) {
        //System.out.println(building.getName() + " " + floor.getName() + " " + layer.getName());
        return getFloor(building, floor.getID()).get("rooms").getAsJsonArray();
    }
    public static JsonArray getRooms(JsonObject floor) {
        return floor.get("rooms").getAsJsonArray();
    }
    /**
     * gets all the POIs in a layer
     * @param layer the layer the POIs are in
     * @return a list of POIs in the layer
     */
    public static LinkedList<JsonObject> getPOIs(Layer layer) {
        //System.out.println(building.getName() + " " + floor.getName() + " " + layer.getName());
        int POINum = getLayer(layer.getID()).get("count").getAsInt();
        JsonArray POIIDArray =  getLayer(layer.getID()).get("rooms").getAsJsonArray();
        
        LinkedList<JsonObject> out = new LinkedList<JsonObject>();
        
        for (int i = 0; i < POINum; ++i) {
            out.add(getPOI(POIIDArray.get(i).getAsInt()));
        }
        
        return out;
        
    }
    
    /**
     * gets the room with the given id
     * @param building the building the room is in
     * @param floor the floor the room is on
     * @param layer the layer the room is on
     * @param id the id of the room
     * @return a JsonObject representing the room if it exists, otherwise null
     */
    public static JsonObject getRoom (Building building, Floor floor, int id) {
        //System.out.println(id);
        int roomCount = floor.getRoomNum();
        JsonArray rooms = getRooms(building, floor);
        
        for (int i = 0; i < roomCount; ++i) {
            
            JsonObject _room = rooms.get(i).getAsJsonObject();
            //System.out.println(_room.get("id"));
            
            if (_room.get("id").getAsInt() == id)
                return _room;
            
        }
        
        return null;
        
    }
    
    /**
     * adds a room to the database
     * @param building the building the room is in
     * @param floor the floor the room is on
     * @param shape the shape of the building
     * @param position the position of the building
     * @return the JsonObject representing the room on success, null on failure
     */
    public static JsonObject addRoom(Building building, Floor floor, Polygon shape, Point position) {
        
        JsonObject jsonFloor = getFloor(building, floor.getID());
        
        JsonObject room = new JsonObject();
        room.addProperty("id", incrementCount("roomCount"));
        room.addProperty("roomNumber", "");
        room.addProperty("x", position.getX());
        room.addProperty("y", position.getY());
        
        room.addProperty("npoints", shape.npoints);
        JsonArray xpoints = new JsonArray(shape.npoints);
        JsonArray ypoints = new JsonArray(shape.npoints);
        for (int x : shape.xpoints) xpoints.add(x);
        for (int y : shape.ypoints) ypoints.add(y);
        room.add("xpoints", xpoints);
        room.add("ypoints", ypoints);
        
        room.addProperty("count", 0);
        room.add("POIs", new JsonArray());
        
        jsonFloor.get("rooms").getAsJsonArray().add(room);
        
        int count = jsonFloor.get("roomCount").getAsInt();
        jsonFloor.addProperty("roomCount", count+1);
        save();
        
        return room;
        
    }
    
    /**
     * get all the POIs in a room
     * @param building the building the room is in
     * @param floor the floor the room is on
     * @param layer the layer the room is in
     * @param room the room the POIs are in
     * @return the JsonArray representing the POIs if they exist, otherwise none
     */
    public static JsonArray getPOIs(Building building, Floor floor, Room room) {
        //System.out.println(building.getName() + " " + floor.getName() + " " + layer.getName());
        return getRoom(building, floor, room.getID()).get("POIs").getAsJsonArray();
    }
    public static JsonArray getPOIs(JsonObject room) {
        return room.get("POIs").getAsJsonArray();
    }
    
    /**
     * gets a specific POI
     * @param building the building the POI is in
     * @param floor the floor the POI is on
     * @param layer the layer the POI is in
     * @param room the room the POI is in
     * @param id the id of the POI
     * @return the JsonObject representing the POI if it exists, otherwise null
     */
    public static JsonObject getPOI (Building building, Floor floor, Room room, int id) {
        //System.out.println(id);
        int POICount = room.getPOINum();
        JsonArray POIs = getPOIs(building, floor, room);
        
        for (int i = 0; i < POICount; ++i) {
            
            JsonObject _POI = POIs.get(i).getAsJsonObject();
            //System.out.println(_room.get("id"));
            
            if (_POI.get("id").getAsInt() == id)
                return _POI;
            
        }
        
        return null;
        
    }
    
    /**
     * get all the POIs in the database
     * @return the JsonArray representing the POIs if they exist, otherwise none
     */
    public static LinkedList<JsonObject> getPOIs() {
        //System.out.println(building.getName() + " " + floor.getName() + " " + layer.getName());
        LinkedList<JsonObject> out = new LinkedList<JsonObject>();
        for(JsonElement building: getBuildings()) {
            for(JsonElement floor: getFloors(building.getAsJsonObject())) {
                for(JsonElement room: getRooms(floor.getAsJsonObject())) {
                    for (JsonElement poi: getPOIs(room.getAsJsonObject())) {
                        out.add(poi.getAsJsonObject());
                    }
                }
            }
        }
        return out;
    }
    
    /**
     * gets a specific POI
     * @param id the id of the POI
     * @return the JsonObject representing the POI if it exists, otherwise null
     */
    public static JsonObject getPOI (int id) {
        //System.out.println(id);
        LinkedList<JsonObject> POIList = getPOIs();
        
        for (JsonObject poi : POIList) {
            
            if (poi.get("id").getAsInt() == id)
                return poi;
            
        }
        
        return null;
        
    }
    
    /**
     * add a POI to the database
     * @param building the building the POI is in
     * @param floor the floor the POI is on
     * @param layer the layer the POI is in
     * @param room the room the POI is in
     * @param user the user the POI belongs to, null if none
     * @param name the name of the POI
     * @param description the description of the POI
     * @param position the position of the POI
     * @return the JsonObject representing the POI if it exists, otherwise null
     */
    public static JsonObject addPOI(Building building, Floor floor, Layer layer, Room room, User user, String name, String description, Point position) {
        
        JsonObject container;
        if (room != null)
            container = getRoom(building, floor, room.getID());
        else
            container = getFloor(building, floor.getID());
        
        JsonObject POI = new JsonObject();
        POI.addProperty("id", incrementCount("POICount"));
        POI.addProperty("name", name);
        POI.addProperty("description", description);
        POI.addProperty("layerID", layer.getID());
        POI.addProperty("x", position.getX());
        POI.addProperty("y", position.getY());
        if (user == null || user.isAdmin())
            POI.addProperty("user", -1);
        else
            POI.addProperty("user", user.getID());
        
        if (room != null)
        {
            container.get("POIs").getAsJsonArray().add(POI);

            int count = container.get("count").getAsInt();
            container.addProperty("count", count+1);
        }
        else {
            container.get("miscPOIs").getAsJsonArray().add(POI);

            int count = container.get("POICount").getAsInt();
            container.addProperty("POICount", count+1);
        }
        save();
        
        return POI;
        
    }
    
}
