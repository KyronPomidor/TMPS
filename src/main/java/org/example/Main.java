package org.example;

import org.example.engines.*;
import org.example.vehicles.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 3) {

            System.out.println("Select a vehicle type to inspect:");
            System.out.println("1. Tesla Model 3 (Electric)");
            System.out.println("2. Toyota Prius (Refuelable)");
            System.out.println("3. Exit the car management");
            System.out.print("Enter choice (1, 2 or 3): ");

            choice = scanner.nextInt();
            System.out.println("----------------------------------------");

            if (choice == 1) {
                Engine teslaEngine = new TeslaEngine();
                ElectricVehicle teslaModel3 = new Model3();

                printEngineDetails(teslaEngine);

                ElectricEngine electricEngine = new TeslaEngine();
                System.out.println("Drive Action: " + electricEngine.driveUsingElectricity());

                teslaModel3.startSystem();
                System.out.println("Model: " + teslaModel3.getModelName());
                System.out.println("Current Battery: " + teslaModel3.getBatteryLevel() + "%");

                System.out.print("Enter target battery percentage to charge to: ");
                int targetCharge = scanner.nextInt();
                teslaModel3.chargeBattery(targetCharge);
                System.out.println("Battery: " + teslaModel3.getBatteryLevel() + "%");
            } else if (choice == 2) {
                Engine fuelEngine = new AtkinsonEngine();
                RefuelableVehicle toyota = new Toyota();

                printEngineDetails(fuelEngine);

                FuelEngine gasEngine = new AtkinsonEngine();
                System.out.println("Drive Action: " + gasEngine.driveUsingGasoline());

                toyota.startSystem();
                System.out.println("Model: " + toyota.getModelName());
                System.out.println("Current Tank Level: " + toyota.getTankLevel() + "L");

                System.out.print("Enter fuel liters to fill: ");
                int fuelAmount = scanner.nextInt();
                toyota.fuelTank(fuelAmount);
                System.out.println("Tank Level: " + toyota.getTankLevel() + "L");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private static void printEngineDetails(Engine engine) {
        System.out.println("Engine Name: " + engine.getEngineName());
        System.out.println("Engine Sound: " + engine.makeSound());
    }
}