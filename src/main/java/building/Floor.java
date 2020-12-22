package building;

import java.util.List;

public class Floor {
    private String name;
    private int level;
    private List<Integer> queue;

    public Floor () {

    }

    public Floor(int level, List<Integer> queue) {
        this.queue = queue;
        this.level = level;
        name = "Level " + level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Integer> getQueue() {
        return queue;
    }

    public void setQueue(List<Integer> queue) {
        this.queue = queue;
    }
}
