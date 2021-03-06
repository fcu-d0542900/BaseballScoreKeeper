package com.example.yuru.baseballscorekeeper.Modal;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by YURU on 2018/6/13.
 */

public class Record implements Serializable {
    private String name;  //比賽名稱
    private Date date;  //比賽日期
    private RecordTeam away;  //客隊名稱
    private RecordTeam home;  //主隊名稱
    private RecordTeam.Faction currenFaction;

    public Record(String name, String awayTeam, String homeTeam, Date date) {
        this.name = name;
        this.date = date;
        this.away = new RecordTeam(awayTeam);
        this.home = new RecordTeam(homeTeam);
        this.currenFaction = RecordTeam.Faction.away;
    }

    public String getGameName() {
        return name;
    }

    public void setGameName(String name) {
        this.name = name;
        DatabaseService.getInstance().write();
    }

    public Date getGameDate() {
        return date;
    }

    public void setGameDate(Date date) {
        this.date = date;
        DatabaseService.getInstance().write();

    }

    public RecordTeam getTeam() {
        switch (currenFaction) {
            case home:
                return getHomeTeam();
            case away:
                return getAwayTeam();
            default:
                return null;
        }
    }

    public void setCurrenFaction(RecordTeam.Faction currentFaction) {
        this.currenFaction = currentFaction;
    }

    public RecordTeam getAwayTeam() {
        return away;
    }


    public RecordTeam getHomeTeam() {
        return home;
    }
}
