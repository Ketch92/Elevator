package elevator;

import building.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void pickUpWrongPersons() {
        elevator = new Elevator();
        Integer[] persons = new Integer[]{0, 0, 0};
        elevator.pickUp(persons);
        int expectedOccupancy = 0;
        int actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    }
    
    @Test
    void lifted() {
        elevator = new Elevator();
        elevator.getContainment().addAll(List.of(2, 4, 4, 6));
        elevator.setFloorPosition(4);
        
        List<Integer> liftedActual = elevator.lifted();
        List<Integer> liftedExpected = List.of(4, 4);
        Assertions.assertEquals(liftedExpected, liftedActual);
        
        elevator = new Elevator();
        elevator.getContainment().addAll(List.of(1, 3, 4, 6, 8));
        elevator.setFloorPosition(4);
        
        for (int i = 0; i < 10; i++) {
            elevator.setFloorPosition(i);
            if(elevator.getContainment().contains(i)) {
                liftedActual = elevator.lifted();
                liftedExpected = List.of(i);
                Assertions.assertEquals(liftedExpected, liftedActual);
                continue;
            }
            liftedActual = elevator.lifted();
            liftedExpected = List.of();
            Assertions.assertEquals(liftedExpected, liftedActual);
        }
        
        int expectedEmptyOccupancy = 0;
        int actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedEmptyOccupancy, actualOccupancy);
    }
}