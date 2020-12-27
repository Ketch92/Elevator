package building;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Building {
    private Floor[] buildinglevels;
    
    private Building(BuildingConstructor builder) {
        buildinglevels = builder.buildingLevels;
    }
    
    public boolean areQueuesEmpty() {
        for (Floor floor : buildinglevels) {
            if (!floor.getQueue().stream().allMatch(i -> i == floor.getLevel())) {
                return false;
            }
        }
        return true;
    }
    
    public Floor[] getLevels() {
        return buildinglevels;
    }
    
    public static class BuildingConstructor {
        public static final int MAX_QUEUE = 10;
        public static final int MIN_FLOORS_NUMBER = 5;
        public static final int MAX_FLOORS_NUMBER = 20;
        private Floor[] buildingLevels;
        
        public Building build() {
            initFloors();
            return new Building(this);
        }
        
        public Queue<Integer> buildQueue(int level) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < new Random().nextInt(MAX_QUEUE); i++) {
                queue.add(getRandomPerson(level));
            }
            return queue;
        }
        
        public int getRandomPerson(int level) {
            int person;
            do {
                person = new Random().nextInt(buildingLevels.length);
            } while (person == level);
            return person;
        }
        
        private void initFloors() {
            buildingLevels = new Floor[getFloorsNumbers()];
            setFloors(buildingLevels);
        }
        
        private void setFloors(Floor[] floors) {
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new Floor(i, buildQueue(i));
            }
        }
        
        private int getFloorsNumbers() {
            int levels;
            do {
                levels = new Random().nextInt(MAX_FLOORS_NUMBER + 1);
            } while (levels < MIN_FLOORS_NUMBER);
            return levels;
        }
    }
}
