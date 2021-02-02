package building;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuildingTest {
    private static final int EXPECTED_MIN_NUMB_FLOORS = 5;
    private static final int EXPECTED_MAX_NUMB_FLOORS = 5;
    private Building building;
    
    @BeforeEach
    void setUp() {
        building = new Building.BuildingConstructor().build();
    }
    
    @Test
    void numberOfFloors() {
        for (int i = 0; i < 20; i++) {
            int actual = building.getLevels().length;
            if (actual < EXPECTED_MIN_NUMB_FLOORS
                || actual > EXPECTED_MAX_NUMB_FLOORS) {
                fail("The number of floors in the building isn't correct \n"
                        + "Actual: " + actual);
            }
        }
    }
    
    @Test
    void sizeOfQueue() {
        for (int i = 0; i < 10; i++) {
            for (Floor floor : building.getLevels()) {
                int actual = floor.getQueue().size();
                if (actual > 10) {
                    fail("The number of floors in the building isn't correct \n"
                            + "Actual: " + actual + "\n"
                            + "expected value from 0 to 10");
                }
            }
        }
    }
    
    @Test
    void queueElements() {
        for (int i = 0; i < building.getLevels().length; i++) {
            Floor floor = building.getLevels()[i];
            int actualLevel = floor.getLevel();
            for (Integer actualPerson : floor.getQueue()) {
                if (actualPerson == actualLevel) {
                    Assertions.fail("The floor queue shouldn't contain persons"
                            + " from the same floor \n"
                            + "Actual level" + actualLevel + "\n"
                            + "Found person" + actualPerson);
                }
                if (actualPerson > building.getLevels().length - 1) {
                    Assertions.fail("Found person " + actualPerson
                            + " with number of floors" + building.getLevels().length);
                }
            }
        }
    }
}
