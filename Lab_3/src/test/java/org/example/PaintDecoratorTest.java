package org.example;

import org.junit.jupiter.api.Test;

import static org.example.TestUtil.capture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Decorator: paint
class PaintDecoratorTest {

    @Test
    void getValue_adds500() {
        assertEquals(1500, new PaintDecorator(new Car("1", "BMW", 1000)).getValue());
    }

    @Test
    void isACarDecoratorAndGarageItem() {
        GarageItem item = new PaintDecorator(new Car("1", "BMW", 1000));
        assertTrue(item instanceof CarDecorator);
    }

    @Test
    void show_printsWrappedCarThenPaintLine() {
        GarageItem item = new PaintDecorator(new Car("MD123", "BMW", 1000));
        String[] lines = capture(() -> item.show("")).split("\n");
        assertEquals("- BMW [MD123] = 1000.0 EUR", lines[0]); // line shows base price
        assertEquals("    + custom paint (+500)", lines[1]);
    }

    @Test
    void doesNotChangeWrappedCar() {
        Car original = new Car("1", "BMW", 1000);
        new PaintDecorator(original);
        assertEquals(1000, original.getValue());
    }

    @Test
    void worksInsideSection() {
        Section section = new Section("A");
        section.add(new PaintDecorator(new Car("1", "BMW", 1000)));
        section.add(new Car("2", "Audi", 2000));
        assertEquals(3500, section.getValue());
    }
}
