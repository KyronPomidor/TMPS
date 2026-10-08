package org.example.vehicles;

public class Model3 implements ElectricVehicle {
    private final String modelName = "Tesla Model 3";
    private int batteryLevel = 45;

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void startSystem() {
        System.out.println(modelName + ": Ready to drive.");
    }

    public void chargeBattery(int targetPercentage) {
        if (this.batteryLevel + batteryLevel > 100) {
            System.out.println("You can have 100% max battery");
            return;
        }

        this.batteryLevel += targetPercentage;
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }
}
