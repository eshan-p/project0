package com.example.repository.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.repository.entities.TeamEntity;
import com.example.util.ConnectionHandler;

public class TeamDAO implements DAOInterface {

    private Connection connection = ConnectionHandler.getConnection();
    
    public Integer create(TeamEntity teamEntity) throws SQLException{
        String sql = "INSERT INTO teams (name, city) VALUES (?, ?) RETURNING id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teamEntity.getName());
            stmt.setString(2, teamEntity.getCity());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Creating team failed, no ID obtained.");
            }
        }
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
                    teamEntity.setName(rs.getString("name"));
                    teamEntity.setCity(rs.getString("city"));

                    return Optional.of(teamEntity);
                } 
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Object> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void updateById(Object entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Integer create(Object entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}
