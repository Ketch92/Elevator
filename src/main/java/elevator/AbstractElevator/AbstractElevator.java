package elevator.AbstractElevator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElevator implements Elevate {
    private List<Integer> containment;
    private int floorPosition;
    private Direction direction;
    
    public AbstractElevator() {
        containment = new ArrayList<>();
        direction = Direction.UP;
        floorPosition = 0;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
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
    public abstract void pickUp(Integer persons);
    
    @Override
    public abstract List<Integer> lifted();
    
    @Override
    public boolean hasSpace() {
        return containment.size() < MAXIMUM_CAPACITY;
    }
    
    public boolean containsPerson(Integer person) {
        return containment.contains(person);
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
}
