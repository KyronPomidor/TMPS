package org.example;

import org.junit.jupiter.api.Test;

import static org.example.TestUtil.capture;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Decorator: tuning (and stacking with paint)
class TuningDecoratorTest {

    @Test
    void getValue_adds1200() {
        assertEquals(2200, new TuningDecorator(new Car("1", "BMW", 1000)).getValue());
    }

    @Test
    void show_printsWrappedCarThenTuningLine() {
        GarageItem item = new TuningDecorator(new Car("MD123", "BMW", 1000));
        String[] lines = capture(() -> item.show("")).split("\n");
        assertEquals("- BMW [MD123] = 1000.0 EUR", lines[0]);
        assertEquals("    + engine tuning (+1200)", lines[1]);
    }

    @Test
    void stacksWithPaint() {
        GarageItem car = new Car("1", "BMW", 1000);
        car = new PaintDecorator(car);
        car = new TuningDecorator(car);
        assertEquals(2700, car.getValue());
    }

    @Test
    void stackingOrderDoesNotChangeTotal() {
        GarageItem a = new TuningDecorator(new PaintDecorator(new Car("1", "X", 1000)));
        GarageItem b = new PaintDecorator(new TuningDecorator(new Car("1", "X", 1000)));
        assertEquals(a.getValue(), b.getValue());
    }

    @Test
    void stackedShow_printsBothExtrasInWrapOrder() {
        GarageItem car = new TuningDecorator(new PaintDecorator(new Car("1", "BMW", 1000)));
        String[] lines = capture(() -> car.show("")).split("\n");
        assertEquals("    + custom paint (+500)", lines[1]);
        assertEquals("    + engine tuning (+1200)", lines[2]);
    }
}
