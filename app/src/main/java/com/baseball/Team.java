package com.baseball;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/8.
 */

public class Team implements Serializable {
    private String teamName="";
    private List<String> teamMember = new ArrayList<>();

    public Team(){}

    public Team(String homeTeam) {
        setTeamName(homeTeam);
    }

    public void setTeamName(String name) {
        this.teamName = name;
    }

    public String getTeamName() {return teamName;}

    public List<String> addTeamMember(String name){
        this.teamMember.add(name);
        DatabaseService.getInstance().write();
        return getTeamMember();
    }

    public List<String> getTeamMember() {
        return this.teamMember;
    }
}
