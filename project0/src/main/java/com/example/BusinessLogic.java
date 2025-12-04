package com.example;

import com.example.Classes.Team;

public class BusinessLogic {
    static void serializeTeam(String filename, String city, String name) {
        try (java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(filename))) {
            Team team = new Team(city, name);
            out.writeObject(team);
            System.out.println("Team serialized: " + team);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static void deserializeTeam(String filename) {
        try (java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(filename))) {
            Team team = (Team) in.readObject();
            System.out.println("Team deserialized: " + team);
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}