package com.example.repository.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.entities.BoxScoreEntity;
import com.example.util.ConnectionHandler;

public class BoxScoreDAO implements DAOInterface<BoxScoreEntity>{

    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(BoxScoreEntity entity) throws SQLException{
        String sql = "INSERT INTO boxscores (game_id, player_id, minutes_played, points, rebounds, assists, steals, blocks, turnovers, field_goals_made, field_goals_attempted, three_points_made, three_points_attempted, free_throws_made, free_throws_attempted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, entity.getGameId());
            stmt.setInt(2, entity.getPlayerId());
            stmt.setInt(3, entity.getMinutesPlayed());
            stmt.setInt(4, entity.getPoints());
            stmt.setInt(5, entity.getRebounds());
            stmt.setInt(6, entity.getAssists());
            stmt.setInt(7, entity.getSteals());
            stmt.setInt(8, entity.getBlocks());
            stmt.setInt(9, entity.getTurnovers());
            stmt.setInt(10, entity.getFgMade());
            stmt.setInt(11, entity.getFgAttempted());
            stmt.setInt(12, entity.getThreesMade());
            stmt.setInt(13, entity.getThreesAttempted());
            stmt.setInt(14, entity.getFtMade());
            stmt.setInt(15, entity.getFtAttempted());

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    public List<BoxScoreEntity> findLatestScoresById(Integer playerId) throws SQLException{
        List<BoxScoreEntity> boxScores = new ArrayList<>();

        String sql = "SELECT * from boxscores where player_id = ? LIMIT 10;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, playerId);

            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    BoxScoreEntity boxScoreEntity = new BoxScoreEntity();
                    boxScoreEntity.setGameId(rs.getInt("game_id"));
                    boxScoreEntity.setPlayerId(rs.getInt("player_id"));
                    boxScoreEntity.setMinutesPlayed(rs.getInt("minutes_played"));
                    boxScoreEntity.setPoints(rs.getInt("points"));
                    boxScoreEntity.setRebounds(rs.getInt("rebounds"));
                    boxScoreEntity.setAssists(rs.getInt("assists"));
                    boxScoreEntity.setSteals(rs.getInt("steals"));
                    boxScoreEntity.setBlocks(rs.getInt("blocks"));
                    boxScoreEntity.setTurnovers(rs.getInt("turnovers"));
                    boxScoreEntity.setFgMade(rs.getInt("field_goals_made"));
                    boxScoreEntity.setFgAttempted(rs.getInt("field_goals_attempted"));
                    boxScoreEntity.setThreesMade(rs.getInt("three_points_made"));
                    boxScoreEntity.setThreesAttempted(rs.getInt("three_points_attempted"));
                    boxScoreEntity.setFtMade(rs.getInt("free_throws_made"));
                    boxScoreEntity.setFtAttempted(rs.getInt("free_throws_attempted"));

                    boxScores.add(boxScoreEntity);
                }
            }
        }

        return boxScores;
    } 

    @Override
    public Optional<BoxScoreEntity> findById(Integer id) throws SQLException{
        // No implementation by design
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<BoxScoreEntity> findAll() throws SQLException{
        // No implementation by design
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public BoxScoreEntity updateById(BoxScoreEntity entity) throws SQLException{
        // No implementation by design
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }
    
    @Override
    public boolean deleteById(Integer id) throws SQLException{
        // No implementation by design
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
