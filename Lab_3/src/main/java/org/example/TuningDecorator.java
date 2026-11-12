package org.example;

class TuningDecorator extends CarDecorator {
    TuningDecorator(GarageItem car) {
        super(car);
    }

    public void show(String indent) {
        car.show(indent);
        System.out.println(indent + "    + engine tuning (+1200)");
    }

    public double getValue() {
        return car.getValue() + 1200;
    }
}
