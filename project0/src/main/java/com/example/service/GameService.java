package com.example.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.DAO.GameDAO;
import com.example.repository.entities.GameEntity;
import com.example.service.interfaces.ServiceInterface;
import com.example.service.model.BoxScore;
import com.example.service.model.Game;
import com.example.service.model.Player;
import com.example.service.model.Team;

public class GameService implements ServiceInterface<GameEntity, Game>{
    private final GameDAO gameDAO = new GameDAO();
    
    private final TeamService teamService = new TeamService();
    private final PlayerService playerService = new PlayerService();
    private final BoxScoreService boxScoreService = new BoxScoreService();

    @Override
    public Integer createEntity(GameEntity entity){
        try {
            Integer id = gameDAO.create(entity);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override 
    public GameEntity updateEntity(Integer id, GameEntity gameEntity){
        // No implementation by design
        // User will only be allowed to create or view games
        throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
    }

    @Override
    public boolean deleteEntity(Integer id){
        // No implementation by design
        // User will only be allowed to create or view games
        throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
    }

    // ############ ENTITY METHODS ############

    @Override
    public Optional<GameEntity> getEntityById(Integer id){
        try {
            Optional<GameEntity> gameEntity =  gameDAO.findById(id);

            if (gameEntity.isEmpty()){
                throw new RuntimeException("Game not found.");
            }
            
            return gameEntity;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<GameEntity> getAllEntitiesByTeamId(Integer id){
        try {
            List<GameEntity> gameEntities = gameDAO.findAllByTeamId(id);
            return gameEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GameEntity> getAllEntities(){
        // No implementation by design
        // User will only be allowed to create or view games (individually or by team)
        throw new UnsupportedOperationException("Unimplemented method 'getAllEntities'");
    }

    // ############ CONVERSION METHODS ############

    @Override
    public Optional<Game> convertEntityToModel(GameEntity entity){
        try {
            Optional<Team> homeTeam = teamService.getModelById(entity.getHomeTeamId());
            Optional<Team> awayTeam = teamService.getModelById(entity.getAwayTeamId());

            if (homeTeam.isEmpty() || awayTeam.isEmpty()){
                throw new RuntimeException("Invalid team id(s)");
            }

            Game game = new Game();
            game.setId(entity.getId());
            game.setHomeTeam(homeTeam.get());
            game.setAwayTeam(awayTeam.get());
            game.setHomeTeamScore(entity.getHomeTeamScore());
            game.setAwayTeamScore(entity.getAwayTeamScore());
            game.setOvertime(entity.isOvertime());

            return Optional.of(game);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // ############ MODEL METHODS ############

    @Override
    public Optional<Game> getModelById(Integer id){
        Optional<GameEntity> gameEntity = getEntityById(id);

        try {
            if (gameEntity.isPresent()){
                Optional<Game> gameModel = convertEntityToModel(gameEntity.get());

                if (gameModel.isPresent()){
                    return gameModel;
                } else {
                    throw new RuntimeException("Conversion to Model failed.");
                }
            } else {
                throw new RuntimeException("GameEntity not found");
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Game> getAllModelsByTeamId(Integer teamId){
        List<GameEntity> gameEntities = getAllEntitiesByTeamId(teamId);
        List<Game> games = new ArrayList<>();

        for (GameEntity entity : gameEntities) {
            Optional<Game> gameOpt = convertEntityToModel(entity);
            if (gameOpt.isPresent()){
                games.add(gameOpt.get());
            }
        }

        return games;
    }

    // ############ SIMULATION METHOD ############

    public Optional<Game> simulateGame(Integer homeTeamId, Integer awayTeamId) {
        try {
            Optional<Team> homeTeamOpt = teamService.getModelById(homeTeamId);
            Optional<Team> awayTeamOpt = teamService.getModelById(awayTeamId);

            if (homeTeamOpt.isEmpty() || awayTeamOpt.isEmpty()) {
                System.out.println("One or both teams not found.");
                return Optional.empty();
            }

            Team homeTeam = homeTeamOpt.get();
            Team awayTeam = awayTeamOpt.get();

            List<Player> homePlayers = playerService.getAllModelsByTeamId(homeTeam.getTeam_id());
            List<Player> awayPlayers = playerService.getAllModelsByTeamId(awayTeam.getTeam_id());

            if (homePlayers.size() < 5 || awayPlayers.size() < 5) {
                System.out.println("Not enough players on one or both teams.");
                return Optional.empty();
            }

            int homeScore = 0;
            int awayScore = 0;
            List<BoxScore> allBoxScores = new ArrayList<>();

            for (Player player : homePlayers) {
                BoxScore bs = boxScoreService.generateBoxScore(player);
                homeScore += bs.getPoints();
                allBoxScores.add(bs);
            }

            for (Player player : awayPlayers) {
                BoxScore bs = boxScoreService.generateBoxScore(player);
                awayScore += bs.getPoints();
                allBoxScores.add(bs);
            }

            GameEntity gameEntity = new GameEntity();
            gameEntity.setHomeTeamId(homeTeam.getTeam_id());
            gameEntity.setAwayTeamId(awayTeam.getTeam_id());
            gameEntity.setHomeTeamScore(homeScore);
            gameEntity.setAwayTeamScore(awayScore);
            gameEntity.setOvertime(false);

            Integer gameId = createEntity(gameEntity);
            if (gameId == null) {
                System.out.println("Failed to create game in the database.");
                return Optional.empty();
            }
            gameEntity.setId(gameId);

            boxScoreService.createEntity(allBoxScores, gameId);

            return convertEntityToModel(gameEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
