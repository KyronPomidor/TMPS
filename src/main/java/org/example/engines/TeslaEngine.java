package org.example.engines;

public class TeslaEngine implements ElectricEngine {
    String name = "Asynchronous Motor";

    @Override
    public String driveUsingElectricity() {
        return "Driving using electricity ⚡\uFE0F";
    }

    @Override
    public String getEngineName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "... quiet";
    }
}
