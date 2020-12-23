package building;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;
    
    @BeforeEach
    void setUp() {
        building = new Building.BuildingConstructor().build();
    }
    
    @Test
    void numberOfFloors() {
        for (int i = 0; i < 20; i++) {
            int actual = building.getBuildingLevels().length;
            if (actual < 5 || actual > 20) {
                fail("The number of floors in the building isn't correct \n"
                        + "Actual: " + actual);
            }
            setUp();
        }
    }
    
    @Test
    void levelNaming() {
        int index = 0;
        for (Floor floor : building.getBuildingLevels()) {
            String expectedName = "Level " + index;
            String actualName = floor.getName();
            if (!actualName.equals(expectedName)) {
                Assertions.fail("Unordered floor naming \n"
                + "Expected: " + expectedName + "\n"
                + "Actual: " + actualName);
            }
            index++;
        }
    }
    
    @Test
    void sizeOfQueue() {
        for (int i = 0; i < 10; i++) {
            for (Floor floor : building.getBuildingLevels()) {
                int actual = floor.getQueue().size();
                if (actual > 10) {
                    fail("The number of floors in the building isn't correct \n"
                            + "Actual: " + actual + "\n"
                            + "expected value from 0 to 10");
                }
            }
            setUp();
        }
    }
    
    @Test
    void queueElements() {
        for (int i = 0; i < building.getBuildingLevels().length; i++) {
            Floor floor = building.getBuildingLevels()[i];
            int actualLevel = floor.getLevel();
            for (Integer actualPerson : floor.getQueue()) {
                if (actualPerson == actualLevel) {
                    Assertions.fail("The floor queue shouldn't contain persons from the same floor \n"
                    + "Actual level" + actualLevel + "\n"
                    + "Found person" + actualPerson);
                }
                if (actualPerson > building.getBuildingLevels().length - 1) {
                    Assertions.fail("Found person " + actualPerson
                            + " with number of floors" + building.getBuildingLevels().length);
                }
            }
        }
    }
    
    @Test
    void nullContaining() {
        for (Floor floor : building.getBuildingLevels()) {
            if (floor == null) {
                Assertions.fail("A null floor was found in building");
            }
            for (Integer person : floor.getQueue()) {
                if (person == null) {
                    Assertions.fail("A null person was found in building " + floor.getName());
                }
            }
        }
    }
}