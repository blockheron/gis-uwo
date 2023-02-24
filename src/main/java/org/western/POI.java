package org.western;

public class POI {
    private int id;
    private String name;
    private String description;
    private String room;
    private float[][] coordinates = new float[4][2];
    private Building building;
    private User user;

    public POI(int id, String name, String description, Building building, String room, float[][] coordinates) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.building = building;
        this.room = room;
        this.coordinates = coordinates;
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

    public String getRoom() {
        return room;
    }

    public float[][] getCoordinates() {
        return coordinates;
    }

    public Building getBuilding() {
        return building;
    }

    public User getUser() {
        return user;
    }
}
