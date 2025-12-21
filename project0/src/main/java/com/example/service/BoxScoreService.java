package com.example.service;

import java.util.Random;
import java.util.List;

import com.example.repository.DAO.BoxScoreDAO;
import com.example.repository.entities.BoxScoreEntity;
import com.example.service.model.BoxScore;
import com.example.service.model.Player;

public class BoxScoreService {

    private final BoxScoreDAO boxScoreDAO = new BoxScoreDAO();
    private final Random random = new Random();

    public BoxScore generateBoxScore(Player player) {
        BoxScore bs = new BoxScore();
        bs.setPlayer(player);

        int minutes = randomBetween(20, 38);
        int fgAtt = randomBetween(minutes / 2, minutes);
        int fgMade = (int) (fgAtt * randomDouble(0.35, 0.60));

        int threeAtt = randomBetween(0, fgAtt / 2);
        int threeMade = randomBetween(0, threeAtt);

        int ftAtt = randomBetween(0, 8);
        int ftMade = randomBetween(0, ftAtt);

        int points =
                (fgMade - threeMade) * 2
              + threeMade * 3
              + ftMade;

        bs.setMinutesPlayed(minutes);
        bs.setFgAttempted(fgAtt);
        bs.setFgMade(fgMade);
        bs.setThreesAttempted(threeAtt);
        bs.setThreesMade(threeMade);
        bs.setFtAttempted(ftAtt);
        bs.setFtMade(ftMade);

        bs.setPoints(points);
        bs.setRebounds(randomBetween(0, 12));
        bs.setAssists(randomBetween(0, 10));
        bs.setSteals(randomBetween(0, 4));
        bs.setBlocks(randomBetween(0, 4));
        bs.setTurnovers(randomBetween(0, 6));

        return bs;
    }

    private int randomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public void saveBoxScores(List<BoxScore> boxScores, int gameId) {
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
}
