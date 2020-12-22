package building;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class FloorTest {
  private final String expectedName = "Level 5";
  private final int expectedLevel = 5;
  private final List<Integer> expectedQueue = List.of(5, 4, 3, 2, 1, 0);
  private final Floor floor = new Floor(expectedLevel, expectedQueue);
  
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
    List<Integer> newQueue = List.of(4, 4, 3, 10, 2, 4);
    floor.setQueue(newQueue);
    Assertions.assertEquals(newQueue, floor.getQueue());
  }
}