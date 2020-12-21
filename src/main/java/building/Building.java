package building;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building {
    private Floor[] buildingLevels;

    private Building(BuildingConstructor builder) {
        buildingLevels = builder.buildingLevels;
    }

    public static class BuildingConstructor {
        private static final int MIN_FLOORS_NUMBER = 5;
        private static final int MAX_FLOORS_NUMBER = 20;
        public static final int MAX_QUEUE = 10;
        private Floor[] buildingLevels;

        public Building build() {
            initFloors();
            return new Building(this);
        }

        private Floor[] initFloors() {
            int levels = 0;
            while ((levels >= MIN_FLOORS_NUMBER) && (levels <= MAX_FLOORS_NUMBER)) {
                levels = new Random().nextInt(21);
            }
            Floor[] floors = new Floor[levels];
            setFloors(floors);
            return floors;
        }

        private void setFloors(Floor[] floors) {
            int level = 0;
            for (Floor floor : floors) {
                floor = new Floor(level, buildQueue());
            }
        }

        private List<Integer> buildQueue() {
            List<Integer> queue = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(MAX_QUEUE); i++) {
                queue.add(new Random().nextInt(buildingLevels.length - 1));
            }
            return queue;
        }
    }
}
