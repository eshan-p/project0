package com.example;

import java.util.Scanner;

import com.example.repository.DAO.TeamDAO;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TeamDAO teamDAO = new TeamDAO();

        while (true) {
            /*System.out.println("Select an option: ");
            System.out.println("1. Add Team");
            System.out.println("2. Add Player");
            System.out.println("3. Add Box Score");
            System.out.println("4. Add Game");
            System.out.println("5. View Team");
            System.out.println("6. View Player");
            System.out.println("7. View Box Score");
            System.out.println("8. View Game");
            System.out.println("9. Exit");*/

            System.out.println("Select an option: ");
            System.out.println("1. Add Team  | 2.  Add Player   | 3.  Add Box Score   | 4.  Add Game");
            System.out.println("5. View Team | 6.  View Player  | 7.  View Box Score  | 8.  View Game");
            System.out.println("9. Edit Team | 10. Edit Player  | 11. Edit Box Score  | 12. Edit Game");
            System.out.println("13. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) { // Add Team

                //System.out.println("Add Team.");
                System.out.print("Enter Team Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Team City: ");
                String city = scanner.nextLine();
                try {
                    var teamEntity = new com.example.repository.entities.TeamEntity();
                    teamEntity.setName(name);
                    teamEntity.setCity(city);

                    Integer generatedId = teamDAO.create(teamEntity);
                    System.out.println("Team created with ID: " + generatedId);
                } catch (Exception e) {
                    System.out.println("Error creating team: " + e.getMessage());
                }

            } else if (choice == 2) { // Add Player

                System.out.println("Add Player.");

            } else if (choice == 3) { // Add Box Score

                System.out.println("Add Box Score.");

            } else if (choice == 4) { // Add Game

                System.out.println("Add Game.");

            } else if (choice == 5) { // View Team

                //System.out.println("View Team.");
                System.out.print("Enter Team ID to view: ");
                Integer teamId = Integer.parseInt(scanner.nextLine());
                try {
                    var teamOpt = teamDAO.findById(teamId);
                    if (teamOpt.isPresent()) {
                        System.out.println("Team Details: " + teamOpt.get());
                    } else {
                        System.out.println("Team with ID " + teamId + " not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Error retrieving team: " + e.getMessage());
                }


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

        System.out.println();
        System.out.println();
        scanner.close();
    }
}