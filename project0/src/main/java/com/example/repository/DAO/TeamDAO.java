package com.example.repository.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.repository.entities.TeamEntity;
import com.example.util.ConnectionHandler;

public class TeamDAO implements DAOInterface<TeamEntity> {

    private Connection connection = ConnectionHandler.getConnection();
    
    public Integer create(TeamEntity teamEntity) throws SQLException{
        String sql = "INSERT INTO teams (mascot, city) VALUES (?, ?) RETURNING id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, teamEntity.getMascot());
            stmt.setString(2, teamEntity.getCity());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }
    
    @Override
    public Optional<TeamEntity> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM teams WHERE id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {;
                if (rs.next()) {
                    TeamEntity teamEntity = new TeamEntity();
                    teamEntity.setTeam_id(rs.getInt("id"));
                    teamEntity.setCity(rs.getString("city"));
                    teamEntity.setMascot(rs.getString("mascot"));

                    return Optional.of(teamEntity);
                } 
            }
        }

        return Optional.empty();
    }

    public Optional<TeamEntity> findByMascot(String mascot) throws SQLException {
        String sql = "SELECT * FROM teams WHERE mascot = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mascot);

            try (ResultSet rs = stmt.executeQuery()) {;
                if (rs.next()) {
                    TeamEntity teamEntity = new TeamEntity();
                    teamEntity.setTeam_id(rs.getInt("id"));
                    teamEntity.setCity(rs.getString("city"));
                    teamEntity.setMascot(rs.getString("mascot"));

                    return Optional.of(teamEntity);
                } 
            }
        }

        return Optional.empty();
    }

    @Override
    public List<TeamEntity> findAll() throws SQLException {
        // TODO Auto-generated method stub
        List<TeamEntity> teams = new ArrayList<>();

        String sql = "SELECT * FROM teams;";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TeamEntity teamEntity = new TeamEntity();
                teamEntity.setTeam_id(rs.getInt("id"));
                teamEntity.setCity(rs.getString("city"));
                teamEntity.setMascot(rs.getString("mascot"));

                teams.add(teamEntity);
            }

        }
        return teams;
    }

    @Override
    public TeamEntity updateById(TeamEntity entity) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
