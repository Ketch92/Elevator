package elevator.AbstractElevator;

import java.util.List;
import java.util.Queue;

public interface Elevate {
    int MAXIMUM_CAPACITY = 5;

    void moveUp();

    void moveDown();

    void pickUp(Integer person);

    List<Integer> lifted();

    boolean hasSpace();
}
