package elevator;

import building.Building;

public class Elevator extends AbstractElevator {

    public Elevator(Building building) {
        super(building);
        setDirection(Direction.UP);
    }

    @Override
    public void moveUp() {
    }

    @Override
    public void moveDown() {

    }

    @Override
    public void pickUp(Integer... persons) {
        int index = 0;
        while (hasSpace()) {
            if(getFloorPosition() == persons[index]) {
                index++;
                continue;
            }
            addToContainment(persons[index++]);
        }
    }

    @Override
    public Integer lifted() {
        if (containsPerson(getFloorPosition())) {
            return getPerson(getFloorPosition());
        }
        return -1;
    }

    @Override
    public boolean hasSpace() {
        return getOccupancy() <= MAXIMUM_CAPACITY;
    }
}
