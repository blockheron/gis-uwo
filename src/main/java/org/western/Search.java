package org.western;

import com.google.gson.JsonArray;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Search {
    private PriorityQueue<Node> sQ; // sQ is the search queue
    private ArrayList<String> fL; // fL is the filter list
    private Set<String> fS; // fS is the filter set
    private String[] regex; // regex is a regular expression table
    private User cU; // cU is the current user
    private Building cB; // cB is the current building
    private Floor cF; // cF is the current floor
    private Layer cL; // cL is the current layer

    // Search() is the constructor for the Search class
    Search() {
        JsonDB db = new JsonDB();
        sQ = new PriorityQueue<>();
        fL = new ArrayList<>();
        fS = new HashSet<>();
        loadFilter();
    }

    // loadFilter() loads the filter list from the database
    private int loadFilter() {
        JsonArray b; // b is a temporary building array
        AtomicReference<JsonArray> f = new AtomicReference<>(); // f is a temporary floor array
        AtomicReference<JsonArray> l = new AtomicReference<>(); // l is a temporary layer array
        try {
            if (JsonDB.getBuildings() != null) {
                b = JsonDB.getBuildings().getAsJsonArray();
                if (b != null) {
                    b.forEach(building -> {
                        f.set(building.getAsJsonObject().get("floors").getAsJsonArray());
                        if (f.get() != null) {
                            f.get().forEach(floor -> {
                                l.set(floor.getAsJsonObject().get("layers").getAsJsonArray());
                                if (l.get() != null) {
                                    l.get().forEach(layer -> {
                                        if (layer != null && layer.getAsJsonObject().get("name") != null && layer.getAsJsonObject().get("name").getAsString().length() > 0) {
                                            fL.add(layer.getAsJsonObject().get("name").getAsString());
                                        } else {
                                            System.out.println("Layer name is null or empty.");
                                        }
                                    });
                                } else {
                                    System.out.println("Failed to load layers.");
                                    return;
                                }
                            });
                        } else {
                            System.out.println("Failed to load floors.");
                            return;
                        }
                    });
                    fS.addAll(fL);
                    fL = new ArrayList<>(fS);
                } else {
                    System.out.println("Failed to load buildings.");
                    return 1;
                }
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    // getFilter() returns the filter list
    public String[] getFilter() {
        return fL.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Search s = new Search();
        for (String str : s.getFilter())
            System.out.println(str);
    }
}
