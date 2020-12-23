package elevator.AbstractElevator;

import building.Building;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElevator implements Elevate {
    private List<Integer> containment;
    private int floorPosition;
    private Building building;
    
    public AbstractElevator() {
        containment = new ArrayList<>(MAXIMUM_CAPACITY);
    }

    public AbstractElevator(Building building){
        this.building = building;
        containment = new ArrayList<>(MAXIMUM_CAPACITY);
    }

    @Override
    public void moveUp() {
        floorPosition++;
    }

    @Override
    public void moveDown() {
        floorPosition--;
    }

    @Override
    public abstract void pickUp(Integer... persons);

    @Override
    public abstract List<Integer> lifted();

    @Override
    public boolean hasSpace() {
        return getOccupancy() < MAXIMUM_CAPACITY;
    };

    public boolean containsPerson(Integer person) {
        return containment.contains(person);
    }

    public Integer getPerson(Integer person) {
        containment.remove(person);
        return person;
    }

    public void addToContainment(Integer person) {
        containment.add(person);
    }

    public List<Integer> getContainment() {
        return containment;
    }

    public int getOccupancy() {
        return containment.size();
    }

    public int getFloorPosition() {
        return floorPosition;
    }

    public void setFloorPosition(int floorPosition) {
        this.floorPosition = floorPosition;
    }

    public Building getBuilding() {
        return building;
    }
}