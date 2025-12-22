package com.example;

import com.example.controller.TeamController;
import com.example.controller.GameController;
import com.example.controller.PlayerController;
import com.example.util.InputHandler;

public class Main {
    public static void main(String[] args) {
        TeamController teamController = new TeamController();
        PlayerController playerController = new PlayerController();
        GameController gameController = new GameController();

        System.out.println();
        System.out.println("----- NBA Database & Game Simulation -----");

        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("[1.] Team Options");
            System.out.println("[2.] Player Options");
            System.out.println("[3.] Game Options");
            System.out.println("[0.] Exit Application");
            System.out.println();

            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> teamController.handleInput();
                case 2 -> playerController.handleInput();
                case 3 -> gameController.handleInput();
                case 0 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        }
        System.out.println();
    }
}