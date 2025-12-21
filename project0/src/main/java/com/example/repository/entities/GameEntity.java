package com.example.repository.entities;

import java.util.Objects;

public class GameEntity {
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Boolean overtime; 

    public GameEntity(){
    }

    public GameEntity(Integer id, Integer homeId, Integer awayId, Integer homeScore, Integer awayScore, Boolean overtime){
        this.id = id;
        this.homeTeamId = homeId;
        this.awayTeamId = awayId;
        this.homeTeamScore = homeScore;
        this.awayTeamScore = awayScore;
        this.overtime = overtime;
    }

    @Override
    public String toString() { 
        return "GameEntity{" +
                "id=" + id +
                ", homeTeamId=" + homeTeamId +
                ", awayTeamId='" + awayTeamId + '\'' +
                ", homeTeamScore='" + homeTeamScore + '\'' +
                ", awayTeamScore='" + awayTeamScore + '\'' +
                ", overtime=" + overtime +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        if (!id.equals(that.id)) return false;
        if (!homeTeamId.equals(that.homeTeamId)) return false;
        if (!awayTeamId.equals(that.awayTeamId)) return false;
        if (!homeTeamScore.equals(that.homeTeamScore)) return false;
        if (!awayTeamScore.equals(that.awayTeamScore)) return false;
        return overtime.equals(that.overtime);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, homeTeamId, awayTeamId, homeTeamScore, awayTeamScore, overtime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
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
