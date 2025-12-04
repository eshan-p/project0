package com.example;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String filename = "team.ser";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option: ");
            System.out.println("1. Add Team");
            System.out.println("2. Add Player");
            System.out.println("3. Add Box Score");
            System.out.println("4. Add Game");
            System.out.println("5. View Team");
            System.out.println("6. View Player");
            System.out.println("7. View Box Score");
            System.out.println("8. View Game");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) { // Add Team

                System.out.println("Enter team city: ");
                String city = scanner.nextLine();
                System.out.println("Enter team name: ");
                String name = scanner.nextLine();
                BusinessLogic.serializeTeam(filename, city, name);
                System.out.println("Team " + city + " " + name + " added.");

            } else if (choice == 2) { // Add Player

                System.out.println("Add Player.");

            } else if (choice == 3) { // Add Box Score

                System.out.println("Add Box Score.");

            } else if (choice == 4) { // Add Game

                System.out.println("Add Game.");

            } else if (choice == 5) { // View Team

                // System.out.println("View Team.");
                System.out.println("Which team do you want to view?");
                scanner.nextLine(); // consume newline
                BusinessLogic.deserializeTeam(filename);

            } else if (choice == 6) { // View Player

                System.out.println("View Player.");

            } else if (choice == 7) { // View Box Score

                System.out.println("View Box Score.");

            } else if (choice == 8) { // View Game

                System.out.println("View Game.");

            } else if (choice == 9) { // Exit

                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid option. Please try again.");
            }   
        }
        scanner.close();
    }
}