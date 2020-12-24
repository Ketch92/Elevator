package building;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorTest {
    private String expectedName = "Level 5";
    private int expectedLevel = 5;
    private Queue<Integer> expectedQueue = new ArrayDeque<>(List.of(5, 4, 3, 2, 1, 0));
    private Floor floor = new Floor(expectedLevel, expectedQueue);
    
    @BeforeEach
    void setUp() {
        floor = new Floor(expectedLevel, expectedQueue);
    }
    
    @Test
    void getName() {
        Assertions.assertEquals(expectedName, floor.getName());
    }
    
    @Test
    void getLevel() {
        Assertions.assertEquals(expectedLevel, floor.getLevel());
    }
    
    @Test
    void setLevel() {
        int newLevel = 10;
        floor.setLevel(newLevel);
        Assertions.assertEquals(newLevel, floor.getLevel());
        Assertions.assertEquals("Level " + newLevel, floor.getName());
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
        int[] persons = new int[]{7, 8, 9};
        int i = 0;
        while (i < persons.length) {
            floor.pushToQueue(persons[i]);
            i++;
        }
        for (int person : persons) {
            if (!floor.getQueue().contains(person)) {
                Assertions.fail("Persons added to the queue weren't found there");
            }
        }
    }
    
    @Test
    void pushNull() {
        Assertions.assertFalse(floor.pushToQueue(null));
    }
    
    @Test
    void popFromQueue() {
        int size = expectedQueue.size();
        for (int i = 0; i < size; i++) {
            floor.pollPerson();
        }
        Assertions.assertTrue(floor.getQueue().isEmpty());
    }
}