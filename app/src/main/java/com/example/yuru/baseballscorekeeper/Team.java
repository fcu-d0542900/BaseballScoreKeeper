package com.example.yuru.baseballscorekeeper;

/**
 * Created by YURU on 2018/6/8.
 */

public class Team {

    private String teamName;

    public Team() {
        teamName="";
    }
    public Team(String name) {
        setTeamName(name);
    }


    public void setTeamName(String name) {
        this.teamName = name;
    }
    public String getTeamName() {return teamName;}

}
