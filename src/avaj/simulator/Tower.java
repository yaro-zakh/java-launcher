package avaj.simulator;

import avaj.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
    	List<Flyable> tmp = new ArrayList<>(this.observers);
        for (Flyable air:tmp) {
            air.updateConditions();
        }
    }
}
