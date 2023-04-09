/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.western;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.util.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Liam
 */
public class JsonDBTest {

    public JsonDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        new File(JsonDB.dbFilePath).delete();
    }

    @AfterAll
    public static void tearDownClass() {
        new File(JsonDB.dbFilePath).delete();
    }

    @BeforeEach
    public void setUp() {
        new JsonDB(true);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class JsonDB.
     */
    /*@org.junit.jupiter.api.Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JsonDB.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of load method, of class JsonDB.
     */
    /*@org.junit.jupiter.api.Test
    public void testLoad() {
        System.out.println("load");
        boolean expResult = false;
        boolean result = JsonDB.load();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of save method, of class JsonDB.
     */
    /*@org.junit.jupiter.api.Test
    public void testSave() {
        System.out.println("save");
        boolean expResult = false;
        boolean result = JsonDB.save();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of getBuildings method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetBuildings() {
        System.out.println("getBuildings");
        JsonArray result = JsonDB.getBuildings();
        assertTrue(result.getAsJsonArray().isEmpty());
        JsonObject mc = JsonDB.addBuilding("Middlesex", "MC", new Polygon(), new Point(0,0));
        JsonObject pab = JsonDB.addBuilding("Panda", "PAB", new Polygon(), new Point(0,0));
        JsonObject wsc = JsonDB.addBuilding("WSC", "WSC", new Polygon(), new Point(0,0));
        assertEquals(mc ,JsonDB.getBuildings().get(0).getAsJsonObject());
        assertEquals(pab ,JsonDB.getBuildings().get(1).getAsJsonObject());
        assertEquals(wsc ,JsonDB.getBuildings().get(2).getAsJsonObject());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getBuilding method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetBuilding() {
        System.out.println("getBuilding");
        int id = 1;
        JsonObject expResult = null;
        JsonObject result = JsonDB.getBuilding(id);
        assertNull(result);
        id = 2;
        JsonObject mc = JsonDB.addBuilding("Middlesex", "MC", new Polygon(), new Point(0,0));
        JsonObject pab = JsonDB.addBuilding("Panda", "PAB", new Polygon(), new Point(0,0));
        JsonObject wsc = JsonDB.addBuilding("WSC", "WSC", new Polygon(), new Point(0,0));
        result = JsonDB.getBuilding(id);
        assertEquals(pab, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addBuilding method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testAddBuilding() {
        System.out.println("addBuilding");
        String name = "Middlesex";
        String shortName = "MC";
        Polygon shape = new Polygon();
        Point position = new Point(0,0);
        JsonObject result = JsonDB.addBuilding(name, shortName, shape, position);
        JsonObject expResult = JsonDB.getBuilding(1);
        assertEquals(expResult, result);
        assertEquals(name, result.get("name").getAsString());
        assertEquals(shortName, result.get("shortName").getAsString());
        assertEquals(position.x, result.get("x").getAsInt());
        assertEquals(position.y, result.get("y").getAsInt());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetUsers() {
        System.out.println("getUsers");
        JsonArray expResult = null;
        JsonArray result = JsonDB.getUsers();
        assertTrue(result.isEmpty());
        JsonObject liam = JsonDB.addUser("Liam", "1234", false);
        JsonObject admin = JsonDB.addUser("admin", "1234", true);
        JsonObject other = JsonDB.addUser("Other", "1234", false);
        assertEquals(liam, JsonDB.getUsers().get(0).getAsJsonObject());
        assertEquals(admin, JsonDB.getUsers().get(1).getAsJsonObject());
        assertEquals(other, JsonDB.getUsers().get(2).getAsJsonObject());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetUser() {
        System.out.println("getUser");
        int id = 1;
        JsonObject expResult = null;
        JsonObject result = JsonDB.getUser(id);
        assertNull(result);
        id = 2;
        JsonObject liam = JsonDB.addUser("Liam", "1234", false);
        JsonObject admin = JsonDB.addUser("admin", "1234", true);
        JsonObject other = JsonDB.addUser("Other", "1234", false);
        result = JsonDB.getUser(id);
        assertEquals(admin, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testAddUser() {
        System.out.println("addUser");
        String username = "";
        String password = "";
        boolean admin = false;
        JsonObject result = JsonDB.addUser(username, password, admin);
        JsonObject expResult = JsonDB.getUser(1);
        assertEquals(expResult, result);
        assertEquals(username, result.get("username").getAsString());
        assertNotEquals(password, result.get("password").getAsString());
        assertEquals(admin, result.get("admin").getAsBoolean());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFloors method, of class JsonDB.
     */
    @org.junit.jupiter.api.Test
    public void testGetFloors_Building() {
        System.out.println("getFloors");
        Building building = null;
        JsonArray expResult = null;
        JsonArray result = JsonDB.getFloors(building);
        assertNull(result);
        Building mc = Map.addBuilding("Middlesex", "MC");
        Building pab = Map.addBuilding("Panda", "PAB");
        Building wsc = Map.addBuilding("WSC", "WSC");
        result = JsonDB.getFloors(mc);
        assertTrue(result.isEmpty());
        Floor l0 = pab.addFloor("l0", "");
        Floor l1 = pab.addFloor("l1", "");
        Floor l2 = pab.addFloor("l2", "");
        result = JsonDB.getFloors(pab);
        assertEquals(l0.getName(), result.get(0).getAsJsonObject().get("name").getAsString());
        assertEquals(l1.getName(), result.get(1).getAsJsonObject().get("name").getAsString());
        assertEquals(l2.getName(), result.get(2).getAsJsonObject().get("name").getAsString());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}