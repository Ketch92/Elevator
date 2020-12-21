package elevator;

public interface Elevate {
    int MAXIMUM_CAPACITY = 5;

    void moveUp();

    void moveDown();

    void pickUp(Integer... persons);

    Integer[] lifted();

    boolean hasSpace();
}
