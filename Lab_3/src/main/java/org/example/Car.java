package org.example;

// Leaf
class Car implements GarageItem {
    private final String plate, brand;
    private final double price;

    Car(String plate, String brand, double price) {
        this.plate = plate;
        this.brand = brand;
        this.price = price;
    }

    public void show(String indent) {
        System.out.println(indent + "- " + brand + " [" + plate + "] = " + getValue() + " EUR");
    }

    public double getValue() {
        return price;
    }
}
