package org.western;

public class Map {
    
    //private Building[] buildings;
    //private String[] layers;

    public Map() {
        
        new JsonDB();
        
    }

    public static Building[] getBuildings() {
        JsonDB.load();
        Building[] buildings = JsonDB.getBuildings();
        JsonDB.save();
        return buildings;
    }
    
    public static Building getBuilding(String name) {
        
        for (Building building: getBuildings()) {
            if (building.getName().equals(name)) return building;
        }
        return null;
        
    }
    
}
