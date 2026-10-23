package org.example;

public class Mechanic {
    private final int id;
    private final String name;

    public Mechanic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Mechanic with id: " + id + " (" + name + ")";
    }
}
