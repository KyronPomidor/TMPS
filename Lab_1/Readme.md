# Laboratory Work Report: SOLID Principles Implementation

## Overview

This laboratory work demonstrates the application of Object-Oriented Design Principles (SOLID) in Java through a motor vehicle and engine management system.

---

## SOLID Principles & Code References

### 1. Single Responsibility Principle (SRP)

* **Theory:** A class should have one, and only one, reason to change, meaning its responsibility should be completely encapsulated by the class.
* **Implementation:** `AtkinsonEngine` handles engine noise and gasoline propulsion logic exclusively. It does not track vehicle state such as fuel levels or model metadata.

```java
// AtkinsonEngine.java
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
```

---

### 2. Interface Segregation Principle (ISP)

* **Theory:** Clients should not be forced to depend upon interfaces that they do not use. Interfaces should be fine-grained and specific to their domain.
* **Implementation:** Rather than creating a single massive `Vehicle` or `Engine` interface containing both charging and fueling logic, interfaces are segregated into focused roles (`ElectricVehicle` vs. `RefuelableVehicle`, and `ElectricEngine` vs. `FuelEngine`).

```java
// Segregated Engine Interfaces
public interface Engine {
    String getEngineName();
    String makeSound();
}

public interface ElectricEngine extends Engine {
    String driveUsingElectricity();
}

public interface FuelEngine extends Engine {
    String driveUsingGasoline();
}
```

```java
// Segregated Vehicle Interfaces
public interface Vehicle {
    String getModelName();
    void startSystem();
}

public interface ElectricVehicle extends Vehicle {
    void chargeBattery(int targetPercentage);
    int getBatteryLevel();
}

public interface RefuelableVehicle extends Vehicle {
    void fuelTank(int targetLevel);
    int getTankLevel();
}
```

---

### 3. Dependency Inversion Principle (DIP)

* **Theory:** High-level modules should not depend on low-level modules; both should depend on abstractions. Abstractions should not depend on details.
* **Implementation:** Concrete implementations like `Model3` and `TeslaEngine` depend directly on abstractions (`ElectricVehicle`, `ElectricEngine`, `Vehicle`). High-level consumers interact with these objects strictly through their interface contracts.

```java
// Model3.java - High-level car implementation relying on ElectricVehicle abstraction
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

    @Override
    public void chargeBattery(int targetPercentage) {
        if (this.batteryLevel + targetPercentage > 100) {
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
```

---

## Code Base Mapping

| File | Type | Applied Principle | Responsibility |
| :--- | :--- | :--- | :--- |
| `Vehicle.java` | Interface | **ISP** | Base abstraction for vehicle identification and startup |
| `ElectricVehicle.java` | Interface | **ISP**, **DIP** | Abstraction defining EV charging capabilities |
| `RefuelableVehicle.java` | Interface | **ISP**, **DIP** | Abstraction defining fuel tank capabilities |
| `Engine.java` | Interface | **ISP** | Base abstraction for engine acoustics and metadata |
| `ElectricEngine.java` | Interface | **ISP**, **DIP** | Abstraction for electric propulsion mechanics |
| `FuelEngine.java` | Interface | **ISP**, **DIP** | Abstraction for gasoline propulsion mechanics |
| `TeslaEngine.java` | Class | **SRP**, **DIP** | Implementation of electric engine mechanics |
| `AtkinsonEngine.java` | Class | **SRP**, **DIP** | Implementation of gasoline engine mechanics |
| `Model3.java` | Class | **SRP**, **DIP** | Implementation of electric vehicle state and charging logic |
| `Toyota.java` | Class | **SRP**, **DIP** | Implementation of combustion vehicle state and fueling logic |
