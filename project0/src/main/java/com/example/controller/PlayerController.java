package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.repository.entities.PlayerEntity;
import com.example.service.BoxScoreService;
import com.example.service.PlayerService;
import com.example.service.TeamService;
import com.example.service.model.BoxScore;
import com.example.service.model.Player;
import com.example.service.model.Team;
import com.example.util.InputHandler;

public class PlayerController {
    private final PlayerService playerService = new PlayerService();
    private final TeamService teamService = new TeamService();
    private final BoxScoreService boxScoreService = new BoxScoreService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> addPlayer();
                //case 2 -> searchPlayerById();
                case 2 -> searchPlayerByName();
                case 3 -> searchPlayersByTeamName();
                case 4 -> getPlayerRecentGames();
                case 5 -> updatePlayer();
                case 6 -> deletePlayer();
                case 0 -> {
                    System.out.println("Exiting player management.");
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
        System.out.println("[1.] Add New Player");
        // System.out.println("[2.] Find Player Info (by ID)");
        System.out.println("[2.] Find a Player");
        System.out.println("[3.] Find All Players on Team");
        System.out.println("[4.] View Player's Recent Games");
        System.out.println("[5.] Update Player Info");
        System.out.println("[6.] Delete Player");
        System.out.println("[0.] Back to Main Menu");
        System.out.println();
    }

    private void addPlayer(){
        String teamName = InputHandler.getStringInput("Enter team name: "); 

        Optional<Team> team = teamService.getModelByTeamMascot(teamName);
        if (team.isPresent()){
            String firstName = InputHandler.getStringInput("Enter first name: ");
            String lastName = InputHandler.getStringInput("Enter last name: ");
            String position = InputHandler.getStringInput("Enter position: ");
            String country = InputHandler.getStringInput("Enter country: ");
            Integer jerseyNum = InputHandler.getIntInput("Enter jersey number: ");

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

    /* private void searchPlayerById(){
        Integer playerId = InputHandler.getIntInput("Enter Player ID to search: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()) {
            System.out.println("Player Details: " + player.get());
        } else {
            System.out.println("Player not found.");
        }
    } */

    private void searchPlayerByName() {
        String firstName = InputHandler.getStringInput("Enter player's first name: ");
        String lastName = InputHandler.getStringInput("Enter player's last name: ");
        Optional<Player> player = playerService.getModelByName(firstName, lastName);

        if (player.isPresent()) {
            System.out.println("Player Details: " + player.get());
        } else {
            System.out.println("Player not found.");
        }
    }

    /* private void searchPlayersByTeamId(){
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
    } */

    private void searchPlayersByTeamName(){
        String teamMascot = InputHandler.getStringInput("Enter team to search: ");
        Optional<Team> team = teamService.getModelByTeamMascot(teamMascot);

        if (team.isPresent()) {
            List<Player> players = playerService.getAllModelsByTeamName(teamMascot);
            for (Player player : players){
                System.out.println(player);
            }
        } else {
            System.out.println("Team not found.");
        }
    }

    private void getPlayerRecentGames(){
        String firstName = InputHandler.getStringInput("Enter player's first name: ");
        String lastName = InputHandler.getStringInput("Enter player's last name: ");
        Optional<Player> player = playerService.getModelByName(firstName, lastName);

        if (player.isPresent()) {
            List<BoxScore> boxScores = boxScoreService.getAllModelsById(player.get().getId());
            for (BoxScore boxScore : boxScores){
                System.out.println(boxScore);
            }
        } else {
            System.out.println("Player not found.");
        }
    }

    private void updatePlayer(){
        Integer playerId = InputHandler.getIntInput("Enter player ID to update: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()){
            Integer teamId = InputHandler.getIntInput("Enter new team ID: ");
            Integer jerseyNum = InputHandler.getIntInput("Enter jersey number: ");

            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(playerId);
            playerEntity.setTeamId(teamId);
            playerEntity.setJerseyNumber(jerseyNum);
            
            playerService.updateEntity(playerId, playerEntity);
        }
    }

    private void deletePlayer(){
        Integer playerId = InputHandler.getIntInput("Enter player ID to delete: ");
        Optional<Player> player = playerService.getModelById(playerId);

        if (player.isPresent()){
            playerService.deleteEntity(playerId);
        }
    }
}
