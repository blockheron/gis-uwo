
package org.western;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

public class FloorTest {

    private Building building;
    private Floor floor;

    @BeforeEach
    void setUp() {
        JsonDB db = new JsonDB(true);
        building = new Building("Test Building", "TB");
        floor = new Floor(building, "Test Floor", "test.png");
    }

    @Test
    void testGetID() {
        int expectedID = 1;
        int actualID = floor.getID();
        assertEquals(expectedID, actualID);
    }
}
