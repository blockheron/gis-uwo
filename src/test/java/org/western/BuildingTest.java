package org.western;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private static Building building;

    @BeforeAll
    static void setUp() {
        JsonDB db = new JsonDB(false);
        building = new Building("Test Building", "TB");
    }

    @AfterAll
    static void tearDown() {
        building.free();
    }

    @Test
    @DisplayName("Test get name")
    void getName() {
        assertEquals("Test Building", building.getName());
    }

    @Test
    @DisplayName("Test set name")
    void setName() {
        building.setName("New Name");
        assertEquals("New Name", building.getName());
    }

    @Test
    @DisplayName("Test get short name")
    void getShortName() {
        assertEquals("TB", building.getShortName());
    }

    @Test
    @DisplayName("Test set short name")
    void setShortName() {
        building.setShortName("NB");
        assertEquals("NB", building.getShortName());
    }

    @Test
    @DisplayName("Test get saved location")
    void getSavedLocation() {
        assertEquals(new Point(0, 0), building.getSavedLocation());
    }

    @Test
    @DisplayName("Test get shape")
    void getShape() {
        Polygon expectedShape = new Polygon();
        expectedShape.addPoint(0, 0);
        assertEquals(expectedShape, building.getShape());
        /* TODO: test get shape from database */
    }

    @Test
    @DisplayName("Test set shape")
    void setShape() {
        Polygon newShape = new Polygon();
        newShape.addPoint(0, 0);
        newShape.addPoint(10, 0);
        newShape.addPoint(0, 10);
        building.setShape(newShape);
        assertEquals(newShape, building.getShape());
        /* TODO: test if the shape is saved in the database */
    }

    @Test
    @DisplayName("Test add point")
    void addPoint() {
        building.addPoint(10, 10);
        Polygon expectedShape = new Polygon();
        expectedShape.addPoint(0, 0);
        expectedShape.addPoint(10, 0);
        expectedShape.addPoint(0, 10);
        expectedShape.addPoint(10, 10);
        assertEquals(expectedShape, building.getShape());
        /* TODO: test add point to database */
    }

    @Test
    @DisplayName("Test get bounds")
    void getBounds() {
        Polygon shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(10, 0);
        shape.addPoint(0, 10);
        building.setShape(shape);
        assertEquals(new Rectangle(0, 0, 10, 10), building.getBounds());
    }

    @Test
    @DisplayName("Test get floor num")
    void getFloorNum() {
        assertEquals(0, building.getFloorNum());
    }

}
