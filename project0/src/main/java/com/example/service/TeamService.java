package com.example.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.repository.DAO.TeamDAO;
import com.example.repository.entities.TeamEntity;
import com.example.service.interfaces.ServiceInterface;
import com.example.service.model.Team;

public class TeamService implements ServiceInterface<TeamEntity, Team> {
    private TeamDAO teamDAO = new TeamDAO();

    @Override
    public Integer createEntity(TeamEntity entity) {
        try {
            Integer id = teamDAO.create(entity);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public TeamEntity updateEntity(Integer id, TeamEntity newEntity) {
        try {
            Optional<TeamEntity> teamEntity = teamDAO.findById(id);

            if (teamEntity.isEmpty()){
                throw new RuntimeException("Team not found");
            }
            return teamDAO.updateById(newEntity);
            
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEntity(Integer id) {   
        try {
            Optional<TeamEntity> teamEntity = teamDAO.findById(id);

            if (teamEntity.isEmpty()){
                throw new RuntimeException("Team not found");
            }
            return teamDAO.deleteById(id);
            
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ############ ENTITY METHODS ############
    
    @Override
    public Optional<TeamEntity> getEntityById(Integer id) {
        try {
            Optional<TeamEntity> teamEntity = teamDAO.findById(id);
            
            if (teamEntity.isEmpty()) {
                throw new RuntimeException("Team not found");
            }
            return teamEntity;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<TeamEntity> getEntityByTeamMascot(String mascot) {
        try {
            Optional<TeamEntity> teamEntity = teamDAO.findByMascot(mascot);

            if (teamEntity.isEmpty()) {
                throw new RuntimeException("Team not found");
            }
            return teamEntity;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<TeamEntity> getAllEntities() {
        try {
            List<TeamEntity> teamEntities = teamDAO.findAll();
            return teamEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    // ############ CONVERSION METHODS ############

    @Override
    public Optional<Team> convertEntityToModel(TeamEntity entity) {
        Team team = new Team();
        team.setTeam_id(entity.getTeam_id());
        team.setMascot(entity.getMascot());
        team.setCity(entity.getCity());

        return Optional.of(team);
    }


    // ############ MODEL METHODS ############

    @Override
    public Optional<Team> getModelById(Integer id) {
        Optional<TeamEntity> teamEntity = getEntityById(id);

        try{
            if (teamEntity.isPresent()){
                Optional<Team> teamModel = convertEntityToModel(teamEntity.get());
                if(teamModel.isPresent()){
                    return teamModel;
                } else {
                    throw new RuntimeException("Conversion to model failed");
                }
            } else {
                throw new RuntimeException("TeamEntity not found");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Team> getModelByTeamMascot(String mascot) {
        Optional<TeamEntity> teamEntity = getEntityByTeamMascot(mascot);
        try{
            if (teamEntity.isPresent()){
                Optional<Team> teamModel = convertEntityToModel(teamEntity.get());
                if(teamModel.isPresent()){
                    return teamModel;
                } else {
                    throw new RuntimeException("Conversion to model failed");
                }
            } else {
                throw new RuntimeException("TeamEntity not found");
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Team> getAllModels() {
        List<TeamEntity> teamEntities = getAllEntities();
        List<Team> teams = new ArrayList<>();
        for (TeamEntity entity : teamEntities) {
            Optional<Team> teamOpt = convertEntityToModel(entity);
            if (teamOpt.isPresent()) {
                teams.add(teamOpt.get());
            }
        }
        return teams;
    }
}
