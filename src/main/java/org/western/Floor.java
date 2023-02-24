package org.western;

import java.util.Dictionary;

public class Floor {
    private Dictionary<String, Layer> layers;
    public Layer getLayer(String floor) {
        return layers.get(floor);
    }
    public POI getPOI(String floor, String name) {
        return layers.get(floor).getPOI(name);
    }
    public void display(String floor) {
        layers.get(floor).display();
    }
}
