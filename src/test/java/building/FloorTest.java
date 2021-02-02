package building;

import elevator.abstractelevator.Direction;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorTest {
    private String expectedName = "Level 5";
    private int expectedLevel = 5;
    private Queue<Integer> expectedQueue = new ArrayDeque<>(List.of(0, 4, 3, 2, 1, 0));
    private Floor floor = new Floor(expectedLevel, expectedQueue);
    
    @BeforeEach
    void setUp() {
        floor = new Floor(expectedLevel, expectedQueue);
    }
    
    @Test
    void getLevel() {
        Assertions.assertEquals(expectedLevel, floor.getLevel());
    }
    
    
    @Test
    void getQueue() {
        Assertions.assertEquals(expectedQueue, floor.getQueue());
    }
    
    @Test
    void setQueue() {
        Queue<Integer> newQueue = new ArrayDeque<>(List.of(4, 4, 3, 10, 2, 4));
        floor.setQueue(newQueue);
        Assertions.assertEquals(newQueue, floor.getQueue());
    }
    
    @Test
    void pushToQueue() {
        int[] persons = new int[]{5, 5, 5};
        for (int i = 0; i < persons.length; i++) {
            floor.pushToQueue(persons[i]);
        }
        for (int person : persons) {
            if (!floor.getQueue().contains(person)) {
                Assertions.fail("Persons added to the queue weren't found there");
            }
        }
    }
    
    @Test
    void popFromQueue() {
        floor = new Floor(0, expectedQueue);
        int expectedSize = 2;
        for (int i = 0; i < 10; i++) {
            floor.pollPerson(Direction.UP);
        }
        int actualSize = expectedQueue.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
    
    @Test
    void popNoneFromQueue() {
        int expectedSize = 6;
        for (int i = 0; i < 10; i++) {
            floor.pollPerson(Direction.UP);
        }
        int actualSize = expectedQueue.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
    
    @Test
    void popAllFromQueue() {
        floor = new Floor(10, expectedQueue);
        int expectedSize = 0;
        for (int i = 0; i < 10; i++) {
            floor.pollPerson(Direction.DOWN);
        }
        int actualSize = expectedQueue.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
}
