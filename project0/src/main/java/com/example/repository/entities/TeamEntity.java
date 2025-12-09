package com.example.repository.entities;

public class TeamEntity {
    private Integer team_id;
    private String city;
    private String name;

    public TeamEntity() {
    }

    public TeamEntity(Integer team_id, String city, String name) {
        this.team_id = team_id;
        this.city = city;
        this.name = name;
    }

    /**
     * @return int return the team_id
     */
    public int getTeam_id() {
        return team_id;
    }

    /**
     * @param team_id the team_id to set
     */
    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        //return "TeamEntity [team_id=" + team_id + ", city=" + city + ", name=" + name + "]";
        return city + " " + name + " (ID: " + team_id + ")";
    }

    /*@Override
    public boolean equals(Object obj) {

    }*/
       
    /*@Override
    public int hashCode() {

    }*/
}
