package com.example.service;

import java.util.Random;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.DAO.BoxScoreDAO;
import com.example.repository.entities.BoxScoreEntity;
import com.example.service.model.BoxScore;
import com.example.service.model.Game;
import com.example.service.model.Player;

public class BoxScoreService {

    private final BoxScoreDAO boxScoreDAO = new BoxScoreDAO();
    private final PlayerService playerService = new PlayerService();
    private final Random random = new Random();

    public BoxScore generateBoxScore(Player player) {
        BoxScore boxScore = new BoxScore();
        boxScore.setPlayer(player);

        int minutes = randomBetween(20, 48);
        int fgAtt = randomBetween(minutes / 2, minutes);
        int fgMade = (int) (fgAtt * randomDouble(0.35, 0.60));

        int threeAtt = randomBetween(0, fgAtt / 2);
        int threeMade = randomBetween(0, threeAtt);

        int ftAtt = randomBetween(0, 8);
        int ftMade = randomBetween(0, ftAtt);

        int points = (fgMade - threeMade) * 2 + threeMade * 3 + ftMade;

        boxScore.setMinutesPlayed(minutes);
        boxScore.setFgAttempted(fgAtt);
        boxScore.setFgMade(fgMade);
        boxScore.setThreesAttempted(threeAtt);
        boxScore.setThreesMade(threeMade);
        boxScore.setFtAttempted(ftAtt);
        boxScore.setFtMade(ftMade);

        boxScore.setPoints(points);
        boxScore.setRebounds(randomBetween(0, 12));
        boxScore.setAssists(randomBetween(0, 10));
        boxScore.setSteals(randomBetween(0, 4));
        boxScore.setBlocks(randomBetween(0, 4));
        boxScore.setTurnovers(randomBetween(0, 6));

        return boxScore;
    }

    private int randomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public void createEntity(List<BoxScore> boxScores, int gameId) {
        for (BoxScore model : boxScores) {
            BoxScoreEntity entity = new BoxScoreEntity();
            entity.setGameId(gameId);
            entity.setPlayerId(model.getPlayer().getId());
            entity.setMinutesPlayed(model.getMinutesPlayed());
            entity.setPoints(model.getPoints());
            entity.setRebounds(model.getRebounds());
            entity.setAssists(model.getAssists());
            entity.setSteals(model.getSteals());
            entity.setBlocks(model.getBlocks());
            entity.setTurnovers(model.getTurnovers());
            entity.setFgMade(model.getFgMade());
            entity.setFgAttempted(model.getFgAttempted());
            entity.setThreesMade(model.getThreesMade());
            entity.setThreesAttempted(model.getThreesAttempted());
            entity.setFtMade(model.getFtMade());
            entity.setFtAttempted(model.getFtAttempted());

            try {
                boxScoreDAO.create(entity);
            } catch (Exception e) {
                throw new RuntimeException("Failed to save box score", e);
            }
        }
    }

    // ############ ENTITY METHODS ############

    public List<BoxScoreEntity> getAllEntitiesByName(Integer id){
        try {
            List<BoxScoreEntity> boxScoreEntities = boxScoreDAO.findLatestScoresByName(id);
            return boxScoreEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ############ CONVERSION METHODS ############

    public Optional<BoxScore> convertEntityToModel(BoxScoreEntity entity, Game game) {
        try {
            Optional<Player> player = playerService.getModelById(entity.getPlayerId());

            if (player.isEmpty()) {
                throw new RuntimeException("Invalid player id");
            }

            BoxScore boxScore = new BoxScore();
            boxScore.setId(entity.getId());
            boxScore.setGame(game);
            boxScore.setPlayer(player.get());
            boxScore.setMinutesPlayed(entity.getMinutesPlayed());
            boxScore.setPoints(entity.getPoints());
            boxScore.setRebounds(entity.getRebounds());
            boxScore.setAssists(entity.getAssists());
            boxScore.setSteals(entity.getSteals());
            boxScore.setBlocks(entity.getBlocks());
            boxScore.setTurnovers(entity.getTurnovers());
            boxScore.setFgMade(entity.getFgMade());
            boxScore.setFgAttempted(entity.getFgAttempted());
            boxScore.setThreesMade(entity.getThreesMade());
            boxScore.setThreesAttempted(entity.getThreesAttempted());
            boxScore.setFtMade(entity.getFtMade());
            boxScore.setFtAttempted(entity.getFtAttempted());

            return Optional.of(boxScore);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    // ############ MODEL METHODS ############

    public List<BoxScore> getAllModelsByName(Integer id){
        List<BoxScoreEntity> boxScoreEntities = getAllEntitiesByName(id);
        List<BoxScore> boxScores = new ArrayList<>();

        for (BoxScoreEntity entity : boxScoreEntities) {
            BoxScore boxScore = new BoxScore();
            boxScore.setId(entity.getId());
            boxScore.setMinutesPlayed(entity.getMinutesPlayed());
            boxScore.setPoints(entity.getPoints());
            boxScore.setRebounds(entity.getRebounds());
            boxScore.setAssists(entity.getAssists());
            boxScore.setSteals(entity.getSteals());
            boxScore.setBlocks(entity.getBlocks());
            boxScore.setTurnovers(entity.getTurnovers());
            boxScore.setFgMade(entity.getFgMade());
            boxScore.setFgAttempted(entity.getFgAttempted());
            boxScore.setThreesMade(entity.getThreesMade());
            boxScore.setThreesAttempted(entity.getThreesAttempted());
            boxScore.setFtMade(entity.getFtMade());
            boxScore.setFtAttempted(entity.getFtAttempted());

            playerService.getModelById(entity.getPlayerId())
                    .ifPresent(boxScore::setPlayer);

            boxScores.add(boxScore);
        }
        return boxScores;
    }

}
