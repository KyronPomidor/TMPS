package org.example.vehicles;

public interface RefuelableVehicle extends Vehicle {
    void fuelTank(int targetLevel);
    int getTankLevel();
}
