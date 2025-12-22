package com.example.service.model;

import java.util.Objects;

public class BoxScore {
    private Integer id;
    private Game game;
    private Player player;

    private Integer minutesPlayed;
    private Integer points;
    private Integer rebounds;
    private Integer assists;
    private Integer steals;
    private Integer blocks;
    private Integer turnovers;
    private Integer fgMade;
    private Integer fgAttemped;
    private Integer threesMade;
    private Integer threesAttempted;
    private Integer ftMade;
    private Integer ftAttempted;

    public BoxScore(){
    }

    public BoxScore(Integer id, Game game, Player player, Integer minutesPlayed, Integer points, Integer rebounds, Integer assists, Integer steals, Integer blocks, Integer turnovers, Integer fgMade, Integer fgAttemped, Integer threesMade, Integer threesAttempted, Integer ftMade, Integer ftAttempted){
        this.id = id;
        this.game = game;
        this.player = player;
        this.minutesPlayed = minutesPlayed;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fgMade = fgMade;
        this.fgAttemped = fgAttemped;
        this.threesMade = threesMade;
        this.ftMade = ftMade;
        this.ftAttempted = ftAttempted;
    }

    @Override
    public String toString(){
        return String.format("%s | MIN %d | PTS %d | REB %d | AST %d | STL %d | BLK %d | TO %d | FG %d/%d | 3PT %d/%d | FT %d/%d",
            player, minutesPlayed, points, rebounds, assists, steals, blocks, turnovers, fgMade, fgAttemped, threesMade, threesAttempted, ftMade, ftAttempted);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxScore that = (BoxScore) o;

        if (!id.equals(that.id)) return false;
        if (!game.equals(that.game)) return false;
        if (!player.equals(that.player)) return false;
        if (!minutesPlayed.equals(that.minutesPlayed)) return false;
        if (!points.equals(that.points)) return false;
        if (!rebounds.equals(that.rebounds)) return false;
        if (!assists.equals(that.assists)) return false;
        if (!steals.equals(that.steals)) return false;
        if (!blocks.equals(that.blocks)) return false;
        if (!turnovers.equals(that.turnovers)) return false;
        if (!fgMade.equals(that.fgMade)) return false;
        if (!fgAttemped.equals(that.fgAttemped)) return false;
        if (!threesMade.equals(that.threesMade)) return false;
        if (!threesAttempted.equals(that.threesAttempted)) return false;
        if (!ftMade.equals(that.ftMade)) return false;
        return ftAttempted.equals(that.ftAttempted);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, game, player, minutesPlayed, points, rebounds, assists, steals, blocks, turnovers, fgMade, fgAttemped, threesMade, threesAttempted, ftMade, ftAttempted);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        return fgAttemped;
    }

    public void setFgAttempted(Integer fgAttemped) {
        this.fgAttemped = fgAttemped;
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
