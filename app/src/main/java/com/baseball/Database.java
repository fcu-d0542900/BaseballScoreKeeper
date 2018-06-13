package com.baseball;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable{
    private String teamName = "";
    private Team team = new Team();
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

}

