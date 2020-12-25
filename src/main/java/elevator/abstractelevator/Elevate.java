package elevator.abstractelevator;

import java.util.List;

public interface Elevate {
    int MAXIMUM_CAPACITY = 5;

    void moveUp();

    void moveDown();

    void pickUp(Integer person);

    List<Integer> lifted();

    boolean hasSpace();
}
