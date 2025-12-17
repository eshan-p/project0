package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.repository.entities.TeamEntity;
import com.example.service.TeamService;
import com.example.service.model.Team;
import com.example.util.InputHandler;

public class TeamController {
    private final TeamService teamService = new TeamService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addTeam();
                case 2 -> searchTeamById();
                case 3 -> searchTeamByMascot();
                case 4 -> getAllTeams();
                case 5 -> updateTeam();
                case 6 -> deleteTeam();
                case 0 -> {
                    System.out.println("Exiting Team Management.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }

            System.out.println();
        }
    }

    private void printMenu(){
        System.out.println();
        System.out.println("Select an option: ");
        System.out.println("[1.] Add Team");
        System.out.println("[2.] Find Team by ID");
        System.out.println("[3.] Find Team by Mascot");
        System.out.println("[4.] Get All Teams");
        System.out.println("[5.] Update Team Info");
        System.out.println("[6.] Delete Team");
        System.out.println("[0.] Exit");
        System.out.println();
    } 

    private void addTeam(){
        String city = InputHandler.getStringInput("Enter Team City: ");
        String mascot = InputHandler.getStringInput("Enter Team Mascot: ");
        
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setCity(city);
        teamEntity.setMascot(mascot);
        Integer generatedId = teamService.createEntity(teamEntity);
        
        if (generatedId == -1) {
            System.out.println("Error creating team.");
        } else {
            System.out.println("Team created with ID: " + generatedId);
        }
    }
        
    private void searchTeamById(){
        Integer teamId = InputHandler.getIntInput("Enter Team ID to search: ");
        Optional<Team> team = teamService.getModelById(teamId);

        if (team.isPresent()) {
            System.out.println("Team Details: " + team.get());
        } else {
            System.out.println("Team not found.");
        }
    }

    private void searchTeamByMascot(){
        String mascot = InputHandler.getStringInput("Enter Team Mascot to search: ");
        Optional<Team> team = teamService.getModelByTeamMascot(mascot);

        if (team.isPresent()) {
            System.out.println("Team Details: " + team.get());
        } else {
            System.out.println("Team not found.");
        }
    }

    private void getAllTeams(){
        List<Team> teams = teamService.getAllModels();
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    private void updateTeam(){
        Integer teamId = InputHandler.getIntInput("Enter Team ID to update: ");
        Optional<Team> team = teamService.getModelById(teamId);

        if (team.isPresent()){
            String mascot = InputHandler.getStringInput("Enter new Mascot: ");
            String city = InputHandler.getStringInput("Enter new City: ");

            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setTeam_id(teamId);
            teamEntity.setMascot(mascot);
            teamEntity.setCity(city);

            teamService.updateEntity(teamId, teamEntity);
        }
    }

    private void deleteTeam(){
        Integer teamId = InputHandler.getIntInput("Enter Team ID to delete: ");
        Optional<Team> team = teamService.getModelById(teamId);

        if (team.isPresent()){
            teamService.deleteEntity(teamId);
        }
    }
}
