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
    void testNumberOfFloors() {
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
    void testSizeOfQueue() {
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
    void testQueue() {
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
    void testNullContaining() {
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