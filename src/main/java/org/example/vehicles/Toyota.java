package org.example.vehicles;

public class Toyota implements RefuelableVehicle {
    private final String modelName = "Prius";
    private int fuelLiters = 45;

    @Override
    public void fuelTank(int targetLevel) {
        if (this.fuelLiters + fuelLiters > 50) {
            System.out.println("The car can have max 50L of fuel");
            return;
        }

        this.fuelLiters += fuelLiters;
    }

    @Override
    public int getTankLevel() {
        return fuelLiters;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void startSystem() {
        System.out.println(modelName + ": Ready to drive.");
    }
}
