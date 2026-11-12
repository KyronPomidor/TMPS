package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GarageFacade facade = new GarageFacade();

        while (true) {
            System.out.println("\n1 - Register car\n2 - Show garage\n3 - Total value\n0 - Exit");
            System.out.print("Choose: ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Plate number: ");
                    String plate = in.nextLine().trim();
                    System.out.print("Brand: ");
                    String brand = in.nextLine().trim();
                    System.out.print("Price (EUR): ");
                    double price = Double.parseDouble(in.nextLine().trim());
                    System.out.print("Garage section (e.g. A, B): ");
                    String section = in.nextLine().trim();
                    System.out.print("Add custom paint? (y/n): ");
                    boolean paint = in.nextLine().trim().equalsIgnoreCase("y");
                    System.out.print("Add engine tuning? (y/n): ");
                    boolean tuning = in.nextLine().trim().equalsIgnoreCase("y");

                    boolean ok = facade.registerCar(plate, brand, price, section, paint, tuning);
                    System.out.println(ok ? "Car registered!" : "Plate already registered.");
                }
                case "2" -> facade.showGarage();
                case "3" -> System.out.println("Total value: " + facade.totalValue() + " EUR");
                case "0" -> System.out.println("Bye!");
                default -> System.out.println("Invalid option.");
            }
        }
    }
}