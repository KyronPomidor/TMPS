package org.example;

class PaintDecorator extends CarDecorator {
    PaintDecorator(GarageItem car) {
        super(car);
    }

    public void show(String indent) {
        car.show(indent);
        System.out.println(indent + "    + custom paint (+500)");
    }

    public double getValue() {
        return car.getValue() + 500;
    }
}
