package com.example.service.model;

import java.util.Objects;

public class Team {
    private Integer team_id;
    private String mascot;
    private String city;

    public Team() {
    }

    public Team(Integer team_id, String mascot, String city) {
        this.team_id = team_id;
        this.mascot = mascot;
        this.city = city;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        Team other = (Team) obj;
        return team_id.equals(other.team_id) && mascot.equals(other.mascot) && city.equals(other.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team_id, mascot, city);
    }
}

