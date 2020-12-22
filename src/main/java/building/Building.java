package building;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building {
    private Floor[] buildingLevels;
    
    private Building(BuildingConstructor builder) {
        buildingLevels = builder.buildingLevels;
    }
    
    public Floor[] getBuildingLevels() {
        return buildingLevels;
    }
    
    public static class BuildingConstructor {
        public static final int MAX_QUEUE = 10;
        private static final int MIN_FLOORS_NUMBER = 5;
        private static final int MAX_FLOORS_NUMBER = 20;
        private Floor[] buildingLevels;
        
        public Building build() {
            initFloors();
            return new Building(this);
        }
        
        public List<Integer> buildQueue(int level) {
            List<Integer> queue = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(MAX_QUEUE); i++) {
                queue.add(getRandomPerson(level));
            }
            return queue;
        }
        
        public int getRandomPerson(int level) {
            int person;
            do {
                person = new Random().nextInt(buildingLevels.length - 1);
            } while (person == level);
            return person;
        }
        
        private Floor[] initFloors() {
            Floor[] floors = new Floor[getFloorsNumbers()];
            setFloors(floors);
            return floors;
        }
        
        private void setFloors(Floor[] floors) {
            int level = 0;
            for (Floor floor : floors) {
                floor = new Floor(level, buildQueue(level));
                level++;
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
