package com.example.repository.entities;

import java.util.Objects;

public class BoxScoreEntity {
    private Integer id;
    private Integer gameId;
    private Integer playerId;

    private Integer minutesPlayed;
    private Integer points;
    private Integer rebounds;
    private Integer assists;
    private Integer steals;
    private Integer blocks;
    private Integer turnovers;
    private Integer fgMade;
    private Integer fgAttempted;
    private Integer threesMade;
    private Integer threesAttempted;
    private Integer ftMade;
    private Integer ftAttempted;

    public BoxScoreEntity(){
    }

    public BoxScoreEntity(Integer id, Integer gameId, Integer playerId, Integer minutesPlayed, Integer points, Integer rebounds, Integer assists, Integer steals, Integer blocks, Integer turnovers, Integer fgMade, Integer fgAttempted, Integer threesMade, Integer threesAttempted, Integer ftMade, Integer ftAttempted){
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.minutesPlayed = minutesPlayed;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fgMade = fgMade;
        this.fgAttempted = fgAttempted;
        this.threesMade = threesMade;
        this.ftMade = ftMade;
        this.ftAttempted = ftAttempted;
    }

    @Override
    public String toString(){
        return "BoxScoreEntity{" +
               "id=" + id +
               ", gameId=" + gameId +
               ", playerId='" + playerId + '\'' +
               ", minutesPlayed='" + minutesPlayed + '\'' +
               ", points='" + points + '\'' +
               ", rebounds='" + rebounds + '\'' +
               ", assists='" + assists + '\'' +
               ", steals='" + steals + '\'' +
               ", blocks='" + blocks + '\'' +
               ", turnovers='" + turnovers + '\'' +
               ", fgMade='" + fgMade + '\'' +
               ", fgAttempted='" + fgAttempted + '\'' +
               ", threesMade='" + threesMade + '\'' +
               ", threesAttempted='" + threesAttempted + '\'' +
               ", ftMade='" + ftMade + '\'' +
               ", ftAttempted='" + ftAttempted + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxScoreEntity that = (BoxScoreEntity) o;

        if (!id.equals(that.id)) return false;
        if (!gameId.equals(that.gameId)) return false;
        if (!playerId.equals(that.playerId)) return false;
        if (!minutesPlayed.equals(that.minutesPlayed)) return false;
        if (!points.equals(that.points)) return false;
        if (!rebounds.equals(that.rebounds)) return false;
        if (!assists.equals(that.assists)) return false;
        if (!steals.equals(that.steals)) return false;
        if (!blocks.equals(that.blocks)) return false;
        if (!turnovers.equals(that.turnovers)) return false;
        if (!fgMade.equals(that.fgMade)) return false;
        if (!fgAttempted.equals(that.fgAttempted)) return false;
        if (!threesMade.equals(that.threesMade)) return false;
        if (!threesAttempted.equals(that.threesAttempted)) return false;
        if (!ftMade.equals(that.ftMade)) return false;
        return ftAttempted.equals(that.ftAttempted);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, gameId, playerId, minutesPlayed, points, rebounds, assists, steals, blocks, turnovers, fgMade, fgAttempted, threesMade, threesAttempted, ftMade, ftAttempted);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRebounds() {
        return rebounds;
    }

    public void setRebounds(Integer rebounds) {
        this.rebounds = rebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getSteals() {
        return steals;
    }

    public void setSteals(Integer steals) {
        this.steals = steals;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Integer turnovers) {
        this.turnovers = turnovers;
    }

    public Integer getFgMade() {
        return fgMade;
    }

    public void setFgMade(Integer fgMade) {
        this.fgMade = fgMade;
    }

    public Integer getFgAttempted() {
        return fgAttempted;
    }

    public void setFgAttempted(Integer fgAttempted) {
        this.fgAttempted = fgAttempted;
    }

    public Integer getThreesMade() {
        return threesMade;
    }

    public void setThreesMade(Integer threesMade) {
        this.threesMade = threesMade;
    }

    public Integer getThreesAttempted() {
        return threesAttempted;
    }

    public void setThreesAttempted(Integer threesAttempted) {
        this.threesAttempted = threesAttempted;
    }

    public Integer getFtMade() {
        return ftMade;
    }

    public void setFtMade(Integer ftMade) {
        this.ftMade = ftMade;
    }

    public Integer getFtAttempted() {
        return ftAttempted;
    }

    public void setFtAttempted(Integer ftAttempted) {
        this.ftAttempted = ftAttempted;
    }
}
