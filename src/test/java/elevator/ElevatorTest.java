package elevator;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElevatorTest {
    private Elevator elevator;

    @Test
    void pickUpOccupancy() {
        elevator = new Elevator();
        
        int expectedOccupancy = 0;
        int actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    
        elevator.pickUp(3);
        elevator.pickUp(2);
        expectedOccupancy = 2;
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(expectedOccupancy, actualOccupancy);
    
        elevator.pickUp(9);
        elevator.pickUp(3);
        elevator.pickUp(5);
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
    
        elevator.pickUp(4);
        actualOccupancy = elevator.getOccupancy();
        Assertions.assertEquals(Elevator.MAXIMUM_CAPACITY, actualOccupancy);
    }
    
    @Test
    void pickUpWrongPersons() {
        elevator = new Elevator();
        Integer persons = 0;
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
            if (elevator.getContainment().contains(i)) {
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
