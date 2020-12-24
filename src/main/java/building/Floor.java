package building;

import java.util.Queue;

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
        if (person == null) {
            return false;
        }
        queue.add(person);
        return true;
    }
    
    public Integer pollPerson() {
        return queue.poll();
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
