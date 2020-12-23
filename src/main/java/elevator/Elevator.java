package elevator;

import building.Building;

import java.util.ArrayList;
import java.util.List;

public class Elevator extends AbstractElevator {
    
    public Elevator() {
    }
    
    public Elevator(Building building) {
        super(building);
        setDirection(Direction.UP);
    }

    @Override
    public void pickUp(Integer... persons) {
        int index = 0;
        while (hasSpace() && index < persons.length) {
            if(getFloorPosition() == persons[index]) {
                index++;
                continue;
            }
            addToContainment(persons[index++]);
        }
    }

    @Override
    public List<Integer> lifted() {
        List<Integer> liftedPersons = new ArrayList<>();
        while (containsPerson(getFloorPosition())) {
            liftedPersons.add(getFloorPosition());
            getContainment().remove(Integer.valueOf(getFloorPosition()));
        }
        return liftedPersons;
    }
}
