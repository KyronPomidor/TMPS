package org.example;

// DECORATOR
// Adds extra features to a car without changing the Car class
abstract class CarDecorator implements GarageItem {
    protected final GarageItem car;

    CarDecorator(GarageItem car) {
        this.car = car;
    }
}
