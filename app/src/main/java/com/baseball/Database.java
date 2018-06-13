package com.baseball;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable{
    private String teamName = "";
    private List<String> teamMember = new ArrayList<>();
    private List<Record> record = new ArrayList<>();

    public List<Record> getAllRecord() {
        return record;
    }

    public void addRecord(Record record) {
        this.record.add(record);
    }

    public Record getRecord(int i) {
        return record.get(i);
    }

    public void setTeamName(String name){
        this.teamName = name;
        DatabaseService.getInstance().write();
    }

    public String getTeamName(){
        return this.teamName;
    }

    public List<String> addTeamMember(String name){
        this.teamMember.add(name);
        DatabaseService.getInstance().write();
        return getTeamMember();
    }

    public List<String> getTeamMember() {
        return this.teamMember;
    }
}

