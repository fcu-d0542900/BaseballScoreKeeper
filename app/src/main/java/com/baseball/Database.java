package com.baseball;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable{
    private String teamname = "";
    private List<String> teamMember = new ArrayList<>();

    public void setTeamname(String name){
        this.teamname = name;
    }

    public String getTeamname(){
        return this.teamname;
    }

    public List<String> addTeamMember(String name){
        this.teamMember.add(name);
        return getTeamMember();
    }

    public List<String> getTeamMember() {
        return this.teamMember;
    }
}

