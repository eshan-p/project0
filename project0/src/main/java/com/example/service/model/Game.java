package com.example.service.model;

public class Game {
    private Integer id;
    private Team homeTeam;
    private Team awayTeam;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Boolean overtime; 

    public Game(){
    }

    public Game(Integer id, Team home, Team away, Integer homeScore, Integer awayScore, Boolean overtime){
        this.id = id;
        this.homeTeam = home;
        this.awayTeam = away;
        this.homeTeamScore = homeScore;
        this.awayTeamScore = awayScore;
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + 
        ", homeTeam=" + homeTeam + 
        ", awayTeam=" + awayTeam + 
        ", homeTeamScore=" + homeTeamScore + 
        ", awayTeamScore=" + awayTeamScore + 
        ", overtime=" + overtime + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        return id.equals(other.id) && 
               homeTeam.equals(other.homeTeam) &&
               awayTeam.equals(other.awayTeam) &&
               homeTeamScore.equals(other.homeTeamScore) &&
               awayTeamScore.equals(other.awayTeamScore) &&
               overtime.equals(other.overtime);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, homeTeam, awayTeam, homeTeamScore, awayTeamScore, overtime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Boolean isOvertime() {
        return overtime;
    }

    public void setOvertime(Boolean overtime) {
        this.overtime = overtime;
    }
}
