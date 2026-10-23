package org.example;

import java.util.ArrayList;
import java.util.List;

// Object pool

public class MechanicPool {

    private final List<Mechanic> available = new ArrayList<>();
    private final List<Mechanic> busy = new ArrayList<>();

    public MechanicPool(String... names) {
        for (int i = 0; i < names.length; i++) {
            available.add(new Mechanic(i + 1, names[i]));
        }
    }

    // Returns a free mechanic, or null if everyone is busy
    public Mechanic acquire() {
        if (available.isEmpty()) {
            return null;
        }

        Mechanic mechanic = available.remove(0);
        busy.add(mechanic);
        return mechanic;
    }

    public void release(Mechanic mechanic) {
        if (busy.remove(mechanic)) {
            available.add(mechanic);
        }
    }

    public int availableCount() {
        return available.size();
    }

    public int busyCount() {
        return busy.size();
    }
}
