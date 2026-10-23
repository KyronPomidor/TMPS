package org.example;

// Factory

public class ServiceFactory {

    private static class OilChange implements CarService {
        public String getName() {
            return "Oil Change";
        }

        public double getPrice() {
            return 60.0;
        }

        public String perform() {
            return "Draining old oil and filling with fresh oil...";
        }
    }

    private static class TireRotation implements CarService {
        public String getName() {
            return "Tire Rotation";
        }

        public double getPrice() {
            return 40.0;
        }

        public String perform() {
            return "Rotating all four tires and checking pressure...";
        }
    }

    private static class BrakeInspection implements CarService {
        public String getName() {
            return "Brake Inspection";
        }

        public double getPrice() {
            return 80.0;
        }

        public String perform() {
            return "Checking brake pads, discs and fluid...";
        }
    }

    public static CarService create(ServiceType type) {
        if (type == ServiceType.OIL_CHANGE) {
            return new OilChange();
        }
        else if (type == ServiceType.TIRE_ROTATION) {
            return new TireRotation();
        }
        else if (type == ServiceType.BRAKE_INSPECTION) {
            return new BrakeInspection();
        }
        else {
            throw new IllegalArgumentException("Unknown service type: " + type);
        }
    }
}
