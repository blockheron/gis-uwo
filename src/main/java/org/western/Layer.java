package org.western;

public class Layer {
    private POI[] POIs;

    public Layer(POI[] POIs) {
        this.POIs = POIs;
    }

    public POI getPOI(String name) {
        for (POI poi : POIs) {
            if (poi.getName().equals(name)) {
                return poi;
            }
        }
        return null;
    }
    public void display() {
        for (POI poi : POIs) {
            System.out.println(poi.getName());
        }
    }
}