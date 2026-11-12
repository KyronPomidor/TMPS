package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// FACADE
// One simple entry point that hides: register check, creating the car,
// decorating it, finding/creating the section and adding it to the garage.
class GarageFacade {
    private final Section garage = new Section("Garage");
    private final Map<String, Section> sections = new HashMap<>();
    private final Set<String> register = new HashSet<>();

    boolean registerCar(String plate, String brand, double price,
                        String sectionName, boolean paint, boolean tuning) {
        if (!register.add(plate)) return false;

        GarageItem car = new Car(plate, brand, price);
        if (paint) car = new PaintDecorator(car);
        if (tuning) car = new TuningDecorator(car);

        sections.computeIfAbsent(sectionName, n -> {
            Section s = new Section(n);
            garage.add(s);
            return s;
        }).add(car);
        return true;
    }

    void showGarage() {
        garage.show("");
    }

    double totalValue() {
        return garage.getValue();
    }
}
