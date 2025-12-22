package com.example.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.repository.DAO.PlayerDAO;
import com.example.repository.entities.PlayerEntity;
import com.example.service.interfaces.ServiceInterface;
import com.example.service.model.Player;
import com.example.service.model.Team;


public class PlayerService implements ServiceInterface<PlayerEntity, Player>{
    private PlayerDAO playerDAO = new PlayerDAO();
    private TeamService teamService = new TeamService();
    
    @Override
    public Integer createEntity(PlayerEntity entity){
        try {
            Integer id = playerDAO.create(entity);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public PlayerEntity updateEntity(Integer id, PlayerEntity newEntity) {
        try {
            Optional<PlayerEntity> playerEntity = playerDAO.findById(id);

            if (playerEntity.isEmpty()){
                throw new RuntimeException("Player not found");
            }
            return playerDAO.updateById(newEntity);
            
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEntity(Integer id) {   
        try {
            Optional<PlayerEntity> playerEntity = playerDAO.findById(id);

            if (playerEntity.isEmpty()){
                throw new RuntimeException("Player not found");
            }
            return playerDAO.deleteById(id);
            
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ############ ENTITY METHODS ############
    
    @Override
    public Optional<PlayerEntity> getEntityById(Integer id){
        try {
            Optional<PlayerEntity> playerEntity = playerDAO.findById(id);

            if (playerEntity.isEmpty()) {
                throw new RuntimeException("Player not found");
            }

            return playerEntity;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<PlayerEntity> getEntityByName(String firstName, String lastName){
        try {
            Optional<PlayerEntity> playerEntity = playerDAO.findbyName(firstName, lastName);

            if (playerEntity.isEmpty()) {
                throw new RuntimeException("Player not found");
            }

            return playerEntity;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<PlayerEntity> getAllEntitiesByTeamId (Integer id){
        try {
            List<PlayerEntity> playerEntities = playerDAO.findAllByTeamId(id);
            return playerEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PlayerEntity> getAllEntitiesByTeamName (String mascot){
        try {
            List<PlayerEntity> playerEntities = playerDAO.findAllByTeamName(mascot);
            return playerEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PlayerEntity> getAllEntities() {
        try {
            List<PlayerEntity> playerEntities = playerDAO.findAll();
            return playerEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // ############ CONVERSION METHODS ############

    @Override
    public Optional<Player> convertEntityToModel(PlayerEntity entity) {
        try{
            Optional<Team> team = teamService.getModelById(entity.getTeamId());

            if (team.isEmpty()){
                throw new RuntimeException("Invalid team id");
            }

            Player player = new Player();
            player.setId(entity.getId());
            player.setTeam(team.get());
            player.setFirstName(entity.getFirstName());
            player.setLastName(entity.getLastName());
            player.setPosition(entity.getPosition());
            player.setJerseyNumber(entity.getJerseyNumber());
            player.setCountry(entity.getCountry());

            return Optional.of(player);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // ############ MODEL METHODS ############
    
    @Override
    public Optional<Player> getModelById(Integer id) {
        Optional<PlayerEntity> playerEntity = getEntityById(id);

        try{
            if (playerEntity.isPresent()){
                Optional<Player> playerModel = convertEntityToModel(playerEntity.get());

                if (playerModel.isPresent()){
                    return playerModel;
                } else {
                    throw new RuntimeException("Conversion to model failed.");
                }
            } else {
                throw new RuntimeException("PlayerEntity not found");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Player> getModelByName(String firstName, String lastName) {
        Optional<PlayerEntity> playerEntity = getEntityByName(firstName, lastName);

        try{
            if (playerEntity.isPresent()){
                Optional<Player> playerModel = convertEntityToModel(playerEntity.get());

                if (playerModel.isPresent()){
                    return playerModel;
                } else {
                    throw new RuntimeException("Conversion to model failed.");
                }
            } else {
                throw new RuntimeException("PlayerEntity not found");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Player> getAllModelsByTeamId(Integer teamId){
        List<PlayerEntity> playerEntities = getAllEntitiesByTeamId(teamId);
        List<Player> players = new ArrayList<>();
        for (PlayerEntity entity : playerEntities) {
            Optional<Player> playerOpt = convertEntityToModel(entity);
            if (playerOpt.isPresent()) {
                players.add(playerOpt.get());
            }
        }
        return players;
    }

     public List<Player> getAllModelsByTeamName(String mascot){
        List<PlayerEntity> playerEntities = getAllEntitiesByTeamName(mascot);
        List<Player> players = new ArrayList<>();
        for (PlayerEntity entity : playerEntities) {
            Optional<Player> playerOpt = convertEntityToModel(entity);
            if (playerOpt.isPresent()) {
                players.add(playerOpt.get());
            }
        }
        return players;
    }

    public List<Player> getAllModels(){
        List<PlayerEntity> playerEntities = getAllEntities();
        List<Player> players = new ArrayList<>();
        for (PlayerEntity entity : playerEntities) {
            Optional<Player> playerOpt = convertEntityToModel(entity);
            if (playerOpt.isPresent()) {
                players.add(playerOpt.get());
            }
        }
        return players;
    }
}


