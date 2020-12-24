package building;

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
    
    public Integer pollPerson() {
        if (queue.isEmpty()) {
            return level;
        }
        return queue.poll();
    }
    
    public boolean isEmptyQueue() {
        return queue.isEmpty() || queue.size() == queue.stream()
                .filter(i -> i == level).toArray().length;
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
