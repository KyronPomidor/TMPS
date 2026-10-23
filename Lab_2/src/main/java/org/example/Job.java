package org.example;

public class Job {
    final ServiceOrder order;
    final CarService service;
    final Mechanic mechanic;

    Job(ServiceOrder order, CarService service, Mechanic mechanic) {
        this.order = order;
        this.service = service;
        this.mechanic = mechanic;
    }
}
