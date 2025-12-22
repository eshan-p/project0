package com.example.repository.entities;

import java.util.Objects;

public class TeamEntity {
    private Integer team_id;
    private String city;
    private String mascot;

    public TeamEntity() {
    }

    public TeamEntity(Integer team_id, String city, String mascot) {
        this.team_id = team_id;
        this.city = city;
        this.mascot = mascot;
    }

    @Override
    public String toString() {
        return city + " " + mascot + " (ID: " + team_id + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TeamEntity other = (TeamEntity) obj;
        return team_id.equals(other.team_id) && city.equals(other.city) && mascot.equals(other.mascot);
    }
       
    @Override
    public int hashCode() {
        return Objects.hash(team_id, city, mascot);
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }
}
