package building;

import elevator.abstractelevator.Direction;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class Floor {
    private String name;
    private int level;
    private Queue<Integer> queue;
    
    public Floor(int level, Queue<Integer> queue) {
        this.queue = queue;
        this.level = level;
        name = "Level " + level;
    }
    
    public boolean pushToQueue(Integer person) {
        if (person == null || person != level) {
            return false;
        }
        queue.add(person);
        return true;
    }
    
    public Integer pollPerson(Direction callElevatorButton) {
        if (isEmptyQueue(callElevatorButton)) {
            return level;
        }
        Queue<Integer> persons;
        if (callElevatorButton.equals(Direction.UP)) {
            persons = queue.stream()
                    .filter(i -> i > level).collect(Collectors.toCollection(ArrayDeque::new));
            Integer person = persons.poll();
            queue.remove(person);
            return person;
        }
        persons = queue.stream()
                .filter(i -> i < level).collect(Collectors.toCollection(ArrayDeque::new));
        Integer person = persons.poll();
        queue.remove(person);
        return person;
    }
    
    public boolean isEmptyQueue(Direction callElevatorButton) {
        boolean wasElevatorCalled = callElevatorButton.equals(Direction.UP)
                ? queue.stream().filter(i -> i > level).toArray().length == 0
                : queue.stream().filter(i -> i < level).toArray().length == 0;
        return wasElevatorCalled || queue.isEmpty();
    }
    
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
        name = "Level " + level;
    }
    
    public Queue<Integer> getQueue() {
        return queue;
    }
    
    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }
}
