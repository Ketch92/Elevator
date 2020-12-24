package elevator;

import building.Building;
import elevator.AbstractElevator.AbstractElevator;
import java.util.ArrayList;
import java.util.List;

public class Elevator extends AbstractElevator {
    
    
    
    @Override
    public void pickUp(Integer person) {
        if (hasSpace() && person != getFloorPosition()) {
            addToContainment(person);
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
