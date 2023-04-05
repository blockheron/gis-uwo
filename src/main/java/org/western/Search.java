package org.western;

import org.w3c.dom.Node;

import java.util.*;

public class Search {
    private ArrayList<String> fList; // fL is the filter list
    private Set<String> fS; // fS is the filter set
    private String[] regex; // regex is a regular expression table
    private User cU; // cU is the current user
    private Building cB; // cB is the current building
    private Floor cF; // cF is the current floor
    private Layer cL; // cL is the current layer

    // Search() is the constructor for the Search class
    Search() {
        fList = new ArrayList<>();
        fS = new HashSet<>();
        regex = new String[]{"(\\D*)(\\d+)", "(\\\\d+)", "([a-zA-Z]+)"};
        loadFilter();
    }

    // loadFilter() loads the filter list from the database
    private int loadFilter() {
        LinkedList<Building> bL;
        LinkedList<Floor> fL;
        LinkedList<Layer> lL;
        try {
//            if (JsonDB.getBuildings() != null) {
//                b = JsonDB.getBuildings().getAsJsonArray();
//                if (b != null) {
//                    b.forEach(building -> {
//                        f.set(building.getAsJsonObject().get("floors").getAsJsonArray());
//                        if (f.get() != null) {
//                            f.get().forEach(floor -> {
//                                l.set(floor.getAsJsonObject().get("layers").getAsJsonArray());
//                                if (l.get() != null) {
//                                    l.get().forEach(layer -> {
//                                        if (layer != null && layer.getAsJsonObject().get("name") != null && layer.getAsJsonObject().get("name").getAsString().length() > 0) {
//                                            fL.add(layer.getAsJsonObject().get("name").getAsString());
//                                        } else {
//                                            System.out.println("Layer name is null or empty.");
//                                        }
//                                    });
//                                } else {
//                                    System.out.println("Failed to load layers.");
//                                    return;
//                                }
//                            });
//                        } else {
//                            System.out.println("Failed to load floors.");
//                            return;
//                        }
//                    });
//                    fS.addAll(fL);
//                    fL = new ArrayList<>(fS);
//                } else {
//                    System.out.println("Failed to load buildings.");
//                    return 1;
//                }
//            } else {
//                System.out.println("Failed to connect to database.");
//            }
            bL = Map.getBuildings();
            if (bL.isEmpty()) return 1;
            for (Building b : bL) {
                fL = b.getFloors();
                if (fL.isEmpty()) continue;
                for (Floor floor : fL) {
                    lL = floor.getLayers();
                    if (lL.isEmpty()) continue;
                    for (Layer layer : lL) {
                        if (layer.getRooms().isEmpty()) continue;
                        fList.add(layer.getName());
                    }
                }
            }
            fS.addAll(fList);
            fList = new ArrayList<>(fS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    // getFilter() returns the filter list
    public String[] getFilters() {
        fList.add(0, "All");
        return fList.toArray(new String[0]);
    }

    public List<POI> handleFilter(String filter) {
        return null;
    }

    public Building searchBuilding(String s) {
        s = performRegex(s).toUpperCase();
        return Map.getBuilding(s);
    }

    public POI searchPOI(POI p, String s, String l) {
        if(s.isEmpty() || s.equals("Search")) {
            return p;
        }
        if(!l.equals("All")) {
            if(!p.getLayer().getName().equals(l)) {
                return null;
            }
        }
        s = s.replaceAll(regex[0], "");
        if(p.getName().toUpperCase().contains(s.toUpperCase()) || s.toUpperCase().contains(p.getName().toUpperCase())) {
            return p;
        }
        return null;
    }

    public String performRegex(String str) {
        return str.replaceAll(regex[0], "$1");
    }
}