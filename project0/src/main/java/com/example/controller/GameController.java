package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.service.GameService;
import com.example.service.TeamService;
import com.example.service.model.Game;
import com.example.service.model.Team;
import com.example.util.InputHandler;

public class GameController {
    private final GameService gameService = new GameService();
    private final TeamService teamService = new TeamService();

    public void handleInput(){
        boolean running = true;
        while(running){
            printMenu();
            int choice = InputHandler.getIntInput("Enter your choice: ");
            switch(choice){
                case 1 -> simulateGame();
                case 2 -> searchGameById();
                case 3 -> searchGamesByTeam();
                case 0 -> {
                    System.out.println("Exiting game management.");
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
        System.out.println("[1.] Simulate a Game");
        System.out.println("[2.] Find Game Outcome by ID");
        System.out.println("[3.] Find All Games Outcomes for Team");
        System.out.println("[0.] Back to Main Menu");
        System.out.println();
    }

    private void simulateGame(){
        String homeTeam = InputHandler.getStringInput("Enter home team: ");
        String awayTeam = InputHandler.getStringInput("Enter away team: ");
        Optional<Team> homeTeamOpt = teamService.getModelByTeamMascot(homeTeam);
        Optional<Team> awayTeamOpt = teamService.getModelByTeamMascot(awayTeam);

        if (homeTeamOpt.isEmpty() || awayTeamOpt.isEmpty()){
            System.out.println("Team(s) not found.");
            return;
        } else {
            Optional<Game> result = gameService.simulateGame(homeTeamOpt.get().getTeam_id(), awayTeamOpt.get().getTeam_id());
            System.out.println(result.get());
        }

    }

    private void searchGameById(){
        Integer gameId = InputHandler.getIntInput("Enter game ID to search: ");
        Optional<Game> game = gameService.getModelById(gameId);

        if (game.isPresent()){
            System.out.println(game.get());
        } else {
            System.out.println("Game not found.");
        }
    }

    private void searchGamesByTeam(){
        String mascot = InputHandler.getStringInput("Enter team to search: ");
        Optional<Team> team = teamService.getModelByTeamMascot(mascot);

        if (team.isPresent()){
            List<Game> games = gameService.getAllModelsByTeamId(team.get().getTeam_id());
            for (Game game : games){
                System.out.println(game);
            }
        } else {
            System.out.println("Team not found.");
        }
    }
}
