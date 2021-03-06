package com.example.yuru.baseballscorekeeper.Modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private String teamName = "";
    private List<Player> teamMember = new ArrayList<>();
    private List<Record> record = new ArrayList<>();

    public List<Record> getAllRecord() {
        return record;
    }

    public int addRecord(Record record) {
        this.record.add(record);
        DatabaseService.getInstance().write();
        return this.record.indexOf(record);
    }

    public Record getRecord(int i) {
        return record.get(i);
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String name) {
        this.teamName = name;
        DatabaseService.getInstance().write();
    }

    public int addTeamMember(Player name) {
        this.teamMember.add(name);
        DatabaseService.getInstance().write();
        return this.teamMember.indexOf(name);
    }

    public List<Player> getTeamMember() {
        return this.teamMember;
    }
}

