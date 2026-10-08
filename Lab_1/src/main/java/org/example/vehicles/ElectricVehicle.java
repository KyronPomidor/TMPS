package org.example.vehicles;

public interface ElectricVehicle extends Vehicle {
    void chargeBattery(int targetPercentage);
    int getBatteryLevel();
}
