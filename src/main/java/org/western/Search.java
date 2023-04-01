package org.western;

import org.w3c.dom.Node;

import java.util.PriorityQueue;

public class Search {
    private PriorityQueue<Node> sQ; // sQ is the search queue
    private String[] fL; // fL is the filter list
    private String[] regex; // regex is a regular expression table
    private User cU; // cU is the current user
    private Building cB; // cB is the current building
    private Floor cF; // cF is the current floor
    private Layer cL; // cL is the current layer

    // Search() is the constructor for the Search class
    Search() {
        JsonDB db = new JsonDB();
        loadFilter();
    }

    // loadFilter() loads the filter list from the database
    private int loadFilter() {
        JsonDB db = new JsonDB();
        System.out.println(db);
        return 0;
    }

    // getFilter() returns the filter list
    public String[] getFilter() {
        return fL;
    }
    public static void main(String[] args) {
        Search s = new Search();
        System.out.println(s.getFilter());
    }
}
