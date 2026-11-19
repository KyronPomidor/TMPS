package org.example;

import org.junit.jupiter.api.Test;

import static org.example.TestUtil.capture;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Composite: composite node
class SectionTest {

    @Test
    void getValue_emptySectionIsZero() {
        assertEquals(0, new Section("A").getValue());
    }

    @Test
    void getValue_sumsItsCars() {
        Section section = new Section("A");
        section.add(new Car("MD123", "BMW", 10000));
        section.add(new Car("MD456", "Audi", 8000));
        assertEquals(18000, section.getValue());
    }

    @Test
    void getValue_sumsNestedSectionsRecursively() {
        Section garage = new Section("Garage");
        Section a = new Section("A");
        Section b = new Section("B");
        a.add(new Car("1", "BMW", 100));
        b.add(new Car("2", "Audi", 200));
        garage.add(a);
        garage.add(b);
        assertEquals(300, garage.getValue());
    }

    @Test
    void section_canBeUsedAsGarageItem() {
        GarageItem item = new Section("A");
        assertEquals(0, item.getValue());
    }

    @Test
    void show_indentsChildrenDeeperThanParent() {
        Section garage = new Section("Garage");
        Section a = new Section("A");
        a.add(new Car("MD123", "BMW", 100));
        garage.add(a);

        String[] lines = capture(() -> garage.show("")).split("\n");

        assertEquals("[Garage] total = 100.0 EUR", lines[0]);
        assertEquals("   [A] total = 100.0 EUR", lines[1]);
        assertEquals("      - BMW [MD123] = 100.0 EUR", lines[2]);
    }

    @Test
    void show_emptySectionPrintsOnlyItsHeader() {
        assertEquals("[A] total = 0.0 EUR\n", capture(() -> new Section("A").show("")));
    }
}
