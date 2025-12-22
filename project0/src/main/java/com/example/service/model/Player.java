package com.example.service.model;

public class Player {
    private Integer id;
    private Team team;
    private String firstName;
    private String lastName;
    private String position;
    private String country;
    private Integer jerseyNumber;

    public Player() {
    }

    public Player(Integer id, Team team, String firstName, String lastName, String position, String country, Integer jerseyNumber) {
        this.id = id;
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.country = country;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String toString() {
        return String.format("[Player ID: %d] #%d %s %s | %s | %s", id, jerseyNumber, firstName, lastName, position, team != null ? team.getMascot() : "Free Agent");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        return id.equals(other.id) && team.equals(other.team) &&
               firstName.equals(other.firstName) &&
               lastName.equals(other.lastName) &&
               position.equals(other.position) &&
               country.equals(other.country) &&
               jerseyNumber.equals(other.jerseyNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, team, firstName, lastName, position, country, jerseyNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    } 

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
}
