package org.example.engines;

public class AtkinsonEngine implements FuelEngine {
    String name = "Atkinson cycle engine";

    @Override
    public String driveUsingGasoline() {
        return "Driving using gasoline ⛽";
    }

    @Override
    public String getEngineName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "brbrbrbrbrbrbrbrbrbr";
    }
}
