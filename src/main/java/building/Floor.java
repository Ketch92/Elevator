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
    
    public boolean popFromQueue(Integer person) {
        if (person == null || !queue.contains(person)) {
            return false;
        }
        queue.remove(person);
        return true;
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
