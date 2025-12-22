package com.example.repository.entities;

import java.util.Objects;

public class PlayerEntity {
    private Integer id;
    private Integer teamId;
    private String firstName;
    private String lastName;
    private String position;
    private String country;
    private Integer jerseyNumber;

    public PlayerEntity() {
    }

    public PlayerEntity(Integer id, Integer teamId, String firstName, String lastName, String position, String country, Integer jerseyNumber) {
        this.id = id;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.country = country;
        this.jerseyNumber = jerseyNumber;
    }
    
    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", country='" + country + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                '}';
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (!id.equals(that.id)) return false;
        if (!teamId.equals(that.teamId)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!position.equals(that.position)) return false;
        if (!country.equals(that.country)) return false;
        return jerseyNumber.equals(that.jerseyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, firstName, lastName, position, country, jerseyNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }
    
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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