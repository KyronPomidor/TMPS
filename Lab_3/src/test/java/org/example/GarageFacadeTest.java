package org.example;

import org.junit.jupiter.api.Test;

import static org.example.TestUtil.capture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Facade
class GarageFacadeTest {

    @Test
    void registerCar_newPlateSucceeds() {
        assertTrue(new GarageFacade().registerCar("MD123", "BMW", 10000, "A", false, false));
    }

    @Test
    void registerCar_duplicatePlateIsRejectedAndNotAdded() {
        GarageFacade facade = new GarageFacade();
        assertTrue(facade.registerCar("MD123", "BMW", 10000, "A", false, false));
        assertFalse(facade.registerCar("MD123", "Audi", 8000, "B", false, false));
        assertEquals(10000, facade.totalValue());
    }

    @Test
    void registerCar_appliesPaintOnly() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 10000, "A", true, false);
        assertEquals(10500, facade.totalValue());
    }

    @Test
    void registerCar_appliesTuningOnly() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 10000, "A", false, true);
        assertEquals(11200, facade.totalValue());
    }

    @Test
    void registerCar_appliesPaintAndTuning() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 10000, "A", true, true);
        assertEquals(11700, facade.totalValue());
    }

    @Test
    void totalValue_emptyGarageIsZero() {
        assertEquals(0, new GarageFacade().totalValue());
    }

    @Test
    void totalValue_coversAllSections() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 10000, "A", true, false);   // 10500
        facade.registerCar("2", "Audi", 8000, "A", false, true);   // 9200
        facade.registerCar("3", "Fiat", 3000, "B", false, false);  // 3000
        assertEquals(22700, facade.totalValue());
    }

    @Test
    void showGarage_reusesExistingSection() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 100, "A", false, false);
        facade.registerCar("2", "Audi", 200, "A", false, false);

        String out = capture(facade::showGarage);
        assertEquals(1, out.lines().filter(l -> l.contains("[A]")).count());
    }

    @Test
    void showGarage_createsSeparateSectionPerName() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("1", "BMW", 100, "A", false, false);
        facade.registerCar("2", "Audi", 200, "B", false, false);

        String out = capture(facade::showGarage);
        assertTrue(out.contains("[A]"));
        assertTrue(out.contains("[B]"));
    }

    @Test
    void showGarage_listsRegisteredCarWithExtras() {
        GarageFacade facade = new GarageFacade();
        facade.registerCar("MD123", "BMW", 10000, "A", true, false);

        String out = capture(facade::showGarage);
        assertTrue(out.contains("[Garage]"));
        assertTrue(out.contains("BMW [MD123]"));
        assertTrue(out.contains("custom paint"));
    }
}
