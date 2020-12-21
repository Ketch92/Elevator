package elevator;

public interface Elevatable {
    int MAXIMUM_CAPACITY = 5;

    void moveUp();

    void moveDown();

    void pickUp(Integer... persons);

    Integer[] lifted();

    boolean hasSpace();
}
