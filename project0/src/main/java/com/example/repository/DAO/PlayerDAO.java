package com.example.repository.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.entities.PlayerEntity;
import com.example.util.ConnectionHandler;

public class PlayerDAO implements DAOInterface<PlayerEntity> {
    
    private Connection connection = ConnectionHandler.getConnection();

    @Override
    public Integer create(PlayerEntity playerEntity) throws SQLException{
        String sql = "INSERT INTO players (team_id, first_name, last_name, plyr_position, country, jersey_number) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, playerEntity.getTeamId());
            stmt.setString(2, playerEntity.getFirstName());
            stmt.setString(3, playerEntity.getLastName());
            stmt.setString(4, playerEntity.getPosition());
            stmt.setString(5, playerEntity.getCountry());
            stmt.setInt(6, playerEntity.getJerseyNumber());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } 
        return null;
    }

    @Override
    public Optional<PlayerEntity> findById(Integer id) throws SQLException {
       String sql = "SELECT * FROM players WHERE id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {;
                if (rs.next()) {
                    PlayerEntity playerEntity = new PlayerEntity();
                    playerEntity.setId(rs.getInt("id"));
                    playerEntity.setTeamId(rs.getInt("team_id"));
                    playerEntity.setFirstName(rs.getString("first_name"));
                    playerEntity.setLastName(rs.getString("last_name"));
                    playerEntity.setPosition(rs.getString("plyr_position"));
                    playerEntity.setCountry(rs.getString("country"));
                    playerEntity.setJerseyNumber(rs.getInt("jersey_number"));

                    return Optional.of(playerEntity);
                } 
            }
        }

        return Optional.empty();
    }

    public Optional<PlayerEntity> findbyName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT * FROM players WHERE first_name = ? AND last_name = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);

            try (ResultSet rs = stmt.executeQuery()) {;
                if (rs.next()) {
                    PlayerEntity playerEntity = new PlayerEntity();
                    playerEntity.setId(rs.getInt("id"));
                    playerEntity.setTeamId(rs.getInt("team_id"));
                    playerEntity.setFirstName(rs.getString("first_name"));
                    playerEntity.setLastName(rs.getString("last_name"));
                    playerEntity.setPosition(rs.getString("plyr_position"));
                    playerEntity.setCountry(rs.getString("country"));
                    playerEntity.setJerseyNumber(rs.getInt("jersey_number"));

                    return Optional.of(playerEntity);
                } 
            }
        }

        return Optional.empty();
    }

    public List<PlayerEntity> findAllByTeamId (Integer teamId) throws SQLException {
        List<PlayerEntity> players = new ArrayList<>();

        String sql = "SELECT * FROM players WHERE team_id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, teamId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PlayerEntity playerEntity = new PlayerEntity();
                    playerEntity.setId(rs.getInt("id"));
                    playerEntity.setTeamId(rs.getInt("team_id"));
                    playerEntity.setFirstName(rs.getString("first_name"));
                    playerEntity.setLastName(rs.getString("last_name"));
                    playerEntity.setPosition(rs.getString("plyr_position"));
                    playerEntity.setCountry(rs.getString("country"));
                    playerEntity.setJerseyNumber(rs.getInt("jersey_number"));

                    players.add(playerEntity);
                }
            }
        }

        return players;
    } 

    public List<PlayerEntity> findAllByTeamName (String mascot) throws SQLException {
        List<PlayerEntity> players = new ArrayList<>();

        String sql = "SELECT * FROM players p INNER JOIN teams t ON p.team_id = t.id WHERE t.mascot = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mascot);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PlayerEntity playerEntity = new PlayerEntity();
                    playerEntity.setId(rs.getInt("id"));
                    playerEntity.setTeamId(rs.getInt("team_id"));
                    playerEntity.setFirstName(rs.getString("first_name"));
                    playerEntity.setLastName(rs.getString("last_name"));
                    playerEntity.setPosition(rs.getString("plyr_position"));
                    playerEntity.setCountry(rs.getString("country"));
                    playerEntity.setJerseyNumber(rs.getInt("jersey_number"));

                    players.add(playerEntity);
                }
            }
        }

        return players;
    }

    @Override
    public List<PlayerEntity> findAll() throws SQLException {
        // No implementation by design
        // User will only be allowed to view players individually or by team
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PlayerEntity updateById(PlayerEntity entity) throws SQLException{
        String sql = "UPDATE players SET team_id = ?, jersey_number = ? WHERE id = ?;";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getTeamId());
            stmt.setInt(2, entity.getJerseyNumber());
            stmt.setInt(3, entity.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0){
                throw new SQLException("No player found with id " + entity.getId());
            }
        }
        return entity;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException{
        String sql = "DELETE FROM players WHERE id = ?;";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0){
                throw new SQLException("No player found with id + " + id);
            }
        }

        return true;
    }
}