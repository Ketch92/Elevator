package elevator;

import building.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    private Floor floor;
    private Elevator elevator;
    
    @BeforeEach
    void setUp() {
        floor = new Floor(0, List.of(1, 1, 3, 5, 1, 9));
    }
    
    @Test
    void pickUpOccupancy() {
        elevator = new Elevator();
        
        int expectedOccupancy = 0;
        int actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
        
        elevator.pickUp(3, 1);
        expectedOccupancy = 2;
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
        
        elevator.pickUp(9, 4, 3);
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
        
        elevator.pickUp(4);
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
    }
    
    @Test
    void lifted() {
    }
}