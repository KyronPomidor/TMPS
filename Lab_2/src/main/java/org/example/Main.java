package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Object Pool
        MechanicPool pool = new MechanicPool("Alex", "John");

        List<Job> activeJobs = new ArrayList<>();

        int choice = 0;
        while (choice != 4) {
            System.out.println("\n=== Car Service ===");
            System.out.println("1. New service order");
            System.out.println("2. Complete a job (frees the mechanic)");
            System.out.println("3. Show garage status");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("----------------------------------------");

            if (choice == 1) {
                Mechanic mechanic = pool.acquire();
                if (mechanic == null) {
                    System.out.println("All mechanics are busy. Complete a job first.");
                    continue;
                }

                System.out.println("Service: 1) Oil Change  2) Tire Rotation  3) Brake Inspection");
                System.out.print("Enter service: ");
                int s = Integer.parseInt(scanner.nextLine().trim());
                ServiceType type = ServiceType.values()[Math.clamp(s - 1, 0, 2)];

                System.out.print("Customer name: ");
                String name = scanner.nextLine();
                System.out.print("Car model: ");
                String car = scanner.nextLine();
                System.out.print("Plate number: ");
                String plate = scanner.nextLine();
                System.out.print("Urgent? (y/n): ");
                boolean urgent = scanner.nextLine().trim().equalsIgnoreCase("y");
                System.out.print("Notes: ");
                String notes = scanner.nextLine();

                // Builder
                ServiceOrder order = new ServiceOrder.Builder()
                        .customerName(name)
                        .carModel(car)
                        .plateNumber(plate)
                        .urgent(urgent)
                        .notes(notes)
                        .build();

                // Factory
                CarService service = ServiceFactory.create(type);

                activeJobs.add(new Job(order, service, mechanic));
                System.out.println(order);
                System.out.println(mechanic + " started: " + service.getName());
                System.out.println(service.perform());
                System.out.println("Price: " + service.getPrice());

            }
            else if (choice == 2) {
                if (activeJobs.isEmpty()) {
                    System.out.println("No active jobs.");
                    continue;
                }
                Job job = activeJobs.remove(0);
                pool.release(job.mechanic);
                System.out.println(job.mechanic + " finished " + job.service.getName()
                        + " for " + job.order.getCustomerName() + ".");

            }
            else if (choice == 3) {
                System.out.println("Free mechanics: " + pool.availableCount());
                System.out.println("Busy mechanics: " + pool.busyCount());
                for (Job job : activeJobs) {
                    System.out.println("- " + job.mechanic.getName() + " -> "
                            + job.service.getName() + " (" + job.order.getCarModel() + ")");
                }

            }
            else if (choice != 4) {
                System.out.println("Invalid selection.");
            }
        }
    }
}
