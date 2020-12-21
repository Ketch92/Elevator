package elevator;

public abstract class Elevator implements Elevatable{
    private int capacity;
    private int floorPosition;


    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void pickUp(Integer... persons) {

    }

    @Override
    public Integer[] lifted() {
        return new Integer[0];
    }

    @Override
    public boolean hasSpace() {
        return false;
    }
}
