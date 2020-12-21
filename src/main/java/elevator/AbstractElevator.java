package elevator;

import building.Building;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElevator implements Elevate {
    private List<Integer> containment;
    private int occupancy;
    private int floorPosition;
    private Direction direction;
    private Building building;

    public AbstractElevator(Building building){
        this.building = building;
        containment = new ArrayList<>();
        direction = Direction.UP;
    }

    @Override
    public abstract void moveUp();

    @Override
    public abstract void moveDown();

    @Override
    public abstract void pickUp(Integer... persons);

    @Override
    public abstract Integer[] lifted();

    @Override
    public abstract boolean hasSpace();

    public List<Integer> getContainment() {
        return containment;
    }

    public void setContainment(List<Integer> containment) {
        this.containment = containment;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getFloorPosition() {
        return floorPosition;
    }

    public void setFloorPosition(int floorPosition) {
        this.floorPosition = floorPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
