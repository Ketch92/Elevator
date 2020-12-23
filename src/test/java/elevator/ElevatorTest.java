package elevator;

import building.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    private Floor floor;
    private Elevator emptyElevator;
    
    @BeforeEach
    void setUp() {
        floor = new Floor(0, List.of(1, 1, 3, 5, 1, 9));
    }
    
    @Test
    void pickUpOccupancy() {
        emptyElevator = new Elevator();
        
        int expectedOccupancy = 0;
        int actualOccupancy = emptyElevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    
        emptyElevator.pickUp(3, 1);
        expectedOccupancy = 2;
        actualOccupancy = emptyElevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    
        emptyElevator.pickUp(9, 4, 3);
        actualOccupancy = emptyElevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
    
        emptyElevator.pickUp(4);
        actualOccupancy = emptyElevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
    }
    
    @Test
    void pickUpWrongPersons() {
        emptyElevator = new Elevator();
        Integer[] persons = new Integer[]{0, 0, 0};
        emptyElevator.pickUp(persons);
        int expectedOccupancy = 0;
        int actualOccupancy = emptyElevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    }
    
    @Test
    void lifted() {
    }
}