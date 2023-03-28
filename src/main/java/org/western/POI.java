package org.western;

import com.google.gson.JsonObject;

public class POI {
    private int id;
    private String name;
    private String description;
    private String building;
    private String floor;

    private String[] layer;
    private String room;
    private float[][] coordinates = new float[4][2];
    private int[] user;

    public POI(int id, String name, String description, String building, String floor, String[] layer, String room, float[][] coordinates, int[] user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.building = building;
        this.floor = floor;
        this.layer = layer;
        this.room = room;
        this.coordinates = coordinates;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public String[] getLayer() {
        return layer;
    }

    public String getRoom() {
        return room;
    }

    public float[][] getCoordinates() {
        return coordinates;
    }

    public int[] getUser() {
        return user;
    }
}
