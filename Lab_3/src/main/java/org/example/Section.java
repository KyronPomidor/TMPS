package org.example;

import java.util.ArrayList;
import java.util.List;

// Composite: a section can contain cars
class Section implements GarageItem {
    private final String name;
    private final List<GarageItem> items = new ArrayList<>();

    Section(String name) {
        this.name = name;
    }

    void add(GarageItem item) {
        items.add(item);
    }

    public void show(String indent) {
        System.out.println(indent + "[" + name + "] total = " + getValue() + " EUR");
        for (GarageItem item : items) item.show(indent + "   ");
    }

    public double getValue() {
        double sum = 0;
        for (GarageItem item : items) sum += item.getValue();
        return sum;
    }
}
