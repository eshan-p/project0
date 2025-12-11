package com.example.util;

import java.util.Scanner;


public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println("Invalid input. Please enter a string.");
            }
        }
    }
}
