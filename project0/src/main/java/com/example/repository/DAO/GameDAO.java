package com.example.repository.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.entities.GameEntity;
import com.example.util.ConnectionHandler;

public class GameDAO implements DAOInterface<GameEntity>{

    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(GameEntity gameEntity) throws SQLException{
        String sql = "INSERT INTO games (home_team_id, away_team_id, home_team_score, away_team_score, overtime) VALUES (?, ?, ?, ?, ?) RETURNING id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, gameEntity.getHomeTeamId());
            stmt.setInt(2, gameEntity.getAwayTeamId());
            stmt.setInt(3, gameEntity.getHomeTeamScore());
            stmt.setInt(4, gameEntity.getAwayTeamScore());
            stmt.setBoolean(5, gameEntity.isOvertime());

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    @Override
    public Optional<GameEntity> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM games WHERE id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {;
                if (rs.next()) {
                    GameEntity gameEntity = new GameEntity();
                    gameEntity.setId(rs.getInt("id"));
                    gameEntity.setHomeTeamId(rs.getInt("home_team_id"));
                    gameEntity.setAwayTeamId(rs.getInt("away_team_id"));
                    gameEntity.setHomeTeamScore(rs.getInt("home_team_score"));
                    gameEntity.setAwayTeamScore(rs.getInt("away_team_score"));
                    gameEntity.setOvertime(rs.getBoolean("overtime"));

                    return Optional.of(gameEntity);
                } 
            }
        }

        return Optional.empty();
    }

    public List<GameEntity> findAllByTeamId(Integer id) throws SQLException{
        List<GameEntity> games = new ArrayList<>();

        String sql = "SELECT * FROM games WHERE home_team_id = ? OR away_team_id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, id);

            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()) {
                    GameEntity entity = new GameEntity();
                    entity.setId(rs.getInt("id"));
                    entity.setHomeTeamId(rs.getInt("home_team_id"));
                    entity.setAwayTeamId(rs.getInt("away_team_id"));
                    entity.setHomeTeamScore(rs.getInt("home_team_score"));
                    entity.setAwayTeamScore(rs.getInt("away_team_score"));
                    entity.setOvertime(rs.getBoolean("overtime"));

                    games.add(entity);
                }
            }
        }

        return games;
    }

    @Override
    public List<GameEntity> findAll() throws SQLException{
        // No implementation by design
        // User will only be allowed to view games per team or specific ID
        return null;
    }

    @Override
    public GameEntity updateById(GameEntity gameEntity) throws SQLException {
        // No implementation by design
        // User will only be allowed to create or view games
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        // No implementation by design
        // User will only be allowed to create or view games
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
