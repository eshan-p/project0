package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.repository.entities.PlayerEntity;
import com.example.service.PlayerService;
import com.example.service.TeamService;
import com.example.service.model.Player;
import com.example.service.model.Team;
import com.example.util.InputHandler;

public class PlayerController {
    private final PlayerService playerService = new PlayerService();
    private final TeamService teamService = new TeamService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addPlayer();
                case 2 -> searchPlayerById();
                case 3 -> searchPlayersByTeamId();
                case 4 -> updatePlayer();
                case 5 -> deletePlayer();
                case 0 -> {
                    System.out.println("Exiting Player Management.");
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
        System.out.println("[1.] Add Player");
        System.out.println("[2.] Find Player by ID");
        System.out.println("[3.] Find Players by Team");
        System.out.println("[4.] Update Player Info");
        System.out.println("[5.] Delete Player");
        System.out.println("[0.] Exit");
        System.out.println();
    }

    private void addPlayer(){
        String teamName = InputHandler.getStringInput("Enter Team Name: "); 

        Optional<Team> team = teamService.getModelByTeamMascot(teamName);
        if (team.isPresent()){
            String firstName = InputHandler.getStringInput("Enter First Name: ");
            String lastName = InputHandler.getStringInput("Enter Last Name: ");
            String position = InputHandler.getStringInput("Enter Position: ");
            String country = InputHandler.getStringInput("Enter Country: ");
            Integer jerseyNum = InputHandler.getIntInput("Enter Jersey Number: ");

            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setTeamId(team.get().getTeam_id());
            playerEntity.setFirstName(firstName);
            playerEntity.setLastName(lastName);
            playerEntity.setPosition(position);
            playerEntity.setCountry(country);
            playerEntity.setJerseyNumber(jerseyNum);

            Integer generatedId = playerService.createEntity(playerEntity);

            if (generatedId == -1) {
            System.out.println("Error creating player.");
            } else {
                System.out.println("Player created with ID: " + generatedId);
            }

        } else {
            System.out.println("Team name is invalid.");
        }
        
    }

    private void searchPlayerById(){
        Integer playerId = InputHandler.getIntInput("Enter Player ID to search: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()) {
            System.out.println("Player Details: " + player.get());
        } else {
            System.out.println("Player not found.");
        }
    }

    private void searchPlayersByTeamId(){
        Integer teamId = InputHandler.getIntInput("Enter Team ID to search: ");
        Optional<Team> team = teamService.getModelById(teamId);

        if (team.isPresent()) {
            List<Player> players = playerService.getAllModelsByTeamId(teamId);
            for (Player player : players){
                System.out.println(player);
            }
        } else {
            System.out.println("Team not found.");
        }
    }

    private void updatePlayer(){
        Integer playerId = InputHandler.getIntInput("Enter Player ID to update: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()){
            Integer teamId = InputHandler.getIntInput("Enter New Team ID: ");
            Integer jerseyNum = InputHandler.getIntInput("Enter Jersey Number: ");

            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(playerId);
            playerEntity.setTeamId(teamId);
            playerEntity.setJerseyNumber(jerseyNum);
            
            playerService.updateEntity(playerId, playerEntity);
        }
    }

    private void deletePlayer(){
        Integer playerId = InputHandler.getIntInput("Enter Player ID to delete: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()){
            playerService.deleteEntity(playerId);
        }
    }
}
