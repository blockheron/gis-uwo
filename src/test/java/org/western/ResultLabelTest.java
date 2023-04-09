package org.western;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.remixicon.RemixiconMZ;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.Color;
import java.lang.reflect.Field;

public class ResultLabelTest {

    private ResultLabel resultLabel;

    @BeforeEach
    public void setUp() {
        JsonDB db = new JsonDB(true);
        resultLabel = new ResultLabel();
    }

    @Test
    @DisplayName("Test ResultLabel with null POI")
    public void testNullPOI() throws NoSuchFieldException, IllegalAccessException {
        ResultLabel resultLabel = new ResultLabel((Building) null);
        Field pField = resultLabel.getClass().getDeclaredField("pField");
        pField.setAccessible(true);
        JTextField pFieldText = (JTextField) pField.get(resultLabel);
        Assertions.assertEquals("Unknown Building", pFieldText.getText());
    }

//    @Test
//    @DisplayName("Test ResultLabel with non-null POI")
//    public void testNonNullPOI() {
//        POI poi = new POI();
//        poi.setName("Test POI");
//        poi.setRoom(new Room());
//        poi.getRoom().setName("Test Room");
//        poi.setFloor(new Floor());
//        poi.getFloor().setName("Test Floor");
//        poi.setBuilding(new Building());
//        poi.getBuilding().setName("Test Building");
//        poi.getBuilding().setShortName("TB");
//
//        ResultLabel resultLabel = new ResultLabel(poi);
//        Assertions.assertEquals(" TB T T Test POI", resultLabel.pField.getText());
//    }

    @Test
    @DisplayName("Test ResultLabel with non-null Building")
    public void testNonNullBuilding() throws NoSuchFieldException, IllegalAccessException {
        Building building = new Building("Test Building", "TB");
        building.setName("Test Building");

        ResultLabel resultLabel = new ResultLabel(building);
        Field pField = resultLabel.getClass().getDeclaredField("pField");
        pField.setAccessible(true);
        JTextField pFieldText = (JTextField) pField.get(resultLabel);
        Assertions.assertEquals("Building Test Building", pFieldText.getText());
    }

    @Test
    @DisplayName("Test ResultLabel star icon")
    public void testStarIcon() throws NoSuchFieldException, IllegalAccessException {
        Building building = new Building("Test Building", "TB");
        ResultLabel resultLabel = new ResultLabel(building);
        Field favIconField = resultLabel.getClass().getDeclaredField("sLabel");
        favIconField.setAccessible(true);
        JLabel favIcon = (JLabel) favIconField.get(resultLabel);
        Assertions.assertEquals(FontIcon.of(RemixiconMZ.STAR_LINE, 20, Color.BLACK), favIcon.getIcon());

//        Assertions.assertEquals(Color.BLACK, resultLabel.unfavIcon.getForeground());
//        Assertions.assertEquals(Color.decode("#ffcc00"), resultLabel.favIcon.getForeground());
    }
}