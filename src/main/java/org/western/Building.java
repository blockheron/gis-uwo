package org.western;

public class Building {
    private int id;
    private String name;
    private String shortName;
    private POI[] POIs;

    private String[] floors;

    public Building(int id, String name, String shortName, POI[] POIs, String[] floors) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.POIs = POIs;
        this.floors = floors;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getShortName() {
        return shortName;
    }
    public POI[] getPOIs() {
        return POIs;
    }
    public String[] getFloors() {
        return floors;
    }
}
