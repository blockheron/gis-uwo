package org.western;

import java.util.Dictionary;
import java.util.LinkedList;
import com.google.gson.*;

public class Floor {
    
    private JsonObject data;
    //private LinkedList<Layer> layers;
    private String name;
    
    
    public Floor(String name) {
        this.name = name;
        //this.layers = layers;
    }
    /*
    public Layer[] getLayers(String floor) {
        data.get("layers");
    }
    public Layer getLayer(String floor) {
        return layers.get(floor);
    }
    public POI getPOI(String floor, String name) {
        return layers.get(floor).getPOI(name);
    }
    public void display(String floor) {
        layers.get(floor).display();
    }*/
}